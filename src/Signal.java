
public class Signal {
	protected double signal;
	
Signal(String signal){
	this.signal=Double.parseDouble(signal);
}
public double getSignal() {return signal;}

public boolean morePowerful(Signal a){
	if(this.signal>=a.getSignal())return true;
	else return false;
}

public boolean equals(Signal a){
	if(this.signal==a.getSignal())return true;
	else return false;
}
public double weight(){
	return (double)1/(Math.pow(this.signal, 2));
}
@Override
public String toString(){
	return ""+signal;
}

}
