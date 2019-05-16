package com.company.company.view;

import com.company.company.client.RestClient;
import com.company.company.model.entity.Employee;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class EmployeeForm extends FormLayout {

	private TextField firstName = new TextField("First name");
	private TextField lastName = new TextField("Last name");
	private TextField email = new TextField("Email");
	
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");
	
	private Binder<Employee> binder = new Binder<>(Employee.class);
	
	private MainView mainView;
	
	private RestClient rc = new RestClient();
	
	public EmployeeForm(MainView mainView) {
	    
		this.mainView = mainView;
		
	    HorizontalLayout buttons = new HorizontalLayout(save, delete);
	    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	    //addThemeVariants makes the save button prominent by decorating it with a style name
	    add(firstName, lastName, email, buttons);
	    
	    binder.bindInstanceFields(this);
	    //The bindInstanceFields(this) method processes all the instance variables that are input fields 
	    //(for example, TextField) and maps them (matching by name) 
	    //to the Java properties in the Customer class.
	    
	    save.addClickListener(event -> save());
	    
	    delete.addClickListener(event -> delete());
	}
	
	public void setEmployee(Employee employee) {
	    binder.setBean(employee);

	    if (employee == null) {
	        setVisible(false);
	    } else {
	        setVisible(true);
	        firstName.focus();
	    }
	}
	
	private void save() {
	    Employee employee = binder.getBean();
	    try {
	    	employee.getEmplId();
	    	String data = rc.putData("http://localhost:8080/employee", employee);
	    }
	    catch(Exception ex) {
	    	String data = rc.postData("http://localhost:8080/employee", employee);
	    }
	    mainView.employeeList();
	    setEmployee(null);
	}
	
	private void delete() {
		Employee employee = binder.getBean();
		String data = rc.deleteData("http://localhost:8080/employee/"+employee.getEmplId());
	    mainView.employeeList();
	    setEmployee(null);
	}
	
}
