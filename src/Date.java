

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This Class represents Time Splitter that takes String date and split it to date and time java constructors
 * 
 * @author Olga & Dan
 *
 */

public class Date {
	LocalDateTime date;
	int accepted;

	Date(String dateToSplit) throws DateTimeParseException{	
		try{
			if(dateToSplit.contains("/")) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm a");
				this.date = LocalDateTime.parse(dateToSplit, formatter);
			}
			else if(dateToSplit.contains(".")){
				if(dateToSplit.length()==16){
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm");
					date = LocalDateTime.parse(dateToSplit, formatter);
					}
				else{
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
					date = LocalDateTime.parse(dateToSplit, formatter);
				}
				
			}
			else if(dateToSplit.contains("-")){
				if(dateToSplit.length()==16){
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					date = LocalDateTime.parse(dateToSplit, formatter);
				}
				else{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				date = LocalDateTime.parse(dateToSplit, formatter);
			}
			}
		}
		catch(DateTimeParseException e) {
			e.printStackTrace();
			accepted=-1;
		}	
	}


	/**
	 * @return date in localDate format
	 */
	public LocalDateTime getDate() {return date;}

	/**
	 * @return 0 if string was in correct date/time format
	 * @return -1 if string was in incorrect date/time format
	 */
	public int getAccepted() {return accepted;}



	/*********************** Date functions*************************/
	/**
	 * Function to check if this date is between two other dates
	 * @param startDate
	 * @param stopDate
	 * @return true if this date is between two other dates
	 */

	public boolean betweenDates(Date startDate,Date stopDate){
		if(this.date.isAfter(startDate.date)){
			if(this.date.isBefore(stopDate.date))return true; 		
		}	return false;
	}

	/**
	 * Function to check if this date is the same as other date
	 * @param b other date
	 * @return true if this date is the same as other date
	 */
	public boolean sameDate(Date b){
		if(this.date.isEqual(b.date))return true;
		return false;
	}
	@Override	
	public String toString(){
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date);
	}
}