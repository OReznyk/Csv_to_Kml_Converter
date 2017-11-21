package Ex0;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This Class represents Time Splitter that takes String date and split it to date and time java constructors
 * 
 * @author Olga Reznyk
 *
 */
public class TimeSplitterFromStringToInt {

	LocalTime time;
	LocalDate date;

	public TimeSplitterFromStringToInt(String timeToSplit){
		String[]helper=timeToSplit.split(" ",2);
		this.date=LocalDate.parse(helper[0]);
		this.time=LocalTime.parse(helper[1]);
	}

	/**
	 * @return time in localTime format
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * @return date in localDate format
	 */
	public LocalDate getDate() {
		return date;
	}

}