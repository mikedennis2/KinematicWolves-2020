package frc.robot.utils;

public class Filter {
    /* FIR filter taps for 
        sampling frequency = 50 Hz
        pass band = 0 - 2 Hz
        stop band = 10 - 25 Hz
        # of taps = 25 (determines the order of the filter)
    */
    private double[] taps = {
        0.0008171388625648901,
        0.0025796090816614394,
        0.004245625441810102,
        0.0028920364306526743,
        -0.004485549864848663,
        -0.017206206747234075,
        -0.027692599432802778,
        -0.022583572720391073,
        0.01028905933557547,
        0.07228314186855418,
        0.14849473849283668,
        0.21195572576869964,
        0.23668096456728935,
        0.21195572576869964,
        0.14849473849283668,
        0.07228314186855418,
        0.01028905933557547,
        -0.022583572720391073,
        -0.027692599432802778,
        -0.017206206747234075,
        -0.004485549864848663,
        0.0028920364306526743,
        0.004245625441810102,
        0.0025796090816614394,
        0.0008171388625648901
    };

    // Number of taps
    private final int NO_TAPS = 25;
    // Historical values of the signal
    private double[] hist = new double[NO_TAPS];
    // Pointer to current position;
    private int pointer = 0;

    public Filter() {
    }

    // Apply FIR filter on signal
    public double applyFilter(double v) {
        double r = 0;

        hist[pointer] = v;

        for(int p = 0; p < NO_TAPS; p++) {
            r+=taps[p]*hist[(pointer-p)%NO_TAPS];
        }

        pointer++;
        pointer%=NO_TAPS;

        return(r);
    }
}