package Robot.Commands.scoringCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.intakeCommandGroups.intObs;
import Robot.Commands.outtakeCommandGroups.outTransfer;
import Robot.Commands.outtakeCommands.out4BarPivotHighChamber;
import Robot.Commands.outtakeCommands.outClawPivotChamber;
import Robot.Commands.outtakeCommands.outViperHighChamber;
import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;


public class scoringObs extends ParallelCommandGroup {
    private outtakeSubsystem outtakeSubsystem;
    private intakeSubsystem intakeSubsystem;
    public scoringObs(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        addCommands(
                new outTransfer(outtakeSubsystem),
                new intObs(intakeSubsystem)
        );
        addRequirements(this.outtakeSubsystem, this.intakeSubsystem);

    }
}
