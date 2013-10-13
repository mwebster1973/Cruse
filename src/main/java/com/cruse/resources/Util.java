package com.cruse.resources;

//****************************************************************
//* Copyright (c) 2009 Ford Motor Company. All Rights Reserved.
//*
//* $$Workfile:   Util.java  $$
//* $$Revision:   1.0  $$
//* $$Author:   mwebst28  $$
//* $$Date:   Oct 29 2009 14:51:20  $$
//*
//*****************************************************************
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;


public class Util {

	
	/**
	 * Eliminate duplicate CDSIDs
	 * @param cdsidsSet
	 * @return
	 */
	public static String[] convertHashSetIntoArray(HashSet<String> cdsidsSet) {
		
		String[] cdsids = new String[cdsidsSet.size()];
		if (cdsidsSet.size() > 0) {
			Iterator it = cdsidsSet.iterator();
			int x = 0;
			while (it.hasNext()) {
				cdsids[x++] = (String) it.next();
			}
		}	
		return cdsids;
	}
	
/**
* Pads a given string s to a size a size of N characters with char c justified either 
* on the left (paddingLeft=True) or on the right(paddingLeft=false).
*
* @param s - the target string
* @param n - size of the resultant string
* @param c - the padding character
* @param padding - if true than justification to the left else to the right
* @return - the resultant string
**/

