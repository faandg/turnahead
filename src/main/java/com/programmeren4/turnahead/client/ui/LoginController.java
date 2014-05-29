package com.programmeren4.turnahead.client.ui;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

public class LoginController {

	private LoginView login = new LoginView();

	public LoginController() {
		onLoad();
	}

	public void onLoad() {
		RootPanel.get().clear();
		RootPanel.get().add(login);
		
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (event.getValue().contains("overview")) {
					System.out.println("HystMgmt: overview is selected");
					new Overview();
				}
			}
		});
	}

}
