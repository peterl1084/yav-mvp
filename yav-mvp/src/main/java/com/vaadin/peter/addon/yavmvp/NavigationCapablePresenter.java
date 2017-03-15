package com.vaadin.peter.addon.yavmvp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

import com.vaadin.spring.annotation.ViewScope;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
@ViewScope
@Inherited
public @interface NavigationCapablePresenter {

}