	public static String paddingStr(
    final String s,
    final int n,
    final char c,
    final boolean padding) {
    StringBuffer str = new StringBuffer(s);
    int strLength = str.length();
    if (strLength > n) {
        if (padding) {
            str = new StringBuffer(s.substring(strLength - n));
        } else {
            str = new StringBuffer(s.substring(0, n - 1));
        }
    }
    if (n > 0 && n > strLength) {
        for (int i = 0; i <= n; i++) {
            if (padding) {
                if (i < n - strLength)
                    str.insert(0, c);
            } else {
                if (i > strLength)
                    str.append(c);
            }
        }
    }
    return str.toString();
}

/**
 * Replaces instances of a specified sub-string with another sub-string with in
 * a given string. 
 *
 * @param input - the given string 
 * @param oldStr - the sub-string to replace
 * @param newStr - the replacement string
 * @return - the resultant string
 */
public static String replaceSubString(
    final String input,
    final String oldStr,
    final String newStr) {
    
    if( input == null || input.trim().equals( "" )){
        return "";
    }
    
    final StringBuffer result = new StringBuffer();

    int startIdx = 0;
    int idxOld = 0;
    while ((idxOld = input.indexOf(oldStr, startIdx)) >= 0) {
        result.append(input.substring(startIdx, idxOld));
        result.append(newStr);
        startIdx = idxOld + oldStr.length();
    }

    result.append(input.substring(startIdx));
    return result.toString();
}

/**
 * Convert a String into a date
 * @param	dateAsString. A String entered that is to be converted
 * to a string.
 * @return	The java.util.Date version of the string
 * @throws  ParseException if the date can not be converted     
 */
public static Date convertToDate(String dateAsString) throws ParseException {
   Date utilDate = null;
   
   if (isDatePopulated(dateAsString)) {
		    DateFormat df = DateFormat.getInstance();
		    df.setLenient(false);
		    SimpleDateFormat sf = (SimpleDateFormat) df;                
		    sf.applyPattern("dd/MM/yyyy");
		    utilDate = sf.parse(dateAsString);
   }
	   return utilDate;
}

/**
 * Concert a date from a dto, to a String. 
 * @param date	The date to be converted
 * @return A stringed representation of the date.
 */
public static String convertDateToString(Date date) {
	    
	    String stringDate = null;
	    if (date!= null) {
			DateFormat df = DateFormat.getInstance();
			SimpleDateFormat sf = (SimpleDateFormat) df;                
			sf.applyPattern("dd/MM/yyyy");
			stringDate = sf.format(date);
	    }
	    return stringDate;
	}
	
/**
 * Determine whether an attribute is populated.
 * @param attribute
 * @return true if the attribute contains a value.
 */
public static boolean isDatePopulated(String attribute){
	boolean populated=true;
	if(
			(attribute==null)||
				(attribute.trim().length()==0) ||
				(attribute.equalsIgnoreCase("dd/mm/yy"))||
				(attribute.equalsIgnoreCase("dd/mm/yyyy"))){
		populated=false;
	}
    return populated;        
}
	
/**
 * Add no of days to a given date, pass negative number if past or to substract date.
 * @param date Date from which addtion or subtraction to be done.
 * @param noOfDays number of days to be added or substracted.
 * @return Date after addition or subtraction.
 */
	public static Date addDate(Date date, int noOfDays){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.DATE,noOfDays);
		return c1.getTime();
	}
	
  /**
  * Add no of weeks to a given date, pass negative number if past or to substract date.
  * @param date Date from which addtion or subtraction to be done.
  * @param noOfWeeks number of days to be added or substracted.
  * @return Date after addition or subtraction.
  */
	public static Date addWeeksToDate(Date date, int noOfWeeks){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.WEEK_OF_YEAR ,noOfWeeks);
		return c1.getTime();
	}
	
	/**
	 * Find out the distance between two dates
	 * @param d1 From date
	 * @param d2 To Date
	 * @return no of days difference between two days.
	 */
	public static long daysBetween2Dates(Date d1, Date d2){
		Calendar c1 = Calendar.getInstance(); 	//new GregorianCalendar();
		Calendar c2 = Calendar.getInstance(); 	//new GregorianCalendar();
	    c1.setTime(d1); 
	    c2.setTime(d2); 
	    return (c2.getTime().getTime() - c1.getTime().getTime()) / (24*3600*1000);
	   	}
	/**
	 * To find no of days available for a given date
	 * @param date  Date for which to indentify the no of days of the month.
	 * @return No of days in a month.
	 */
	public static int daysInMonth(Date date) {
		Calendar c1 = Calendar.getInstance(); 	//new GregorianCalendar();
	    c1.setTime(date); 
	    int year = c1.get(Calendar.YEAR);

		int [] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
		daysInMonths[1] += Util.isLeapYear(year) ? 1 : 0;
		return daysInMonths[c1.get(Calendar.MONTH)];
	}
	
	/**
	 * To Find the day of the week 
	 * @param d1 the date in question
	 * @return the day in number depending startin from 1 as Sunday
	 */
	public static String getDayofTheDate(Date d1) {
		
		String day = null;
	    DateFormat f = new SimpleDateFormat("EEE");
	    try {
	     day = f.format(d1);
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	    return day;
	}
	
	/**
	 * Compare two days and returns -1 if d1 is less than d2 
	 * 0 if both are equal, 1 if d1 is greater than d2
	 * @param d1 First date to compare
	 * @param d2 Second date to compre
	 * @return -1 if d1<d2, 0 if d1==d2, 1 if d1>d2
	 */
	public static int compare2Dates(Date d1, Date d2){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		if(c1.before(c2)){
			return -1;
		}
		else if(c1.after(c2)){
			return 1;
		}
		return 0;
	}
	
	/**
	 * Returns true if the given date is BEFORE today.
	 * NOTE: the TIME is irrelevent in this comparison. It the actual
	 * DATE that is compared
	 * @param date The date to compare
	 * @return true if date < today, else false
	 */
	public static boolean isDateInPast(Date date){	    		   
	    	
	    Calendar c1 = (GregorianCalendar)Calendar.getInstance();
	    c1.setTime( date );
	    c1.set(Calendar.HOUR_OF_DAY, 0 );
	    c1.set(Calendar.MINUTE, 0);	
	    c1.set(Calendar.SECOND, 0 );
	    c1.set(Calendar.MILLISECOND, 0);	
	    
	    Calendar today = Calendar.getInstance();
	    today.set(Calendar.HOUR_OF_DAY, 0);	
	    today.set(Calendar.MINUTE, 0);	
	    today.set(Calendar.SECOND, 0 );
	    today.set(Calendar.MILLISECOND, 0);	
		
		return c1.before( today );
		
	}
	
	/**
	 * Returns true if the given date is AFTER today.
	 * NOTE: the TIME is irrelevent in this comparison. It the actual
	 * DATE that is compared
	 * @param date The date to compare
	 * @return true if date > today, else false
	 */
	public static boolean isDateInFuture(Date date){
	    
	    Calendar c1 = (GregorianCalendar)Calendar.getInstance();
	    c1.setTime( date );
	    c1.set(Calendar.HOUR_OF_DAY, 0 );
	    c1.set(Calendar.MINUTE, 0);	
	    c1.set(Calendar.SECOND, 0 );
	    c1.set(Calendar.MILLISECOND, 0);	
	    
	    Calendar today = Calendar.getInstance();
	    today.set(Calendar.HOUR_OF_DAY, 0);	
	    today.set(Calendar.MINUTE, 0);	
	    today.set(Calendar.SECOND, 0 );
	    today.set(Calendar.MILLISECOND, 0);	
		
		return c1.after( today );
		
	}
	
	/**
	 * Returns true if the given date is the same as today.
	 * NOTE: the TIME is irrelevent in this comparison. It the actual
	 * DATE that is compared
	 * @param date The date to compare
	 * @return true if date == today, else false
	 */
	public static boolean isDateToday(Date date){
	    
	    Calendar c1 = (GregorianCalendar)Calendar.getInstance();
	    c1.setTime( date );
	    c1.set(Calendar.HOUR_OF_DAY, 0 );
	    c1.set(Calendar.MINUTE, 0);	
	    c1.set(Calendar.SECOND, 0 );
	    c1.set(Calendar.MILLISECOND, 0);	
	    
	    Calendar today = Calendar.getInstance();
	    today.set(Calendar.HOUR_OF_DAY, 0);	
	    today.set(Calendar.MINUTE, 0);	
	    today.set(Calendar.SECOND, 0 );
	    today.set(Calendar.MILLISECOND, 0);	
		
		return c1.equals( today );
		
	}
	
	/**
	 * Returns true if the 2 dates are the same, regardless of the time
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDate( Date date1, Date date2 ){
	    if( date1 == null || date2 == null ){
	        return false;
	    }
	    Calendar c1 = (GregorianCalendar)Calendar.getInstance();
	    c1.setTime( date1 );
	    c1.set(Calendar.HOUR_OF_DAY, 0 );
	    c1.set(Calendar.MINUTE, 0);	
	    c1.set(Calendar.SECOND, 0 );
	    c1.set(Calendar.MILLISECOND, 0);
	    
	    Calendar c2 = (GregorianCalendar)Calendar.getInstance();
	    c2.setTime( date2 );
	    c2.set(Calendar.HOUR_OF_DAY, 0 );
	    c2.set(Calendar.MINUTE, 0);	
	    c2.set(Calendar.SECOND, 0 );
	    c2.set(Calendar.MILLISECOND, 0);
	    
	    return c1.equals( c2 );
	}
	
	/**
	 * Find the date of the starting week of a given date
	 * @param date The date in question
	 * @return the starting week date of a give date.
	 */
			
	public static Date beginDateOfWeek(Date date){
		int firstDayOfWeek,dayOfWeek=0;;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		firstDayOfWeek=1;
		dayOfWeek=c1.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek!=firstDayOfWeek){
			int diff=firstDayOfWeek-dayOfWeek;
			date=addDate(date,diff);
		}
		return date;
	}
	
	/**
	 * Findouts the whether the give year is fall on the leapyear or not 
	 * @param year 
	 * @return true if leap year false otherwise
	 */
	public static boolean isLeapYear(int year){
		 GregorianCalendar gcal = new GregorianCalendar();
		 return gcal.isLeapYear(year);
	}

	/**
	 * Find out the day of a give date 1 for Sunday (depends on the locale settings) 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		return c1.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * Formats a give date with the supplied format
	 * @param unformattedDate the date to be formated
	 * @param format the format to be formated like dd/mm/yyyy or mm/dd/yy etc 
	 * refer SimpleDateFormat for supported format. 
	 * @return
	 */
