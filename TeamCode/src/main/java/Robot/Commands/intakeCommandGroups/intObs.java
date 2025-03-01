package Robot.Commands.intakeCommandGroups;

import Robot.Commands.IntakeCommands.intClawPivotObs;
import Robot.Commands.IntakeCommands.intClawRotateMiddle;
import Robot.Commands.IntakeCommands.intPivotObs;
import Robot.Commands.IntakeCommands.intSetViperObs;
import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

public class intObs extends ParallelCommandGroup {
  private intakeSubsystem subsystem;

  public intObs(intakeSubsystem subsystem) {
    this.subsystem = subsystem;
    addCommands(
        new intClawPivotObs(this.subsystem),
        new intClawRotateMiddle(this.subsystem),
        new intSetViperObs(this.subsystem),
        new intPivotObs(this.subsystem));
    addRequirements(this.subsystem);
  }
}
