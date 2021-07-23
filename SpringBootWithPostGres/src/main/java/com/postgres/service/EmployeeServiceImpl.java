package com.postgres.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postgres.dao.EmployeeDao;
import com.postgres.entity.Employee;
import com.postgres.repository.EmployeeRepository;
import com.postgres.utils.CommonUtils;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
	        return employee;
	}

	@Override
	public Employee saveEmployeeDetails(JSONObject jsondata) {
		Employee employee = new Employee();
		if(jsondata!=null) {
			employee.setFirstName(jsondata.getString("firstName"));
	        employee.setLastName(jsondata.getString("lastName"));
	        employee.setAge(jsondata.getString("age"));
	        employee.setEmailId(jsondata.getString("emailId"));
	        employee.setJobTitle(jsondata.getString("jobTitle"));
	        employee.setPhoneNumber(jsondata.getString("phoneNumber"));
	        
	        Timestamp startDate = new Timestamp(System.currentTimeMillis());
	        Timestamp endDate = new Timestamp(System.currentTimeMillis());
	        
	        employee.setStartDate(CommonUtils.convertStringTypeDateToDateTypeForPostgres(startDate.toString()));
	        employee.setEndDate(CommonUtils.convertStringTypeDateToDateTypeForPostgres(endDate.toString()));
	        
	        //employee.setStartDate(CommonUtils.convertStringTypeDateToDateTypeForPostgres(jsondata.get("startDate").toString()));
	        //employee.setEndDate(CommonUtils.convertStringTypeDateToDateTypeForPostgres(jsondata.get("endDate").toString()));
	        			
			employee = employeeRepository.save(employee);
		}
		return employee;
	}

	@Override
	public String getEmployeeDetails(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
			
		
				List<Employee> empList = employeeDao.getEmployeeDetails(map);
				if (empList != null && empList.size() > 0) {
					jsonObject.put("empList", empList);
					jsonObject.put("msg", "List of employeeName successfully...");
					jsonObject.put("status", 1);
				} else {
					return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
				}

		return jsonObject.toString();
	}

}
