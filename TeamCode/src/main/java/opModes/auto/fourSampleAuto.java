package opModes.auto;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.pedropathing.commands.FollowPath;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.util.ArrayList;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@Autonomous
public class fourSampleAuto extends CommandOpMode {
  // robotContainer robot;
  Follower follower;
  private final ArrayList<PathChain> paths = new ArrayList<>();

  public void generatePushPath() {
    follower.setStartingPose(new Pose(10, 118, Math.toRadians(-45)));
    paths.add(
        follower
            .pathBuilder()
            .addPath(
                // Line 1
                new BezierLine(
                    new Point(10.000, 118.000, Point.CARTESIAN),
                    new Point(17.000, 128.000, Point.CARTESIAN)))
            .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(-45))
            .build());
    paths.add(
        follower
            .pathBuilder()
            .addPath(
                // line 2
                new BezierLine(
                    new Point(17.000, 128.000, Point.CARTESIAN),
                    new Point(22.000, 128.000, Point.CARTESIAN)))
            .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(-25))
            .build());
    paths.add(
        follower
            .pathBuilder()
            .addPath(
                // line 3
                new BezierLine(
                    new Point(22.000, 128.000, Point.CARTESIAN),
                    new Point(17.000, 128.000, Point.CARTESIAN)))
            .setLinearHeadingInterpolation(Math.toRadians(-25), Math.toRadians(-45))
            .build());
    paths.add(
        follower
            .pathBuilder()
            .addPath(
                // line 4
                new BezierLine(
                    new Point(17.000, 128.000, Point.CARTESIAN),
                    new Point(22.000, 128.000, Point.CARTESIAN)))
            .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(10))
            .build());
    paths.add(
        follower
            .pathBuilder()
            .addPath(
                // line 5
                new BezierLine(
                    new Point(17.000, 128.000, Point.CARTESIAN),
                    new Point(22.000, 128.000, Point.CARTESIAN)))
            .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(10))
            .build());
    paths.add(
        follower
            .pathBuilder()
            .addPath(
                // line 6
                new BezierLine(
                    new Point(17.000, 128.000, Point.CARTESIAN),
                    new Point(32.000, 124.000, Point.CARTESIAN)))
            .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(60))
            .build());
    paths.add(
        follower
            .pathBuilder()
            .addPath(
                // line 7
                new BezierLine(
                    new Point(32.000, 124.000, Point.CARTESIAN),
                    new Point(17.000, 128.000, Point.CARTESIAN)))
            .setLinearHeadingInterpolation(Math.toRadians(60), Math.toRadians(-45))
            .build());
    paths.add(
        follower
            .pathBuilder()
            .addPath(
                // line 8
                new BezierCurve(
                    new Point(17.000, 128.000, Point.CARTESIAN),
                    new Point(62.000, 108.000, Point.CARTESIAN),
                    new Point(60.000, 96.000, Point.CARTESIAN)))
            .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(90))
            .build());
  }

  @Override
  public void initialize() {
    Constants.setConstants(FConstants.class, LConstants.class);
    follower = new Follower(hardwareMap);
    // robot = new robotContainer(hardwareMap, constants.opModeType.AUTONOMOUS);
    super.reset();
    // register(robot.drivetrainSubsystem, robot.intakeSubsystem, robot.outtakeSubsystem);
    follower.setMaxPower(1);

    generatePushPath();
    schedule(
        new RunCommand(() -> follower.update()),
        new SequentialCommandGroup(
            new FollowPath(follower, paths.get(0), true),
            new waitForPathFinished(follower),
            new FollowPath(follower, paths.get(1), true),
            new waitForPathFinished(follower),
            new FollowPath(follower, paths.get(2), true),
            new waitForPathFinished(follower),
            new FollowPath(follower, paths.get(3), true),
            new waitForPathFinished(follower),
            new FollowPath(follower, paths.get(4), true),
            new waitForPathFinished(follower),
            new FollowPath(follower, paths.get(5), true),
            new waitForPathFinished(follower),
            new FollowPath(follower, paths.get(6), true),
            new waitForPathFinished(follower),
            new FollowPath(follower, paths.get(7), true),
            new waitForPathFinished(follower),
            new FollowPath(follower, paths.get(8), true)));
  }

  @Override
  public void run() {
    super.run();
    //        robot.periodic();
  }

  public class waitForPathFinished extends CommandBase {
    Follower follower;

    public waitForPathFinished(Follower follower) {
      this.follower = follower;
    }

    @Override
    public boolean isFinished() {
      return follower.isBusy();
    }
  }
}
