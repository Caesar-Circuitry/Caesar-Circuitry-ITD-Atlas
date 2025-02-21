package pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches = 0.001996147786493306;
        ThreeWheelConstants.strafeTicksToInches = 0.0023660827904289426;
        ThreeWheelConstants.turnTicksToInches = 0.002;
        ThreeWheelConstants.leftY = 5.25;
        ThreeWheelConstants.rightY = -5.25;
        ThreeWheelConstants.strafeX = -.125;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "BRM";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "BLM";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "FLM";
        ThreeWheelConstants.leftEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
    }
}




