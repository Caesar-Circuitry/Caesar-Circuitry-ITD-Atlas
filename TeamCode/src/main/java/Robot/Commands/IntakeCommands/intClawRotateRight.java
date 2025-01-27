package Robot.Commands.IntakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;

public class intClawRotateRight extends CommandBase {
    private final intakeSubsystem i_IntakeSubystem;

    public intClawRotateRight(intakeSubsystem subSystem) {
        i_IntakeSubystem = subSystem;
        addRequirements(i_IntakeSubystem);
    }

    @Override
    public void initialize() {
        i_IntakeSubystem.setIntClawRotateRight();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}