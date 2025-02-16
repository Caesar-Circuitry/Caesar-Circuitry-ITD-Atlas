package Robot.Commands.IntakeCommands;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.subsystems.intakeSubsystem;

public class intTransfer extends ParallelCommandGroup {

    public intTransfer(intakeSubsystem intake){
        new intClawPivotTransfer(intake);
        new intClawRotateMiddle(intake);
        new intSetViperTransfer(intake);
    }
}
