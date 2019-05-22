package com.company.company.view;

import com.company.company.client.RestClient;
import com.company.company.model.entity.Employee;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog.ConfirmEvent;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class EmployeeForm extends FormLayout {

	private TextField firstName = new TextField("First name");
	private TextField lastName = new TextField("Last name");
	private TextField email = new TextField("Email");
	private TextField password = new TextField("Password");
	private TextField role = new TextField("Role");

	private Button save = new Button("Save");
	private Button delete = new Button("Delete");

	private Binder<Employee> binder = new Binder<>(Employee.class);

	private ManagerView managerView;

	private RestClient rc = new RestClient();

	public EmployeeForm(ManagerView managerView) {

		this.managerView = managerView;

		HorizontalLayout buttons = new HorizontalLayout(save, delete);
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		// addThemeVariants makes the save button prominent by decorating it with a
		// style name
		add(firstName, lastName, email, password, role, buttons);

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
		// The bindInstanceFields(this) method processes all the instance variables that
		// are input fields
		// (for example, TextField) and maps them (matching by name)
		// to the Java properties in the Employee class.

		save.addClickListener(event -> confirmSave());

		delete.addClickListener(event -> confirmDelete());

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
			rc.putData("http://localhost:8080/employee", employee);
		} catch (Exception ex) {
			rc.postData("http://localhost:8080/employee", employee);
		}
		managerView.employeeList();
		setEmployee(null);
		UI.getCurrent().getPage().reload();
	}

	private void delete() {

		Employee employee = binder.getBean();
		rc.deleteData("http://localhost:8080/employee/" + employee.getEmplId());
		managerView.employeeList();
		setEmployee(null);
		UI.getCurrent().getPage().reload();
	}

	private void confirmDelete() {
		ConfirmDialog dialog = new ConfirmDialog("Confirm delete", "Are you sure you want to delete employee?",
				"Delete", new ComponentEventListener() {

					@Override
					public void onComponentEvent(ComponentEvent event) {
						delete();
					}

				}, "Cancel", new ComponentEventListener() {

					@Override
					public void onComponentEvent(ComponentEvent event) {
					
					}

				});
		dialog.setConfirmButtonTheme("error primary");
		dialog.open();
	}
	
	private void confirmSave() {
		ConfirmDialog dialog = new ConfirmDialog("Confirm save", "Are you sure you want to save your changes?",
				"Save", new ComponentEventListener() {

					@Override
					public void onComponentEvent(ComponentEvent event) {
						if(binder.isValid()) {
							save();
						}
						else {
							Notification n = new Notification("Please fill in required fields");
							n.setDuration(3000);
							n.setPosition(Position.TOP_END);
							n.open();
						}
					}

				}, "Cancel", new ComponentEventListener() {

					@Override
					public void onComponentEvent(ComponentEvent event) {
					}

				});
		dialog.open();
	}


}
