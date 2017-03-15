package com.vaadin.peter.addon.yavmvp.test;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;

@SpringViewDisplay
public class TestViewDisplay extends Panel implements ViewDisplay {

	public TestViewDisplay() {
		setSizeFull();
	}

	@Override
	public void showView(View view) {
		setContent((Component) view);
	}
}
