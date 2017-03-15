package com.vaadin.peter.addon.yavmvp.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI
public class TestUI extends UI {

	@Autowired
	private TestViewDisplay viewDisplay;

	@Override
	protected void init(VaadinRequest request) {
		setSizeFull();

		setContent(viewDisplay);
	}
}
