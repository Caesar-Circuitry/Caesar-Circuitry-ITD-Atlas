package Robot.Commands.scoringCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.intakeCommandGroups.intObs;
import Robot.Commands.intakeCommandGroups.intSub;
import Robot.Commands.outtakeCommandGroups.outTransfer;
import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;


public class scoringSub extends ParallelCommandGroup {
    private outtakeSubsystem outtakeSubsystem;
    private intakeSubsystem intakeSubsystem;
    public scoringSub(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        addCommands(
                new outTransfer(outtakeSubsystem),
                new intSub(intakeSubsystem)
        );
        addRequirements(this.outtakeSubsystem, this.intakeSubsystem);

    }
}
