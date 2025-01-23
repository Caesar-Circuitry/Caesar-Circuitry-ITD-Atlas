package pedroPathing.newRobot.subsystems;

import static pedroPathing.newRobot.constants.intClawPivotObs;
import static pedroPathing.newRobot.constants.intClawPivotSub;
import static pedroPathing.newRobot.constants.intClawPivotTransfer;
import static pedroPathing.newRobot.constants.intClawRotateLeft;
import static pedroPathing.newRobot.constants.intClawRotateMiddle;
import static pedroPathing.newRobot.constants.intClawRotateRight;
import static pedroPathing.newRobot.constants.intViperObs;
import static pedroPathing.newRobot.constants.intViperSub;
import static pedroPathing.newRobot.constants.intViperTransfer;
import static pedroPathing.newRobot.constants.intViperZero;
import static pedroPathing.newRobot.constants.oKA;
import static pedroPathing.newRobot.constants.oKD;
import static pedroPathing.newRobot.constants.oKG;
import static pedroPathing.newRobot.constants.oKP;
import static pedroPathing.newRobot.constants.oKS;
import static pedroPathing.newRobot.constants.oKV;
import static pedroPathing.newRobot.constants.outClawClose;
import static pedroPathing.newRobot.constants.outClawOpen;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import pedroPathing.newRobot.hardware.cachingMotor;
import pedroPathing.newRobot.hardware.cachingServo;

public class intakeSubsystem extends SubsystemBase {
    private cachingMotor horzSlide; // Port -
    private Motor.Encoder horzEnc; // Port -
    private cachingServo intClaw; // Port -
    private cachingServo intClawPivot; // Port -
    private cachingServo intClawRotate; // Port -
    private cachingServo intPivot; // Port -
    private PIDController viper;
    private ElevatorFeedforward viperF;
    private double horzSlideTargetPos = 0, horzSlideTargetVelocity = 5, horzSlideTargetAcceleration =5;
    private double horzSlidePower=0;
    private double intClawPos=0;
    private double intClawPivotPos=0;
    private double intPivotPos = 0;
    private double EncoderPos = 0;
    private double ticksPerInch = 0;
    private double Error = 0;

    public intakeSubsystem(HardwareMap hmap){
        horzSlide = new cachingMotor(hmap.get(DcMotorEx.class, "vertSlide"), true);
        horzEnc = new Motor(hmap, "vertSlide", Motor.GoBILDA.RPM_435).encoder;
        horzEnc.reset();
        intClaw = new cachingServo(hmap.get(Servo.class,"intClaw"));
        intClawPivot = new cachingServo(hmap.get(Servo.class,"intClawPivot"));
        intClawRotate = new cachingServo(hmap.get(Servo.class,"intClawRotate"));
        intPivot = new cachingServo(hmap.get(Servo.class,"intPivot"));
        viper = new PIDController(oKP,0,oKD);
        viperF = new ElevatorFeedforward(oKS,oKG,oKV,oKA);
        horzSlide.enableVoltageCompensation();
    }

    private void setFeedBackPower(){
        try {
            this.EncoderPos = horzEnc.getDistance();
        } catch (Exception e) {
            this.EncoderPos = 0;
        }
        horzSlide.setPower(
                viper.calculate(EncoderPos/ ticksPerInch, horzSlideTargetPos) +
                        viperF.calculate(horzSlideTargetVelocity,horzSlideTargetAcceleration)
        );
    }

    @Override
    public void periodic(){
        setFeedBackPower();
    }

    public void openClaw(){
        this.intClaw.setServoPos(outClawOpen);
    }
    public void closeClaw(){
        this.intClaw.setServoPos(outClawClose);
    }

    public void setIntClawPivotSub(){
        this.intClawPivot.setServoPos(intClawPivotSub);
    }
    public void setIntClawPivotObs(){
        this.intClawPivot.setServoPos(intClawPivotObs);
    }
    public void setIntClawPivotTransfer(){
        this.intClawPivot.setServoPos(intClawPivotTransfer);
    }

    public void setIntClawRotateLeft(){
        this.intClawRotate.setServoPos(intClawRotateLeft);
    }
    public void setIntClawRotateMiddle(){
        this.intClawRotate.setServoPos(intClawRotateMiddle);
    }
    public void setIntClawRotateRight(){
        this.intClawRotate.setServoPos(intClawRotateRight);
    }

    public void setViperZero(){
        this.horzSlideTargetPos = intViperZero;
    }
    public void setViperTransfer(){
        this.horzSlideTargetPos = intViperTransfer;
    }
    public void setViperSub(){
        this.horzSlideTargetPos = intViperSub;
    }
    public void setViperObs(){
        this.horzSlideTargetPos = intViperObs;
    }


}
