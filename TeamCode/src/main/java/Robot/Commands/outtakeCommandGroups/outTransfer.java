package Robot.Commands.outtakeCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.outtakeCommands.out4BarPivotHighChamber;
import Robot.Commands.outtakeCommands.out4BarPivotTransfer;
import Robot.Commands.outtakeCommands.outClawPivotChamber;
import Robot.Commands.outtakeCommands.outClawPivotTransfer;
import Robot.Commands.outtakeCommands.outViperHighChamber;
import Robot.Commands.outtakeCommands.outViperTransfer;
import Robot.subsystems.outtakeSubsystem;


public class outTransfer extends ParallelCommandGroup {
    private outtakeSubsystem subsystem;
    public outTransfer(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addCommands(
                new out4BarPivotTransfer(this.subsystem),
                new outClawPivotTransfer(this.subsystem),
                new outViperTransfer(this.subsystem)
        );
        addRequirements(this.subsystem);

    }
}
