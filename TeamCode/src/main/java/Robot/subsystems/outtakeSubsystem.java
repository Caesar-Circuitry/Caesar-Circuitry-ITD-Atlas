package Robot.subsystems;

import static Robot.constants.oKD;
import static Robot.constants.oKF;
import static Robot.constants.oKP;
import static Robot.constants.out4BarPivot1HighBasket;
import static Robot.constants.out4BarPivot1HighChamber;
import static Robot.constants.out4BarPivot1LowBasket;
import static Robot.constants.out4BarPivot1LowChamber;
import static Robot.constants.out4BarPivot1Transfer;
import static Robot.constants.out4BarPivot2HighBasket;
import static Robot.constants.out4BarPivot2HighChamber;
import static Robot.constants.out4BarPivot2LowBasket;
import static Robot.constants.out4BarPivot2LowChamber;
import static Robot.constants.out4BarPivot2Transfer;
import static Robot.constants.outClawClose;
import static Robot.constants.outClawOpen;
import static Robot.constants.outClawPivotBasket;
import static Robot.constants.outClawPivotChamber;
import static Robot.constants.outClawPivotTransfer;
import static Robot.constants.outViperHighBasket;
import static Robot.constants.outViperHighChamber;
import static Robot.constants.outViperLowBasket;
import static Robot.constants.outViperLowChamber;
import static Robot.constants.outViperTransfer;
import static Robot.constants.outViperZero;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import Robot.hardware.cachingMotor;
import Robot.hardware.cachingServo;
import Robot.hardware.dualCachingServo;

public class outtakeSubsystem extends SubsystemBase {

    private cachingMotor vertSlide; // Port 0
    private Motor.Encoder vertEnc; // Port 0
    private cachingServo outClaw; // Port 2
    private cachingServo outClawPivot; // Port 5
    private cachingServo out4BarPivot; // Port 3
    private PIDFController viper;
    private double vertSlideTargetPos = 0;
    private double EncoderPos = 0;
    private double ticksPerInch = 0;
    public outtakeSubsystem(HardwareMap hmap){
        vertSlide = new cachingMotor(hmap.get(DcMotorEx.class, "vertSlide"), true);
        vertEnc = new Motor(hmap, "vertSlide", Motor.GoBILDA.RPM_435).encoder;
        vertEnc.reset();
        outClaw = new cachingServo(hmap.get(Servo.class,"outClaw"));
        outClawPivot = new cachingServo(hmap.get(Servo.class,"outClawPivot"));
        out4BarPivot= new cachingServo(hmap.get(Servo.class,"out4BarPivot1"));
        viper = new PIDFController(oKP,0,oKD,oKF);
        vertSlide.enableVoltageCompensation();
    }

    private void setFeedBackPower(){
        try {
            this.EncoderPos = vertEnc.getDistance();
        } catch (Exception e) {
            this.EncoderPos = 0;
        }
            vertSlide.setPower(
                viper.calculate(EncoderPos/ ticksPerInch, vertSlideTargetPos)
            );
        if(vertSlide.isOverCurrent()){
            vertEnc.reset();
        }
    }

    @Override
    public void periodic(){
        setFeedBackPower();
    }

    public void openClaw(){
        this.outClaw.setServoPos(outClawOpen);
    }
    public void closeClaw(){
        this.outClaw.setServoPos(outClawClose);
    }

    public void setOutClawPivotTransfer(){
        this.outClawPivot.setServoPos(outClawPivotTransfer);
    }
    public void setOutClawPivotChamber(){
        this.outClawPivot.setServoPos(outClawPivotChamber);
    }
    public void setOutClawPivotBasket(){
        this.outClawPivot.setServoPos(outClawPivotBasket);
    }

    public void setOut4BarPivotTransfer(){
        this.out4BarPivot.setServoPos(out4BarPivot1Transfer);
    }
    public void setOut4BarPivotHighChamber(){
        this.out4BarPivot.setServoPos(out4BarPivot1HighChamber);
    }
    public void setOut4BarPivotLowChamber(){
        this.out4BarPivot.setServoPos(out4BarPivot1LowChamber);
    }
    public void setOut4BarPivotLowBasket(){
        this.out4BarPivot.setServoPos(out4BarPivot1LowBasket);
    }
    public void setOut4BarPivotHighBasket(){
        this.out4BarPivot.setServoPos(out4BarPivot1HighBasket);
    }
    public void setVertSlideZero(){
        this.vertSlideTargetPos = outViperZero;
    }
    public void setVertSlideTransfer(){
        this.vertSlideTargetPos = outViperTransfer;
    }
    public void setVertSlideLowChamber(){
        this.vertSlideTargetPos = outViperLowChamber;
    }public void setVertSlideHighChamber(){
        this.vertSlideTargetPos = outViperHighChamber;
    }
    public void setVertSlideLowBasket(){
        this.vertSlideTargetPos = outViperLowBasket;
    }
    public void setVertSlideHighBasket(){
        this.vertSlideTargetPos = outViperHighBasket;
    }
}
