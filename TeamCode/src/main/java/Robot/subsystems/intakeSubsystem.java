package Robot.subsystems;


import static Robot.constants.iKD;
import static Robot.constants.iKP;
import static Robot.constants.intClawClose;
import static Robot.constants.intClawCloseTele;
import static Robot.constants.intClawOpen;
import static Robot.constants.intClawPivotObs;
import static Robot.constants.intClawPivotStart;
import static Robot.constants.intClawPivotSub;
import static Robot.constants.intClawPivotTransfer;
import static Robot.constants.intClawRotateLeft;
import static Robot.constants.intClawRotateMiddle;
import static Robot.constants.intClawRotateRight;
import static Robot.constants.intPivotObs;
import static Robot.constants.intPivotObsAuto;
import static Robot.constants.intPivotStart;
import static Robot.constants.intPivotSub;
import static Robot.constants.intPivotTransfer;
import static Robot.constants.intViperObs;
import static Robot.constants.intViperObsShort;
import static Robot.constants.intViperSub;
import static Robot.constants.intViperTransfer;
import static Robot.constants.intViperZero;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import Robot.hardware.cachingMotor;
import Robot.hardware.cachingServo;
import Robot.robotContainer;

public class intakeSubsystem extends SubsystemBase {
    private DcMotorEx horzSlide; // Port -
    private Motor.Encoder horzEnc; // Port -
    private cachingServo intClaw; // Port -
    private cachingServo intClawPivot; // Port -
    private cachingServo intClawRotate; // Port -
    private cachingServo intPivot; // Port -
    private PIDController viper;
    private double horzSlideTargetPos = 0;
    private double EncoderPos = 0;
    private double ticksPerInch = 5.8;
    private final robotContainer robot;

    public intakeSubsystem(HardwareMap hmap, robotContainer robot){
        horzSlide = hmap.get(DcMotorEx.class,"horzSlide");
        horzEnc = new Motor(hmap, "horzSlide", Motor.GoBILDA.RPM_435).encoder;
        horzEnc.reset();
        horzSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intClaw = new cachingServo(hmap.get(Servo.class,"intClaw"));
        intClawPivot = new cachingServo(hmap.get(Servo.class,"intClawPivot"));
        intClawRotate = new cachingServo(hmap.get(Servo.class,"intClawRotate"));
        intPivot = new cachingServo(hmap.get(Servo.class,"intPivot"));
        viper = new PIDController(iKP,0,iKD);
        this.robot = robot;
    }

    private void setFeedBackPower(){
        try {
            this.EncoderPos = horzEnc.getDistance();
        } catch (Exception e) {
            this.EncoderPos = 0;
        }
        horzSlide.setPower(
                (viper.calculate(EncoderPos/ ticksPerInch, horzSlideTargetPos) * robot.getVoltage())/12
        );
    }

    @Override
    public void periodic(){
        setFeedBackPower();
    }

    public void openClaw(){
        this.intClaw.setServoPos(intClawOpen);
    }
    public void closeClawTele(){this.intClaw.setServoPos(intClawCloseTele);}
    public void closeClaw(){
        this.intClaw.setServoPos(intClawClose);
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
    public void setIntClawPivotStart(){
        this.intClawPivot.setServoPos(intClawPivotStart);
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

    public void setIntPivotSub(){this.intPivot.setServoPos(intPivotSub);}
    public void setIntPivotObs(){this.intPivot.setServoPos(intPivotObs);}
    public void setIntPivotObsAuto(){this.intPivot.setServoPos(intPivotObsAuto);}
    public void setIntPivotObsUp(){this.intPivot.setServoPos(intPivotSub+.025);}
    public void setIntPivotTransfer(){this.intPivot.setServoPos(intPivotTransfer);}
    public void setIntPivotStart(){
        this.intPivot.setServoPos(intPivotStart);
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
    public void setViperObsShort(){
        this.horzSlideTargetPos = intViperObsShort;
    }


}
