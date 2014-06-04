package com.programmeren4.turnahead.client.ui;

import com.google.gwt.user.client.ui.RootPanel;

public class MakeCharController {
	
	private MakeCharView makeCharForm = new MakeCharView(LoginView.IngelogdID);
		
		public MakeCharController() {
			super();
			onLoad();
		}

		public void onLoad() {
			RootPanel.get().clear();
			RootPanel.get().add(makeCharForm);
		}
	

}
