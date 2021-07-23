package com.postgres.utils;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonUtils {
	
	 public static int calculateAgeNoOfYear(Date dob ) {
			 
			 Calendar lCal = Calendar.getInstance();
			    lCal.setTime(dob);
			    int yr=lCal.get(Calendar.YEAR);
			    int mn=lCal.get(Calendar.MONTH) + 1;
			    int dt=lCal.get(Calendar.DATE);
			    LocalDate today = LocalDate.now(); //Today's date
			LocalDate birthday = LocalDate.of(yr,mn,dt) ; //Birth date
			Period p = Period.between(birthday, today); // Period
			return p.getYears();
		 }
	 
	 
	 
	 public static String getProperties(String fileName, String propName){
			String propertyValue = null;
			try{
				URL resourcePath = Thread.currentThread().getContextClassLoader()
						.getResource(fileName);
				Properties properties= new Properties();
				properties.load(resourcePath.openStream());
				propertyValue = properties.getProperty(propName);
			}catch(Exception e){e.printStackTrace();}
			return propertyValue;
		}
	 
	 public static String convertDateToStringFormat(Date date, String format){
	     String dateFormat="";
	     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
	     if(date != null) {
	    	 dateFormat=simpleDateFormat.format(date);    
	     }    
	     return dateFormat;	

	}
	 
	 public static String convertDateToStringFormat1(LocalDate visitDate, String format){
	     String dateFormat="";
	     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
	     if(visitDate != null) {
	    	 dateFormat=simpleDateFormat.format(visitDate);    
	     }    
	     return dateFormat;	

	}
	 
	 
	 
	 
	 public static Date getNextDate(Date today) {
		 
		String strDate= convertDateToStringFormat(today, "yyyy-MM-dd");
		Date returnDate=null;
		try {
			returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 final Calendar calendar = Calendar.getInstance();
		 calendar.setTime(returnDate);
		 calendar.add(Calendar.DAY_OF_YEAR, 1);
		 return calendar.getTime();
		}
	 
	 
	 
	 public static Date dateFormatteryyyymmdd(String stringDate) throws Exception {
			SimpleDateFormat dateFormatterYYYYMMDD = new SimpleDateFormat(
					"yyyy-MM-dd");
			try {
				return (dateFormatterYYYYMMDD.parse(stringDate));
			} catch (ParseException e) {
				e.printStackTrace();
				throw e;			
			}
			
		}
	 
	 
		public static Date convertStringDateToUtilDate(String date, String format) {
			Date utilDate = null;

			SimpleDateFormat df = new SimpleDateFormat(format);
			if (date != null) {
				try {
					utilDate = df.parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			return utilDate;
		}
		
		
		public static Date convertStringDateToUtilDateForDatabase(String date) {
			Date utilDate = null;
			String format="";
			if(date.trim().length()>10) {
				 format="dd/MM/yyyy HH:mm:ss";
			}else {
				 format="dd/MM/yyyy";
			}
			
			SimpleDateFormat df = new SimpleDateFormat(format);
			if (date != null) {
				try {
					utilDate = df.parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			return utilDate;
		}
		
		
		
		public static String convertNullToEmptyString(Object obj) {
			return (obj == null) ? "" : obj.toString();
		}
		
		
		
		public static long calculateTotalMinutes(String startTime, String endTime) {

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date d1;
			Date d2;
			long totalMinutes=0;
			try {
				d1 = format.parse(startTime);
				d2 = format.parse(endTime);
				totalMinutes = (d2.getTime() - d1.getTime())/(60 * 1000); 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			return totalMinutes;
		}
		
		
		public static String addingMinutes(String startTime, int timeInterval) {

			 SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			 Date d;
			 String newTime="";
			try {
				d = df.parse(startTime);
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(d);
				 cal.add(Calendar.MINUTE, timeInterval);
				 newTime = df.format(cal.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			 return newTime;
		}
		
		
		 public static String getDateWithoutTime(Date date) {
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    return sdf.format(date);
			  }
		
		 
		 public static String changeDateToddMMyyyy(Date dbDate) {
			 String strDate = dbDate.toString();
			 String strNewDate = "", year = "", dt = "", month = "";
			 year = strDate.substring(0, 4);
			 month = strDate.substring(5, 7);
			 dt = strDate.substring(8, 10);
			 strNewDate = (dt + "/" + month + "/" + year);
			 return strNewDate;
			}
		 
		 
		 public static Date convertStringTypeDateToDateType(String date) {
				Date orderDateTime = null;

				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				if (date != null) {
					try {
						orderDateTime = df.parse(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

				return orderDateTime;
			}
		 public static Date convertStringTypeDateToDateTypeForPostgres(String date) {
				Date orderDateTime = null;

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				if (date != null) {
					try {
						orderDateTime = df.parse(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

				return orderDateTime;
			}
		 
		 
		public static String calculateAge(Date birthDate) {
			// get todays date
			Calendar now = Calendar.getInstance();
			// get a calendar representing their birth date

			Calendar cal = Calendar.getInstance();
			cal.setTime(birthDate);

			// calculate age as the difference in years.
			@SuppressWarnings("unused")
			int calculatedDays, calculatedMonth, calculatedYear;
			int currentDays = now.get(Calendar.DATE);
			int birthDays = cal.get(Calendar.DATE);
			int currentMonth = now.get(Calendar.MONTH);
			int birthMonth = cal.get(Calendar.MONTH);
			int currentYear = now.get(Calendar.YEAR);
			int birthYear = cal.get(Calendar.YEAR);

			if (currentDays < birthDays) {
				currentDays = currentDays + 30;
				calculatedDays = currentDays - birthDays;
				currentMonth = currentMonth - 1;
			} else {
				calculatedDays = currentDays - birthDays;
			}

			if (currentMonth < birthMonth) {
				currentMonth = currentMonth + 12;
				calculatedMonth = currentMonth - birthMonth;
				currentYear = currentYear - 1;
			} else {
				calculatedMonth = currentMonth - birthMonth;
			}

			int age = currentYear - birthYear;
			String patientAge = "";

			if (age == 0 && calculatedMonth != 0 && calculatedDays != 0) {
				patientAge = calculatedMonth + " Months ";
			} else if (age == 0 && calculatedMonth == 0 && calculatedDays != 0) {
				patientAge = calculatedDays + "  Days";
			} else if (age == 0 && calculatedMonth != 0 && calculatedDays == 0) {
				patientAge = calculatedMonth + " Months ";
			} else if (age == 0 && calculatedMonth == 0 && calculatedDays == 0) {
				patientAge = "1 Days";
			} else {
				patientAge = age + " Years ";
			}
			return patientAge;
		}
		 
		
		 public static String convertDateAndTimeToUSformatOnlyDate(Date dat) {
			  String date = "";
			  try {
				  SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");
				  if (dat != null) {
					  date = sdf.format(dat);
				  }
			  } catch (Exception e) {
			  }
			  return date;
		  }
		
		/*
		 * public static final String[] tensNames = { "", " ten", " twenty", " thirty",
		 * " forty", " fifty", " sixty", " seventy", " eighty", " ninety" }; public
		 * static final String[] numNames = { "", " one", " two", " three", " four",
		 * " five", " six", " seven", " eight", " nine", " ten", " eleven", " twelve",
		 * " thirteen", " fourteen", " fifteen", " sixteen", " seventeen", " eighteen",
		 * " nineteen" };
		 * 
		 * 
		 * 
		 * public static String convertLessThanOneThousand(int paramInt) { String str;
		 * if (paramInt % 100 < 20) { str = numNames[(paramInt % 100)]; paramInt /= 100;
		 * } else { str = numNames[(paramInt % 10)]; paramInt /= 10;
		 * 
		 * str = tensNames[(paramInt % 10)] + str; paramInt /= 10; } if (paramInt == 0)
		 * return str; return numNames[paramInt] + " hundred" + str; }
		 * 
		 */
		
		 public static final String[] units;
		    public static final String[] tens;
		    
		    
		    static {
		        units = new String[] { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
		        tens = new String[] { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
		    }
		    
		    public static String convert(final int n) {
		        if (n < 0) {
		            return "Minus " + convert(-n);
		        }
		        if (n < 20) {
		            return units[n];
		        }
		        if (n < 100) {
		            return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
		        }
		        if (n < 1000) {
		            return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		        }
		        if (n < 100000) {
		            return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		        }
		        if (n < 10000000) {
		            return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
		        }
		        return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
		    }

			public static String convertTimeinHHMMfromLong(long timestamp) {
				DateFormat format = new SimpleDateFormat( "HH:mm" );
				String time = format.format( timestamp );
				return time;
			}
			
			public static long getNoOfDaysFromPreviousDateToCurrentDate(Date previousDate) {
				long noOfDays = 0;
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				//current date in dd/MM/yyyy
				Date todayDate = new Date();
		    	String currentdate = dateFormat.format(todayDate);
		    	//prevous date in dd/MM/yyyy
		    	String pastDate = dateFormat.format(previousDate);

		    	try {
		    	    Date date1 = dateFormat.parse(currentdate);
		    	    Date date2 = dateFormat.parse(pastDate);
		    	    long diff = date1.getTime() - date2.getTime();
		    	    noOfDays =  + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    	    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		    	} catch (Exception e) {
		    	    e.printStackTrace();
		    	}
		    	
				return noOfDays;
			}
			
			public static Date getDateBeforeDays(int day) {
				
				 Calendar cal = Calendar.getInstance();
				 cal.add(Calendar.DATE, day);
				 cal.set(Calendar.HOUR_OF_DAY,0);
				 cal.set(Calendar.MINUTE,0);
				 Date date = cal.getTime();
				 return date;
				 
			}
			
			public static String convertDateToLocalDateFormate(String date) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
				LocalDate localdate = LocalDate.parse(date, formatter);
				LocalDate returnvalue = localdate.plusDays(1);
				//System.out.println(localdate);
				String formattedDate = returnvalue.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				//System.out.println(formattedDate);
				return formattedDate;
				
			}

}
