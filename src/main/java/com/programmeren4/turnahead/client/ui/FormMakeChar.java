package com.programmeren4.turnahead.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.programmeren4.turnahead.client.services.KarakterDataService;
import com.programmeren4.turnahead.client.services.KarakterDataServiceAsync;
import com.programmeren4.turnahead.shared.dto.KarakterDTO;

public class FormMakeChar extends Composite {

	private static CharAanmakenUiBinder uiBinder = GWT
			.create(CharAanmakenUiBinder.class);
	KarakterDataServiceAsync KarakterDataAsync;

	interface CharAanmakenUiBinder extends UiBinder<Widget, FormMakeChar> {
	}

	public FormMakeChar() {
		initWidget(uiBinder.createAndBindUi(this));
		KarakterDataAsync = GWT.create(KarakterDataService.class);
	}

	@UiField
	Button OKbutton;
	@UiField
	Button cancelButton;
	@UiField
	TextBox name;
	@UiField
	TextBox currentLocation;
	@UiField
	TextBox userId;
	@UiField
	TextBox locationId;

	@UiHandler("OKbutton")
	void onClickOKknop(ClickEvent e) {
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				Window.alert("Karakter toegevoegd aan de DB");
				new OverviewController();
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Karakter niet toegevoegd :(");
				
				System.out.println(caught.getMessage());
				
				
			}

		};
		System.out.println(name.getText()+" "+ currentLocation.getText()+" "+userId.getText()+" "+locationId.getText());
		try{
			KarakterDataAsync.addKarakterData(new KarakterDTO(name.getText(), currentLocation.getText(),
					Long.parseLong(userId.getText(), 10), Long.parseLong(locationId.getText(), 10)), callback);
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
		
	}

	@UiHandler("cancelButton")
	void onClickcancelKnop(ClickEvent e) {
		// de bewerking wordt afgebroken en men wordt terug gestuurd naar de
		// overzichtspagina
		FormOverview formOverzichtspagina = new FormOverview();
		RootPanel.get().clear();
		RootPanel.get().add(formOverzichtspagina);
	}

}
