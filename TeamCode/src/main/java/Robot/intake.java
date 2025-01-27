package Robot;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

public class intake {

    private Robot robot;
    private DcMotorEx horzSlide; // Port -
    private DcMotorEx horzRight; // Port -
    private Motor.Encoder horzEnc; // Port -
    private Servo intClaw; // Port -
    private Servo intClawRotate; // Port -
    private Servo intClawPivot; // Port -
    private Servo intPivot; // Port -
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
    public intake(Robot robot){
        this.robot = robot;
        horzSlide = robot.getHorzSlide();
        horzEnc = robot.getHorzEnc();
        intClaw = robot.getIntClaw();
        intClawRotate = robot.getIntClawRotate();
        intClawPivot = robot.getIntClawPivot();
        intPivot = robot.getIntPivot();
        viper = new PIDFController(robot.getHorzCoefficients().p,robot.getHorzCoefficients().i,robot.getHorzCoefficients().d,robot.getHorzCoefficients().f);
        ticksPerInch = robot.getTicksPerInchHorz();
    }

    public void setTargetPos(double Pos){
        targetPos = Pos;
        targetPos *=ticksPerInch;
    }
    public void setCoefficients(PIDFCoefficients pidfCoefficients){
        this.viper.setPIDF(pidfCoefficients.p,pidfCoefficients.i,pidfCoefficients.d,pidfCoefficients.f);
    }
    public double getError(){
        return Error/ticksPerInch;
    }
    public double getPos(){
        return EncoderPos / ticksPerInch;
    }

    public void periodic(){
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
        try {
            EncoderPos = horzEnc.getDistance();
        }catch (Exception e){
            EncoderPos = 0;
        }
    }

}
