package main;

public class PersonalStuffCalc {
    private double velocity;
    private double spinRate;
    private double hb;
    private double ivb;
    private String pitchType;
    private boolean isRHP;

    // Constructor
    public PersonalStuffCalc(double velocity, double spinRate, double hb, double ivb, String pitchType, boolean isRHP) {
        this.velocity = velocity;
        this.spinRate = spinRate;
        this.hb = hb;
        this.ivb = ivb;
        this.pitchType = pitchType.toLowerCase();
        this.isRHP = isRHP;
    }

    // Averages and Standard Deviations
    private static final double FourVeloAvg = 93.91904762;
    private static final double FourVeloStDev = 2.043709638;
    private static final double FourSpinAvg = 2287.2;
    private static final double FourSpinStDev = 122.8053024;
    private static final double FourHBAvgRHP = -8.039473684;
    private static final double FourHBStDevRHP = 2.859654009;
    private static final double FourHBAvgLHP = 8.010344828;
    private static final double FourHBStDevLHP = 3.837907245;
    private static final double FourIVBAvg = 15.37333333;
    private static final double FourIVBStDev = 2.330412091;

    private static final double ChVeloAvg = 86.10340909;
    private static final double ChVeloStDev = 3.261388494;
    private static final double ChSpinAvg = 1758.193182;
    private static final double ChSpinStDev = 280.3248878;
    private static final double ChHBAvgRHP = -13.79516129;
    private static final double ChHBStDevRHP = 2.755943052;
    private static final double ChHBAvgLHP = 13.99615385;
    private static final double ChHBStDevLHP = 2.087578649;
    private static final double ChIVBAvg = 5.2875;
    private static final double ChIVBStDev = 3.715726519;

    private static final double SinkVeloAvg = 93.39684211;
    private static final double SinkVeloStDev = 2.112095492;
    private static final double SinkSpinAvg = 2199.389474;
    private static final double SinkSpinStDev = 142.0405467;
    private static final double SinkHBAvgRHP = -14.77083333;
    private static final double SinkHBStDevRHP = 2.247341935;
    private static final double SinkHBAvgLHP = 14.8173913;
    private static final double SinkHBStDevLHP = 2.577111169;
    private static final double SinkIVBAvg = 9.565263158;
    private static final double SinkIVBStDev = 3.820459158;

    private static final double CutVeloAvg = 89.45833333;
    private static final double CutVeloStDev = 1.970312861;
    private static final double CutSpinAvg = 2379.416667;
    private static final double CutSpinStDev = 161.009663;
    private static final double CutHBAvgRHP = 2.314634146;
    private static final double CutHBStDevRHP = 1.652356041;
    private static final double CutHBAvgLHP = -1.811111111;
    private static final double CutHBStDevLHP = 1.25833171;
    private static final double CutIVBAvg = 8.233333333;
    private static final double CutIVBStDev = 2.566053929;

    private static final double SplitVeloAvg = 86.75416667;
    private static final double SplitVeloStDev = 3.097120565;
    private static final double SplitSpinAvg = 1364;
    private static final double SplitSpinStDev = 295.8066348;
    private static final double SplitHBAvgRHP = -12.13809524;
    private static final double SplitHBStDevRHP = 2.267482346;
    private static final double SplitHBAvgLHP = 9.8;
    private static final double SplitHBStDevLHP = 3.71;
    private static final double SplitIVBAvg = 4.252173913;
    private static final double SplitIVBStDev = 4.047431235;

    private static final double SlideVeloAvg = 85.31862745;
    private static final double SlideVeloStDev = 2.41835925;
    private static final double SlideSpinAvg = 2425.705882;
    private static final double SlideSpinStDev = 226.1290523;
    private static final double SlideHBAvgRHP = 5.373333333;
    private static final double SlideHBStDevRHP = 4.122493779;
    private static final double SlideHBAvgLHP = -4.603703704;
    private static final double SlideHBStDevLHP = 3.705970389;
    private static final double SlideIVBAvg = 2.214851485;
    private static final double SlideIVBStDev = 3.129772712;

