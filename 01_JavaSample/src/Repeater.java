
public class Repeater {
	String callsign;
	double RX, TX;
	
	public Repeater(String callsign, double d, double e) {
		this.callsign = callsign;
		this.RX = d;
		this.TX = e;
	}
	
	public boolean equals(Repeater rep) {
		return this.callsign == rep.callsign && this.RX == rep.RX && this.TX == rep.TX;
	}
}
