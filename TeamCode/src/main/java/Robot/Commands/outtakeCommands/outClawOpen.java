package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class outClawOpen extends CommandBase {

  private final outtakeSubsystem subsystem;

  public outClawOpen(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.openClaw();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
