package Robot.Commands.outtakeCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import Robot.Commands.outtakeCommands.out4BarPivotHighBasket;
import Robot.Commands.outtakeCommands.out4BarPivotHighChamber;
import Robot.Commands.outtakeCommands.outClawPivotBasket;
import Robot.Commands.outtakeCommands.outClawPivotChamber;
import Robot.Commands.outtakeCommands.outViperHighBasket;
import Robot.Commands.outtakeCommands.outViperHighChamber;
import Robot.subsystems.outtakeSubsystem;


public class outHighChamber extends SequentialCommandGroup {
    private outtakeSubsystem subsystem;
    public outHighChamber(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addCommands(
                new out4BarPivotHighChamber(this.subsystem),
                new outClawPivotChamber(this.subsystem),
                new outViperHighChamber(this.subsystem)
        );
        addRequirements(this.subsystem);

    }
}
