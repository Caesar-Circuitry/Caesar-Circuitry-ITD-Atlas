package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class outClawClose extends CommandBase {
  private final outtakeSubsystem subsystem;

  public outClawClose(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.closeClaw();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
