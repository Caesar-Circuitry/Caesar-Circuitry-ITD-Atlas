package Robot.Commands.newScoringCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;

public class scoringSub extends CommandBase {

    private final outtakeSubsystem outtakeSubsystem;
    private final intakeSubsystem intakeSubsystem;

    public scoringSub(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
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
        intakeSubsystem.setIntClawPivotSub();
        intakeSubsystem.setIntPivotSub();
        intakeSubsystem.setViperSub();
        intakeSubsystem.setIntClawRotateMiddle();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
