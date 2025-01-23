package pedroPathing.newRobot.Commands.IntakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import pedroPathing.newRobot.subsystems.intakeSubsystem;

public class intSetViperTransfer extends CommandBase {
    private final intakeSubsystem i_IntakeSubystem;

    public intSetViperTransfer(intakeSubsystem subSystem) {
        i_IntakeSubystem = subSystem;
        addRequirements(i_IntakeSubystem);
    }

    @Override
    public void initialize() {
        i_IntakeSubystem.setViperTransfer();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}