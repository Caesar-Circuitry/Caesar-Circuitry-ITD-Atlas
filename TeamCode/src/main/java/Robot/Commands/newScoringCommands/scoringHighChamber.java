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
        outtakeSubsystem.setOut4BarPivotHighChamber();
        outtakeSubsystem.setVertSlideHighChamber();
        outtakeSubsystem.setOutClawPivotChamber();

        intakeSubsystem.openClaw();
        intakeSubsystem.setIntClawPivotTransfer();
        intakeSubsystem.setIntPivotTransfer();
        intakeSubsystem.setViperTransfer();
        intakeSubsystem.setIntClawRotateMiddle();
    }

    @Override
    public void execute(){
        outtakeSubsystem.periodic();
        intakeSubsystem.periodic();
    }

}
