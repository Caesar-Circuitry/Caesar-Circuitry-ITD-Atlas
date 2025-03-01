package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class outViperLowBasket extends CommandBase {
  private final outtakeSubsystem subsystem;

  public outViperLowBasket(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setVertSlideLowBasket();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
