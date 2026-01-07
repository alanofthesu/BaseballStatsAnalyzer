package main;

import java.util.Arrays;
import java.util.Scanner;

public class MainCode {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("Welcome to the Pitch Analyzer!");

        while (keepRunning) {
            System.out.println("\nEnter pitch type (4seam, sinker, cutter, splitter, slider, curveball, changeup)");
            System.out.println("Or type 'quit' to exit:");
            String pitchType = console.nextLine().trim();

            if (pitchType.equalsIgnoreCase("quit")) {
                keepRunning = false;
                break;
            }

            System.out.println("velocity: ");
            double velocity = Double.parseDouble(console.nextLine());

            System.out.println("spin rate: ");
            double spinRate = Double.parseDouble(console.nextLine());

            System.out.println("h-break (flip sign): ");
            double hb = Double.parseDouble(console.nextLine());

            System.out.println("ivb: ");
            double ivb = Double.parseDouble(console.nextLine());

            System.out.println("\nYour inputs:");
            System.out.println("velocity: " + velocity);
            System.out.println("spin rate: " + spinRate);
            System.out.println("h-break: " + hb);
            System.out.println("ivb: " + ivb);

            // =============================
            // Process based on pitch type
            // =============================
            if (pitchType.equalsIgnoreCase("4seam")) {
                Parse4SeamData data = new Parse4SeamData(
                        "fastball metrics BEST.csv",
                        "fangraphs stuff all - fangraphs stuff all.csv"
                );
                System.out.print("closest pitcher match for 4seam fastball: ");
                System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
                System.out.println("stuff value: " + data.getStuff());

                printDifferences(
                        data.getFinalRawVeloDiff(),
                        data.getFinalRawSpinDiff(),
                        data.getFinalRawHBDiff(),
                        data.getFinalRawIVBDiff()
                );

            } else if (pitchType.equalsIgnoreCase("changeup")) {
                ParseChangeupData data = new ParseChangeupData(
                        "changeup metrics BEST.csv",
                        "fangraphs stuff all - fangraphs stuff all.csv"
                );
                System.out.print("closest pitcher match for changeup: ");
                System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
                System.out.println("stuff value: " + data.getStuff());

                printDifferences(
                        data.getFinalRawVeloDiff(),
                        data.getFinalRawSpinDiff(),
                        data.getFinalRawHBDiff(),
                        data.getFinalRawIVBDiff()
                );

            } else if (pitchType.equalsIgnoreCase("sinker")) {
                ParseSinkerData data = new ParseSinkerData(
                        "12_21 pitch data 5 measurements - sinker.csv",
                        "fangraphs stuff all - fangraphs stuff all.csv"
                );
                System.out.print("closest pitcher match for sinker: ");
                System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
                System.out.println("stuff value: " + data.getStuff());

                printDifferences(
                        data.getFinalRawVeloDiff(),
                        data.getFinalRawSpinDiff(),
                        data.getFinalRawHBDiff(),
                        data.getFinalRawIVBDiff()
                );

            } else if (pitchType.equalsIgnoreCase("cutter")) {
                ParseCutterData data = new ParseCutterData(
                        "12_21 pitch data 5 measurements - cutter.csv",
                        "fangraphs stuff all - fangraphs stuff all.csv"
                );
                System.out.print("closest pitcher match for cutter: ");
                System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
                System.out.println("stuff value: " + data.getStuff());

                printDifferences(
                        data.getFinalRawVeloDiff(),
                        data.getFinalRawSpinDiff(),
                        data.getFinalRawHBDiff(),
                        data.getFinalRawIVBDiff()
                );

            } else if (pitchType.equalsIgnoreCase("splitter")) {
                ParseSplitterData data = new ParseSplitterData(
                        "12_21 pitch data 5 measurements - splitter.csv",
                        "fangraphs stuff all - fangraphs stuff all.csv"
                );
                System.out.print("closest pitcher match for splitter: ");
                System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
                System.out.println("stuff value: " + data.getStuff());

                printDifferences(
                        data.getFinalRawVeloDiff(),
                        data.getFinalRawSpinDiff(),
                        data.getFinalRawHBDiff(),
                        data.getFinalRawIVBDiff()
                );

            } else if (pitchType.equalsIgnoreCase("slider")) {
                ParseSliderData data = new ParseSliderData(
                        "12_21 pitch data 5 measurements - slider.csv",
                        "fangraphs stuff all - fangraphs stuff all.csv"
                );
                System.out.print("closest pitcher match for slider: ");
                System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
                System.out.println("stuff value: " + data.getStuff());

                printDifferences(
                        data.getFinalRawVeloDiff(),
                        data.getFinalRawSpinDiff(),
                        data.getFinalRawHBDiff(),
                        data.getFinalRawIVBDiff()
                );

            } else if (pitchType.equalsIgnoreCase("curveball")) {
                ParseCurveballData data = new ParseCurveballData(
                        "12_21 pitch data 5 measurements - curveball.csv",
                        "fangraphs stuff all - fangraphs stuff all.csv"
                );
                System.out.print("closest pitcher match for curveball: ");
                System.out.println(Arrays.toString(data.findClosestMatch(velocity, spinRate, hb, ivb)));
                System.out.println("stuff value: " + data.getStuff());

                printDifferences(
                        data.getFinalRawVeloDiff(),
                        data.getFinalRawSpinDiff(),
                        data.getFinalRawHBDiff(),
                        data.getFinalRawIVBDiff()
                );

            } else {
                System.out.println("Unknown pitch type. Please try again.");
            }

            System.out.println("\n--- Run another pitch or type 'quit' to exit ---");
        }

        // Program is ending
        System.out.println("\nExiting program. Press Enter to close.");
        console.nextLine(); // wait for final Enter
        console.close();
    }

    // =============================
    // Helper method to print differences
    // =============================
    private static void printDifferences(double veloDiff, double spinDiff, double hbDiff, double ivbDiff) {
        // Velocity
        if (veloDiff < 0) {
            System.out.println("Your velocity is " + Math.abs(veloDiff) + " MPH less");
        } else if (veloDiff == 0) {
            System.out.println("Velocities are equal!");
        } else {
            System.out.println("Your velocity is " + Math.abs(veloDiff) + " MPH more");
        }

        // Spin rate
        if (spinDiff < 0) {
            System.out.println("Your spin rate is " + Math.abs(spinDiff) + " RPM less");
        } else if (spinDiff == 0) {
            System.out.println("Spin rates are equal!");
        } else {
            System.out.println("Your spin rate is " + Math.abs(spinDiff) + " RPM more");
        }

        // H-break
        if (hbDiff < 0) {
            System.out.println("Your HB is " + Math.abs(hbDiff) + " inches less");
        } else if (hbDiff == 0) {
            System.out.println("HB is equal!");
        } else {
            System.out.println("Your HB is " + Math.abs(hbDiff) + " inches more");
        }

        // IVB
        if (ivbDiff < 0) {
            System.out.println("Your IVB is " + Math.abs(ivbDiff) + " inches less");
        } else if (ivbDiff == 0) {
            System.out.println("IVB is equal!");
        } else {
            System.out.println("Your IVB is " + Math.abs(ivbDiff) + " inches more");
        }
    }
}
