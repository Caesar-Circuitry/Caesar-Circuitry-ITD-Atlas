package Robot.Commands.IntakeCommands;

import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class intClawRotateMiddle extends CommandBase {
  private final intakeSubsystem i_IntakeSubystem;

  public intClawRotateMiddle(intakeSubsystem subSystem) {
    i_IntakeSubystem = subSystem;
    addRequirements(i_IntakeSubystem);
  }

  @Override
  public void initialize() {
    i_IntakeSubystem.setIntClawRotateMiddle();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
