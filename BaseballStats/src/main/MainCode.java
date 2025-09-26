package main;
import java.util.Arrays;
import java.util.Scanner;


public class MainCode {

	
	public static void main(String[] args) {
		
		Scanner console = new Scanner(System.in);
		System.out.println("pitch type: ");
		String pitchType = console.nextLine();
		System.out.println("velocity: ");
		Double velocity = Double.parseDouble(console.nextLine());
		System.out.println("spin rate: ");
		Double spinRate = Double.parseDouble(console.nextLine());
		System.out.println("h-break: ");
		Double hb = Double.parseDouble(console.nextLine());
		System.out.println("ivb: ");
		Double ivb = Double.parseDouble(console.nextLine());
		
		System.out.println("velocity: " + velocity + "\nspin rate: " + spinRate + "\nh-break: " + hb + "\nivb: "+ivb);
		console.close();
		
		if(pitchType.equals("4seam")) {
			Parse4SeamData data = new Parse4SeamData("fastball metrics BEST.csv","stuff values all BEST.csv");
	    	System.out.print("closest pitcher match for 4seam fastball: ");
			System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
			System.out.println("stuff value: " + data.getStuff());
		}
		else if (pitchType.equals("changeup")) {
			ParseChangeupData data = new ParseChangeupData("changeup metrics BEST.csv","stuff values all BEST.csv");
	    	System.out.print("closest pitcher match for changeup: ");
			System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
			System.out.println("stuff value: " + data.getStuff());
		
		}
		else
		{
			System.out.println("hello this is alan");
		}
		}
	
	

}