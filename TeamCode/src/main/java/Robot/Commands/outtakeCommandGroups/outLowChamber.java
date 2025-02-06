package Robot.Commands.outtakeCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.outtakeCommands.out4BarPivotHighChamber;
import Robot.Commands.outtakeCommands.out4BarPivotLowChamber;
import Robot.Commands.outtakeCommands.outClawPivotChamber;
import Robot.Commands.outtakeCommands.outViperHighChamber;
import Robot.Commands.outtakeCommands.outViperLowChamber;
import Robot.subsystems.outtakeSubsystem;


public class outLowChamber extends ParallelCommandGroup {
    private outtakeSubsystem subsystem;
    public outLowChamber(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addCommands(
                new out4BarPivotLowChamber(this.subsystem),
                new outClawPivotChamber(this.subsystem),
                new outViperLowChamber(this.subsystem)
        );
        addRequirements(this.subsystem);

    }
}
