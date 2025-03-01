package Robot.Commands.IntakeCommands;

import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

public class intTransfer extends ParallelCommandGroup {

  public intTransfer(intakeSubsystem intake) {
    new intClawPivotTransfer(intake);
    new intClawRotateMiddle(intake);
    new intSetViperTransfer(intake);
  }
}
