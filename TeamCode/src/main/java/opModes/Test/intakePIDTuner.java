package opModes.Test;

import static Robot.constants.iKD;
import static Robot.constants.iKF;
import static Robot.constants.iKP;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Config
@TeleOp
public class intakePIDTuner extends LinearOpMode {
  public static DcMotor motor;
  public static String motorName = "horzSlide";
  public static VoltageSensor voltageSensor;
  public static double voltage = 12;
  private Motor.Encoder motorEnc; // Port -
  public static double horzSlideTargetPos = 0,
      horzSlideTargetVelocity = 5,
      horzSlideTargetAcceleration = 5;
  public static double horzSlidePower = 0;
  public static double EncoderPos = 0;
  public static double ticksPerInch = 5.8;
  private PIDFController viper;
  private MultipleTelemetry multipleTelemetry;

  private enum tune {
    PIDF,
    TICKSPERINCH
  }

  public static tune TuneMode = tune.TICKSPERINCH;

  @Override
  public void runOpMode() throws InterruptedException {
    motor = hardwareMap.get(DcMotor.class, motorName);
    motorEnc = new Motor(hardwareMap, motorName, Motor.GoBILDA.RPM_435).encoder;
    viper = new PIDFController(iKP, 0, iKD, iKF);
    motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    voltageSensor = hardwareMap.voltageSensor.iterator().next();
    multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    motorEnc.reset();
    waitForStart();
    while (opModeIsActive()) {
      switch (TuneMode) {
        case PIDF:
          setFeedBackPower();
          viper.setPIDF(iKP, 0, iKD, iKF);
          break;
        case TICKSPERINCH:
          try {
            this.EncoderPos = motorEnc.getDistance();
          } catch (Exception e) {
            this.EncoderPos = 0;
          }
          telemetry.addData("pos", EncoderPos);
          telemetry.update();
          break;
      }
      multipleTelemetry.addData("targetPos", horzSlideTargetPos);
      multipleTelemetry.addData("actualPos", EncoderPos / ticksPerInch);
      multipleTelemetry.addData("error", (horzSlideTargetPos - (EncoderPos / ticksPerInch)));
      multipleTelemetry.update();
    }
  }

  private void setFeedBackPower() {
    try {
      this.EncoderPos = motorEnc.getDistance();
    } catch (Exception e) {
      this.EncoderPos = 0;
    }
    motor.setPower(
        (viper.calculate(EncoderPos / ticksPerInch, horzSlideTargetPos) * voltage)
            / voltageSensor.getVoltage());
  }
}
