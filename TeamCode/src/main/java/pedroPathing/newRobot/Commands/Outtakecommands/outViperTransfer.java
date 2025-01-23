package pedroPathing.newRobot.Commands.Outtakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import pedroPathing.newRobot.subsystems.outtakeSubsystem;

public class outViperTransfer extends CommandBase {
    private final outtakeSubsystem subsystem;

    public outViperTransfer(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setVertSlideTransfer();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
