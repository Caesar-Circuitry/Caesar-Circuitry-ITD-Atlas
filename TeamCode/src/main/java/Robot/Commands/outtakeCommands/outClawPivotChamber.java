package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class outClawPivotChamber extends CommandBase {
  private final outtakeSubsystem subsystem;

  public outClawPivotChamber(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setOutClawPivotChamber();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
