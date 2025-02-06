package Robot.Commands.intakeCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.IntakeCommands.intClawPivotObs;
import Robot.Commands.IntakeCommands.intClawPivotTransfer;
import Robot.Commands.IntakeCommands.intClawRotateMiddle;
import Robot.Commands.IntakeCommands.intSetViperObs;
import Robot.Commands.IntakeCommands.intSetViperTransfer;
import Robot.subsystems.intakeSubsystem;


public class intTransfer extends ParallelCommandGroup {
    private intakeSubsystem subsystem;
    public intTransfer(intakeSubsystem subsystem){
        this.subsystem = subsystem;
        addCommands(
                new intClawPivotTransfer(this.subsystem),
                new intClawRotateMiddle(this.subsystem),
                new intSetViperTransfer(this.subsystem)
        );
        addRequirements(this.subsystem);

    }
}
