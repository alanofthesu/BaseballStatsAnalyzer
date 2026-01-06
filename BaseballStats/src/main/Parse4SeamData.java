package main;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Parse4SeamData {

    private ArrayList<String> firstNames = new ArrayList<>();
    private ArrayList<String> lastNames = new ArrayList<>();
    private ArrayList<Double> velocities = new ArrayList<>();
    private ArrayList<Double> spinRates = new ArrayList<>();
    private ArrayList<Double> hbs = new ArrayList<>();
    private ArrayList<Double> ivbs = new ArrayList<>();
    private ArrayList<Double> stuff = new ArrayList<>();

    private int minPercentDiffIndex = 0;

    private double finalRawVeloDiff;
    private double finalRawSpinDiff;
    private double finalRawHBDiff;
    private double finalRawIVBDiff;

    public Parse4SeamData(String fourSeamCSV, String stuffCSV) {

        // ---------- Load 4-seam fastball data ----------
        InputStream fourSeamStream = getClass()
                .getClassLoader()
                .getResourceAsStream(fourSeamCSV);

        if (fourSeamStream == null) {
            throw new RuntimeException("CSV not found in resources: " + fourSeamCSV);
        }

        Scanner sc = new Scanner(fourSeamStream);
        sc.nextLine(); // skip header

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] columns = line.split(",");

            firstNames.add(columns[1].substring(0, columns[1].length() - 1));
            lastNames.add(columns[0].substring(1));
            velocities.add(Double.parseDouble(columns[5]));
            spinRates.add(Double.parseDouble(columns[6]));
            hbs.add(Double.parseDouble(columns[7]));
            ivbs.add(Double.parseDouble(columns[8]));
        }
        sc.close();

        // ---------- Load Stuff+ CSV ----------
        InputStream stuffStream = getClass()
                .getClassLoader()
                .getResourceAsStream(stuffCSV);

        if (stuffStream == null) {
            throw new RuntimeException("CSV not found in resources: " + stuffCSV);
        }

        sc = new Scanner(stuffStream);
        sc.nextLine(); // skip header

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] columns = line.split(",");
            stuff.add(Double.parseDouble(columns[3]));
        }
        sc.close();

        // ---------- Safety check ----------
        if (firstNames.isEmpty() || stuff.isEmpty()) {
            throw new IllegalStateException("CSV data failed to load correctly");
        }
    }

    public String[] findClosestMatch(double velocity, double spinRate, double hb, double ivb) {

        double minTotalDiff = Double.MAX_VALUE;

        for (int i = 0; i < firstNames.size(); i++) {

            double velocityPercentDiff = Math.abs((velocity - velocities.get(i)) / velocities.get(i));
            double spinRatePercentDiff = Math.abs((spinRate - spinRates.get(i)) / spinRates.get(i));
            double hbPercentDiff = Math.abs((hb - hbs.get(i)) / hbs.get(i));
            double ivbPercentDiff = Math.abs((ivb - ivbs.get(i)) / ivbs.get(i));

            double totalDiff = velocityPercentDiff
                             + spinRatePercentDiff
                             + hbPercentDiff
                             + ivbPercentDiff;

            if (totalDiff < minTotalDiff) {
                minTotalDiff = totalDiff;
                minPercentDiffIndex = i;

                finalRawVeloDiff = Math.round((velocity - velocities.get(i)) * 10.0) / 10.0;
                finalRawSpinDiff = Math.round((spinRate - spinRates.get(i)) * 10.0) / 10.0;
                finalRawHBDiff   = Math.round((hb - hbs.get(i)) * 10.0) / 10.0;
                finalRawIVBDiff  = Math.round((ivb - ivbs.get(i)) * 10.0) / 10.0;
            }
        }

        return new String[] {
                firstNames.get(minPercentDiffIndex),
                lastNames.get(minPercentDiffIndex)
        };
    }

    public double getStuff() {
        return stuff.get(minPercentDiffIndex);
    }

    public double getFinalRawVeloDiff() {
        return finalRawVeloDiff;
    }

    public double getFinalRawSpinDiff() {
        return finalRawSpinDiff;
    }

    public double getFinalRawHBDiff() {
        return finalRawHBDiff;
    }

    public double getFinalRawIVBDiff() {
        return finalRawIVBDiff;
    }
}
