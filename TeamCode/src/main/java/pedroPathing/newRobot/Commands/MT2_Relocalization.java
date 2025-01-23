package pedroPathing.newRobot.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.localization.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

import java.util.ArrayList;

import Robot.Robot;
//Credit to Team 23511
public class MT2_Relocalization extends CommandBase {
    private final Robot robot;
    private final int neededReads;
    private final ArrayList<Position> reads = new ArrayList<>();
    ElapsedTime timer;

    public MT2_Relocalization(Robot robot, int neededReads) {
        this.robot = robot;
        this.neededReads = neededReads;
    }

    @Override
    public void initialize() {
        if (timer == null) {
            timer = new ElapsedTime();
            // Should be unnecessary
            timer.reset();
        }

        robot.getLimelight().updateRobotOrientation(
                // Limelight needs heading in degrees
                Math.toDegrees(
                        // Limelight needs -180 to 180 normalized angle (here its -PI to PI)
                        AngleUnit.normalizeRadians(
                                // Actual heading
                                robot.getFollower().getPose().getHeading()
                                        // Compensate for limelight heading being off by 90 (0.5 pi) degrees compared to Pedro
                                        - Math.PI/2
                        )
                )
        );

        LLResult result = robot.getLimelight().getLatestResult();

        if (result != null) {
            if (result.isValid()) {
                reads.add(result.getBotpose_MT2().getPosition());
            }
        }
    }

    @Override
    public void execute() {
        initialize();
    }

    @Override
    public boolean isFinished() {
        return (reads.size() >= neededReads) || timer.milliseconds() >= 400;
    }

    @Override
    public void end(boolean interrupted) {
        if (!reads.isEmpty()) {
            // Averaging
            double x = 0;
            double y = 0;

            for (Position read : reads) {
                // Flip the x and y axes and y is negative in pedro
                // found from https://docs.limelightvision.io/docs/docs-limelight/pipeline-apriltag/apriltag-coordinate-systems and pedro pathing gen
                x += read.y;
                y -= read.x;
            }

            // Convert to inches from meters
            x *= 39.3701;
            y *= 39.3701;

            x /= reads.size();
            y /= reads.size();

            // Switch to pedro 0" to 144" coordinate system instead of -72" to 72" coordinate system
            x += 72;
            y += 72;

            robot.getFollower().setPose(new Pose(x , y, robot.getFollower().getPose().getHeading()));
        }
    }
}