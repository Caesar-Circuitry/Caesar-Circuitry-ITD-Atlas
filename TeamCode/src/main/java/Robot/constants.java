package Robot;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.localization.Pose;

@Config
public class constants{
    public static double oKP = 5,oKD = 0.12,oKF = .015;
    public static double iKP = 0.515,iKD = 0, iKF = 0.02;

    public static double outClawOpen = 0.2, outClawClose = .05;
    public static double outClawPivotTransfer = 0.87, outClawPivotChamber = 0, outClawPivotBasket = 0;
    public static double out4BarPivot1Transfer = .47, out4BarPivot1HighChamber = .83, out4BarPivot1LowChamber = 0,out4BarPivot1LowBasket = 0,out4BarPivot1HighBasket = .83;
    public static double out4BarPivot2Transfer = 0, out4BarPivot2HighChamber = 0, out4BarPivot2LowChamber = 0,out4BarPivot2LowBasket = 0,out4BarPivot2HighBasket = 0;
    public static double outViperZero = 0, outViperTransfer = 0, outViperLowChamber = 5, outViperHighChamber = 7.5, outViperLowBasket = 10, outViperHighBasket = 15;
    public static double out4BarPivot1Start = 0.57, intClawPivotStart = .7,intPivotStart = 0.65;
    public static double intClawOpen = 1, intClawClose = .75;
    public static double intClawPivotSub = .35, intClawPivotObs = .35, intClawPivotTransfer = 1;
    public static double intClawRotateLeft = 0, intClawRotateMiddle = .45, intClawRotateRight = 1;//DONT USE RIGHT
    public static double intPivotSub = .058, intPivotObs = .048, intPivotTransfer = .42;
    public static double intViperZero = -1, intViperTransfer = 0, intViperSub = 10, intViperObs = 10, intViperObsShort = 9; //max 10in

    public static Pose teleStartPose = new Pose(8,13.5/2,Math.toRadians(0));

    public enum opModeType{
        TELEOP,
        AUTONOMOUS
    }
}
