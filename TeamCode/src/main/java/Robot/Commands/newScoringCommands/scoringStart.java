package Robot.Commands.newScoringCommands;

import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class scoringStart extends CommandBase {

  private final outtakeSubsystem outtakeSubsystem;
  private final intakeSubsystem intakeSubsystem;

  public scoringStart(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem) {
    this.outtakeSubsystem = outtakeSubsystem;
    this.intakeSubsystem = intakeSubsystem;
  }

  @Override
  public void initialize() {
    outtakeSubsystem.closeClaw();
    outtakeSubsystem.setOut4BarPivotSart();
    outtakeSubsystem.setVertSlideTransfer();
    outtakeSubsystem.setOutClawPivotChamber();

    intakeSubsystem.openClaw();
    intakeSubsystem.setIntClawPivotStart();
    intakeSubsystem.setIntPivotStart();
    intakeSubsystem.setViperTransfer();
    intakeSubsystem.setIntClawRotateMiddle();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
