package WifiData;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import Filters.filter;

public class ListOfWifiRows extends ArrayList<RowOfWifiPoints> {

	public ListOfWifiRows() {
		super();
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

	
	public void Print() {
		Iterator<RowOfWifiPoints> is = this.iterator();
		while (is.hasNext()) {
			System.out.println(is.next().toString());
		}
	}
	
	
}
