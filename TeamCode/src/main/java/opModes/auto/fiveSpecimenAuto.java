package opModes.auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;

import Robot.Commands.intakeCommandGroups.intTransfer;
import Robot.Commands.outtakeCommandGroups.outHighChamber;
import Robot.Commands.scoringCommandGroups.scoringHighChamber;
import Robot.constants;
import Robot.robotContainer;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@Autonomous
public class fiveSpecimenAuto extends LinearOpMode {
    Follower follower;
    private enum auto{
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        ELEVEN,
        TWELVE,
        THIRTEEN,
        END
    }
    private auto Auto = auto.ZERO;
    private final ArrayList<PathChain> paths = new ArrayList<>();
    public void generatePushPath(){
        follower.setStartingPose(new Pose(8, 66, Math.toRadians(180)));
        paths.add(
            follower.pathBuilder()
                .addPath(
                        //line 1
                        new BezierLine(
                                new Point(8.000, 66.000, Point.CARTESIAN),
                                new Point(33.000, 77.000, Point.CARTESIAN)
                        )
                )
                    .setConstantHeadingInterpolation(Math.toRadians(180))
                    .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 2
                                new BezierCurve(
                                        new Point(33.000, 77.000, Point.CARTESIAN),
                                        new Point(23.000, 25.000, Point.CARTESIAN),
                                        new Point(62.000, 37.000, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 3
                                new BezierCurve(
                                        new Point(62.000, 37.000, Point.CARTESIAN),
                                        new Point(74.957, 21.469, Point.CARTESIAN),
                                        new Point(18.000, 24.000, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 4
                                new BezierCurve(
                                        new Point(18.000, 24, Point.CARTESIAN),
                                        new Point(80.000, 22.5, Point.CARTESIAN),
                                        new Point(72.000, 7, Point.CARTESIAN),
                                        new Point(19.000, 10, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 5
                                new BezierCurve(
                                        new Point(19.000, 11.000, Point.CARTESIAN),
                                        new Point(80.000, 14.357, Point.CARTESIAN),
                                        new Point(72.000, 5.000, Point.CARTESIAN),
                                        new Point(19.000, 7.000, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 6
                                new BezierLine(
                                        new Point(19.000, 7.000, Point.CARTESIAN),
                                        new Point(25.000, 39.000, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(220))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 7
                                new BezierLine(
                                        new Point(25.000, 39.000, Point.CARTESIAN),
                                        new Point(37.000, 62.000, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(220), Math.toRadians(180))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 8
                                new BezierLine(
                                        new Point(37.000, 62.000, Point.CARTESIAN),
                                        new Point(25.000, 39.000, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(220))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 9
                                new BezierLine(
                                        new Point(25.000, 39.000, Point.CARTESIAN),
                                        new Point(37.000, 64.500, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(220), Math.toRadians(180))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 10
                                new BezierLine(
                                        new Point(37.000, 64.500, Point.CARTESIAN),
                                        new Point(25.000, 39.000, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(220))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 11
                                new BezierLine(
                                        new Point(25.000, 39.000, Point.CARTESIAN),
                                        new Point(37.000, 67.000, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(220), Math.toRadians(180))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 12
                                new BezierLine(
                                        new Point(37.000, 67.000, Point.CARTESIAN),
                                        new Point(25.000, 39.000, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(220))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 14
                                new BezierLine(
                                        new Point(25.000, 39.000, Point.CARTESIAN),
                                        new Point(37.000, 69.500, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(220), Math.toRadians(180))
                        .build()
        );
        paths.add(
                follower.pathBuilder()
                        .addPath(
                                //line 15
                                new BezierLine(
                                        new Point(37.000, 69.500, Point.CARTESIAN),
                                        new Point(17.000, 12.000, Point.CARTESIAN)
                                )
                        )
                        .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                        .build()
        );
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Constants.setConstants(FConstants.class,LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setMaxPower(1);
        generatePushPath();
        waitForStart();
        while (opModeIsActive()){
        switch (Auto){
            case ZERO:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(0),true);
                    Auto = auto.ONE;
                }
                break;
            case ONE:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(1),true);
                    Auto = auto.TWO;
                }
                break;
            case TWO:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(2),true);
                    Auto = auto.THREE;
                }
                break;
            case THREE:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(3),true);
                    Auto = auto.FOUR;
                }
                break;
            case FOUR:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(4),true);
                    Auto = auto.FIVE;
                }
                break;
            case FIVE:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(5),true);
                    Auto = auto.SIX;
                }
                break;
            case SIX:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(6),true);
                    Auto = auto.SEVEN;
                }
                break;
            case SEVEN:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(7),true);
                    Auto = auto.EIGHT;
                }
                break;
            case EIGHT:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(8),true);
                    Auto = auto.NINE;
                }
                break;
            case NINE:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(9),true);
                    Auto = auto.TEN;
                }
                break;
            case TEN:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(10),true);
                    Auto = auto.ELEVEN;
                }
                break;
            case ELEVEN:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(11),true);
                    Auto = auto.TWELVE;
                }
                break;
            case TWELVE:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(12),true);
                    Auto = auto.THIRTEEN;
                }
                break;
            case THIRTEEN:
                if (!follower.isBusy()){
                    follower.followPath(paths.get(13),true);
                    Auto = auto.END;
                }
                break;
        }
        follower.update();
        }
    }
}
