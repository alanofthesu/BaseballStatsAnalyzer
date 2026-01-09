package main;

import java.util.Arrays;
import java.util.Scanner;

public class MainCode {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("Welcome to the Stuff+!");

        while (keepRunning) {
            try {
                System.out.println("\nEnter pitch type (4seam, sinker, cutter, splitter, slider, curveball, changeup)");
                System.out.println("Or type 'quit' to exit:");
                String pitchType = console.nextLine().trim();

                if (pitchType.equalsIgnoreCase("quit")) {
                    keepRunning = false;
                    break;
                }

                double velocity = readDouble(console, "velocity: ");
                double spinRate = readDouble(console, "spin rate: ");
                double hb = readDouble(console, "h-break (flip sign): ");
                double ivb = readDouble(console, "ivb: ");

                System.out.println("\nYour inputs:");
                System.out.println("velocity: " + velocity);
                System.out.println("spin rate: " + spinRate);
                System.out.println("h-break: " + hb);
                System.out.println("ivb: " + ivb);

                // Process pitch
                switch (pitchType.toLowerCase()) {
                    case "4seam" -> run4Seam(velocity, spinRate, hb, ivb);
                    case "changeup" -> runChangeup(velocity, spinRate, hb, ivb);
                    case "sinker" -> runSinker(velocity, spinRate, hb, ivb);
                    case "cutter" -> runCutter(velocity, spinRate, hb, ivb);
                    case "splitter" -> runSplitter(velocity, spinRate, hb, ivb);
                    case "slider" -> runSlider(velocity, spinRate, hb, ivb);
                    case "curveball" -> runCurveball(velocity, spinRate, hb, ivb);
                    default -> System.out.println("Unknown pitch type. Please try again.");
                }

                System.out.println("\n--- Run another pitch or type 'quit' to exit ---");

            } catch (Exception e) {
                System.out.println("\nError occurred: " + e.getMessage());
                e.printStackTrace();
                System.out.println("Please try again.");
            }
        }

