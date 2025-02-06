package Robot.Commands.intakeCommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.Commands.IntakeCommands.intClawPivotSub;
import Robot.Commands.IntakeCommands.intClawRotateMiddle;
import Robot.Commands.IntakeCommands.intSetViperSub;
import Robot.subsystems.intakeSubsystem;


public class intSub extends ParallelCommandGroup {
    private intakeSubsystem subsystem;
    public intSub(intakeSubsystem subsystem){
        this.subsystem = subsystem;
        addCommands(
                new intClawPivotSub(this.subsystem),
                new intClawRotateMiddle(this.subsystem),
                new intSetViperSub(this.subsystem)
        );
        addRequirements(this.subsystem);

    }
}
