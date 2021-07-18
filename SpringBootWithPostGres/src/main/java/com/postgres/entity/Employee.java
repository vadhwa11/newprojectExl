package com.postgres.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mas_employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MAS_EMPLOYEE_GENERATOR")
	@SequenceGenerator(name="MAS_EMPLOYEE_GENERATOR", sequenceName="employee_seq", allocationSize=1)
	@Column(name="emp_id")
	private Long employeeId;
	
	@Column(name="emp_fname")
    private String firstName;
	
	@Column(name="emp_lname")
    private String lastName;
	
	@Column(name="job_title")
    private String jobTitle;
	
	@Column(name="age")
    private String age;
	
	@Column(name="email_Id")
    private String emailId;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
    private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
    private Date endDate;

	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Employee(Long employeeId, String firstName, String lastName, String jobTitle, String age, String emailId,
			String phoneNumber, Date startDate, Date endDate) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.age = age;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public Long getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}
