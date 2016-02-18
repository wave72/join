package it.burningboots.join.client;

import it.burningboots.join.client.widgets.MessagePanel;
import it.burningboots.join.client.widgets.Popup;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class UiSingleton implements ValueChangeHandler<String> {

	private static UiSingleton instance = null;
	
	private SimplePanel headerPanel = null;
	private SimplePanel contentPanel = null;
	private MessagePanel messagePanel = null;
	
	private UiSingleton() { }
	
	public static UiSingleton get() {
		if (instance == null) {
			instance = new UiSingleton();
		}
		return instance;
	}
	
	public void drawUi() {
		RootPanel.get("header").clear();
		RootPanel.get("content-area").clear();
		RootPanel.get("message-panel").clear();
		initUi();
	}
	
	private void initUi() {
		// Panels
		headerPanel = new SimplePanel();
		RootPanel.get("header").add(headerPanel);
		
		contentPanel = new SimplePanel();
		contentPanel.setStyleName("content");
		RootPanel.get("content-area").add(contentPanel);
		
		// message panel
		messagePanel = new MessagePanel();
		RootPanel.get("message-panel").add(messagePanel);
		
		// navigation functionality
		initHistorySupport();
	}
	
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	// history mgmt
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

	private void initHistorySupport() {
		// add the MainPanel as a history listener
		History.addValueChangeHandler(this);
		// check to see if there are any tokens passed at startup via the browser's URI
		String token = History.getToken();
		if (token.length() == 0) {
			UriDispatcher.loadContent(UriDispatcher.INDEX);
		}
		else {
			UriDispatcher.loadContent(token);
		}
	}

	/**
	 * this method is called when the fwd/back buttons are invoked on the browser.
	 * this is also invoked when hyperlinks (generated by the app) are clicked.
	 */
	public void onValueChange(ValueChangeEvent<String> event) {
		// This method is called whenever the application's history changes. Set
		// the label to reflect the current history token.
		UriDispatcher.loadContent(event.getValue());
	}


	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	// getters & setters
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

	public void addError(Throwable e) {
		//messagePanel.addError(e);
		Popup pop = new Popup(e);
		pop.setGlassEnabled(true);
		pop.centerAndShow();
	}

	public void addWarning(String warning) {
		//messagePanel.addWarning(warning);
		Popup pop = new Popup(warning);
		pop.setGlassEnabled(true);
		pop.centerAndShow();
	}
	
	public void addInfo(String info) {
		messagePanel.addInfo(info);
	}
	
	public SimplePanel getContentPanel() {
		return contentPanel;
	}
	
	public SimplePanel getHeaderPanel() {
		return headerPanel;
	}
	
	public void setApplicationTitle(String title) {
		Window.setTitle(title);
	}
	
}
