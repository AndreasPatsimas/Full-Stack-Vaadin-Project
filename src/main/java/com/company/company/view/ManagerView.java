package com.company.company.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.company.client.RestClient;
import com.company.company.model.entity.Employee;
import com.company.company.model.entity.Role;
import com.company.company.model.entity.Token;
import com.company.company.model.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("manager")
public class ManagerView extends VerticalLayout {
	
	private List<Employee> employees = new ArrayList<>();
	
	private Grid<Employee> grid = new Grid<>(Employee.class);
	
	private TextField filterText = new TextField();
	
	private EmployeeForm form = new EmployeeForm(this);
	
	private RestClient rc = new RestClient();
	
	Button logout = new Button("Logout");
	
	public ManagerView() {
		
		logout.addClickListener(e -> confirmLogout());
		add(logout);
		
		filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> employeeFilteredList(e.getValue()));
        
        Button addEmployeeBtn = new Button("Add new employee");
        addEmployeeBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setEmployee(new Employee());
        });
    	
        HorizontalLayout toolbar = new HorizontalLayout(filterText,
        	    addEmployeeBtn);
        
		grid.setColumns("firstName", "lastName", "email", "password", "roles");
				
		String data = rc.getData("http://localhost:8080/employees");
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Employee[] empl = mapper.readValue(data, Employee[].class);
			employees.addAll( Arrays.asList( empl ));
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setSizeFull();	
		
		employeeList();
		
		HorizontalLayout mainContent = new HorizontalLayout(grid, form);
		mainContent.setSizeFull();
		grid.setSizeFull();

		add(toolbar, mainContent);
		
		form.setEmployee(null);
		
		grid.asSingleSelect().addValueChangeListener(event ->
        form.setEmployee(grid.asSingleSelect().getValue()));
	}
	
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		 if (UI.getCurrent().getSession().getAttribute("logManager") == null) {
			 getUI().ifPresent(ui -> ui.navigate(""));
		 }
	}
	
	public void employeeFilteredList(String filterText) {
		
		List<Employee> employeeList = new ArrayList<>();
		
		String data = rc.getData("http://localhost:8080/employees/"+filterText);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Employee[] empl = mapper.readValue(data, Employee[].class);
			employeeList.addAll( Arrays.asList( empl ));
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		grid.setItems(employeeList);
		
	}
	
	public void employeeList() {
		grid.setItems(employees);
	}
	
	private void confirmLogout() {
		ConfirmDialog dialog = new ConfirmDialog("Confirm logout", "Are you sure you want to logout?",
				"Logout", new ComponentEventListener() {

					@Override
					public void onComponentEvent(ComponentEvent event) {
						logout();
					}

				}, "Cancel", new ComponentEventListener() {

					@Override
					public void onComponentEvent(ComponentEvent event) {
					
					}

				});
		dialog.setConfirmButtonTheme("error primary");
		dialog.open();
	}
	
	public void logout() {
		
		Token manager = (Token) UI.getCurrent().getSession().getAttribute("logManager");
		rc.deleteData("http://localhost:8080/logout/"+manager.getUuId());
		UI.getCurrent().getSession().close();
		logout.getUI().ifPresent(ui ->{ ui.navigate(""); });
	}
	
	
}
