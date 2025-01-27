package Robot.Commands.Outtakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.outtakeSubsystem;

public class outViperZero extends CommandBase {
    private final outtakeSubsystem subsystem;

    public outViperZero(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setVertSlideZero();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
