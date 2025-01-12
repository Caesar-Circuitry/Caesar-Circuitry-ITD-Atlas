package Robot;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class intakeSubsystem extends SubsystemBase {
    public enum sampleColor{
        RED,
        YELLOW,
        BLUE,
        NONE
    }
    public sampleColor sampleColorDetected = sampleColor.NONE;
    public enum sampleColorTarget{
        ANY_COLOR, // if basket
        ALLIANCE_ONLY // if specimen
    }
    public sampleColorTarget colorTarget = sampleColorTarget.ALLIANCE_ONLY;
    private Robot robot;
    private DcMotorEx horzSlide; // Port -
    private Motor.Encoder horzEnc; // Port -
    private Servo intClaw; // Port -
    private Servo intClawRotate; // Port -
    private Servo intClawPivot; // Port -
    private Servo intPivot; // Port -
    private RevColorSensorV3 colorSensor;
    private PIDFController viper;
    private double horzSlidePower=0, prevhorzSlidePower=0;
    private double intClawPos=0, previntClawPos=0;
    private double intClawRotatePos=0, previntClawRotatePos=0;
    private double intClawPivotPos=0, previntClawPivotPos=0;
    private double intPivotPos=0, previntPivotPos=0;
    private double EncoderPos = 0;
    private double ticksPerInch;
    private double Error = 0;
    private double targetPos = 0;

    public intakeSubsystem(Robot robot) {
        this.robot = robot;
        horzSlide = robot.getHorzSlide();
        horzEnc = robot.getHorzEnc();
        intClaw = robot.getIntClaw();
        intClawRotate = robot.getIntClawRotate();
        intClawPivot = robot.getIntClawPivot();
        intPivot = robot.getIntPivot();
        colorSensor = robot.getColorSensor();
        viper = new PIDFController(robot.getHorzCoefficients().p,robot.getHorzCoefficients().i,robot.getHorzCoefficients().d,robot.getHorzCoefficients().f);
        ticksPerInch = robot.getTicksPerInchHorz();
    }

    @Override
    public void periodic() {
        updateCase();
        try {
            EncoderPos = horzEnc.getDistance();
        }catch (Exception e){
            EncoderPos = 0;
        }
        Error = targetPos - EncoderPos;
        horzSlidePower = viper.calculate(Error);
        horzSlidePower = (horzSlidePower * robot.getNormalVoltage()) / robot.getVoltage();
        if(horzSlidePower != prevhorzSlidePower){
            this.horzSlide.setPower(horzSlidePower);
            this.prevhorzSlidePower = this.horzSlidePower;
        }
            if (intClawPos != previntClawPos) {
                this.intClaw.setPosition(intClawPos);
                this.previntClawPos = this.intClawPos;
            }
        if (intClawRotatePos != previntClawRotatePos) {
            this.intClawRotate.setPosition(intClawRotatePos);
            this.previntClawRotatePos = this.intClawRotatePos;
        }
        if (intClawPivotPos != previntClawPivotPos) {
            this.intClawPivot.setPosition(intClawPivotPos);
            this.previntClawPivotPos = this.intClawPivotPos;
        }
        if (intPivotPos != previntPivotPos) {
            this.intPivot.setPosition(intPivotPos);
            this.previntPivotPos = this.intPivotPos;
        }
    }
    private void updateCase(){
        switch (robot.getScoringState()){
            case SUB:
                this.setTargetPos(intakeConstants.viperSub);
                this.intClawRotatePos = intakeConstants.intClawRotateFloor;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotFloor;
                break;
            case HOMED:
                this.setTargetPos(intakeConstants.viperZero);
                this.intClawRotatePos = intakeConstants.intClawRotateHome;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotHome;
                break;
            case TRANSFER:
                this.setTargetPos(intakeConstants.viperZero);
                this.intClawRotatePos = intakeConstants.intClawRotateTransfer;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotTransfer;
                break;
            case OBSERVATION_ZONE:
                this.setTargetPos(intakeConstants.viperMid);
                this.intClawRotatePos = intakeConstants.intClawRotateFloor;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotFloor;
                break;
            case HIGH_CHAMBER:
                this.setTargetPos(intakeConstants.viperZero);
                this.intClawRotatePos = intakeConstants.intClawRotateHome;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotHome;
                break;
            case HIGH_BASKET:
                this.setTargetPos(intakeConstants.viperZero);
                this.intClawRotatePos = intakeConstants.intClawRotateHome;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotHome;
                break;

        }
    }
    private void setTargetPos(double Pos){
        targetPos = Pos;
        targetPos *=ticksPerInch;
    }
    private void setCoefficients(PIDFCoefficients pidfCoefficients){
        this.viper.setPIDF(pidfCoefficients.p,pidfCoefficients.i,pidfCoefficients.d,pidfCoefficients.f);
    }
    private double getError(){
        return Error/ticksPerInch;
    }
    private double getPos(){
        return EncoderPos / ticksPerInch;
    }
    public void closeClaw(){
            intClawPos = intakeConstants.intClawClose;
    }
    public void setScoringState(Robot.scoringState scoringState){
        this.robot.setScoringState(scoringState);
    }
    public sampleColor sampleColorDetected() {
        int red = colorSensor.red();
        int green = colorSensor.green();
        int blue = colorSensor.blue();
        if ((blue + green + red) >= 900) { // If it's less than 900, then there isn't a sample fully in yet
            if (blue >= green && blue >= red) {
                return sampleColor.BLUE;
            } else if (green >= red) {
                return sampleColor.YELLOW;
            } else {
                return sampleColor.RED;
            }
        }
        else {
            return sampleColor.NONE;
        }
    }
    public boolean correctSampleDetected() {
        switch (colorTarget) {
            case ANY_COLOR:
                if (sampleColorDetected.equals(sampleColor.YELLOW) ||
                        (sampleColorDetected.equals(sampleColor.BLUE) && robot.getTeamColor().equals(Robot.TeamColor.BLUE) ||
                                sampleColorDetected.equals(sampleColor.RED) && robot.getTeamColor().equals(Robot.TeamColor.RED))) {
                    return true;
                }
                break;
            case ALLIANCE_ONLY:
                if (sampleColorDetected.equals(sampleColor.BLUE) && robot.getTeamColor().equals(Robot.TeamColor.BLUE) ||
                        sampleColorDetected.equals(sampleColor.RED) && robot.getTeamColor().equals(Robot.TeamColor.RED)) {
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean hasSample() {
        return colorSensor.getDistance(DistanceUnit.CM) < intakeConstants.SAMPLE_DISTANCE_THRESHOLD;
    }

}
