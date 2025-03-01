package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class outViperHang extends CommandBase {
  private final outtakeSubsystem subsystem;

  public outViperHang(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setVertSlideHang();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
