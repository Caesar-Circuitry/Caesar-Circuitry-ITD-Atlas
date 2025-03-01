package Robot.Commands.IntakeCommands;

import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class intSetViperObsFirst extends CommandBase {
  private final intakeSubsystem i_IntakeSubystem;

  public intSetViperObsFirst(intakeSubsystem subSystem) {
    i_IntakeSubystem = subSystem;
    addRequirements(i_IntakeSubystem);
  }

  @Override
  public void initialize() {
    i_IntakeSubystem.setViperObsShort();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
