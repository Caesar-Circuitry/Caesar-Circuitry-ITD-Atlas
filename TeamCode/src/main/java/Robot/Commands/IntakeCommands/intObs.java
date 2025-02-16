package Robot.Commands.IntakeCommands;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import Robot.subsystems.intakeSubsystem;

public class intObs extends ParallelCommandGroup {


    public intObs(intakeSubsystem intake){
        new intClawPivotObs(intake);
        new intClawRotateMiddle(intake);
        new intSetViperObs(intake);
    }


}
