package main;

public class PersonalStuffCalc {

	private double velocity;
	private double spinRate;
	private double hb;
	private double ivb;
	
	public PersonalStuffCalc(double velocity, double spinRate, double hb, double ivb) {
		 this.velocity = velocity;
	        this.spinRate = spinRate;
	        this.hb = hb;
	        this.ivb = ivb;
	}
	
	double FourVeloAvg = 93.91904762;
	double FourVeloStDev = 2.043709638;
	double FourSpinAvg = 2287.2;
	double FourSpinStDev = 122.8053024;
	double FourHBAvgRHP = -8.039473684;
	double FourHBStDevRHP = 2.859654009;
	double FourHBAvgLHP = 8.010344828;
	double FourHBStDevLHP = 3.837907245;
	double FourIVBAvg = 15.37333333;
	double FourIVBStDev = 2.330412091;
	
	double ChVeloAvg = 86.10340909;
	double ChVeloStDev = 3.261388494;
	double ChSpinAvg = 1758.193182;
	double ChSpinStDev = 280.3248878;
	double ChHBAvgRHP = -13.79516129;
	double ChHBStDevRHP =2.755943052;
	double ChHBAvgLHP =13.99615385;
	double ChHBStDevLHP =2.087578649;
	double ChIVBAvg =5.2875;
	double ChIVBStDev =3.715726519;
	
	public double CalculateStuff() {
		double ans = 0;
		double zVelo = (this.velocity - FourVeloAvg) / FourVeloStDev;
		double zSpin = (this.spinRate - FourSpinAvg) / FourSpinStDev;
		ans = 100 + zVelo + zSpin;
		
		
		return ans;
	}
	
}
