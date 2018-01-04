package WifiData;

/**
 * This class represents the simplest wifi signal
 * @author Olga & Dan
 *
 */
public class Signal {
	public double signal;
	
Signal(String signal){
	this.signal=Double.parseDouble(signal);
}


/*************getters&setters***************/
public void setSignal(double signal) {this.signal = signal;}
public double getSignal() {return signal;}


/*********************** Signal manipulations*************************/
/**
 * Function to check which Signal more powerful
 * @param a Second signal for check up
 * @return true if this signal more powerful then a
 */
public boolean morePowerful(Signal a){
	if(this.signal>=a.getSignal())return true;
	else return false;
}

/**
 * Function to check if two signals are equals
 * @param a Second signal for check up
 * @return true if they equals
 */

public boolean equalSignal(Signal a){
	if(this.signal==a.getSignal())return true;
	else return false;
}

/**
 * Calculate signal weight
 * @return signal weight
 */
public double weightOfSignal(){
	return (double)1/(Math.pow(this.signal, 2));
}

@Override
public String toString(){
	return ""+signal;
}

}
