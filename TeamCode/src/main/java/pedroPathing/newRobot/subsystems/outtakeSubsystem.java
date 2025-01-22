package pedroPathing.newRobot.subsystems;

import static pedroPathing.newRobot.constants.oKA;
import static pedroPathing.newRobot.constants.oKD;
import static pedroPathing.newRobot.constants.oKG;
import static pedroPathing.newRobot.constants.oKP;
import static pedroPathing.newRobot.constants.oKS;
import static pedroPathing.newRobot.constants.oKV;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Servo;

import pedroPathing.newRobot.hardware.cachingMotor;
import pedroPathing.newRobot.hardware.dualServo;
import pedroPathing.newRobot.constants;

public class outtakeSubsystem extends SubsystemBase {

    private cachingMotor vertSlide; // Port -
    private Motor.Encoder vertEnc; // Port -
    private Servo outClaw; // Port -
    private Servo outClawPivot; // Port -
    private dualServo out4BarPivot; // Port -
    private PIDController viper;
    private ElevatorFeedforward viperF;
    private double vertSlidePower=0, prevvertSlidePower=0;
    private double outClawPos=0, prevOutClawPos=0;
    private double outClawPivotPos=0, prevOutClawPivotPos=0;
    private double out4BarPivot1Pos=0, prevout4BarPivot1Pos=0;
    private double out4BarPivot2Pos=0, prevout4BarPivot2Pos=0;
    private double EncoderPos = 0;
    private double ticksPerInch;
    private double Error = 0;
    private double targetPos = 0;

    public outtakeSubsystem(){
        viper = new PIDController(oKP,0,oKD);
        viperF = new ElevatorFeedforward(oKS,oKG,oKV,oKA);
    }

    private void setFeedBackPower(){
        vertSlide.setPower(

                viper.calculate() +
                        viperF.calculate(10,10)
        );
    }

    @Override
    public void periodic(){

    }
}
