
public class Frequncy {
	protected int frequncy;
	
	Frequncy(String frequncy){
	this.frequncy=Integer.parseInt(frequncy);
}
public int getFrequncy() {return frequncy;}

@Override
public String toString(){
	return ""+frequncy;
}
}