public static String formatDate(Date unformattedDate,String format) {
    String formattedDate = "";

    try {
        DateFormat df = DateFormat.getInstance();
        SimpleDateFormat sf = (SimpleDateFormat) df;
        sf.applyPattern(format);
        formattedDate = df.format(unformattedDate);

    } catch (RuntimeException e) {
        //LOG.log(Level.WARNING, "Error Formatting Date:" + unformattedDate);
    }
    return formattedDate;
}

public static Date getDate(String strDate, String format){
	Date newDate=null;
	 try {
	    	DateFormat df = DateFormat.getInstance();
	        SimpleDateFormat sf = (SimpleDateFormat) df;
	        sf.applyPattern(format);
	        newDate = df.parse(strDate);
	        } catch (ParseException e) {
	        	e.printStackTrace();
//	 	LOG.log(Level.WARNING, "Error Formatting Date:" + unformattedDate);
	 }
	return newDate;
}


/**
 * Returns true if the 2 given values are not equal
 * @param oldValue
 * @param newValue
 * @return
 */
	public static boolean hasChanged( String oldValue, String newValue ){
	    if( oldValue !=  null && oldValue.trim().length() == 0 ){
	        oldValue = null;
	    }
	    if( newValue !=  null && newValue.trim().length() == 0 ){
	        newValue = null;
	    }
		
		if(oldValue == null && newValue == null){
			return false;
		}
		if( (oldValue == null && newValue != null)
			|| (oldValue != null && newValue == null)
			|| ! oldValue.trim().equals( newValue.trim() ) ){
			return true;
		}
		return false;
	}
	/**
 * Returns new Date incrementing given hours with the date passed
 * @param date date to increment
 * @param hours hours to increment.
 * @return
 */
	public static Date addHours(Date date,int hours){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.HOUR,hours);
		return c1.getTime();
	}
	
	/**
 * Returns new Date incrementing given minutes with the date passed
 * @param date date to increment
 * @param minutes minutes to increment.
 * @return
 */
	public static Date addMinutes(Date date,int minutes){

		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.MINUTE,minutes);
		return c1.getTime();
	}
	
	/**
	 * Convert a String array to a comma seperated list 
	 */
	public static String convertToString(String[] list){
	    if( list == null || list.length == 0 ){
        return null;
    }
    StringBuffer buffer = new StringBuffer();
    for( int i=0; i < list.length; i++ ){
        if( list[i]!=null && ! list[ i ] .trim().equals( "" )){
            buffer.append( list[ i ] + ", ");
        }
    }
    String convertedString = buffer.toString().trim();
    if( convertedString.endsWith( ",") ){
        convertedString = convertedString.substring( 0, convertedString.lastIndexOf( "," ) );
    }
   
    return  convertedString;
	}
	
	/**
	 * Convert a Collection of String objects to a comma seperated list 
	 */
	public static String convertToString(Collection list){
	    if( list == null || list.isEmpty() ){
        return null;
    }
    StringBuffer buffer = new StringBuffer();
    Iterator i = list.iterator();
    while( i.hasNext() ){
        String element = (String) i.next();
        if( element != null && ! element.equals( "" )){
            buffer.append( element + ", ");
        }
    }
    String convertedString = buffer.toString().trim();
    if( convertedString.endsWith( ",") ){
        convertedString = convertedString.substring( 0, convertedString.lastIndexOf( "," ) );
    }
   
    return  convertedString;
	}
	
	
	/**
	 * Take a comma seperated list and convert to a String array
	 */
	public static String[] convertToStringArray(String list){
		
		ArrayList<String> names = new ArrayList<String>();
		StringTokenizer tokens = new StringTokenizer(list, ",");
		while (tokens.hasMoreTokens()){
			String token = tokens.nextToken();
			String name = token.toUpperCase().trim();
			if (name.length()>0){
				names.add(name);
			}
		}		
		// convert ArrayList to string array
		String[] result = new String[names.size()];
		Iterator it = names.iterator();
		int x= 0;
		while (it.hasNext()){
			result[x++] = (String)it.next();
		}
		return result;
	}
	
	/**
 * Format a date in the following format 
	 * 2007-09-27 23:59:00
 * @param passoutDate
 * @return Timestamp
 * @throws FordResourceException
 */
