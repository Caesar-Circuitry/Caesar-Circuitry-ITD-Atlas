package Robot;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.List;


public class Robot {
public enum opModeType{
    TELE_OP,
    AUTO
}
private opModeType opMode= opModeType.TELE_OP;
    RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
            RevHubOrientationOnRobot.UsbFacingDirection.UP);

    private outtake outtake;
    private intake intake;
    private HardwareMap hardwareMap;
    public static double normalVoltage = 12.0;
    private double voltage;
    private ElapsedTime voltageTimer;
    private double voltageUpdate = 5.0;
    private Follower follower;
    List<LynxModule> allHubs;
    /*motors*/
        /*Drive Motors*/
            private DcMotorEx FRM; // Port -
            private DcMotorEx FLM; // Port -
            private DcMotorEx BRM; // Port -
            private DcMotorEx BLM; // Port -
        /*ViperMotors*/
            /*Vertical*/
                private DcMotorEx vertLeft; // Port -
                private DcMotorEx vertRight; // Port -
            /*Horizontal*/
                private DcMotorEx horzLeft; // Port -
                private DcMotorEx horzRight; // Port -
        /*ViperEncoders*/
            /*Vertical*/
                private Motor.Encoder vertEnc; // Port -
    //TODO tune PDFL coefficients and ticks per inch
                private PIDFCoefficients vertCoefficients = new PIDFCoefficients(0,0,0,0);
                private double ticksPerInchVert;
            /*Horizontal*/
                private Motor.Encoder horzEnc; // Port -
    //TODO tune PDFL coefficients and ticks per Inch
                private PIDFCoefficients horzCoefficients = new PIDFCoefficients(0,0,0,0);
                private double ticksPerInchHorz;

    /*Servos*/
        /*Intake*/
            private Servo intClaw; // Port -
            private Servo intClawRotate; // Port -
            private Servo intClawPivot; // Port -
            private Servo intPivot; // Port -
        /*Outtake*/
            private Servo outClaw; // Port -
            private Servo outClawPivot; // Port -
            private Servo out4BarPivot1; // Port -
            private Servo out4BarPivot2; // Port -

    /*Sensors*/
        private Limelight3A limelight;
        private RevColorSensorV3 colorSensor;
        private IMU imu;
        private VoltageSensor voltageSensor;

    public Robot(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        initMotors();
        initServos();
        initOther();
        voltageTimer.reset();
    }
    public Robot(HardwareMap hardwareMap, opModeType opMode){
        this.hardwareMap = hardwareMap;
        initMotors();
        initServos();
        initOther();
        voltageTimer.reset();
        this.opMode = opMode;
        follower = new Follower(hardwareMap);
        follower.setStartingPose(new Pose(0,0,0));
        if (opMode.equals(opModeType.TELE_OP)){
            follower.startTeleopDrive();
        }else{
            limelight.pipelineSwitch(0);
            limelight.start();
        }
    }
    private void initMotors(){
        FRM = hardwareMap.get(DcMotorEx.class,"FRM");
        FLM = hardwareMap.get(DcMotorEx.class,"FLM");
        BRM = hardwareMap.get(DcMotorEx.class,"BRM");
        BLM = hardwareMap.get(DcMotorEx.class,"BLM");

        vertLeft = hardwareMap.get(DcMotorEx.class, "vertLeft");
        vertRight = hardwareMap.get(DcMotorEx.class, "vertRight");
        vertEnc = new Motor(hardwareMap,"vertRight", Motor.GoBILDA.RPM_1150).encoder;

        horzLeft = hardwareMap.get(DcMotorEx.class, "horzLeft");
        horzRight = hardwareMap.get(DcMotorEx.class, "horzRight");
        horzEnc = new Motor(hardwareMap,"vertRight", Motor.GoBILDA.RPM_1150).encoder;
    }

    private void initServos(){
        intClaw = hardwareMap.get(Servo.class, "intClaw");
        intClawRotate = hardwareMap.get(Servo.class, "intClawRotate");
        intClawPivot = hardwareMap.get(Servo.class, "intClawPivot");
        intPivot = hardwareMap.get(Servo.class, "intPivot");

        outClaw = hardwareMap.get(Servo.class, "outClaw");
        out4BarPivot1 = hardwareMap.get(Servo.class, "out4BarPivot1");
        outClawPivot = hardwareMap.get(Servo.class, "outClawPivot");
        out4BarPivot2 = hardwareMap.get(Servo.class, "out4BarPivot2");

    }

    private void initOther(){
        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        limelight = hardwareMap.get(Limelight3A.class,"Limelight");
        colorSensor = hardwareMap.get(RevColorSensorV3.class,"color");
        imu = hardwareMap.get(IMU.class,"imu");
        imu.initialize(new IMU.Parameters(orientation));
        outtake = new outtake(this);
        intake = new intake(this);
        voltageSensor = hardwareMap.voltageSensor.iterator().next();
        voltageTimer = new ElapsedTime();
    }

    public void Periodic(){
        if (voltageTimer.seconds() > voltageUpdate){
            voltage = voltageSensor.getVoltage();
            voltageTimer.reset();
        }
        this.intake.periodic();
        this.outtake.periodic();
        //Should be last thing in loop
        for (LynxModule hub : allHubs) {
            hub.clearBulkCache();
        }

    }


    public DcMotorEx getFRM() {
        return FRM;
    }

    public DcMotorEx getFLM() {
        return FLM;
    }

    public DcMotorEx getBRM() {
        return BRM;
    }

    public DcMotorEx getBLM() {
        return BLM;
    }

    public DcMotorEx getVertLeft() {
        return vertLeft;
    }

    public DcMotorEx getVertRight() {
        return vertRight;
    }

    public DcMotorEx getHorzLeft() {
        return horzLeft;
    }

    public DcMotorEx getHorzRight() {
        return horzRight;
    }

    public Motor.Encoder getVertEnc() {
        return vertEnc;
    }

    public Motor.Encoder getHorzEnc() {
        return horzEnc;
    }

    public Servo getIntClaw() {
        return intClaw;
    }

    public Servo getIntClawRotate() {
        return intClawRotate;
    }

    public Servo getIntClawPivot() {
        return intClawPivot;
    }

    public Servo getIntPivot() {
        return intPivot;
    }

    public Servo getOutClaw() {
        return outClaw;
    }

    public Servo getOutClawPivot() {
        return outClawPivot;
    }

    public Servo getout4BarPivot1() {
        return out4BarPivot1;
    }

    public Servo getout4BarPivot2() {
        return out4BarPivot2;
    }

    public Follower getFollower() {
        return follower;
    }

    public Limelight3A getLimelight() {
        return limelight;
    }

    public PIDFCoefficients getVertCoefficients() {
        return vertCoefficients;
    }

    public intake getIntake() {
        return intake;
    }

    public outtake getOuttake() {
        return outtake;
    }

    public double getTicksPerInchVert() {
        return ticksPerInchVert;
    }
    public double getTicksPerInchHorz() {
        return ticksPerInchHorz;
    }
    public double getVoltage(){
        return voltage;
    }
    public double getNormalVoltage(){
        return normalVoltage;
    }
    public void setVertCoefficients(PIDFCoefficients vertCoefficients){
        this.vertCoefficients = vertCoefficients;
        outtake.setCoefficients(vertCoefficients);
    }
    public PIDFCoefficients getHorzCoefficients() {
        return horzCoefficients;
    }
    public void setHorzCoefficients(PIDFCoefficients horzCoefficients){
        this.horzCoefficients = horzCoefficients;
        intake.setCoefficients(horzCoefficients);
    }
}
