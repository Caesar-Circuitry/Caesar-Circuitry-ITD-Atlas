package Robot;

import com.acmerobotics.dashboard.config.Config;

@Config
public class constants{
    public static double oKP = 0,oKD = 0,oKV = 0,oKA = 0,oKS = 0,oKG = 0;
    public static double iKP = 0,iKD = 0,iKV = 0,iKA = 0,iKS = 0,iKG = 0;

    public static double outClawOpen = 0, outClawClose = 1;
    public static double outClawPivotTransfer = 0, outClawPivotChamber = 0, outClawPivotBasket = 0;
    public static double out4BarPivot1Transfer = 0, out4BarPivot1HighChamber = 0, out4BarPivot1LowChamber = 0,out4BarPivot1LowBasket = 0,out4BarPivot1HighBasket = 0;
    public static double out4BarPivot2Transfer = 0, out4BarPivot2HighChamber = 0, out4BarPivot2LowChamber = 0,out4BarPivot2LowBasket = 0,out4BarPivot2HighBasket = 0;
    public static double outViperZero = 0, outViperTransfer = 0, outViperLowChamber = 5, outViperHighChamber = 10, outViperLowBasket = 10, outViperHighBasket = 15;

    public static double intClawOpen = 0, intClawClose = 0;
    public static double intClawPivotSub = 0, intClawPivotObs = 0, intClawPivotTransfer = 0;
    public static double intClawRotateLeft = 0, intClawRotateMiddle = 0, intClawRotateRight = 0;
    public static double intViperZero = 0, intViperTransfer = 0, intViperSub = 0, intViperObs = 0;

    public enum opModeType{
        TELEOP,
        AUTONOMOUS
    }
}
