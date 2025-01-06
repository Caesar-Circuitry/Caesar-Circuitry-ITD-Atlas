package Robot;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

public class intakeSubsystem extends SubsystemBase {
    private Robot robot;
    private DcMotorEx horzLeft; // Port -
    private DcMotorEx horzRight; // Port -
    private Motor.Encoder horzEnc; // Port -
    private Servo intClaw; // Port -
    private Servo intClawRotate; // Port -
    private Servo intClawPivot; // Port -
    private Servo intPivot; // Port -
    private PIDFController viper;
    private double horzLeftPower=0, prevhorzLeftPower=0;
    private double horzRightPower=0, prevhorzRightPower=0;
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
        horzLeft = robot.getHorzLeft();
        horzRight = robot.getHorzRight();
        horzEnc = robot.getHorzEnc();
        intClaw = robot.getIntClaw();
        intClawRotate = robot.getIntClawRotate();
        intClawPivot = robot.getIntClawPivot();
        intPivot = robot.getIntPivot();
        viper = new PIDFController(robot.getHorzCoefficients().p,robot.getHorzCoefficients().i,robot.getHorzCoefficients().d,robot.getHorzCoefficients().f);
        ticksPerInch = robot.getTicksPerInchHorz();
    }

    @Override
    public void periodic() {
        Error = targetPos - EncoderPos;
        horzLeftPower = viper.calculate(Error);
        horzLeftPower = (horzLeftPower * robot.getNormalVoltage()) / robot.getVoltage();
        horzRightPower = horzLeftPower;
        if(horzLeftPower != prevhorzLeftPower){
            this.horzLeft.setPower(horzLeftPower);
            this.prevhorzLeftPower = this.horzLeftPower;
        }
        if (horzRightPower != prevhorzRightPower) {
            this.horzRight.setPower(horzRightPower);
            this.prevhorzRightPower = this.horzRightPower;
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
        try {
            EncoderPos = horzEnc.getDistance();
        }catch (Exception e){
            EncoderPos = 0;
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
}
