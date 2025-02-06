package Robot.Commands.outtakeCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.outtakeCommands.out4BarPivotHighBasket;
import Robot.Commands.outtakeCommands.out4BarPivotLowBasket;
import Robot.Commands.outtakeCommands.outClawPivotBasket;
import Robot.Commands.outtakeCommands.outViperHighBasket;
import Robot.Commands.outtakeCommands.outViperLowBasket;
import Robot.subsystems.outtakeSubsystem;


public class outLowBasket extends ParallelCommandGroup {
    private outtakeSubsystem subsystem;
    public outLowBasket(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addCommands(
                new out4BarPivotLowBasket(this.subsystem),
                new outClawPivotBasket(this.subsystem),
                new outViperLowBasket(this.subsystem)
        );
        addRequirements(this.subsystem);

    }
}
