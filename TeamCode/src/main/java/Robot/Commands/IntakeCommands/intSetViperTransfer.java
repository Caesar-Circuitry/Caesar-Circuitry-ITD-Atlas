package Robot.Commands.IntakeCommands;

import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

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
