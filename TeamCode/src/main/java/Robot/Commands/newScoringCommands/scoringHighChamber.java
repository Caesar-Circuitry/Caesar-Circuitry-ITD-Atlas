package Robot.Commands.newScoringCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;

public class scoringHighChamber extends CommandBase {

    private final outtakeSubsystem outtakeSubsystem;
    private final intakeSubsystem intakeSubsystem;

    public scoringHighChamber(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void initialize(){
        outtakeSubsystem.closeClaw();
        intakeSubsystem.openClaw();
        outtakeSubsystem.closeClaw();

        intakeSubsystem.setIntClawPivotObs();
        intakeSubsystem.setIntPivotTransfer();
        intakeSubsystem.setViperTransfer();
        intakeSubsystem.setIntClawRotateMiddle();

        outtakeSubsystem.setOut4BarPivotHighChamber();
        outtakeSubsystem.setVertSlideHighChamber();
        outtakeSubsystem.setOutClawPivotChamber();

        //intakeSubsystem.openClaw();
    }
    @Override
    public boolean isFinished(){
        return true;
    }

}
