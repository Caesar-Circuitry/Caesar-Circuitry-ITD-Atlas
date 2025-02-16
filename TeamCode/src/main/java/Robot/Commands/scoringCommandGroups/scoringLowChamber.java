package Robot.Commands.scoringCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.intakeCommandGroups.intTransfer;
import Robot.Commands.outtakeCommandGroups.outHighBasket;
import Robot.Commands.outtakeCommandGroups.outLowChamber;
import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;


public class scoringLowChamber extends ParallelCommandGroup {
    private outtakeSubsystem outtakeSubsystem;
    private intakeSubsystem intakeSubsystem;
    public scoringLowChamber(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        addCommands(
                new outLowChamber(outtakeSubsystem),
                new intTransfer(intakeSubsystem)
        );
        addRequirements(this.outtakeSubsystem, this.intakeSubsystem);

    }
}
