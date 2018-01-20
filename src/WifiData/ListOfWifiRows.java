package WifiData;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import Filters.Filters;
import Filters.filter;

public class ListOfWifiRows extends ArrayList<RowOfWifiPoints> {
public int numOfWifiInList;
public int numOfDifferentWifiInList;

	public ListOfWifiRows() {
		super();
		numOfDifferentWifiInList=0;
		numOfWifiInList=0;
	}
	
	public ListOfWifiRows copy() {
		ListOfWifiRows ans = new ListOfWifiRows();
		Iterator<RowOfWifiPoints> itr = this.iterator();
		while(itr.hasNext()) {
			ans.add(itr.next());
		}
		return ans;
	}
	
	public void filter(filter f){
		int i=0;
		while(i<this.size()) {
			RowOfWifiPoints rec = this.get(i);
			boolean ans = f.test(rec);
			if(ans) {i++;}
			else {
				this.remove(i);
			}
		}
	}
	
	public void save_to_csv(String file_name) {
		try {
			PrintWriter pw = new PrintWriter(file_name);
			StringBuilder sb = new StringBuilder();
			Iterator<RowOfWifiPoints> is = this.iterator();
			while (is.hasNext()) {
				sb.append(is.next().toString());
				if(is.hasNext()) {sb.append("\n");}
			}
			pw.print(sb);
			pw.close();
			System.out.println("File saved");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNumOfWifiInList() {
		int sum=0;
		for (int i = 0; i < this.size(); i++) {
			sum+=this.get(i).wifiList.size();
		}
		this.numOfWifiInList=sum;
		
		return numOfWifiInList;
	}

	public int getNumOfDifferentWifiInList() {
			if(this.isEmpty())return 0;
			ArrayList<String>addedMac=new ArrayList<String>();
			
			for (int i = 0; i <this.get(0).wifiList.size(); i++) {
				addedMac.add(this.get(0).wifiList.get(i).mac);
			}
			
			for (int row =1; row < this.size(); row++) { 
				for (int col = 0; col < this.get(row).wifiList.size(); col++) {
					int i=addedMac.size()-1;
					while(i>=0 && !this.get(row).wifiList.get(col).getMac().equals(addedMac.get(i))) i--;
					
					if(i<=0){
						addedMac.add(this.get(row).wifiList.get(col).getMac());
					}
				}
			}
			this.setNumOfDifferentWifiInList(addedMac.size());
		
		return this.numOfDifferentWifiInList;
	}

	public void setNumOfDifferentWifiInList(int numOfDifferentWifiInList) {
		this.numOfDifferentWifiInList = numOfDifferentWifiInList;
	}

	public String properties(){
		if(this.isEmpty())return "Total wifi points: "+0+"  Different wifi points: "+0;
		return "Total wifi points: "+this.getNumOfWifiInList()+"  Different wifi points: "+this.getNumOfDifferentWifiInList();
	}

	
	public void Print() {
		Iterator<RowOfWifiPoints> is = this.iterator();
		while (is.hasNext()) {
			System.out.println(is.next().toString());
		}
	}
	
	
}
