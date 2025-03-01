package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class outClawPivotTransfer extends CommandBase {
  private final outtakeSubsystem subsystem;

  public outClawPivotTransfer(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setOutClawPivotTransfer();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
