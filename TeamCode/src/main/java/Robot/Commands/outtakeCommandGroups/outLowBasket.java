package Robot.Commands.outtakeCommandGroups;

import Robot.Commands.outtakeCommands.out4BarPivotLowBasket;
import Robot.Commands.outtakeCommands.outClawPivotBasket;
import Robot.Commands.outtakeCommands.outViperLowBasket;
import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

public class outLowBasket extends ParallelCommandGroup {
  private outtakeSubsystem subsystem;

  public outLowBasket(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addCommands(
        new out4BarPivotLowBasket(this.subsystem),
        new outClawPivotBasket(this.subsystem),
        new outViperLowBasket(this.subsystem));
    addRequirements(this.subsystem);
  }
}