public static Timestamp formatDateWithCinderellaTime(Date date){   

    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.setTime(date);
    gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY, 23);
    gregorianCalendar.set(GregorianCalendar.MINUTE, 59);
    gregorianCalendar.set(GregorianCalendar.SECOND, 0);
    Date formattedPassoutDate = gregorianCalendar.getTime();	
    
    return new Timestamp(formattedPassoutDate.getTime());
}

public static Timestamp formatDateWithCinderellaTime(String date){   
    try{
        return formatDateWithCinderellaTime( convertToDate( date ));
    }
    catch( ParseException pe ){
        return null;
    }
}

/**
 * @param startDelegation
 * @param endDelegation
 * @return
 */
public static boolean todayOnOrBetween(Date startDate, Date endDate) {

    Date today = new Date();
    if( ( isSameDate( today, startDate ) || today.after( startDate )
            && ( isSameDate( today, endDate ) || today.before( endDate) ))){
        return true;
    }
    return false;
}

/**
 * Return true if the given date is the next working day after today 
 * (doesn't know about bank holidays)
 * 
 * @param startDate
 * @return
 */
public static Date getNextWorkingDay() {
    
    GregorianCalendar today = new GregorianCalendar();        
    int daysToAdd = 0;
    int day = today.get( Calendar.DAY_OF_WEEK );  // Sunday == 1
    if( day < 6 ){ // Sunday to Thursday
        daysToAdd = 1; 
    }
    else{
        daysToAdd = 9 - day;
    }
    GregorianCalendar nextWorkingDay = new GregorianCalendar();
    nextWorkingDay.set( Calendar.DATE, today.get(Calendar.DATE) + daysToAdd );
    
    return nextWorkingDay.getTime();
}

