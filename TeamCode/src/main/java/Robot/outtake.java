package Robot;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

public class outtake {

    private Robot robot;
    private DcMotorEx vertLeft; // Port -
    private DcMotorEx vertRight; // Port -
    private Motor.Encoder vertEnc; // Port -
    private Servo outClaw; // Port -
    private Servo outClawPivot; // Port -
    private Servo out4BarPivot1; // Port -
    private Servo out4BarPivot2; // Port -
    private PIDFController viper;
    private double vertLeftPower=0, prevVertLeftPower=0;
    private double vertRightPower=0, prevVertRightPower=0;
    private double outClawPos=0, prevOutClawPos=0;
    private double outClawPivotPos=0, prevOutClawPivotPos=0;
    private double out4BarPivot1Pos=0, prevout4BarPivot1Pos=0;
    private double out4BarPivot2Pos=0, prevout4BarPivot2Pos=0;
    private double EncoderPos = 0;
    private double ticksPerInch;
    private double Error = 0;
    private double targetPos = 0;

    public outtake(Robot robot){
        this.robot = robot;
        vertLeft = robot.getVertLeft();
        vertRight = robot.getVertRight();
        vertEnc = robot.getVertEnc();
        outClaw = robot.getOutClaw();
        outClawPivot = robot.getOutClawPivot();
        out4BarPivot1 = robot.getout4BarPivot1();
        out4BarPivot2 = robot.getout4BarPivot2();
        viper = new PIDFController(robot.getVertCoefficients().p,robot.getVertCoefficients().i,robot.getVertCoefficients().d,robot.getVertCoefficients().f);
        ticksPerInch = robot.getTicksPerInchVert();
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
        vertLeftPower = viper.calculate(Error);
        vertLeftPower = (vertLeftPower * robot.getNormalVoltage()) / robot.getVoltage();
        vertRightPower = vertLeftPower;
        if(vertLeftPower != prevVertLeftPower){
            this.vertLeft.setPower(vertLeftPower);
            this.prevVertLeftPower = this.vertLeftPower;
        }
        if (vertRightPower != prevVertRightPower) {
            this.vertRight.setPower(vertRightPower);
            this.prevVertRightPower = this.vertRightPower;
        }
        if (outClawPos != prevOutClawPos) {
            this.outClaw.setPosition(outClawPos);
            this.prevOutClawPos = this.outClawPos;
        }
        if (outClawPivotPos != prevOutClawPivotPos) {
            this.outClawPivot.setPosition(outClawPivotPos);
            this.prevOutClawPivotPos = this.outClawPivotPos;
        }
        if (out4BarPivot1Pos != prevout4BarPivot1Pos) {
            this.out4BarPivot1.setPosition(out4BarPivot1Pos);
            this.prevout4BarPivot1Pos = this.out4BarPivot1Pos;
        }
        if (out4BarPivot2Pos != prevout4BarPivot2Pos) {
            this.out4BarPivot2.setPosition(out4BarPivot2Pos);
            this.prevout4BarPivot2Pos = this.out4BarPivot2Pos;
        }
        try {
            EncoderPos = vertEnc.getDistance();
        }catch (Exception e){
            EncoderPos = 0;
        }
    }

}
