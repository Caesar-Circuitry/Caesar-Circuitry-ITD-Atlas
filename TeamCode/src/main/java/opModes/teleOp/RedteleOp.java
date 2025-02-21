package opModes.teleOp;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.pedropathing.commands.FollowPath;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
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
public class RedteleOp extends CommandOpMode {
private robotContainer robot;
private GamepadEx driver, operator;
private ElapsedTime transferTimer;
private boolean fieldCentric = false;
private boolean followingPath = false;
private boolean transfer = false;
    @Override
    public void initialize(){
        super.reset();
        transferTimer = new ElapsedTime();
        transferTimer.reset();
        robot = new robotContainer(hardwareMap, constants.opModeType.TELEOP);
        robot.setOffset(constants.teleStartPose);
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        register(robot.drivetrainSubsystem,robot.outtakeSubsystem,robot.intakeSubsystem);
        driverMapping();
        operatorMapping();
    }

    @Override
    public void run() {
        super.run();
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
                    new InstantCommand(robot.outtakeSubsystem::closeClaw)
                );
                transfer = false;
            }
        }
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
                    new intClawOpen(robot.intakeSubsystem),
                    new scoringHighChamberClip(robot.outtakeSubsystem,robot.intakeSubsystem),
                    new intSetViperTransfer(robot.intakeSubsystem),
                    new MT2_Relocalization(robot.drivetrainSubsystem, 5)
                )
        );
        operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new SequentialCommandGroup(
                    new scoringTransfer(robot.outtakeSubsystem,robot.intakeSubsystem),
                    new intSetViperTransfer(robot.intakeSubsystem),
                    new InstantCommand(this::enableTransfer)
                ));
        operator.getGamepadButton(GamepadKeys.Button.X).whenPressed(
                new SequentialCommandGroup(
                    new intClawOpen(robot.intakeSubsystem),
                    new out4BarPivotHighChamber(robot.outtakeSubsystem),
                    new scoringHighChamber(robot.outtakeSubsystem,robot.intakeSubsystem),
                    new intSetViperTransfer(robot.intakeSubsystem)
                )
        );
        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(robot.outtakeSubsystem::openClaw),
                        new InstantCommand(robot.intakeSubsystem::openClaw)
                )
        );
        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(robot.outtakeSubsystem::closeClaw),
                        new InstantCommand(robot.intakeSubsystem::closeClaw)
                )
        );
        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new SequentialCommandGroup(
                    new intClawOpen(robot.intakeSubsystem),
                    new scoringSub(robot.outtakeSubsystem,robot.intakeSubsystem),
                    new intSetViperSub(robot.intakeSubsystem)
                )
        );
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new SequentialCommandGroup(
                    new intClawOpen(robot.intakeSubsystem),
                    new scoringObs(robot.outtakeSubsystem,robot.intakeSubsystem),
                    new intSetViperObs(robot.intakeSubsystem)
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
                        new Point(new Pose(107,72.000, Math.toRadians(0)))))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        return path;
    }
    private PathChain toObs(){
        PathChain path;
        path = robot.drivetrainSubsystem.follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(robot.drivetrainSubsystem.follower.getPose()),
                                new Point(new Pose(118,96, Math.toRadians(180)))))
                .setConstantHeadingInterpolation(Math.toRadians(180))
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
