package Robot.Commands.IntakeCommands;

import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

public class intObs extends ParallelCommandGroup {

  public intObs(intakeSubsystem intake) {
    new intClawPivotObs(intake);
    new intClawRotateMiddle(intake);
    new intSetViperObs(intake);
  }
}
