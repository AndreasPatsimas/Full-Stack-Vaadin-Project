package com.company.company.view;

import com.company.company.client.RestClient;
import com.company.company.model.entity.Token;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("employee")
public class EmployeePage extends VerticalLayout {

	Button button = new Button("Wait employee");
	
	Button logout = new Button("Logout");
	
	private RestClient rc = new RestClient();

	public EmployeePage() {
		
		logout.addClickListener(e -> confirmLogout());
		add(logout);
		
		button.addClickListener(e -> e.getButton());
		
		add(button);
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
	
	void logout() {
		Token employee = (Token) UI.getCurrent().getSession().getAttribute("logEmployee");
		rc.deleteData("http://localhost:8080/logout/"+employee.getUuId());
		UI.getCurrent().getSession().close();
		logout.getUI().ifPresent(ui ->{ ui.navigate(""); });
	}
}
