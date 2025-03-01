package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class outViperZero extends CommandBase {
  private final outtakeSubsystem subsystem;

  public outViperZero(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setVertSlideZero();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
