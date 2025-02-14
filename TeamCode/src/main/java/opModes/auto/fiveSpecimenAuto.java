package opModes.auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.commands.FollowPath;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.localization.Localizer;
import com.pedropathing.localization.Pose;
import com.pedropathing.localization.localizers.ThreeWheelIMULocalizer;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.ArrayList;

import Robot.constants;
import Robot.robotContainer;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@Autonomous
public class fiveSpecimenAuto extends CommandOpMode {
    //robotContainer robot;
    Follower follower;
    private final ArrayList<PathChain> paths = new ArrayList<>();
    public void generatePushPath(){
        follower.setStartingPose(new Pose(8, 66, Math.toRadians(180)));
        paths.add(
            follower.pathBuilder()
                .addPath(
                    new BezierLine(
                            new Point(8, 66, Point.CARTESIAN),
                            new Point(28, 70.000, Point.CARTESIAN)
                    )
                ).setConstantHeadingInterpolation(Math.toRadians(180))
                    .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierCurve(
                                        new Point(39.000, 77.000, Point.CARTESIAN),
                                        new Point(23.000, 25.000, Point.CARTESIAN),
                                        new Point(62.000, 37.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierCurve(
                                        new Point(62.000, 37.000, Point.CARTESIAN),
                                        new Point(74.957, 21.469, Point.CARTESIAN),
                                        new Point(18.000, 24.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierCurve(
                                        new Point(18.000, 24.000, Point.CARTESIAN),
                                        new Point(94.239, 22.563, Point.CARTESIAN),
                                        new Point(76.051, 9.161, Point.CARTESIAN),
                                        new Point(19.000, 11.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierCurve(
                                        new Point(19.000, 11.000, Point.CARTESIAN),
                                        new Point(94.375, 14.357, Point.CARTESIAN),
                                        new Point(79.000, 5.000, Point.CARTESIAN),
                                        new Point(19.000, 7.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(19.000, 7.000, Point.CARTESIAN),
                                        new Point(19.000, 31.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(220))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(19.000, 31.000, Point.CARTESIAN),
                                        new Point(37.000, 59.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(220), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(37.000, 59.000, Point.CARTESIAN),
                                        new Point(19.000, 31.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(220))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(19.000, 31.000, Point.CARTESIAN),
                                        new Point(37.000, 61.000, Point.CARTESIAN)
                                )
                        ) .setLinearHeadingInterpolation(Math.toRadians(220), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(37.000, 61.000, Point.CARTESIAN),
                                        new Point(19.000, 31.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(220))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(19.000, 31.000, Point.CARTESIAN),
                                        new Point(37.000, 63.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(220), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(37.000, 63.000, Point.CARTESIAN),
                                        new Point(19.000, 31.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(220))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(19.000, 31.000, Point.CARTESIAN),
                                        new Point(37.000, 65.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(220), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(37.000, 65.000, Point.CARTESIAN),
                                        new Point(12.000, 25.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                        .build()
        );
    }
    public void generateIntakePath(){
        follower.setStartingPose(new Pose(7, 81, Math.toRadians(180)));
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(7.000, 81.000, Point.CARTESIAN),
                                        new Point(39.000, 77.000, Point.CARTESIAN)
                                )
                        ).setConstantHeadingInterpolation(Math.toRadians(180))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(39.000, 77.000, Point.CARTESIAN),
                                        new Point(25.000, 39.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-40))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(25.000, 39.000, Point.CARTESIAN),
                                        new Point(32.000, 33.000, Point.CARTESIAN)
                                )
                        ).setConstantHeadingInterpolation(Math.toRadians(-40))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(32.000, 33.000, Point.CARTESIAN),
                                        new Point(32.000, 33.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(-40), Math.toRadians(-135))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(32.000, 33.000, Point.CARTESIAN),
                                        new Point(32.000, 22.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(-40))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(32.000, 22.000, Point.CARTESIAN),
                                        new Point(32.000, 22.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(-40), Math.toRadians(-140))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(32.000, 22.000, Point.CARTESIAN),
                                        new Point(32.000, 13.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(-140), Math.toRadians(-40))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(32.000, 13.000, Point.CARTESIAN),
                                        new Point(32.000, 13.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(-40), Math.toRadians(-140))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(32.000, 13.000, Point.CARTESIAN),
                                        new Point(21.000, 34.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(-140), Math.toRadians(-135))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(21.000, 34.000, Point.CARTESIAN),
                                        new Point(38.000, 60.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(38.000, 60.000, Point.CARTESIAN),
                                        new Point(21.000, 34.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-135))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(21.000, 34.000, Point.CARTESIAN),
                                        new Point(38.000, 61.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(38.000, 61.000, Point.CARTESIAN),
                                        new Point(21.000, 34.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-135))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(21.000, 34.000, Point.CARTESIAN),
                                        new Point(38.000, 62.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(38.000, 62.000, Point.CARTESIAN),
                                        new Point(21.000, 34.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-135))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                new BezierLine(
                                        new Point(21.000, 34.000, Point.CARTESIAN),
                                        new Point(38.000, 63.000, Point.CARTESIAN)
                                )
                        ).setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180))
                        .build()
        );

    }
    @Override
    public void initialize() {
        Constants.setConstants(FConstants.class, LConstants.class);
        follower =new Follower(hardwareMap);
        //robot = new robotContainer(hardwareMap, constants.opModeType.AUTONOMOUS);
        super.reset();
        //register(robot.drivetrainSubsystem, robot.intakeSubsystem, robot.outtakeSubsystem);
        follower.setMaxPower(.5);

        generatePushPath();
        schedule(
                new RunCommand(() -> follower.update()),
                //TODO replace wait Command with waitForPathFinished once done tuning
                new SequentialCommandGroup(
                        new FollowPath(follower,paths.get(0), true),
                        new WaitCommand(10000),
                        new FollowPath(follower,paths.get(1), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(2), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(3), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(4), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(5), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(6), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(7), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(8), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(9), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(10), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(11), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(12), true),
                        new WaitCommand(1000),
                        new FollowPath(follower,paths.get(13), true),
                        new WaitCommand(1000)//,
//                        new FollowPath(follower,paths.get(14), true),
//                        new WaitCommand(1000),
//                        new FollowPath(follower,paths.get(15), true),
//                        new WaitCommand(1000)
                )
        );
    }

    @Override
    public void run(){
        super.run();
//        robot.periodic();
    }


    public class waitForPathFinished extends CommandBase{
        Follower follower;
        public waitForPathFinished(Follower follower){
            this.follower = follower;
        }
        @Override
        public boolean isFinished(){
            return follower.isBusy();
        }
    }
}