/**
 * @param vehicleIds
 * @return
 */
public static String convertToParams(Collection list) {
	    if( list == null || list.isEmpty() ){
        return null;
    }
    StringBuffer buffer = new StringBuffer();
    Iterator i = list.iterator();
    while( i.hasNext() ){
        String element = (String) i.next();
        if( element != null && ! element.equals( "" )){
            buffer.append( "'"+ element + "', ");
        }
    }
    String convertedString = buffer.toString().trim();
    if( convertedString.endsWith( ",") ){
        convertedString = convertedString.substring( 0, convertedString.lastIndexOf( "," ) );
    }
   
    return  convertedString;
	}

public static String getDisplayDate( Date date ){
	if( date != null ){
			return Util.convertDateToString( date );
		}
		return( "dd/mm/yyyy" );
}

	/**
	 * If stability = AAA
	 * if( (currentCalInterval  + 3) < maxCalInterval) return currentCalInterval  + 3
	 * else return maxCalInterval
	 * 	
	 * 	else if stability == ( BBB ) || stability == ( CCC )
	 * if( (currentCalInterval - 3) > minCalInterval return currentCalInterval - 3
	 * else return minCalInterval
	 * 	
	  * else if stability.endsWith( A ) || stability.endsWith( BB ) || stability.endsWith( CC )
	* return currentCalInterval
		
	 * else
	 * return initialCalInterval
	 */
	public static int calculateRevisedCalInterval( int initialCalInterval,
												int revisedCalInterval,
												String stability,
												int minCalInterval,
												int maxCalInterval
												){
		
	    int newCalInterval = revisedCalInterval;
		if( stability != null ){
			if( stability.equals( "AAA" )){
			   if( maxCalInterval != 0 
					   && ((revisedCalInterval  + 3) < maxCalInterval)){
				   newCalInterval =  revisedCalInterval  + 3;
			   }
			   else{
				   newCalInterval = maxCalInterval;
			   }
			} 	
			else if( stability.equals( "BBB" ) || stability.equals( "CCC" )){
			   if( (revisedCalInterval - 3) > minCalInterval ){
				   newCalInterval = revisedCalInterval - 3;
			   }
			   else{
				   newCalInterval = minCalInterval;
			   }
			}
			else if( stability.endsWith( "A" ) 
					|| stability.endsWith( "BB" ) 
					|| stability.endsWith( "CC" ) ){
				newCalInterval = revisedCalInterval;
			}
			else{
				newCalInterval = initialCalInterval;
			}			
		}
		return newCalInterval ;		
	}
	
}


