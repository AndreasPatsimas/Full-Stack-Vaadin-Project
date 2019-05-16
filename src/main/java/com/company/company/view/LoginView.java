package com.company.company.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;

import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends FormLayout {
	
	
	
	public LoginView() {
		
		Button button = new Button("Save to click");
		button.addClickListener(e -> System.out.println("Button was clicked and confirmed by user"));
		
		add(button);
	}
	
}
