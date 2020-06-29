package com.mascix.module2;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.mascix.multimodule1.Controller1;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
public class CucumberTest {

	@Autowired
	Controller1 c;

	@Autowired
	private MockMvc mockMvc;

	@Given("user wants to create an employee with the following attributes")
	public void user_wants_to_create_an_employee_with_the_following_attributes(
			io.cucumber.datatable.DataTable dataTable) {
		for (Object o : dataTable.asLists()) {
			System.out.println(o);
		}
	}

	@Given("with the following phone numbers")
	public void with_the_following_phone_numbers(io.cucumber.datatable.DataTable dataTable) {
		for (Object o : dataTable.asList()) {
			System.out.println(o);
		}
	}

	@When("user saves the new employee {string}")
	public void user_saves_the_new_employee(String string) {
		System.out.println(string);
	}

	@Then("the save {string}")
	public void the_save(String string) throws Exception {
		System.out.println(string);
		mockMvc.perform(get("/module1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(equalTo(c.home())));

	}
}