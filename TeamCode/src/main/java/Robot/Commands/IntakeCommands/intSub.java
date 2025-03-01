package Robot.Commands.IntakeCommands;

import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class intSub extends CommandBase {

  public intSub(intakeSubsystem intake) {
    new intClawPivotSub(intake);
    new intClawRotateMiddle(intake);
    new intSetViperSub(intake);
  }
}
