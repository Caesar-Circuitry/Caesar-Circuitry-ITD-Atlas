package opModes.Test;

import static Robot.constants.iKD;
import static Robot.constants.iKF;
import static Robot.constants.iKP;
import static Robot.constants.intClawClose;
import static Robot.constants.intClawOpen;
import static Robot.constants.intClawPivotSub;
import static Robot.constants.intClawPivotTransfer;
import static Robot.constants.intClawRotateMiddle;
import static Robot.constants.intPivotSub;
import static Robot.constants.intPivotTransfer;
import static Robot.constants.intViperObs;
import static Robot.constants.intViperZero;
import static Robot.constants.oKD;
import static Robot.constants.oKP;
import static Robot.constants.out4BarPivot1HighBasket;
import static Robot.constants.out4BarPivot1HighChamber;
import static Robot.constants.out4BarPivot1Transfer;
import static Robot.constants.outClawClose;
import static Robot.constants.outClawOpen;
import static Robot.constants.outClawPivotBasket;
import static Robot.constants.outClawPivotChamber;
import static Robot.constants.outClawPivotTransfer;
import static Robot.constants.outViperHighBasket;
import static Robot.constants.outViperHighChamber;
import static Robot.constants.outViperZero;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

import java.util.List;

import Robot.hardware.cachingMotor;

