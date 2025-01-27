package Robot.Commands.Outtakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.outtakeSubsystem;

public class outViperHighChamber extends CommandBase {
    private final outtakeSubsystem subsystem;

    public outViperHighChamber(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setVertSlideHighChamber();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
