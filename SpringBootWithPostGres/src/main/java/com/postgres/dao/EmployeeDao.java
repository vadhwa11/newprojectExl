package com.postgres.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.postgres.entity.Employee;
import com.sun.el.parser.ParseException;

@Repository
public interface EmployeeDao {
	

	List<Employee> getEmployeeDetails(Map<String, Object> map);

}
