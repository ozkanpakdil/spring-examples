package com.example.openapidemo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Tag(name = "get", description = "GET methods of Employee APIs")
    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        return repository.findAll();
    }

    @Tag(name = "get", description = "Retrieve one employee")
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@Parameter(
            description = "ID of employee to be retrieved",
            required = true)
                                @PathVariable int employeeId) {
        return repository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee id not found - " + employeeId));
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        Employee newEmployee = repository.save(employee);
        return newEmployee;
    }

    @Operation(summary = "Update an employee",
            description = "Update an existing employee. The response is updated Employee object with id, first name, and last name.")
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content)})
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee id not found - " + employeeId));
        repository.delete(employee);
        return "Deleted employee with id: " + employeeId;
    }

}
