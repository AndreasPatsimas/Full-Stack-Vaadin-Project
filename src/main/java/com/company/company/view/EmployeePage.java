package com.company.company.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("employee")
public class EmployeePage extends VerticalLayout {

	Button employee = new Button("Wait employee");

	public EmployeePage() {
		add(employee);
	}
	
	
}
