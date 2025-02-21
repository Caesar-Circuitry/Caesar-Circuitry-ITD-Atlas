package Robot.Commands.newScoringCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;

public class scoringTransfer extends CommandBase {

    private final outtakeSubsystem outtakeSubsystem;
    private final intakeSubsystem intakeSubsystem;

    public scoringTransfer(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void initialize(){
        outtakeSubsystem.openClaw();
        outtakeSubsystem.setOut4BarPivotTransfer();
        outtakeSubsystem.setVertSlideTransfer();
        outtakeSubsystem.setOutClawPivotTransfer();

        intakeSubsystem.closeClaw();
        intakeSubsystem.setIntClawPivotTransfer();
        intakeSubsystem.setIntPivotTransfer();
        intakeSubsystem.setIntClawRotateMiddle();
    }


    @Override
    public boolean isFinished(){
        return true;
    }

}
