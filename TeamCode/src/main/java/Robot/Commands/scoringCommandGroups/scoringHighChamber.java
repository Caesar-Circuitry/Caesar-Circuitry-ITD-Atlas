package Robot.Commands.scoringCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.intakeCommandGroups.intTransfer;
import Robot.Commands.outtakeCommandGroups.outHighBasket;
import Robot.Commands.outtakeCommandGroups.outHighChamber;
import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;


public class scoringHighChamber extends ParallelCommandGroup {
    private outtakeSubsystem outtakeSubsystem;
    private intakeSubsystem intakeSubsystem;
    public scoringHighChamber(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        addCommands(
                new outHighChamber(outtakeSubsystem),
                new intTransfer(intakeSubsystem)
        );
        addRequirements(this.outtakeSubsystem, this.intakeSubsystem);

    }
}
