package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class outViperLowChamber extends CommandBase {
  private final outtakeSubsystem subsystem;

  public outViperLowChamber(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setVertSlideLowChamber();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
