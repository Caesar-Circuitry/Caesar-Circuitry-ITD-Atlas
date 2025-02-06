package opModes.Test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Robot.constants;
import Robot.robotContainer;

@TeleOp
public class outtakeConstantsTuner extends CommandOpMode {

    private robotContainer robot;
    private GamepadEx driver;
    private GamepadEx operator;
    private MultipleTelemetry multipleTelemetry;

    @Override
    public void initialize() {

        super.reset();

        robot = new robotContainer(hardwareMap, constants.opModeType.TELEOP);

        register(robot.outtakeSubsystem,robot.drivetrainSubsystem,robot.intakeSubsystem);

        driver =new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        driver.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.openClaw())
        );
        driver.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.closeClaw())
        );
        driver.getGamepadButton(GamepadKeys.Button.X).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setOut4BarPivotHighBasket())
        );
        driver.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setOut4BarPivotHighChamber())
        );
        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setOut4BarPivotLowBasket())
        );
        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setOut4BarPivotLowChamber())
        );
        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setOut4BarPivotTransfer())
        );
        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setOutClawPivotBasket())
        );
        operator.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setOutClawPivotChamber())
        );
        operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setOutClawPivotTransfer())
        );
        operator.getGamepadButton(GamepadKeys.Button.X).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setVertSlideHighBasket())
        );
        operator.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setVertSlideHighChamber())
        );
        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setVertSlideLowBasket())
        );
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setVertSlideLowChamber())
        );
        operator.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setVertSlideTransfer())
        );
        operator.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                new InstantCommand(() -> robot.outtakeSubsystem.setVertSlideZero())
        );

        super.run();

    }

    @Override
    public void run(){
        multipleTelemetry.addLine("Driver A - Open Claw");
        multipleTelemetry.addLine("Driver B - Close Claw ");
        multipleTelemetry.addLine("Driver X - 4 Bar pivot High Basket");
        multipleTelemetry.addLine("Driver Y - 4 bar pivot High Chamber");
        multipleTelemetry.addLine("Driver DPAD UP - 4 bar pivot Low Basket");
        multipleTelemetry.addLine("Driver DPAD LEFT - 4 bar pivot Low Chamber");
        multipleTelemetry.addLine("Driver DPAD RIGHT - 4 bar pivot Transfer");
        multipleTelemetry.addLine("Driver DPAD DOWN - claw Pivot Basket");
        multipleTelemetry.addLine("Operator A - claw Pivot Chamber");
        multipleTelemetry.addLine("Operator B - claw Pivot Transfer");
        multipleTelemetry.addLine("Operator X - Slide High Basket");
        multipleTelemetry.addLine("Operator Y - Slide High Chamber");
        multipleTelemetry.addLine("Operator DPAD UP - slide Low Basket");
        multipleTelemetry.addLine("Operator DPAD DOWN - Slide Low Chamber");
        multipleTelemetry.addLine("Operator DPAD LEFT - Slide Transfer");
        multipleTelemetry.addLine("Operator DPAD RIGHT - Slide Zero");
        multipleTelemetry.update();
        super.run();
        robot.periodic();
    }
}
