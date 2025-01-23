package pedroPathing.newRobot.subsystems;

import static pedroPathing.newRobot.constants.iKA;
import static pedroPathing.newRobot.constants.iKD;
import static pedroPathing.newRobot.constants.iKG;
import static pedroPathing.newRobot.constants.iKP;
import static pedroPathing.newRobot.constants.iKS;
import static pedroPathing.newRobot.constants.iKV;
import static pedroPathing.newRobot.constants.oKA;
import static pedroPathing.newRobot.constants.oKG;
import static pedroPathing.newRobot.constants.oKS;
import static pedroPathing.newRobot.constants.oKV;
import static pedroPathing.newRobot.constants.out4BarPivot1HighBasket;
import static pedroPathing.newRobot.constants.out4BarPivot1HighChamber;
import static pedroPathing.newRobot.constants.out4BarPivot1LowBasket;
import static pedroPathing.newRobot.constants.out4BarPivot1LowChamber;
import static pedroPathing.newRobot.constants.out4BarPivot1Transfer;
import static pedroPathing.newRobot.constants.out4BarPivot2HighBasket;
import static pedroPathing.newRobot.constants.out4BarPivot2HighChamber;
import static pedroPathing.newRobot.constants.out4BarPivot2LowBasket;
import static pedroPathing.newRobot.constants.out4BarPivot2LowChamber;
import static pedroPathing.newRobot.constants.out4BarPivot2Transfer;
import static pedroPathing.newRobot.constants.outClawClose;
import static pedroPathing.newRobot.constants.outClawOpen;
import static pedroPathing.newRobot.constants.outClawPivotBasket;
import static pedroPathing.newRobot.constants.outClawPivotChamber;
import static pedroPathing.newRobot.constants.outClawPivotTransfer;
import static pedroPathing.newRobot.constants.outViperHighBasket;
import static pedroPathing.newRobot.constants.outViperHighChamber;
import static pedroPathing.newRobot.constants.outViperLowBasket;
import static pedroPathing.newRobot.constants.outViperLowChamber;
import static pedroPathing.newRobot.constants.outViperTransfer;
import static pedroPathing.newRobot.constants.outViperZero;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import pedroPathing.newRobot.hardware.cachingMotor;
import pedroPathing.newRobot.hardware.cachingServo;
import pedroPathing.newRobot.hardware.dualCachingServo;

public class outtakeSubsystem extends SubsystemBase {

    private cachingMotor vertSlide; // Port -
    private Motor.Encoder vertEnc; // Port -
    private cachingServo outClaw; // Port -
    private cachingServo outClawPivot; // Port -
    private dualCachingServo out4BarPivot; // Port -
    private PIDController viper;
    private ElevatorFeedforward viperF;
    private double vertSlideTargetPos = 0, vertSlideTargetVelocity = 5, vertSlideTargetAcceleration =5;
    private double EncoderPos = 0;
    private double ticksPerInch = 0;
    public outtakeSubsystem(HardwareMap hmap){
        vertSlide = new cachingMotor(hmap.get(DcMotorEx.class, "vertSlide"), true);
        vertEnc = new Motor(hmap, "vertSlide", Motor.GoBILDA.RPM_435).encoder;
        vertEnc.reset();
        outClaw = new cachingServo(hmap.get(Servo.class,"outClaw"));
        outClawPivot = new cachingServo(hmap.get(Servo.class,"outClawPivot"));
        out4BarPivot= new dualCachingServo(hmap.get(Servo.class,"out4BarPivot1"), hmap.get(Servo.class,"out4BarPivot2"));
        viper = new PIDController(iKP,0,iKD);
        viperF = new ElevatorFeedforward(iKS,iKG,iKV,iKA);
        vertSlide.enableVoltageCompensation();
    }

    private void setFeedBackPower(){
        try {
            this.EncoderPos = vertEnc.getDistance();
        } catch (Exception e) {
            this.EncoderPos = 0;
        }
            vertSlide.setPower(
                viper.calculate(EncoderPos/ ticksPerInch, vertSlideTargetPos) +
                        viperF.calculate(vertSlideTargetVelocity,vertSlideTargetAcceleration)
        );
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
        this.out4BarPivot.setPos(out4BarPivot1Transfer,out4BarPivot2Transfer);
    }
    public void setOut4BarPivotHighChamber(){
        this.out4BarPivot.setPos(out4BarPivot1HighChamber,out4BarPivot2HighChamber);
    }
    public void setOut4BarPivotLowChamber(){
        this.out4BarPivot.setPos(out4BarPivot1LowChamber,out4BarPivot2LowChamber);
    }
    public void setOut4BarPivotLowBasket(){
        this.out4BarPivot.setPos(out4BarPivot1LowBasket, out4BarPivot2LowBasket);
    }
    public void setOut4BarPivotHighBasket(){
        this.out4BarPivot.setPos(out4BarPivot1HighBasket, out4BarPivot2HighBasket);
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
