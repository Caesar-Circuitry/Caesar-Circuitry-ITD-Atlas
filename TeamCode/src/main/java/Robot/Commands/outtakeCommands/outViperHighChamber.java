package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class outViperHighChamber extends CommandBase {
  private final outtakeSubsystem subsystem;

  public outViperHighChamber(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setVertSlideHighChamber();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
