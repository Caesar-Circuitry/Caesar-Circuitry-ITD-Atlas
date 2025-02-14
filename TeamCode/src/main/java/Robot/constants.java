package Robot;

import com.acmerobotics.dashboard.config.Config;

@Config
public class constants{
    public static double oKP = 5,oKD = 0.12,oKF = .015;
    public static double iKP = .5,iKD = 0, iKF = 0.02;

    public static double outClawOpen = 0.2, outClawClose = .05;
    public static double outClawPivotTransfer = .95, outClawPivotChamber = 0, outClawPivotBasket = 0;
    public static double out4BarPivot1Transfer = .43, out4BarPivot1HighChamber = .8, out4BarPivot1LowChamber = 0,out4BarPivot1LowBasket = 0,out4BarPivot1HighBasket = .8;
    public static double out4BarPivot2Transfer = 0, out4BarPivot2HighChamber = 0, out4BarPivot2LowChamber = 0,out4BarPivot2LowBasket = 0,out4BarPivot2HighBasket = 0;
    public static double outViperZero = 3, outViperTransfer = 0, outViperLowChamber = 5, outViperHighChamber = 10, outViperLowBasket = 10, outViperHighBasket = 15;

    public static double intClawOpen = 1, intClawClose = .8;
    public static double intClawPivotSub = .35, intClawPivotObs = .35, intClawPivotTransfer = 1;
    public static double intClawRotateLeft = 0, intClawRotateMiddle = .45, intClawRotateRight = 1;//DONT USE RIGHT
    public static double intPivotSub = .053, intPivotObs = .053, intPivotTransfer = .42;
    public static double intViperZero = 0, intViperTransfer = 0, intViperSub = 10, intViperObs = 0; //max 10in

    public enum opModeType{
        TELEOP,
        AUTONOMOUS
    }
}
