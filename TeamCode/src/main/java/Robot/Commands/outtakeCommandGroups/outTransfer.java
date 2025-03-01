package Robot.Commands.outtakeCommandGroups;

import Robot.Commands.outtakeCommands.out4BarPivotTransfer;
import Robot.Commands.outtakeCommands.outClawPivotTransfer;
import Robot.Commands.outtakeCommands.outViperTransfer;
import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

public class outTransfer extends ParallelCommandGroup {
  private outtakeSubsystem subsystem;

  public outTransfer(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addCommands(
        new out4BarPivotTransfer(this.subsystem),
        new outClawPivotTransfer(this.subsystem),
        new outViperTransfer(this.subsystem));
    addRequirements(this.subsystem);
  }
}
