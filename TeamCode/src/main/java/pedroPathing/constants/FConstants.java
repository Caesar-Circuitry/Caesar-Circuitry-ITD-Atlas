package pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.util.CustomFilteredPIDFCoefficients;
import com.pedropathing.util.CustomPIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.THREE_WHEEL;

        FollowerConstants.leftFrontMotorName = "FLM";
        FollowerConstants.leftRearMotorName = "BLM";
        FollowerConstants.rightFrontMotorName = "FRM";
        FollowerConstants.rightRearMotorName = "BRM";

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        FollowerConstants.mass = 11.88;
        FollowerConstants.useVoltageCompensationInAuto = true;
        FollowerConstants.useVoltageCompensationInTeleOp = true;
        FollowerConstants.nominalVoltage =12.4; //default 12.00

        FollowerConstants.xMovement = 69.42822965885895;
        FollowerConstants.yMovement = 54.193187011874194;

        FollowerConstants.forwardZeroPowerAcceleration = -47.389581963069574;
        FollowerConstants.lateralZeroPowerAcceleration = -81.56406610772116;

        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0.5,0,0.01,0);
        FollowerConstants.useSecondaryTranslationalPID = true;
        FollowerConstants.secondaryTranslationalPIDFCoefficients.setCoefficients(0.3,0.001,0.01,0); // Not being used, @see useSecondaryTranslationalPID

        FollowerConstants.headingPIDFCoefficients.setCoefficients(3,0,0.1,0);
        FollowerConstants.useSecondaryHeadingPID = true;
        FollowerConstants.secondaryHeadingPIDFCoefficients.setCoefficients(2,0.001,0.1,0); // Not being used, @see useSecondaryHeadingPID

        FollowerConstants.drivePIDFCoefficients.setCoefficients(0.01,0,0,0.6,0);
        FollowerConstants.useSecondaryDrivePID = true;
        FollowerConstants.secondaryDrivePIDFCoefficients.setCoefficients(0.01,0,0,0.6,0); // Not being used, @see useSecondaryDrivePID

        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
        FollowerConstants.centripetalScaling = 0.0005;

        FollowerConstants.pathEndTimeoutConstraint = 500;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 0.1;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;
    }
}
