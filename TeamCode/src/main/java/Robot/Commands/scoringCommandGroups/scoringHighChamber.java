package Robot.Commands.scoringCommandGroups;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import Robot.Commands.intakeCommandGroups.intTransfer;
import Robot.Commands.outtakeCommandGroups.outHighBasket;
import Robot.Commands.outtakeCommandGroups.outHighChamber;
import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;


public class scoringHighChamber extends CommandBase {
    private outtakeSubsystem outtakeSubsystem;
    private intakeSubsystem intakeSubsystem;
    public scoringHighChamber(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;

       addRequirements(this.outtakeSubsystem, this.intakeSubsystem);

    }
    @Override
    public void initialize(){
        outtakeSubsystem.setVertSlideHighChamber();
        outtakeSubsystem.setOutClawPivotChamber();
        outtakeSubsystem.setOut4BarPivotHighChamber();
        outtakeSubsystem.closeClaw();
        intakeSubsystem.setIntPivotTransfer();
        intakeSubsystem.setViperTransfer();
        intakeSubsystem.setIntClawRotateMiddle();
        intakeSubsystem.setIntClawPivotTransfer();
    }
}
