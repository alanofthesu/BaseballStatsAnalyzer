package main;
import java.util.Arrays;
import java.util.Scanner;


public class MainCode {

	
	public static void main(String[] args) {
		
		Scanner console = new Scanner(System.in);
		System.out.println("pitch type (4seam, sinker, cutter, splitter, slider, curveball, changeup): ");
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
			Parse4SeamData data = new Parse4SeamData("fastball metrics BEST.csv","fangraphs stuff all - fangraphs stuff all.csv");
	    	System.out.print("closest pitcher match for 4seam fastball: ");
			System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
			System.out.println("stuff value: " + data.getStuff());
		}
		else if (pitchType.equals("changeup")) {
			ParseChangeupData data = new ParseChangeupData("changeup metrics BEST.csv","fangraphs stuff all - fangraphs stuff all.csv");
	    	System.out.print("closest pitcher match for changeup: ");
			System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
			System.out.println("stuff value: " + data.getStuff());
		
		}
		else if (pitchType.equals("sinker")) {
			ParseSinkerData data = new ParseSinkerData("12_21 pitch data 5 measurements - sinker.csv","fangraphs stuff all - fangraphs stuff all.csv");
	    	System.out.print("closest pitcher match for sinker: ");
			System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
			System.out.println("stuff value: " + data.getStuff());
		
		}
		else if (pitchType.equals("cutter")) {
			ParseCutterData data = new ParseCutterData("12_21 pitch data 5 measurements - cutter.csv","fangraphs stuff all - fangraphs stuff all.csv");
	    	System.out.print("closest pitcher match for cutter: ");
			System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
			System.out.println("stuff value: " + data.getStuff());
		
		}
		else if (pitchType.equals("splitter")) {
			ParseSplitterData data = new ParseSplitterData("12_21 pitch data 5 measurements - splitter.csv","fangraphs stuff all - fangraphs stuff all.csv");
	    	System.out.print("closest pitcher match for splitter: ");
			System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
			System.out.println("stuff value: " + data.getStuff());
		
		}
		else if (pitchType.equals("slider")) {
			ParseSliderData data = new ParseSliderData("12_21 pitch data 5 measurements - slider.csv","fangraphs stuff all - fangraphs stuff all.csv");
	    	System.out.print("closest pitcher match for slider: ");
			System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
			System.out.println("stuff value: " + data.getStuff());
		
		}
		else if (pitchType.equals("curveball")) {
			ParseCurveballData data = new ParseCurveballData("12_21 pitch data 5 measurements - curveball.csv","fangraphs stuff all - fangraphs stuff all.csv");
	    	System.out.print("closest pitcher match for curveball: ");
			System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
			System.out.println("stuff value: " + data.getStuff());
		
		}
		else
		{
			System.out.println("hello this is chriustian");
		}
		}
	
	

}