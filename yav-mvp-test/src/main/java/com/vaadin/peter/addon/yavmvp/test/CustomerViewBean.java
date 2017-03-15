package com.vaadin.peter.addon.yavmvp.test;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.peter.addon.yavmvp.AbstractView;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = "")
public class CustomerViewBean extends AbstractView<CustomerPresenter> implements CustomerView, View {

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
