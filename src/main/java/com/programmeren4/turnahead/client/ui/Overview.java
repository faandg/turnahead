package com.programmeren4.turnahead.client.ui;

import com.google.gwt.user.client.ui.RootPanel;
import com.programmeren4.turnahead.server.LoginServiceImpl;

public class Overview {

	private FormOverview overview = new FormOverview();

	public Overview() {
		onLoad();
	}

	public void onLoad() {
		RootPanel.get().clear();
		RootPanel.get().add(overview);
		// System.out.println("Overview => UserID is ");
		// System.out.println(LoginView.IngelogdID.toString());
	}

}
