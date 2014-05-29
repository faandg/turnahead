package com.programmeren4.turnahead.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FormOverview extends Composite {

	private static Uibinder1UiBinder uiBinder = GWT
			.create(Uibinder1UiBinder.class);

	interface Uibinder1UiBinder extends UiBinder<Widget, FormOverview> {
	}

	public FormOverview() {
		initWidget(uiBinder.createAndBindUi(this));
		setUserID();
	}

	public FormOverview(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		terugKnop.setText(firstName);

	}
	
	// ui field herhalen
	@UiField
	Label labelUserID;
	@UiField
	Button terugKnop;
	@UiField
	Button aanpassenKnop;
	@UiField
	Button verwijderCharKnop;
	@UiField
	Button nieuwCharKnop;
	@UiField
	VerticalPanel userDataPanel;
	@UiField
	VerticalPanel charPanel;

	/*
	 * CellList<String> karaktersGegevens; Label naam; Label voorNaam; Label
	 * email; Label geboortedatum; VerticalPanel persoonsGegevensLabel;
	 */

	private void setUserID(){
		labelUserID.setText("Welkom, uw userID is : "+LoginView.IngelogdID.toString());
	}

//	// userdatapanel-------------------------------------------------------------------------
//	// data uit databank halen voor persoonsgegevens
//	@UiHandler("userDataPanel")
//	void displayUserData(final String userData) {
//		// string userData(naam, voornaam, geboortedatum, email,....)
//		// methode die userdata in panel gaat plaatsen
//		// setUserdata();
//	}
//
//	// ----------------------------------------------------------------------------------------
//
//	// charpanel------------------------------------------------------------------------------
//	// data uit databank van karakter dat bij gebruiker hoort
//	@UiHandler("charPanel")
//	void displayChar(final String character) {
//		// setCharacterData
//	}
//
	@UiHandler("nieuwCharKnop")
	// er wordt een nieuwe pagina geladen waar en een nieuw karakter
	// eigenschappen kan geven
	void onClickNieuw(ClickEvent e) {
		// open nieuw scherm
		// MakeNewChar;
		FormMakeChar formChar = new FormMakeChar();
		RootPanel.get().clear();
		RootPanel.get().add(formChar);
	}

	@UiHandler("verwijderCharKnop")
	// het karakter wordt verwijderd in de databank
	void onClickVerwijderen(ClickEvent e) {
		// deleteChar();
	}

	// ----------------------------------------------------------------------------------------

	// location-------------------------------------------------------------------------------
	// toont aanwezige locatie van wereld in de vorm van een listbox
//	@UiHandler("locations")
//	void displayLocations(final String locations) {
//		// getCurrentLocations();
//	}

	void onMouseDoubleClick(SelectionEvent e) {
		// wanneer een locatie wordt geslecteerd gaat er een niew venster open
		// waar men
		// de items kan toevoegen
	}

	// ----------------------------------------------------------------------------------------
	// userdatapanel------------------------------------------------------------------------
	// userdatapanel------------------------------------------------------------------------

	@UiHandler("terugKnop")
	// terug keren naar de homepagina of doen in FormOverzichtPagina.xml zelf
	void onClickTerugKnop(ClickEvent e) {
		// // voor compleet nieuwe url buiten appalicatie
		// //Window.Location.assign("com.lucypeeters.test2.client.client.home.Home");
		//
		// FormHome formHome = new FormHome();
		// RootPanel.get().clear();
		// RootPanel.get().add(formHome);
		//
		// // met controller werken goto()

	}

}
