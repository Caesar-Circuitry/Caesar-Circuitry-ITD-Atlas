package Robot.Commands.outtakeCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.IntakeCommands.intClawPivotObs;
import Robot.Commands.IntakeCommands.intClawRotateMiddle;
import Robot.Commands.IntakeCommands.intSetViperObs;
import Robot.Commands.outtakeCommands.out4BarPivotHighBasket;
import Robot.Commands.outtakeCommands.outClawPivotBasket;
import Robot.Commands.outtakeCommands.outViperHighBasket;
import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;


public class outHighBasket extends ParallelCommandGroup {
    private outtakeSubsystem subsystem;
    public outHighBasket(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addCommands(
                new out4BarPivotHighBasket(this.subsystem),
                new outClawPivotBasket(this.subsystem),
                new outViperHighBasket(this.subsystem)
        );
        addRequirements(this.subsystem);

    }
}
