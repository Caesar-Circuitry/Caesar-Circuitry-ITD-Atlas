package Robot.Commands.IntakeCommands;

import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class intPivotTransfer extends CommandBase {
  private final intakeSubsystem i_IntakeSubystem;

  public intPivotTransfer(intakeSubsystem subSystem) {
    i_IntakeSubystem = subSystem;
    addRequirements(i_IntakeSubystem);
  }

  @Override
  public void initialize() {
    i_IntakeSubystem.setIntPivotTransfer();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
