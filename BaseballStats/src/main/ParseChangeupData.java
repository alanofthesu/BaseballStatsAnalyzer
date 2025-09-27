package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseChangeupData {
	
private ArrayList<String> firstNames = new ArrayList<>();
private ArrayList<String> lastNames = new ArrayList<>();
private ArrayList<Double> velocities = new ArrayList<>();
private ArrayList<Double> spinRates = new ArrayList<>();
private ArrayList<Double> hbs = new ArrayList<>();
private ArrayList<Double> ivbs = new ArrayList<>();

private ArrayList<Double> stuff = new ArrayList<>();
int minPercentdiffIndx = 0;


public ParseChangeupData(String changeup, String ch) {
	
	//scan through 4seam file
	try {
		Scanner sc = new Scanner(new File(changeup));
		sc.nextLine();

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] columns = line.split(","); 
			firstNames.add(columns[1].substring(0,columns[1].length()-1));
			lastNames.add(columns[0].substring(1));
			velocities.add(Double.parseDouble(columns[5]));
			spinRates.add(Double.parseDouble(columns[6]));
			hbs.add(Double.parseDouble(columns[7]));
			ivbs.add(Double.parseDouble(columns[8]));
			
		}
		sc.close();
	} catch (FileNotFoundException e) {
    System.out.println("CSV file not found!");
    e.printStackTrace();
	}
	
	//scan through stuff pitcher list and add to array
	try {
		Scanner sc = new Scanner(new File(ch));
		sc.nextLine();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] columns = line.split(","); 
			stuff.add(Double.parseDouble(columns[3]));
			
		}
		sc.close();
	} catch (FileNotFoundException e) {
    System.out.println("CSV file not found!");
    e.printStackTrace();
	}

}

public String[] findClosestMatch(double velocity, double spinRate, double hb, double ivb){
	
	double minTotalDiff = Double.MAX_VALUE;
	
	try {
		for(int i = 0; i < firstNames.size(); i++) {
			double velocityPercentDiff = Math.abs((velocity - velocities.get(i))/velocities.get(i));
			double spinRatePercentDiff = Math.abs((spinRate - spinRates.get(i))/spinRates.get(i));
			double hbPercentDiff = Math.abs((hb - hbs.get(i))/hbs.get(i));
			double ivbPercentDiff = Math.abs((ivb - ivbs.get(i))/ivbs.get(i));

			double totalDiff = velocityPercentDiff + spinRatePercentDiff + hbPercentDiff + ivbPercentDiff;
			if(totalDiff < minTotalDiff) {
				minTotalDiff = totalDiff;
				minPercentdiffIndx = i;
			}
		}
	} catch (Exception e) {
        System.out.println("nice butt");
		e.printStackTrace();
	}
	
	return new String[] {firstNames.get(minPercentdiffIndx),lastNames.get(minPercentdiffIndx)};
}

public double getStuff() {
	return stuff.get(minPercentdiffIndx);
}


}
