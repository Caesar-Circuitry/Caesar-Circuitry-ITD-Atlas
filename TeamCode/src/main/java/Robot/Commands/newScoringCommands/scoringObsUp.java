package Robot.Commands.newScoringCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;

public class scoringObsUp extends CommandBase {

    private final outtakeSubsystem outtakeSubsystem;
    private final intakeSubsystem intakeSubsystem;

    public scoringObsUp(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void initialize(){
        outtakeSubsystem.openClaw();
        outtakeSubsystem.setOut4BarPivotTransfer();
        outtakeSubsystem.setVertSlideTransfer();
        outtakeSubsystem.setOutClawPivotTransfer();

        //intakeSubsystem.setViperObs();
        intakeSubsystem.openClaw();
        intakeSubsystem.setIntClawPivotObs();
        intakeSubsystem.openClaw();
        intakeSubsystem.setIntPivotObsUp();
        intakeSubsystem.openClaw();
        intakeSubsystem.openClaw();
        intakeSubsystem.setIntClawRotateMiddle();
        intakeSubsystem.openClaw();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