        System.out.println("\nExiting program. Press Enter to close.");
        console.nextLine(); // wait for final Enter
        console.close();
    }

    // ================================
    // Helper method to read doubles safely
    // ================================
    private static double readDouble(Scanner console, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(console.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    // ================================
    // Pitch type processing methods
    // ================================
    private static void run4Seam(double velocity, double spinRate, double hb, double ivb) {
        try {
            Parse4SeamData data = new Parse4SeamData(
                    "fastball metrics BEST.csv",
                    "fangraphs stuff all - fangraphs stuff all.csv"
            );
            String[] match = data.findClosestMatch(velocity, spinRate, hb, ivb);
            String pitcherName = String.join(" ", match);

            System.out.print("\nClosest pitcher match for changeup:");
            System.out.println(pitcherName);
            System.out.println("His FanGraphs Stuff+ score for the same pitch: " + data.getStuff());
            PersonalStuffCalc personal = new PersonalStuffCalc(velocity, spinRate, hb, ivb);
            System.out.println("Your Stuff+ score for the same pitch: " + personal.CalculateStuff());
            System.out.println("\nCompared to" + pitcherName + ":");
            printDifferences(
                    data.getFinalRawVeloDiff(),
                    data.getFinalRawSpinDiff(),
                    data.getFinalRawHBDiff(),
                    data.getFinalRawIVBDiff()
            );
        } catch (Exception e) {
            System.out.println("Error processing 4seam: " + e.getMessage());
        }
    }

    private static void runChangeup(double velocity, double spinRate, double hb, double ivb) {
        try {
            ParseChangeupData data = new ParseChangeupData(
                    "changeup metrics BEST.csv",
                    "fangraphs stuff all - fangraphs stuff all.csv"
            );
            String[] match = data.findClosestMatch(velocity, spinRate, hb, ivb);
            String pitcherName = String.join(" ", match);

            System.out.print("\nClosest pitcher match for changeup:");
            System.out.println(pitcherName);
            System.out.println("His FanGraphs Stuff+ score for the same pitch: " + data.getStuff());
            System.out.println("\nCompared to" + pitcherName + ":");
            printDifferences(
                    data.getFinalRawVeloDiff(),
                    data.getFinalRawSpinDiff(),
                    data.getFinalRawHBDiff(),
                    data.getFinalRawIVBDiff()
            );
        } catch (Exception e) {
            System.out.println("Error processing changeup: " + e.getMessage());
        }
    }

    private static void runSinker(double velocity, double spinRate, double hb, double ivb) {
        try {
            ParseSinkerData data = new ParseSinkerData(
                    "12_21 pitch data 5 measurements - sinker.csv",
                    "fangraphs stuff all - fangraphs stuff all.csv"
            );
            String[] match = data.findClosestMatch(velocity, spinRate, hb, ivb);
            String pitcherName = String.join(" ", match);

            System.out.print("\nClosest pitcher match for changeup:");
            System.out.println(pitcherName);
            System.out.println("His FanGraphs Stuff+ score for the same pitch: " + data.getStuff());
            System.out.println("\nCompared to" + pitcherName + ":");
            printDifferences(
                    data.getFinalRawVeloDiff(),
                    data.getFinalRawSpinDiff(),
                    data.getFinalRawHBDiff(),
                    data.getFinalRawIVBDiff()
            );
        } catch (Exception e) {
            System.out.println("Error processing sinker: " + e.getMessage());
        }
    }

    private static void runCutter(double velocity, double spinRate, double hb, double ivb) {
        try {
            ParseCutterData data = new ParseCutterData(
                    "12_21 pitch data 5 measurements - cutter.csv",
                    "fangraphs stuff all - fangraphs stuff all.csv"
            );
            String[] match = data.findClosestMatch(velocity, spinRate, hb, ivb);
            String pitcherName = String.join(" ", match);

            System.out.print("\nClosest pitcher match for changeup:");
            System.out.println(pitcherName);
            System.out.println("His FanGraphs Stuff+ score for the same pitch: " + data.getStuff());
            System.out.println("\nCompared to" + pitcherName + ":");
            printDifferences(
                    data.getFinalRawVeloDiff(),
                    data.getFinalRawSpinDiff(),
                    data.getFinalRawHBDiff(),
                    data.getFinalRawIVBDiff()
            );
        } catch (Exception e) {
            System.out.println("Error processing cutter: " + e.getMessage());
        }
    }

    private static void runSplitter(double velocity, double spinRate, double hb, double ivb) {
        try {
            ParseSplitterData data = new ParseSplitterData(
                    "12_21 pitch data 5 measurements - splitter.csv",
                    "fangraphs stuff all - fangraphs stuff all.csv"
            );
            String[] match = data.findClosestMatch(velocity, spinRate, hb, ivb);
            String pitcherName = String.join(" ", match);

            System.out.print("\nClosest pitcher match for changeup:");
            System.out.println(pitcherName);
            System.out.println("His FanGraphs Stuff+ score for the same pitch: " + data.getStuff());
            System.out.println("\nCompared to" + pitcherName + ":");
            printDifferences(
                    data.getFinalRawVeloDiff(),
                    data.getFinalRawSpinDiff(),
                    data.getFinalRawHBDiff(),
                    data.getFinalRawIVBDiff()
            );
        } catch (Exception e) {
            System.out.println("Error processing splitter: " + e.getMessage());
        }
    }

    private static void runSlider(double velocity, double spinRate, double hb, double ivb) {
        try {
            ParseSliderData data = new ParseSliderData(
                    "12_21 pitch data 5 measurements - slider.csv",
                    "fangraphs stuff all - fangraphs stuff all.csv"
            );
            String[] match = data.findClosestMatch(velocity, spinRate, hb, ivb);
            String pitcherName = String.join(" ", match);

            System.out.print("\nClosest pitcher match for changeup:");
            System.out.println(pitcherName);
            System.out.println("His FanGraphs Stuff+ score for the same pitch: " + data.getStuff());
            System.out.println("\nCompared to" + pitcherName + ":");
            printDifferences(
                    data.getFinalRawVeloDiff(),
                    data.getFinalRawSpinDiff(),
                    data.getFinalRawHBDiff(),
                    data.getFinalRawIVBDiff()
            );
        } catch (Exception e) {
            System.out.println("Error processing slider: " + e.getMessage());
        }
    }

    private static void runCurveball(double velocity, double spinRate, double hb, double ivb) {
        try {
            ParseCurveballData data = new ParseCurveballData(
                    "12_21 pitch data 5 measurements - curveball.csv",
                    "fangraphs stuff all - fangraphs stuff all.csv"
            );
            String[] match = data.findClosestMatch(velocity, spinRate, hb, ivb);
            String pitcherName = String.join(" ", match);

            System.out.print("\nClosest pitcher match for changeup:");
            System.out.println(pitcherName);
            System.out.println("His FanGraphs Stuff+ score for the same pitch: " + data.getStuff());
            PersonalStuffCalc personal = new PersonalStuffCalc(velocity, spinRate, hb, ivb);
            
            System.out.println("\nCompared to" + pitcherName + ":");
            printDifferences(
                    data.getFinalRawVeloDiff(),
                    data.getFinalRawSpinDiff(),
                    data.getFinalRawHBDiff(),
                    data.getFinalRawIVBDiff()
            );
        } catch (Exception e) {
            System.out.println("Error processing curveball: " + e.getMessage());
        }
    }

    // ================================
    // Helper method to print differences
    // ================================
    private static void printDifferences(double veloDiff, double spinDiff, double hbDiff, double ivbDiff) {
        System.out.println(veloDiff < 0 ? "Your velocity is " + Math.abs(veloDiff) + " MPH less"
                : veloDiff == 0 ? "Velocities are equal!" : "Your velocity is " + Math.abs(veloDiff) + " MPH more");

        System.out.println(spinDiff < 0 ? "Your spin rate is " + Math.abs(spinDiff) + " RPM less"
                : spinDiff == 0 ? "Spin rates are equal!" : "Your spin rate is " + Math.abs(spinDiff) + " RPM more");

        System.out.println(hbDiff < 0 ? "Your HB is " + Math.abs(hbDiff) + " inches less"
                : hbDiff == 0 ? "HB is equal!" : "Your HB is " + Math.abs(hbDiff) + " inches more");

        System.out.println(ivbDiff < 0 ? "Your IVB is " + Math.abs(ivbDiff) + " inches less"
                : ivbDiff == 0 ? "IVB is equal!" : "Your IVB is " + Math.abs(ivbDiff) + " inches more");
    }
  
 
}
