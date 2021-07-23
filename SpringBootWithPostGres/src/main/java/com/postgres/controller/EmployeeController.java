package com.postgres.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.postgres.entity.Employee;
import com.postgres.exception.ResourceNotFoundException;
import com.postgres.repository.EmployeeRepository;
import com.postgres.service.EmployeeService;


@RestController
@RequestMapping("/api/postgres")
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
    public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
    }
	
	@GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
	
	@PostMapping("/createEmployees")
    public Employee createEmployee(@RequestBody Map<String, Object> requestdata) {
		JSONObject jsondata = new JSONObject(requestdata);
		return employeeService.saveEmployeeDetails(jsondata);
       // return employeeRepository.save(employee);
    }
	
	  @PutMapping("/employees/{id}")
	    public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") Long employeeId,
	         @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
	        Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

	        employee.setFirstName(employeeDetails.getFirstName());
	        employee.setLastName(employeeDetails.getLastName());
	        employee.setAge(employeeDetails.getAge());
	        employee.setEmailId(employeeDetails.getEmailId());
	        employee.setJobTitle(employeeDetails.getJobTitle());
	        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
	        final Employee updatedEmployee = employeeRepository.save(employee);
	        return ResponseEntity.ok(updatedEmployee);
	    }
	  
	  @DeleteMapping("/employees/{id}")
	    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
	    throws ResourceNotFoundException {
	        Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

	        employeeRepository.delete(employee);
	        Map<String, Boolean> response = new HashMap< > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	  
	  @RequestMapping(value = "/getEmployees")
	   public ResponseEntity<Object> getAllEmployess() {
		 List<Employee> empList =  employeeService.getAllEmployees();
		 if(empList!=null && empList.size()>0) {
			 return new ResponseEntity<>(empList, HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(empList, HttpStatus.NO_CONTENT);
		 }
		  
	   }
	  
	  @RequestMapping(value="/getEmployeeDetails", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public String getEmployeeDetails(@RequestBody Map<String, Object> employeeName, HttpServletRequest request,
				HttpServletResponse response) {
		  
		  	String employee = "";
		  	employee = employeeService.getEmployeeDetails(employeeName, request, response);
		  	System.out.println("employee ::: "+employee);
			return employee;
		  
	  }
}
