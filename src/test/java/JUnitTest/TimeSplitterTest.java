/**
 * 
 */
package test.java.JUnitTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
//import org.junit.Test;

import org.junit.Test;

import main.java.WifiData.Date;;

/**
 * @author Olga
 *
 */
public class TimeSplitterTest {


	@Test
	public void test() {
		
		/******************* correct ******************/
		String time="2017-11-08 13:40:29";
		Date test=new Date(time);
		assertEquals("2017-11-08 13:40:29", test.toString());
		assertEquals(0, test.getAccepted());
		
		
	}

}
