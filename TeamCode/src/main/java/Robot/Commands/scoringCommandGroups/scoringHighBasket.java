package Robot.Commands.scoringCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.intakeCommandGroups.intObs;
import Robot.Commands.intakeCommandGroups.intTransfer;
import Robot.Commands.outtakeCommandGroups.outHighBasket;
import Robot.Commands.outtakeCommandGroups.outTransfer;
import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;


public class scoringHighBasket extends ParallelCommandGroup {
    private outtakeSubsystem outtakeSubsystem;
    private intakeSubsystem intakeSubsystem;
    public scoringHighBasket(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        addCommands(
                new outHighBasket(outtakeSubsystem),
                new intTransfer(intakeSubsystem)
        );
        addRequirements(this.outtakeSubsystem, this.intakeSubsystem);

    }
}
