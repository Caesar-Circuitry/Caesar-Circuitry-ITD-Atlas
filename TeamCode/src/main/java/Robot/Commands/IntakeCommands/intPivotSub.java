package Robot.Commands.IntakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;

public class intPivotSub extends CommandBase {
    private final intakeSubsystem i_IntakeSubystem;

    public intPivotSub(intakeSubsystem subSystem) {
        i_IntakeSubystem = subSystem;
        addRequirements(i_IntakeSubystem);
    }

    @Override
    public void initialize() {
        i_IntakeSubystem.setIntPivotSub();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}