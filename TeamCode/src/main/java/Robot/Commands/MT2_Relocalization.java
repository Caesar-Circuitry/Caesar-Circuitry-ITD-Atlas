package Robot.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.localization.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

import java.util.ArrayList;


import Robot.subsystems.drivetrainSubsystem;

//Credit to Team 23511
public class MT2_Relocalization extends CommandBase {
    private final int neededReads;
    private final ArrayList<Position> reads = new ArrayList<>();
    private final drivetrainSubsystem subsystem;
    private final ElapsedTime timer = new ElapsedTime();

    public MT2_Relocalization(drivetrainSubsystem subsystem, int neededReads) {
        this.subsystem = subsystem;
        this.neededReads = neededReads;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        updateRobotOrientation();
        collectLimelightData();
    }

    private void updateRobotOrientation() {
        double heading = Math.toDegrees(AngleUnit.normalizeRadians(subsystem.getFollower().getPose().getHeading() - Math.PI / 2));
        subsystem.getLimelight().updateRobotOrientation(heading);
    }

    private void collectLimelightData() {
        LLResult result = subsystem.getLimelight().getLatestResult();
        if (result != null && result.isValid()) {
            reads.add(result.getBotpose_MT2().getPosition());
        }
    }

    @Override
    public void execute() {
        collectLimelightData();
    }

    @Override
    public boolean isFinished() {
        return (reads.size() >= neededReads) || (timer.milliseconds() >= 400);
    }

    @Override
    public void end(boolean interrupted) {
        if (!reads.isEmpty()) {
            Pose averagedPose = calculateAveragedPose();
            subsystem.getFollower().setPose(averagedPose);
        }
    }

    private Pose calculateAveragedPose() {
        double x = 0;
        double y = 0;

        for (Position read : reads) {
            x += read.y; // Flip the x and y axes
            y -= read.x; // y is negative in Pedro
        }

        // Convert to inches from meters and average
        x = (x * 39.3701 / reads.size()) + 72; // Switch to Pedro coordinate system
        y = (y * 39.3701 / reads.size()) + 72;

        return new Pose(x, y, subsystem.getFollower().getPose().getHeading());
    }
}