@TeleOp
@Config
public class transferTest extends LinearOpMode {
    private Servo outClawPivot, out4BarPivot, outClaw, intClaw, intClawPivot, intClawRotate, intPivot;
    private MultipleTelemetry multipleTelemetry;
    private enum transfer{
        INTAKE_OUT,
        TRANSFER,
        HIGH_BASKET,
        HIGH_CHAMBER
    }
    private enum claw{
        CLAW_CLOSE,
        CLAW_OPEN
    }
    public DcMotorEx vertMotor;
    private DcMotorEx horzSlide; // Port -
    private Motor.Encoder horzEnc; // Port -
    public static String vertMotorName = "vertSlide";
    public VoltageSensor voltageSensor;
    public static double voltage = 12;
    private Motor.Encoder vertMotorEnc; // Port -
    public static double vertSlideTargetPos = 0;
    public static double horzSlideTargetPos=0;
    private double voltageSensorVoltage;
    public static double vertEncoderPos = 0, horzEncoderPos = 0;
    public static double vertTicksPerInch = 204.6, horzTickPerInch = 5.8;
    private PIDFController vertViper;
    private PIDFController horzViper;
    public static double oKF = 0.015;
    private transfer TransferSequence = transfer.TRANSFER;
    private claw clawState = claw.CLAW_OPEN;
    public static double maxCurrent = 5;//9.2Amps is stall current
    private List<LynxModule> allHubs;
    private ElapsedTime voltageTimer;
    private double prevHorzMotorPower=0,HorzMotorPower=0,prevVertMotorPower=0,VertMotorPower=0;
    @Override
    public void runOpMode() throws InterruptedException {
        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        voltageTimer = new ElapsedTime();
        voltageTimer.reset();
        horzSlide = hardwareMap.get(DcMotorEx.class,"horzSlide");
        horzEnc = new Motor(hardwareMap, "horzSlide", Motor.GoBILDA.RPM_435).encoder;
        horzEnc.reset();
        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        out4BarPivot = hardwareMap.get(Servo.class, "out4BarPivot1");
        outClawPivot = hardwareMap.get(Servo.class, "outClawPivot");
        outClaw = hardwareMap.get(Servo.class, "outClaw");
        intClaw = hardwareMap.get(Servo.class, "intClaw");
        intClawPivot = hardwareMap.get(Servo.class,"intClawPivot");
        intClawRotate = hardwareMap.get(Servo.class,"intClawRotate");
        intPivot = hardwareMap.get(Servo.class,"intPivot");
        vertMotor = hardwareMap.get(DcMotorEx.class,vertMotorName);
        vertMotorEnc = new Motor(hardwareMap,vertMotorName, Motor.GoBILDA.RPM_435).encoder;
        vertViper = new PIDFController(oKP,0,oKD, oKF);
        horzViper = new PIDController(iKP,0,iKD);
        voltageSensor = hardwareMap.voltageSensor.iterator().next();
        waitForStart();
        voltageSensorVoltage = voltageSensor.getVoltage();
        while (opModeIsActive()){
            if(gamepad1.b) {
                TransferSequence = transfer.TRANSFER;
            } else if (gamepad1.a) {
                clawState = claw.CLAW_CLOSE;
                TransferSequence = transfer.HIGH_BASKET;
            }
            else if (gamepad1.x) {
                clawState = claw.CLAW_CLOSE;
                TransferSequence = transfer.HIGH_CHAMBER;
            }
            else if (gamepad1.y) {
                TransferSequence = transfer.INTAKE_OUT;
            }
            else if (gamepad1.right_trigger>0) {
                clawState = claw.CLAW_OPEN;
            }
            else if (gamepad1.left_trigger>0) {
                clawState = claw.CLAW_CLOSE;
            }
            TransferSequence();
            setFeedBackPower();
            multipleTelemetry.addLine("gamepad1 y - intake out");
            multipleTelemetry.addLine("gamepad1 b - transfer");
            multipleTelemetry.addLine("gamepad1 a - high basket");
            multipleTelemetry.addLine("gamepad1 x - high chamber");
            multipleTelemetry.addLine("gamepad1 rt - claw open");
            multipleTelemetry.addLine("gamepad1 lt - claw close");
            multipleTelemetry.addData("Encoder Pos", vertEncoderPos);
            multipleTelemetry.addData("vertCurrent", vertMotor.getCurrent(CurrentUnit.AMPS));
            multipleTelemetry.addData("horzCurrent", horzSlide.getCurrent(CurrentUnit.AMPS));
            multipleTelemetry.addData("horzPower",HorzMotorPower);
            multipleTelemetry.update();
            for (LynxModule hub : allHubs) {
                hub.clearBulkCache();
            }
        }


    }
    private void setFeedBackPower(){
        try {
            this.vertEncoderPos = vertMotorEnc.getDistance();
            this.horzEncoderPos = horzEnc.getDistance();
        } catch (Exception e) {
            this.vertEncoderPos = 0;
            this.horzEncoderPos = 0;
        }
        if(voltageTimer.seconds()>=5) {
            voltageSensorVoltage = voltageSensor.getVoltage();
            voltageTimer.reset();
        }
        VertMotorPower = (vertViper.calculate(vertEncoderPos/ vertTicksPerInch, vertSlideTargetPos)*voltage)/voltageSensorVoltage;
        if(VertMotorPower > prevVertMotorPower + .01 || VertMotorPower< prevVertMotorPower -.01 ) {
            vertMotor.setPower(VertMotorPower);
            prevVertMotorPower = VertMotorPower;
        }
        horzSlide.setPower((horzViper.calculate(horzEncoderPos/ horzTickPerInch, horzSlideTargetPos)*voltage)/voltageSensorVoltage);
//            prevHorzMotorPower = HorzMotorPower;
    }
    private void clawState(){
        switch (clawState){
            case CLAW_OPEN:
                intClaw.setPosition(intClawOpen);
                outClaw.setPosition(outClawClose);
                break;
            case CLAW_CLOSE:
                intClaw.setPosition(intClawClose);
                outClaw.setPosition(outClawOpen);
                break;
        }
    }
    private void TransferSequence(){
        switch (TransferSequence){
            case TRANSFER:
                out4BarPivot.setPosition(out4BarPivot1Transfer);
                outClawPivot.setPosition(outClawPivotTransfer);
                intPivot.setPosition(intPivotTransfer);
                intClawPivot.setPosition(intClawPivotTransfer);
                intClawRotate.setPosition(intClawRotateMiddle);
                vertSlideTargetPos = outViperZero;
                horzSlideTargetPos = intViperZero;
                if (vertMotor.getCurrent(CurrentUnit.AMPS)>=maxCurrent){
                    vertMotorEnc.reset();
                }
                if (horzSlide.getCurrent(CurrentUnit.AMPS)>=maxCurrent){
                    horzEnc.reset();
                }
                clawState();
                break;

            case INTAKE_OUT:
                out4BarPivot.setPosition(out4BarPivot1Transfer);
                outClawPivot.setPosition(outClawPivotTransfer);
                intPivot.setPosition(intPivotSub);
                intClawPivot.setPosition(intClawPivotSub);
                intClawRotate.setPosition(intClawRotateMiddle);
                vertSlideTargetPos = outViperZero;
                horzSlideTargetPos = intViperObs;
                if (vertMotor.getCurrent(CurrentUnit.AMPS)>=maxCurrent){
                    vertMotorEnc.reset();
                }
                if (horzSlide.getCurrent(CurrentUnit.AMPS)>=maxCurrent){
                    horzEnc.reset();
                }
                clawState();
                break;

            case HIGH_BASKET:
                out4BarPivot.setPosition(out4BarPivot1HighBasket);
                outClawPivot.setPosition(outClawPivotBasket);
                intPivot.setPosition(intPivotTransfer);
                intClawPivot.setPosition(intClawPivotTransfer);
                intClawRotate.setPosition(intClawRotateMiddle);
                vertSlideTargetPos = outViperHighBasket;
                horzSlideTargetPos = intViperZero;
                clawState();
                break;

            case HIGH_CHAMBER:
                out4BarPivot.setPosition(out4BarPivot1HighChamber);
                outClawPivot.setPosition(outClawPivotChamber);
                intPivot.setPosition(intPivotTransfer);
                intClawPivot.setPosition(intClawPivotTransfer);
                intClawRotate.setPosition(intClawRotateMiddle);
                vertSlideTargetPos = outViperHighChamber;
                horzSlideTargetPos = intViperZero;
                clawState();
                break;
        }
    }
}
