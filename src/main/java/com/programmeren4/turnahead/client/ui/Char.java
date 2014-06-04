package com.programmeren4.turnahead.client.ui;

import com.google.gwt.user.client.ui.RootPanel;

public class Char {

	private MakeCharView charForm = new MakeCharView();

	public Char() {
		super();
		onLoad();
	}

	public void onLoad() {
		RootPanel.get().add(charForm);
	}

}
