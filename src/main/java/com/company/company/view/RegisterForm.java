package com.company.company.view;

import com.company.company.client.RestClient;
import com.company.company.model.entity.Employee;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("register")
public class RegisterForm extends VerticalLayout {

	private TextField firstName = new TextField("First name");
	private TextField lastName = new TextField("Last name");
	private TextField email = new TextField("Email");
	private PasswordField password = new PasswordField("password");
	
	Header header = new Header();
	
	private Button save = new Button("Save");
	private Button clear = new Button("Clear", event -> {
	    firstName.clear();
	    lastName.clear();
	    email.clear();
	    password.clear();
	});
	
	private Binder<Employee> binder = new Binder<>(Employee.class);
	
	//private MainView mainView;
	
	private RestClient rc = new RestClient();

	public RegisterForm() {
		
		VerticalLayout layout = new VerticalLayout();

		// label will only take the space it needs
		Label label = new Label("Register Form");
		add(label);
		
		HorizontalLayout buttons = new HorizontalLayout(save, clear);
	    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	    //addThemeVariants makes the save button prominent by decorating it with a style name
	    add(firstName, lastName, email, password, buttons,header);
	    
	    binder.forField(firstName)
	    .asRequired("Every employee must have a firstname")
	    .bind(Employee::getFirstName, Employee::setFirstName);
	    
	    binder.forField(lastName)
	    .asRequired("Every employee must have a lastName")
	    .bind(Employee::getLastName, Employee::setLastName);
	    
	    binder.forField(email)
	    .asRequired("Every employee must have an email")
	    .bind(Employee::getEmail, Employee::setEmail);
	    
	    binder.forField(password)
	    .asRequired("Every employee must have a password")
	    .bind(Employee::getPassword, Employee::setPassword);
	    
	    binder.bindInstanceFields(this);
	  
	    //The bindInstanceFields(this) method processes all the instance variables that are input fields 
	    //(for example, TextField) and maps them (matching by name) 
	    //to the Java properties in the Employee class.

	    
	    save.addClickListener(event -> confirmSave());
	    
	    /*save.addClickListener( e-> {
	        save.getUI().ifPresent(ui -> ui.navigate("login"));
	   });*/
	    
	}
	
	
	private void save() {
		Employee employee = new Employee();
		employee.setFirstName(firstName.getValue());
		employee.setLastName(lastName.getValue());
		employee.setEmail(email.getValue());
		employee.setPassword(password.getValue());
	    try {
	    	rc.postData("http://localhost:8080/employee", employee);
	    }
	    catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	}
	
	private void confirmSave() {
		ConfirmDialog dialog = new ConfirmDialog("Confirm save", "Are you sure you want to save your changes?",
				"Save", new ComponentEventListener() {

					@Override
					public void onComponentEvent(ComponentEvent event) {
						if(binder.isValid()) {
							save();
							save.getUI().ifPresent(ui -> ui.navigate(""));
						}
						else {
							Notification n = new Notification("Please fill in required fields");
							n.setDuration(3000);
							n.setPosition(Position.TOP_START);
							n.open();
						}
					}

				}, "Cancel", new ComponentEventListener() {

					@Override
					public void onComponentEvent(ComponentEvent event) {
					//
					}

				});
		dialog.open();
	}
	
	
}
