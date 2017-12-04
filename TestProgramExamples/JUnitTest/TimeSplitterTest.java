/**
 * 
 */
package JUnitTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Test;

import Ex0.TimeSplitterFromStringToInt;

/**
 * @author Olga
 *
 */
public class TimeSplitterTest {


	@Test
	public void test() {
		
		/******************* correct ******************/
		String time="2017-11-08 13:40:29";
		TimeSplitterFromStringToInt test=new TimeSplitterFromStringToInt(time);
		assertEquals(LocalDate.parse("2017-11-08"), test.getDate());
		assertEquals(LocalTime.parse("13:40:29"), test.getTime());
		assertEquals(0, test.getAccepted());
		
		/**************** incorrect format ************/
		
		time="dgfgcvrea";
		test=new TimeSplitterFromStringToInt(time);
		assertEquals( -1, test.getAccepted());
		
		time="2017-11-0813:40:29"; 
		test=new TimeSplitterFromStringToInt(time);
		assertEquals( -1, test.getAccepted());
		
		time="2017:11:08 13-40-29"; 
		test=new TimeSplitterFromStringToInt(time);
		assertEquals( -1, test.getAccepted());
		
		
		/************** illegal date/time *************/
		
		time="2017-11-08 13:40:99"; 
		test=new TimeSplitterFromStringToInt(time);
		assertEquals( -1, test.getAccepted());
		
		time="0000-15-08 13:99:99"; 
		test=new TimeSplitterFromStringToInt(time);
		assertEquals( -1, test.getAccepted());

	}

}
