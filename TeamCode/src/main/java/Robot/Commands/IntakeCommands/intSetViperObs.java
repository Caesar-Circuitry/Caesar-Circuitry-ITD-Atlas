package Robot.Commands.IntakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;

public class intSetViperObs extends CommandBase {
    private final intakeSubsystem i_IntakeSubystem;

    public intSetViperObs(intakeSubsystem subSystem) {
        i_IntakeSubystem = subSystem;
        addRequirements(i_IntakeSubystem);
    }

    @Override
    public void initialize() {
        i_IntakeSubystem.setViperObs();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}