package main.java.Filters;

import java.util.ArrayList;

import main.java.Tools.ReaderFromCsv;
import main.java.WifiData.ListOfWifiRows;
import main.java.WifiData.RowOfWifiPoints;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ListOfWifiRows list=ReaderFromCsv.readerFromMergedCSVtoList("C:\\Users\\Olga\\Desktop\\a.csv");
		ListOfWifiRows ans=list.copy();
		String start = "2017-11-08 13:30:06";
		String end = "2017-11-08 14:06:24";
		filter tf = new Time_Filter(start, end);
		//filter _f=new Not_Filter(tf);
		filter id=new Id_Filter("MRA58K");
		filter _and=new And_Filter(tf, id);
		ans=list.copy();
		filter _mac=new Mac_Filter("00:02:6f:91:c3:db");
		ans.filter(_mac);
		
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i).toString());
		}
		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	
	}

}
