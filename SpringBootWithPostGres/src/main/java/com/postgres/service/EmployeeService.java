package com.postgres.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.postgres.entity.Employee;


public interface EmployeeService {

	public List<Employee> getAllEmployees();
	Optional<Employee> getEmployeeById(Long employeeId);
	public Employee saveEmployeeDetails(JSONObject jsondata);
	public String getEmployeeDetails(Map<String, Object> employeeName, HttpServletRequest request,
			HttpServletResponse response);
}
