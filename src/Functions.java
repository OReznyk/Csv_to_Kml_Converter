
import java.util.ArrayList;
import java.util.List;
/**
 * Help Functions. It's not completed . Most of this funtions we'll  remove one day
 * @author Olga & Dan
 *
 */
public	  class Functions {

	
	/**
	 * increases the matrix's size by adding rows
	 * @param matrix of strings from csv file
	 * @return	bigger matrix
	 */
	public	 static String[][] reBuild(String [][] matrix){ 
		int moreRows=20;
		String[][]temp=new String[matrix.length+moreRows][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				temp[i][j]=matrix[i][j];
			}
		}	

		return temp;
	}


	/**
	 * adds data to a matrix from an string array
	 * @param matrix matrix to put data un
	 * @param arr string array
	 * @param rowIndexInMatrix row in matrix to put data in
	 * @return the matrix with the data
	 */
	public	 static String[][] putAllDataFromStringArrToRowInMatrix(String [][] matrix, String [] arr, int rowIndexInMatrix ){

		if(rowIndexInMatrix+1==matrix.length){
			matrix=Functions.reBuild(matrix);
		} 
			for (int j = 0; j < arr.length; j++) {
			    matrix[rowIndexInMatrix][j]=arr[j];
			
		}
		return matrix;	
	}

	/**
	 * prints the matrix
	 * @param matrix
	 */
	public	 	 static void toPrint(String[][] matrix){
		int i=0;
		while(i<matrix.length) {
			for (int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j]!=null ){
					System.out.print(matrix[i][j]+"    ");
				}else break;
			}
			i++;
			System.out.println();
		}
	}

//	public static void filterALfromSameWifiPoints(ArrayList<RowOfTenWifiPoints>listToPrint){
//		int indexOfWifi=0,indexOfRow=0,indexOfWifiToSearchIn=0;
//		while(indexOfRow<listToPrint.size()){
//			while(indexOfWifi<listToPrint.get(indexOfRow).wifiList.size()){			
//				int indexOfRowToSerchIn=indexOfRow;
//				while(indexOfRowToSerchIn<listToPrint.size()){
//					while(indexOfWifiToSearchIn<listToPrint.get(indexOfRowToSerchIn).wifiList.size()){
//					if(listToPrint.get(indexOfRow).wifiList.get(indexOfWifi).sameMAC(listToPrint.get(indexOfRowToSerchIn).wifiList.get(indexOfWifiToSearchIn))){
//						if(listToPrint.get(indexOfRow).wifiList.get(indexOfWifi).signal.morePowerful(listToPrint.get(indexOfRowToSerchIn).wifiList.get(indexOfWifiToSearchIn).signal)){
//							listToPrint.get(indexOfRowToSerchIn).wifiList.remove(indexOfWifiToSearchIn);
//						}
//						else{
//							listToPrint.get(indexOfRow).wifiList.remove(indexOfWifi);
//							indexOfRow=indexOfRowToSerchIn;
//							indexOfWifi=indexOfWifiToSearchIn;
//							
//						}
//					}
//				}
//			}
//			}
//			}
//	}



	/**
	 * adds the strongest wifi networks to a list of map points
	 * @param matrix
	 * @return list
	 */
	public	 	static List<MapPoint> separator(String[][] matrix){
		String[][]temp=new String[matrix.length][10];
		int i=0,j=6;
		int row=0, col=6;
		int rowTemp=0;

		if(matrix[0][0]==null){ 
			List<MapPoint> path=new ArrayList<MapPoint>();
			return path;
		}
		else if(matrix[1][0]==null && matrix[0][0]!=null){
			Coordinates_3D coord=new Coordinates_3D(matrix[i][2], matrix[i][3], matrix[i][4]);
			Date date=new Date( matrix[i][0]);
			Wifi wifi=new Wifi(matrix[i][6],matrix[i][7],matrix[i][8],matrix[i][9]);
			MapPoint a=new MapPoint(date,matrix[i][1],coord,wifi);
			List<MapPoint> path=new ArrayList<MapPoint>();
			path.add(a);
			return path;
		}

		while(row<matrix.length && matrix[row][0]!=null) {
			i=0;
			if(rowTemp==temp.length)temp=reBuild(temp);	
			while(i<6){
				temp[rowTemp][i]=matrix[row][i];
				i++;
			}

			j=6;
			while(j<10 && matrix[row][col]!=null){
				temp[rowTemp][j]=matrix[row][col];	
				j++;
				col++;
			}

			rowTemp++;
			if(col==matrix[0].length || (col<matrix[0].length && matrix[row][col+1]==null)){
				row++;
				col=6;
			}

		}
		toPrint(temp);
		System.out.println(temp.length);

		return chooser(temp);
	}


	/********************private methods************************/

	/**
	 * deletes the wifi point from matrix
	 * @param matrix
	 * @param row
	 * @return matrix
	 */
	private static String[][] deleteWifiPoint(String[][] matrix,int row){
		int i=0;
		while(i<matrix.length && matrix[i][0]!=null){
			i++;
		}

		for (int j = 0; j < 10; j++) {
			matrix[row][j]=matrix[i-1][j];
			matrix[i-1][j]=null;
		}
		return matrix; 
	}

	/**
	 * checks if the same network exists in the matrix (checks the mac addresses) and keeps the strongest network
	 * @param matrix
	 * @return list of map points
	 */
	private static List<MapPoint> chooser(String[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			int j=i+1;
			while(j<matrix.length && matrix[j][0]!=null){
				if(matrix[i][7].equals(matrix[j][7])){
					if(Double.parseDouble(matrix[i][9])<Double.parseDouble(matrix[j][9])){
						for (int k = 0; k < 10; k++) {
							matrix[i][k]=matrix[j][k];
						}
					}	
					matrix=deleteWifiPoint(matrix,j);
					if(matrix[i][7].equals(matrix[j][7])){
						j--;
					}
				}
				j++;

			}
		}
		toPrint(matrix);
		System.out.println(matrix.length);

		return arrToList(matrix);
	}

	/**
	 * converts the data from a matrix to a list of map points
	 * @param matrix
	 * @return
	 */
	private static List<MapPoint> arrToList(String[][] matrix){
		List<MapPoint> path=new ArrayList<MapPoint>();
		int i=0;
		while(matrix[i][0]!=null && i<matrix.length){
			Coordinates_3D coord=new Coordinates_3D(matrix[i][2], matrix[i][3], matrix[i][4]);
			Date date=new Date( matrix[i][0]);
			Wifi wifi=new Wifi(matrix[i][6],matrix[i][7],matrix[i][8],matrix[i][9]);
			MapPoint a=new MapPoint(date,matrix[i][1],coord,wifi);
			path.add(i,a);	
			i++;
		}
		return path;
	}


}






