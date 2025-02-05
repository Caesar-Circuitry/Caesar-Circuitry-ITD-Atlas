package Robot.Commands.IntakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;

public class intSub extends CommandBase{

    public intSub(intakeSubsystem intake){
        new intClawPivotSub(intake);
        new intClawRotateMiddle(intake);
        new intSetViperSub(intake);
    }
}
