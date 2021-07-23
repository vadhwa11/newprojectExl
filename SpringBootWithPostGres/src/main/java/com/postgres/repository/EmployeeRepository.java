package com.postgres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.postgres.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	

}
