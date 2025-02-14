package opModes.Test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class multiServoTester extends LinearOpMode {
    private Servo servo1, servo2, servo3, servo4;
    public static String servo1Name = "";
    public static String servo2Name = "";
    public static String servo3Name = "";
    public static String servo4Name = "";
    public static double pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
    private MultipleTelemetry multipleTelemetry;
    @Override
    public void runOpMode() throws InterruptedException {
        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        servo1 = hardwareMap.get(Servo.class, servo1Name);
        servo2 = hardwareMap.get(Servo.class, servo2Name);
        servo3 = hardwareMap.get(Servo.class, servo3Name);
        servo4 = hardwareMap.get(Servo.class, servo4Name);
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.b) {
                servo1.setPosition(pos1);
                servo2.setPosition(pos2);
                servo3.setPosition(pos3);
                servo4.setPosition(pos4);
            } else if (gamepad1.a) {
                servo1.setPosition(0);
                servo2.setPosition(0);
                servo3.setPosition(0);
                servo4.setPosition(0);
            }
            multipleTelemetry.addLine("gamepad1 b - pos");
            multipleTelemetry.addLine("gamepad1 a - 0");
            multipleTelemetry.update();
        }

    }
}
