package Robot.Commands.intakeCommandGroups;

import Robot.Commands.IntakeCommands.intClawPivotSub;
import Robot.Commands.IntakeCommands.intClawRotateMiddle;
import Robot.Commands.IntakeCommands.intPivotSub;
import Robot.Commands.IntakeCommands.intSetViperSub;
import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

public class intSub extends ParallelCommandGroup {
  private intakeSubsystem subsystem;

  public intSub(intakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addCommands(
        new intClawPivotSub(this.subsystem),
        new intClawRotateMiddle(this.subsystem),
        new intSetViperSub(this.subsystem),
        new intPivotSub(this.subsystem));
    addRequirements(this.subsystem);
  }
}
