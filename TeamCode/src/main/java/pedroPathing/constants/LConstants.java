package pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        ThreeWheelIMUConstants.forwardTicksToInches = .001989436789;
        ThreeWheelIMUConstants.strafeTicksToInches = .001989436789;
        ThreeWheelIMUConstants.turnTicksToInches = .001989436789;
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




