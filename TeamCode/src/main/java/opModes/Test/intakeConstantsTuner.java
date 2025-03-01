package opModes.Test;

import Robot.constants;
import Robot.robotContainer;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class intakeConstantsTuner extends CommandOpMode {

  private robotContainer robot;
  private GamepadEx driver;
  private GamepadEx operator;
  private MultipleTelemetry multipleTelemetry;

  @Override
  public void initialize() {

    super.reset();

    robot = new robotContainer(hardwareMap, constants.opModeType.TELEOP);

    register(robot.outtakeSubsystem, robot.drivetrainSubsystem, robot.intakeSubsystem);

    driver = new GamepadEx(gamepad1);
    operator = new GamepadEx(gamepad2);
    multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

    driver
        .getGamepadButton(GamepadKeys.Button.A)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.openClaw()));
    driver
        .getGamepadButton(GamepadKeys.Button.B)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.closeClaw()));
    driver
        .getGamepadButton(GamepadKeys.Button.X)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.setIntClawPivotObs()));
    driver
        .getGamepadButton(GamepadKeys.Button.Y)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.setIntClawPivotSub()));
    driver
        .getGamepadButton(GamepadKeys.Button.DPAD_UP)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.setIntClawPivotTransfer()));
    driver
        .getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.setIntClawRotateLeft()));
    driver
        .getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.setIntClawRotateMiddle()));
    driver
        .getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.setIntClawRotateRight()));
    operator
        .getGamepadButton(GamepadKeys.Button.A)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.setViperObs()));
    operator
        .getGamepadButton(GamepadKeys.Button.B)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.setViperSub()));
    operator
        .getGamepadButton(GamepadKeys.Button.X)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.setViperTransfer()));
    operator
        .getGamepadButton(GamepadKeys.Button.Y)
        .whenPressed(new InstantCommand(() -> robot.intakeSubsystem.setViperZero()));

    super.run();
  }

  @Override
  public void run() {
    multipleTelemetry.addLine("Driver A - Open Claw");
    multipleTelemetry.addLine("Driver B - Close Claw ");
    multipleTelemetry.addLine("Driver X - Set Intake Claw Pivot to Observation zone");
    multipleTelemetry.addLine("Driver Y - Set Intake Claw Pivot to Sub");
    multipleTelemetry.addLine("Driver DPAD UP - Set Intake Claw Pivot to Transfer");
    multipleTelemetry.addLine("Driver DPAD LEFT - Rotate Intake Claw Left");
    multipleTelemetry.addLine("Driver DPAD RIGHT - Rotate Intake Claw to Middle");
    multipleTelemetry.addLine("Driver DPAD DOWN - Rotate Intake Claw Right");
    multipleTelemetry.addLine("Operator A - Set Viper to Obstacle");
    multipleTelemetry.addLine("Operator B - Set Viper to Sub");
    multipleTelemetry.addLine("Operator X - Set Viper to Transfer");
    multipleTelemetry.addLine("Operator Y - Set Viper to Zero");
    multipleTelemetry.update();
    super.run();
    robot.periodic();
  }
}