    private static final double CurveVeloAvg = 79.38444444;
    private static final double CurveVeloStDev = 3.650547477;
    private static final double CurveSpinAvg = 2525.311111;
    private static final double CurveSpinStDev = 272.0686729;
    private static final double CurveHBAvgRHP = 8.665151515;
    private static final double CurveHBStDevRHP = 3.892932153;
    private static final double CurveHBAvgLHP = -6.583333333;
    private static final double CurveHBStDevLHP = 3.585073726;
    private static final double CurveIVBAvg = -9.522222222;
    private static final double CurveIVBStDev = 4.353089508;

    /**
     * Calculate the Stuff+ score for the pitch
     * Calibrated to match FanGraphs Stuff+ values as closely as possible
     */
    public double CalculateStuff() {
        double veloZ, spinZ, hbZ, ivbZ;
        double stuffPlus;
        
        switch (pitchType) {
            case "4seam":
                veloZ = (velocity - FourVeloAvg) / FourVeloStDev;
                spinZ = (spinRate - FourSpinAvg) / FourSpinStDev;
                if (isRHP) {
                    hbZ = (hb - FourHBAvgRHP) / FourHBStDevRHP;
                } else {
                    hbZ = (hb - FourHBAvgLHP) / FourHBStDevLHP;
                }
                ivbZ = (ivb - FourIVBAvg) / FourIVBStDev;
                
                // Weighted combination calibrated for 4seam fastballs
                // Higher velo and IVB are strongly positive, HB matters for movement profile
                stuffPlus = 100 + (veloZ * 7.5) + (ivbZ * 6.0) + (Math.abs(hbZ) * 2.5) + (spinZ * 1.5);
                break;
                
            case "changeup":
                veloZ = (velocity - ChVeloAvg) / ChVeloStDev;
                spinZ = (spinRate - ChSpinAvg) / ChSpinStDev;
                if (isRHP) {
                    hbZ = (hb - ChHBAvgRHP) / ChHBStDevRHP;
                } else {
                    hbZ = (hb - ChHBAvgLHP) / ChHBStDevLHP;
                }
                ivbZ = (ivb - ChIVBAvg) / ChIVBStDev;
                
                // Changeups: arm-side fade critical, low IVB better (drop/tumble), velocity separation matters
                // Based on FG data: velo ~77-94mph, spin ~950-2500 (lower often better)
                // IVB ranges from -5 to +18 (lower is better for fade/drop)
                // HB varies by handedness (extreme arm-side fade valued)
                // Top changeups like Wacha (132.3), Sánchez (116.4), Skubal (123.5) have extreme fade + depth
                stuffPlus = 100 + (Math.abs(hbZ) * 7.0) + (-ivbZ * 6.5) + (-spinZ * 4.5) + (-veloZ * 2.5);
                break;
                
            case "sinker":
                veloZ = (velocity - SinkVeloAvg) / SinkVeloStDev;
                spinZ = (spinRate - SinkSpinAvg) / SinkSpinStDev;
                if (isRHP) {
                    hbZ = (hb - SinkHBAvgRHP) / SinkHBStDevRHP;
                } else {
                    hbZ = (hb - SinkHBAvgLHP) / SinkHBStDevLHP;
                }
                ivbZ = (ivb - SinkIVBAvg) / SinkIVBStDev;
                
                // Sinkers: high velocity critical, arm-side run valued, less IVB better (sink)
                // Based on FG data: velo ~88-98mph, IVB ~0-18 (lower is better for sink)
                // HB ~-18 to +18 depending on handedness (more extreme is better)
                // Top sinkers like Alcantara (107.3), Gausman (118.7), Skenes (114.5) have velo + movement
                stuffPlus = 100 + (veloZ * 7.5) + (Math.abs(hbZ) * 5.5) + (-ivbZ * 4.0) + (spinZ * 1.5);
                break;
                
            case "cutter":
                veloZ = (velocity - CutVeloAvg) / CutVeloStDev;
                spinZ = (spinRate - CutSpinAvg) / CutSpinStDev;
                if (isRHP) {
                    hbZ = (hb - CutHBAvgRHP) / CutHBStDevRHP;
                } else {
                    hbZ = (hb - CutHBAvgLHP) / CutHBStDevLHP;
                }
                ivbZ = (ivb - CutIVBAvg) / CutIVBStDev;
                
                // Cutters: velocity is critical, IVB (ride) important, glove-side movement valued
                // Based on FG data: velo ~85-94mph, IVB ~2-14 (more is better), HB varies by handedness
                // High performers like Fried (109.8), Crochet (117.2), Pepiot (96.9) have good velo + IVB
                stuffPlus = 100 + (veloZ * 8.5) + (ivbZ * 5.5) + (Math.abs(hbZ) * 3.5) + (spinZ * 1.5);
                break;
                
            case "splitter":
                veloZ = (velocity - SplitVeloAvg) / SplitVeloStDev;
                spinZ = (spinRate - SplitSpinAvg) / SplitSpinStDev;
                if (isRHP) {
                    hbZ = (hb - SplitHBAvgRHP) / SplitHBStDevRHP;
                } else {
                    hbZ = (hb - SplitHBAvgLHP) / SplitHBStDevLHP;
                }
                ivbZ = (ivb - SplitIVBAvg) / SplitIVBStDev;
                
                // Splitters: velocity matters, low spin critical (tumbling action), arm-side fade valued, low IVB better
                // Based on FG data: velo ~82-94mph, spin ~700-2000 (lower is better)
                // IVB ranges from -1 to +13 (lower is better for drop)
                // HB varies with handedness (arm-side fade valued)
                // Top splitters like Gilbert (144.3), Soriano (144.5), Skenes (89.5) have velocity + low spin
                stuffPlus = 100 + (veloZ * 6.5) + (-spinZ * 8.0) + (Math.abs(hbZ) * 3.5) + (-ivbZ * 5.0);
                break;
                
            case "slider":
                veloZ = (velocity - SlideVeloAvg) / SlideVeloStDev;
                spinZ = (spinRate - SlideSpinAvg) / SlideSpinStDev;
                if (isRHP) {
                    hbZ = (hb - SlideHBAvgRHP) / SlideHBStDevRHP;
                } else {
                    hbZ = (hb - SlideHBAvgLHP) / SlideHBStDevLHP;
                }
                ivbZ = (ivb - SlideIVBAvg) / SlideIVBStDev;
                
                // Sliders: horizontal break and spin are critical, velocity matters, less IVB is better
                // Based on FG data: velo ~78-90mph, spin ~1800-3200, HB varies widely
                // IVB ranges from -4 to +9 (lower/more sweep is often better)
                // Top sliders like Crochet (141.6), Severino (132.8), Skenes (131.2) have extreme movement + spin
                stuffPlus = 100 + (Math.abs(hbZ) * 7.5) + (spinZ * 5.5) + (veloZ * 3.5) + (-ivbZ * 2.5);
                break;
                
            case "curveball":
                veloZ = (velocity - CurveVeloAvg) / CurveVeloStDev;
                spinZ = (spinRate - CurveSpinAvg) / CurveSpinStDev;
                if (isRHP) {
                    hbZ = (hb - CurveHBAvgRHP) / CurveHBStDevRHP;
                } else {
                    hbZ = (hb - CurveHBAvgLHP) / CurveHBStDevLHP;
                }
                ivbZ = (ivb - CurveIVBAvg) / CurveIVBStDev;
                
                // Curveballs: high spin is critical, more negative IVB (drop) is better
                // Horizontal break also valued, velocity less important
                // Based on FG data: spin ~2500-3100, IVB ranges from 0 to -18
                stuffPlus = 100 + (spinZ * 8.0) + (-ivbZ * 6.5) + (Math.abs(hbZ) * 3.5) + (veloZ * 1.0);
                break;
                
            default:
                throw new IllegalArgumentException("Unknown pitch type: " + pitchType);
        }
        
        return Math.round(stuffPlus * 100.0) / 100.0;
    }
}