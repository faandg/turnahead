package com.programmeren4.turnahead.client.ui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

public class Home implements EntryPoint {
	private HomeView application = new HomeView();

	@Override
	public void onModuleLoad() {
		RootPanel.get().add(application);
		History.newItem("home");
		
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (event.getValue().contains("login")) {
					System.out.println("HystMgmt: login is selected");
					new LoginController();
				} else if (event.getValue().contains("register")) {
					System.out.println("HystMgmt: register is selected");
					new RegistrationController();
				}
			}
		});
	}

}
