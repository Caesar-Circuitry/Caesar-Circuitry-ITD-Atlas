package Robot.Commands.IntakeCommands;

import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class intClawPivotTransfer extends CommandBase {
  private final intakeSubsystem i_IntakeSubystem;

  public intClawPivotTransfer(intakeSubsystem subSystem) {
    i_IntakeSubystem = subSystem;
    addRequirements(i_IntakeSubystem);
  }

  @Override
  public void initialize() {
    i_IntakeSubystem.setIntClawPivotTransfer();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
