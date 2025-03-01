package opModes.Test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class ServoTester extends LinearOpMode {
  private Servo servo;
  public static String servoName = "";
  public static double pos1 = 0, pos2 = 0, pos3 = 0;
  private MultipleTelemetry multipleTelemetry;

  @Override
  public void runOpMode() throws InterruptedException {
    multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    servo = hardwareMap.get(Servo.class, servoName);
    waitForStart();
    while (opModeIsActive()) {
      if (gamepad1.b) {
        servo.setPosition(pos1);
      } else if (gamepad1.a) {
        servo.setPosition(pos2);
      } else if (gamepad1.x) {
        servo.setPosition(pos3);
      }
      multipleTelemetry.addLine("gamepad1 b - pos1");
      multipleTelemetry.addLine("gamepad1 a - pos2");
      multipleTelemetry.addLine("gamepad1 x - pos3");
      multipleTelemetry.update();
    }
  }
}
