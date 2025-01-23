package pedroPathing.newRobot.Commands.IntakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import pedroPathing.newRobot.subsystems.intakeSubsystem;

public class intClawClosed extends CommandBase {
    private final intakeSubsystem i_IntakeSubystem;

    public intClawClosed(intakeSubsystem subSystem) {
        i_IntakeSubystem = subSystem;
        addRequirements(i_IntakeSubystem);
    }

    @Override
    public void initialize() {
        i_IntakeSubystem.closeClaw();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}