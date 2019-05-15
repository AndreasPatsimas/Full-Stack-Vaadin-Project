package com.company.company.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.company.client.RestClient;
import com.company.company.model.entity.Employee;
import com.company.company.model.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout{
	
	private List<Employee> employees = new ArrayList<>();
	
	private Grid<Employee> grid = new Grid<>(Employee.class);
	
	private TextField filterText = new TextField();
	
	private RestClient rc = new RestClient();
	
	public MainView() {
		add(new Button("Click me", e -> Notification.show("That's GREAT!!!")));
		
		filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> employeeFilteredList(e.getValue()));
        
		grid.setColumns("firstName", "lastName", "email");
				
		String data = rc.getData("http://localhost:8080/employees");
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Employee[] empl = mapper.readValue(data, Employee[].class);
			employees.addAll( Arrays.asList( empl ));
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(filterText,grid);
		
		setSizeFull();	
		
		employeeList();
		
		
	}
	
	public void employeeFilteredList(String filterText) {
		
		List<Employee> employeeList = new ArrayList<>();
		
		String data = rc.getData("http://localhost:8080/employees/"+filterText);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Employee[] empl = mapper.readValue(data, Employee[].class);
			employeeList.addAll( Arrays.asList( empl ));
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		grid.setItems(employeeList);
		
	}
	
	public void employeeList() {
		grid.setItems(employees);
	}
	
}
