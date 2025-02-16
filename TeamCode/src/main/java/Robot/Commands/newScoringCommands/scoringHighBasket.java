package Robot.Commands.newScoringCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;

public class scoringHighBasket extends CommandBase {

    private final outtakeSubsystem outtakeSubsystem;
    private final intakeSubsystem intakeSubsystem;

    public scoringHighBasket(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void initialize(){
        outtakeSubsystem.closeClaw();
        outtakeSubsystem.setOut4BarPivotHighBasket();
        outtakeSubsystem.setVertSlideHighBasket();
        outtakeSubsystem.setOutClawPivotBasket();

        intakeSubsystem.openClaw();
        intakeSubsystem.setIntClawPivotTransfer();
        intakeSubsystem.setIntPivotTransfer();
        intakeSubsystem.setViperTransfer();
        intakeSubsystem.setIntClawRotateMiddle();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
