package com.company.company.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class LoginView extends VerticalLayout {
	
	
	
	public LoginView()  {
		
		Button button = new Button("Click");
		button.addClickListener(e -> Notification.show("Login is coming!!!"));
		
		add(button);
	}
	
}
