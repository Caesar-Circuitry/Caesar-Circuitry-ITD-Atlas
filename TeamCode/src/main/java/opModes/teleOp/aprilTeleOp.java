package opModes.teleOp;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.pedropathing.commands.FollowPath;
import com.pedropathing.localization.Pose;
import com.pedropathing.localization.PoseUpdater;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.DashboardPoseTracker;
import com.pedropathing.util.Drawing;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import Robot.Commands.IntakeCommands.intClawOpen;
import Robot.Commands.IntakeCommands.intSetViperObs;
import Robot.Commands.IntakeCommands.intSetViperSub;
import Robot.Commands.IntakeCommands.intSetViperTransfer;
import Robot.Commands.MT2_Relocalization;
import Robot.Commands.newScoringCommands.scoringHighChamber;
import Robot.Commands.newScoringCommands.scoringHighChamberClip;
import Robot.Commands.newScoringCommands.scoringObs;
import Robot.Commands.newScoringCommands.scoringSub;
import Robot.Commands.newScoringCommands.scoringTransfer;
import Robot.Commands.outtakeCommands.out4BarPivotHighChamber;
import Robot.constants;
import Robot.robotContainer;

@TeleOp
public class aprilTeleOp extends CommandOpMode {
private robotContainer robot;
private GamepadEx driver, operator;
private ElapsedTime transferTimer;
private boolean fieldCentric = false;
private boolean followingPath = false;
private boolean transfer = false;
private DashboardPoseTracker dashboardPoseTracker;
    @Override
    public void initialize(){
        super.reset();
        transferTimer = new ElapsedTime();
        transferTimer.reset();
        robot = new robotContainer(hardwareMap, constants.opModeType.TELEOP);
        robot.setOffset(constants.teleStartPose);
        dashboardPoseTracker = new DashboardPoseTracker(robot.drivetrainSubsystem.follower.poseUpdater);
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        register(robot.drivetrainSubsystem,robot.outtakeSubsystem,robot.intakeSubsystem);
        driverMapping();
        operatorMapping();
        Drawing.drawRobot(robot.drivetrainSubsystem.follower.getPose(), "#4CAF50");
        Drawing.sendPacket();
    }

    @Override
    public void run() {
        super.run();
        dashboardPoseTracker.update();

        if (followingPath){
            if (!robot.drivetrainSubsystem.follower.isBusy()){
                robot.drivetrainSubsystem.follower.breakFollowing();
                disableFollowingPath();
                robot.drivetrainSubsystem.follower.startTeleopDrive();
            }
        }else{
            robot.drivetrainSubsystem.follower.setTeleOpMovementVectors(-gamepad1.left_stick_y,-gamepad1.left_stick_x/2,-gamepad1.right_stick_x,!fieldCentric);
        }
        if (transfer){
            if (transferTimer.milliseconds()>1000){
                schedule(
                );
                transfer = false;
            }
        }
        Drawing.drawPoseHistory(dashboardPoseTracker, "#4CAF50");
        Drawing.drawRobot(robot.drivetrainSubsystem.follower.getPose(), "#4CAF50");
        Drawing.sendPacket();
        telemetry.addData("pose",robot.drivetrainSubsystem.follower.getPose());
        telemetry.update();
        robot.periodic();
    }

    private void driverMapping(){
            driver.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new InstantCommand(this::enableFieldCentric)
            );
            driver.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new InstantCommand(this::disableFieldCentric)
            );
            driver.getGamepadButton(GamepadKeys.Button.X).whenPressed(//to specimen
                new SequentialCommandGroup(
                        new FollowPath(robot.drivetrainSubsystem.follower, toSpeciman(), false),
                        new InstantCommand(this::enableFollowingPath)
                )
            );
            driver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                    new SequentialCommandGroup(
                            new FollowPath(robot.drivetrainSubsystem.follower, toObs(), false),
                            new InstantCommand(this::enableFollowingPath)
                    )
            );
    }

    private void operatorMapping(){
        operator.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new SequentialCommandGroup(
                    new MT2_Relocalization(robot.drivetrainSubsystem, 5)
                )
        );

    }

    private void enableFieldCentric(){
        fieldCentric = true;
    }
    private void disableFieldCentric(){
        fieldCentric = false;
    }
    private PathChain toSpeciman(){
        PathChain path;
        path = robot.drivetrainSubsystem.follower.pathBuilder()
                .addPath(
                new BezierLine(
                        new Point(robot.drivetrainSubsystem.follower.getPose()),
                        new Point(new Pose(37,72.000, Math.toRadians(180)))))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();
        return path;
    }
    private PathChain toObs(){
        PathChain path;
        path = robot.drivetrainSubsystem.follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(robot.drivetrainSubsystem.follower.getPose()),
                                new Point(new Pose(26,48, Math.toRadians(0)))))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        return path;
    }
    private void enableFollowingPath(){
        followingPath = true;
    }
    private void disableFollowingPath(){
        followingPath = false;
    }
    private void enableTransfer(){
        transferTimer.reset();
        transfer = true;
    }
    private void disableTransfer(){
        transfer = false;
    }
}
