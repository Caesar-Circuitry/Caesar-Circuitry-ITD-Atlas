package Robot.Commands.outtakeCommandGroups;

import Robot.Commands.outtakeCommands.out4BarPivotHighChamber;
import Robot.Commands.outtakeCommands.outClawPivotChamber;
import Robot.Commands.outtakeCommands.outViperHighChamber;
import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

public class outHighChamber extends ParallelCommandGroup {
  private outtakeSubsystem subsystem;

  public outHighChamber(outtakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addCommands(
        new out4BarPivotHighChamber(this.subsystem),
        new outClawPivotChamber(this.subsystem),
        new outViperHighChamber(this.subsystem));
    addRequirements(this.subsystem);
  }
}
