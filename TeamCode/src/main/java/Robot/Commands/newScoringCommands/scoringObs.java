package Robot.Commands.newScoringCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;

public class scoringObs extends CommandBase {

    private final outtakeSubsystem outtakeSubsystem;
    private final intakeSubsystem intakeSubsystem;

    public scoringObs(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void initialize(){
        outtakeSubsystem.openClaw();
        outtakeSubsystem.setOut4BarPivotTransfer();
        outtakeSubsystem.setVertSlideTransfer();
        outtakeSubsystem.setOutClawPivotTransfer();

        intakeSubsystem.openClaw();
        intakeSubsystem.setIntClawPivotObs();
        intakeSubsystem.setIntPivotObs();
        intakeSubsystem.setViperObs();
        intakeSubsystem.setIntClawRotateMiddle();
    }

    @Override
    public void execute(){
        outtakeSubsystem.periodic();
        intakeSubsystem.periodic();
    }

}
