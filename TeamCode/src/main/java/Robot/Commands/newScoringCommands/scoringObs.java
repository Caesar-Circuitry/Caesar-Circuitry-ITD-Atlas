package Robot.Commands.newScoringCommands;

import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class scoringObs extends CommandBase {

  private final outtakeSubsystem outtakeSubsystem;
  private final intakeSubsystem intakeSubsystem;

  public scoringObs(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem) {
    this.outtakeSubsystem = outtakeSubsystem;
    this.intakeSubsystem = intakeSubsystem;
  }

  @Override
  public void initialize() {
    outtakeSubsystem.openClaw();
    outtakeSubsystem.setOut4BarPivotTransfer();
    outtakeSubsystem.setVertSlideTransfer();
    outtakeSubsystem.setOutClawPivotTransfer();

    // intakeSubsystem.setViperObs();
    intakeSubsystem.openClaw();
    intakeSubsystem.setIntClawPivotObs();
    intakeSubsystem.openClaw();
    intakeSubsystem.setIntPivotObs();
    intakeSubsystem.openClaw();
    intakeSubsystem.openClaw();
    intakeSubsystem.setIntClawRotateMiddle();
    intakeSubsystem.openClaw();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
