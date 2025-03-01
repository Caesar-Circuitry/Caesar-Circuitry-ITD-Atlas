package Robot.Commands.IntakeCommands;

import Robot.subsystems.intakeSubsystem;
import com.arcrobotics.ftclib.command.CommandBase;

public class intClawPivotSub extends CommandBase {
  private final intakeSubsystem i_IntakeSubystem;

  public intClawPivotSub(intakeSubsystem subSystem) {
    i_IntakeSubystem = subSystem;
    addRequirements(i_IntakeSubystem);
  }

  @Override
  public void initialize() {
    i_IntakeSubystem.setIntClawPivotSub();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
