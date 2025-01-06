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
    private Servo out4BarPivot; // Port -
    //TODO: change name once servo is figured out -ask Zac-
    private Servo outUnknown; // Port -
    private PIDFController viper;
    private double vertLeftPower=0, prevVertLeftPower=0;
    private double vertRightPower=0, prevVertRightPower=0;
    private double outClawPos=0, prevOutClawPos=0;
    private double outClawPivotPos=0, prevOutClawPivotPos=0;
    private double out4BarPivotPos=0, prevOut4BarPivotPos=0;
    private double outUnknownPos=0, prevOutUnknownPos=0;
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
        out4BarPivot = robot.getOut4BarPivot();
        outUnknown = robot.getOutUnknown();
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
        if (out4BarPivotPos != prevOut4BarPivotPos) {
            this.out4BarPivot.setPosition(out4BarPivotPos);
            this.prevOut4BarPivotPos = this.out4BarPivotPos;
        }
        if (outUnknownPos != prevOutUnknownPos) {
            this.outUnknown.setPosition(outUnknownPos);
            this.prevOutUnknownPos = this.outUnknownPos;
        }
        try {
            EncoderPos = vertEnc.getDistance();
        }catch (Exception e){
            EncoderPos = 0;
        }
    }

}
