package Robot.Commands.outtakeCommandGroups;

import Robot.Commands.outtakeCommands.out4BarPivotHighBasket;
import Robot.Commands.outtakeCommands.outClawPivotBasket;
import Robot.Commands.outtakeCommands.outViperHighBasket;
import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

public class outHighBasket extends ParallelCommandGroup {
  private outtakeSubsystem subsystem;

  public outHighBasket(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addCommands(
        new out4BarPivotHighBasket(this.subsystem),
        new outClawPivotBasket(this.subsystem),
        new outViperHighBasket(this.subsystem));
    addRequirements(this.subsystem);
  }
}
