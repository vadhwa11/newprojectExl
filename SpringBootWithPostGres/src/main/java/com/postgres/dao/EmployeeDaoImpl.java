package com.postgres.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.postgres.entity.Employee;
import com.postgres.utils.CommonUtils;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Employee> getEmployeeDetails(Map<String,Object> map) {
		String employeeName=null;
		String startDate=null;
		String endDate=null;
		List<Employee> employees = new ArrayList<Employee>();
		
		
			if(map.get("employeeName").toString()!=null) {
				employeeName = map.get("employeeName").toString();
				
			}
				
			if(map.get("startDate").toString()==null) {
				startDate = "";
			}else {
				startDate = map.get("startDate").toString();
			}
			if(map.get("endDate").toString()==null) {
				endDate="";
			}else {
				endDate = map.get("endDate").toString();
			}
			
			if(!employeeName.equalsIgnoreCase("") && !startDate.equalsIgnoreCase("") && !endDate.equalsIgnoreCase("")) {
				String sstartDate = CommonUtils.convertDateToLocalDateFormate(startDate);
				String eendDate = CommonUtils.convertDateToLocalDateFormate(endDate);
				Criteria crit = em.unwrap(Session.class).createCriteria(Employee.class);
				crit = crit.add(Restrictions.between("startDate", CommonUtils.convertStringDateToUtilDate(sstartDate, "MM/dd/yyyy"),
						CommonUtils.convertStringDateToUtilDate(eendDate, "MM/dd/yyyy")));
				crit.add(Restrictions.ilike("firstName", employeeName));
				employees = crit.list();
				/*
				employees = crit.add(Restrictions.and(Restrictions.eq("startDate", CommonUtils.convertStringDateToUtilDate(sstartDate, "MM/dd/yyyy")),
													  Restrictions.eq("endDate", CommonUtils.convertStringDateToUtilDate(eendDate, "MM/dd/yyyy")),
									Restrictions.eq("firstName", employeeName))).list();
				*/
			}else if(!startDate.equalsIgnoreCase("") || !endDate.equalsIgnoreCase("")) {
				
				String sstartDate = CommonUtils.convertDateToLocalDateFormate(startDate);
				String eendDate = CommonUtils.convertDateToLocalDateFormate(endDate);
				
					Criteria crit = em.unwrap(Session.class).createCriteria(Employee.class);
					employees = crit.add(Restrictions.between("startDate", CommonUtils.convertStringDateToUtilDate(sstartDate, "MM/dd/yyyy"),
											CommonUtils.convertStringDateToUtilDate(eendDate, "MM/dd/yyyy"))).list();
			
			} else {		
			if(map.containsKey("employeeName")) {
				employeeName = map.get("employeeName").toString();
				Criteria crit = em.unwrap(Session.class).createCriteria(Employee.class);
				employees = crit.add(Restrictions.eq("firstName", employeeName)).list();
			}
			
		}
			

		
		System.out.println("listof employees :: "+employees);
        return employees;
	}
	
}
		
