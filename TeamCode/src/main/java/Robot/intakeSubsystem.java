package Robot;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

public class intakeSubsystem extends SubsystemBase {
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
    private boolean useColorSensor = false;

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
                useColorSensor = true;
                this.setTargetPos(intakeConstants.viperSub);
                this.intClawRotatePos = intakeConstants.intClawRotateFloor;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotFloor;
                break;
            case HOMED:
                useColorSensor = false;
                this.setTargetPos(intakeConstants.viperZero);
                this.intClawRotatePos = intakeConstants.intClawRotateHome;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotHome;
                break;
            case TRANSFER:
                useColorSensor = false;
                this.setTargetPos(intakeConstants.viperZero);
                this.intClawRotatePos = intakeConstants.intClawRotateTransfer;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotTransfer;
                break;
            case OBSERVATION_ZONE:
                useColorSensor = false;
                this.setTargetPos(intakeConstants.viperMid);
                this.intClawRotatePos = intakeConstants.intClawRotateFloor;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotFloor;
                break;
            case HIGH_CHAMBER:
                useColorSensor = false;
                this.setTargetPos(intakeConstants.viperZero);
                this.intClawRotatePos = intakeConstants.intClawRotateHome;
                this.intClawPivotPos = intakeConstants.intClawPivotMiddle;
                this.intPivotPos = intakeConstants.intPivotHome;
                break;
            case HIGH_BASKET:
                useColorSensor = false;
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
    public boolean closeClaw(){
        if (useColorSensor){
            if (robot.getTeamColor().equals(Robot.TeamColor.RED)){
                
            }else{

            }
        }
        else{
            intClawPos = intakeConstants.intClawClose;
            return true;
        }
        return false;
    }
}
