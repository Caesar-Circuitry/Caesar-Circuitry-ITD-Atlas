package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class out4BarPivotLowChamber extends CommandBase {
  private final outtakeSubsystem subsystem;

  public out4BarPivotLowChamber(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setOut4BarPivotLowChamber();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
