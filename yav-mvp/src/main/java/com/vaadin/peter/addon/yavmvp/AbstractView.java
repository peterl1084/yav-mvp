package com.vaadin.peter.addon.yavmvp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;

import com.vaadin.ui.CustomComponent;

public abstract class AbstractView<PRESENTER extends IsPresenter<?>> extends CustomComponent
		implements IsView<PRESENTER>, ApplicationContextAware {

	private ApplicationContext applicationContext;
	private PRESENTER presenter;

	@PostConstruct
	protected void initialize() {
		this.presenter = initializePresenter();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	protected PRESENTER getPresenter() {
		return presenter;
	}

	@SuppressWarnings("unchecked")
	protected PRESENTER initializePresenter() {
		ResolvableType presenterType = ResolvableType.forClassWithGenerics(IsPresenter.class,
				findInterfaceType().orElse(getClass()));
		List<String> presenterNames = Arrays.asList(applicationContext.getBeanNamesForType(presenterType));
		if (presenterNames.isEmpty()) {
			throw new RuntimeException(
					"No presenter found for view " + this.getClass().getSimpleName() + ", please implement "
							+ IsPresenter.class.getCanonicalName() + "<" + this.getClass().getSimpleName() + ">");
		}
		if (presenterNames.size() > 1) {
			throw new RuntimeException(
					"There are more than one presenter type available for view " + this.getClass().getCanonicalName()
							+ ": " + presenterNames.stream().map(beanName -> applicationContext.getType(beanName))
									.map(type -> type.getSimpleName()).collect(Collectors.joining(",")));
		}

		String presenterBeanName = presenterNames.stream().findFirst().get();
		return (PRESENTER) applicationContext.getBean(presenterBeanName, IsPresenter.class);
	}

	protected Optional<Class<? extends IsView>> findInterfaceType() {
		Class<? extends IsView> type = (Class<? extends IsView>) Arrays.asList(getClass().getInterfaces()).stream()
				.filter(interfaceType -> IsView.class.isAssignableFrom(interfaceType)).findFirst().orElse(null);
		return Optional.ofNullable(type);
	}
}
