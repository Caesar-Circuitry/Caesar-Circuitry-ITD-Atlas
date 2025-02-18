package Robot.Commands.newScoringCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;

public class scoringHighChamberClip extends CommandBase {

    private final outtakeSubsystem outtakeSubsystem;
    private final intakeSubsystem intakeSubsystem;

    public scoringHighChamberClip(outtakeSubsystem outtakeSubsystem, intakeSubsystem intakeSubsystem){
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void initialize(){
        intakeSubsystem.openClaw();
        intakeSubsystem.setIntClawPivotTransfer();
        intakeSubsystem.setIntPivotTransfer();
        intakeSubsystem.setViperTransfer();
        intakeSubsystem.setIntClawRotateMiddle();
        
        outtakeSubsystem.setOut4BarPivotHighChamber();
        outtakeSubsystem.setVertSlideTransfer();
        outtakeSubsystem.setOutClawPivotChamber();
        //outtakeSubsystem.openClaw();

    }
    @Override
    public void execute(){
        if (outtakeSubsystem.getEncoderPos()<(outtakeSubsystem.getTicksPerInch()*2)){
            outtakeSubsystem.openClaw();
        }
    }
    @Override
    public boolean isFinished(){
        return outtakeSubsystem.getEncoderPos()<outtakeSubsystem.getTicksPerInch();// viper slide is less than 1 inch
    }

}
