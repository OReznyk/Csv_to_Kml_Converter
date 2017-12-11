
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * This Class represents Time Splitter that takes String date and split it to date and time java constructors
 * 
 * @author Olga Reznyk
 *
 */
public class Date {

	LocalTime time;
	LocalDate date;
	int accepted;

  Date(String dateToSplit) throws DateTimeParseException{		
		String[]helper=dateToSplit.split(" ",2);
		try{
		this.date=LocalDate.parse(helper[0]);
		this.time=LocalTime.parse(helper[1]);
		accepted=0;
		}
			catch(DateTimeParseException e) {
				e.printStackTrace();
				accepted=-1;
			}	
	}
  public String getDateAtTimeAsString() {	return date+" "+time;}

	/**
	 * @return time in localTime format
	 */
	public LocalTime getTime() {	return time;}

	/**
	 * @return date in localDate format
	 */
	public LocalDate getDate() {return date;}
	
	/**
	 * @return 0 if string was in correct date/time format
	 * @return -1 if string was in incorrect date/time format
	 */
	public int getAccepted() {return accepted;}
	
    public boolean betweenDates(Date startDate,Date stopDate){
    	if(this.date.atTime(this.time).isAfter(startDate.date.atTime(startDate.getTime()))){
        	if(this.date.atTime(this.time).isBefore(stopDate.date.atTime(stopDate.getTime())))return true; 		
    	}	return false;
    }
	
 	public boolean sameDate(Date b){
 		if(this.date.atTime(this.time).isEqual(b.date.atTime(b.getTime())))return true;
 		return false;
 	}
}