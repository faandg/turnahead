package com.programmeren4.turnahead.client.ui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.programmeren4.turnahead.client.services.UserDataService;
import com.programmeren4.turnahead.client.services.UserDataServiceAsync;
import com.programmeren4.turnahead.shared.dto.UserDataDTO;

public class HomeView extends Composite {

	private static Form1UiBinder uiBinder = GWT.create(Form1UiBinder.class);
	UserDataServiceAsync userDataAsync;

	interface Form1UiBinder extends UiBinder<Widget, HomeView> {
	}

	public HomeView() {
		initWidget(uiBinder.createAndBindUi(this));
		userDataAsync = GWT.create(UserDataService.class);
	}

	@UiField
	Button buttonAanmelden;
	@UiField
	Button buttonRegisteren;
	@UiField
	Button buttonMakeUser;
	@UiField
	Button buttonGetUser;

	public HomeView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		buttonAanmelden.setText("aanmelden");
		buttonRegisteren.setText("registeren");
	}

	@UiHandler("buttonAanmelden")
	void onClickLogin(ClickEvent e) {
		History.newItem("login");
	}

	@UiHandler("buttonRegisteren")
	void onClickRegister(ClickEvent e) {
		History.newItem("registers");
	}

	@UiHandler("buttonMakeUser")
	void onClickMakeUser(ClickEvent e) {
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					Window.alert("Saved");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				caught.printStackTrace();

			}
		};
		// move to appropriate form
		// in textfields
		userDataAsync.createUser(new UserDataDTO("Stefaan", "De Geyter",
				"faandg@gmail.com", "tester"), callback);

	}

	@UiHandler("buttonGetUser")
	void onClickGetUser(ClickEvent e) {
		AsyncCallback<List<UserDataDTO>> callback = new AsyncCallback<List<UserDataDTO>>() {

			@Override
			public void onSuccess(List<UserDataDTO> result) {
				// TODO check if list not empty
				String msg = "Retrieved user with userID "
						+ result.get(0).getUserId() + " succesfully!" + " - "
						+ result.get(0).getFirstName() + " - "
						+ result.get(0).getLastName() + " - "
						+ result.get(0).getEMail() + " - "
						+ result.get(0).getPassword();
				Window.alert(msg);

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to retrieve user list :(");

			}

		};
		userDataAsync.getUserData(callback);

	}
}
