package pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        ThreeWheelIMUConstants.forwardTicksToInches = 0.001996147786493306;
        ThreeWheelIMUConstants.strafeTicksToInches = 0.0019915326357416835;
        ThreeWheelIMUConstants.turnTicksToInches = 0.002426576799220129;
        ThreeWheelIMUConstants.leftY = 5.25;
        ThreeWheelIMUConstants.rightY = -5.25;
        ThreeWheelIMUConstants.strafeX = -.125;
        ThreeWheelIMUConstants.leftEncoder_HardwareMapName = "BRM";
        ThreeWheelIMUConstants.rightEncoder_HardwareMapName = "BLM";
        ThreeWheelIMUConstants.strafeEncoder_HardwareMapName = "FLM";
        ThreeWheelIMUConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelIMUConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelIMUConstants.strafeEncoderDirection = Encoder.FORWARD;
        ThreeWheelIMUConstants.IMU_HardwareMapName = "imu";
        ThreeWheelIMUConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
    }
}




