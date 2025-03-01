package Robot.Commands.outtakeCommands;

import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class out4BarPivotHighBasket extends CommandBase {
  private final outtakeSubsystem subsystem;

  public out4BarPivotHighBasket(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addRequirements(this.subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setOut4BarPivotHighBasket();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
