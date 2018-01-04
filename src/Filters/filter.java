package Filters;
import java.io.Serializable;

import WifiData.*;


	/**
	 * This interface represents a simple boolean filter
	 * @author Boaz
	 *
	 */
	public interface filter extends Serializable{
		/**
		 * test if the Record f is pass the filter 
		 * @param rec
		 * @return true iff: the record pass the filter, else returns false 
		 */
		public boolean test(RowOfWifiPoints f);
	}

