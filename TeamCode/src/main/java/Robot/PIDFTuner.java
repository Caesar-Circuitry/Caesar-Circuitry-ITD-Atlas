package Robot;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
@Config
public class PIDFTuner extends OpMode {
    private Robot robot;
    FtcDashboard dashboard;
    MultipleTelemetry Telemetry;
    public static double kP = 0, kI = 0, kD = 0, kF = 0;
    //add different PidTypes here
    private enum PidType{
        OUTTAKE,
        INTAKE,
    }
    public static PidType pidType = PidType.OUTTAKE;
    public static double TargetPos = 0;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        Telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }

    @Override
    public void loop() {
        switch (pidType){
            case INTAKE:
                robot.setHorzCoefficients(new PIDFCoefficients(kP,kI,kD,kF));
                robot.getIntake().setTargetPos(TargetPos);
                robot.Periodic();
                Telemetry.addData("Error", robot.getIntake().getError());
                Telemetry.addData("Pos", robot.getIntake().getPos());
                Telemetry.update();
                break;
            case OUTTAKE:
                robot.setVertCoefficients(new PIDFCoefficients(kP,kI,kD,kF));
                robot.getOuttake().setTargetPos(TargetPos);
                robot.Periodic();
                Telemetry.addData("Error", robot.getOuttake().getError());
                Telemetry.addData("Pos", robot.getOuttake().getPos());
                Telemetry.update();
                break;
        }
    }
}

