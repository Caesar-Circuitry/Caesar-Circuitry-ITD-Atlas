package Robot.subsystems;

import static Robot.constants.intClawClose;
import static Robot.constants.intClawOpen;
import static Robot.constants.intClawPivotObs;
import static Robot.constants.intClawPivotSub;
import static Robot.constants.intClawPivotTransfer;
import static Robot.constants.intClawRotateLeft;
import static Robot.constants.intClawRotateMiddle;
import static Robot.constants.intClawRotateRight;
import static Robot.constants.intPivotObs;
import static Robot.constants.intPivotSub;
import static Robot.constants.intPivotTransfer;
import static Robot.constants.intViperObs;
import static Robot.constants.intViperSub;
import static Robot.constants.intViperTransfer;
import static Robot.constants.intViperZero;
import static Robot.constants.oKA;
import static Robot.constants.oKD;
import static Robot.constants.oKG;
import static Robot.constants.oKP;
import static Robot.constants.oKS;
import static Robot.constants.oKV;
import static Robot.constants.outClawClose;
import static Robot.constants.outClawOpen;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import Robot.hardware.cachingMotor;
import Robot.hardware.cachingServo;

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
        horzSlide = new cachingMotor(hmap.get(DcMotorEx.class, "horzSlide"), true);
        horzEnc = new Motor(hmap, "horzSlide", Motor.GoBILDA.RPM_435).encoder;
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
        this.intClaw.setServoPos(intClawOpen);
    }
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
    public void setIntPivotTransfer(){this.intPivot.setServoPos(intPivotTransfer);}

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
