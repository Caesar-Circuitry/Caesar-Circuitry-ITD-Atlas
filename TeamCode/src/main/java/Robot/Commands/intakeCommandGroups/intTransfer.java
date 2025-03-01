package Robot.Commands.intakeCommandGroups;

import Robot.Commands.IntakeCommands.intClawPivotTransfer;
import Robot.Commands.IntakeCommands.intClawRotateMiddle;
import Robot.Commands.IntakeCommands.intPivotTransfer;
import Robot.Commands.IntakeCommands.intSetViperTransfer;
import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

public class intTransfer extends ParallelCommandGroup {
  private intakeSubsystem subsystem;

  public intTransfer(intakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addCommands(
        new intClawPivotTransfer(this.subsystem),
        new intClawRotateMiddle(this.subsystem),
        new intSetViperTransfer(this.subsystem),
        new intPivotTransfer(this.subsystem));
    addRequirements(this.subsystem);
  }
}
