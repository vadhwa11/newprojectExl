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
	
	@Override
	public List<Employee> getEmployeeDetails(Map<String,Object> map) {
		String employeeName=null;
		String startDate=null;
		String endDate=null;
		List<Employee> employees = new ArrayList<Employee>();
		
		
		
		if(map.containsKey("employeeName") && map.containsKey("startDate") && map.containsKey("endDate")) {
			employeeName = map.get("employeeName").toString();
			startDate = map.get("startDate").toString();
			endDate = map.get("endDate").toString();
			
			Date fromDate = CommonUtils.convertStringDateToUtilDateForDatabase(startDate);
			Date toDate = CommonUtils.convertStringDateToUtilDateForDatabase(endDate);
			
			Criteria crit = em.unwrap(Session.class).createCriteria(Employee.class);
			employees = crit.add(Restrictions.and(Restrictions.eq("startDate", fromDate),
												  Restrictions.eq("endDate", toDate),
								Restrictions.eq("firstName", employeeName)))
					.list();
			
		}else if(map.containsKey("startDate") && map.containsKey("endDate")) {
			startDate = map.get("startDate").toString();
			endDate = map.get("endDate").toString();
			
			//SimpleDateFormat startDate1 = new SimpleDateFormat(startDate);
			//SimpleDateFormat endDate1 = new SimpleDateFormat(endDate);
			
			Date fromDate = CommonUtils.convertStringDateToUtilDate(startDate, "MM/dd/yyyy");
			Date toDate = CommonUtils.convertStringDateToUtilDate(endDate, "MM/dd/yyyy");
			
			//Date fromDate = new Date();
			// convert date to calendar current date
	        Calendar c = Calendar.getInstance();
	        c.setTime(fromDate);
	        c.add(Calendar.DATE, 1);
	        Date toDate1 = c.getTime();
			
			Criteria crit = em.unwrap(Session.class).createCriteria(Employee.class);
			employees = crit.add(Restrictions.and(Restrictions.eq("startDate", fromDate),
					Restrictions.eq("endDate", toDate1))).list();
		}else {		
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
