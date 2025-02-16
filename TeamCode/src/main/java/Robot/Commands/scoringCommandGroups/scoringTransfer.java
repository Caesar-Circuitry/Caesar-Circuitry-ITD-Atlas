package Robot.Commands.scoringCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.intakeCommandGroups.intObs;
import Robot.Commands.intakeCommandGroups.intTransfer;
import Robot.Commands.outtakeCommandGroups.outTransfer;
import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;


public class scoringTransfer extends ParallelCommandGroup {
    private outtakeSubsystem outtakeSubsystem;
    private intakeSubsystem intakeSubsystem;
    public scoringTransfer(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        addCommands(
                new outTransfer(outtakeSubsystem),
                new intTransfer(intakeSubsystem)
        );
        addRequirements(this.outtakeSubsystem, this.intakeSubsystem);
    }
}
