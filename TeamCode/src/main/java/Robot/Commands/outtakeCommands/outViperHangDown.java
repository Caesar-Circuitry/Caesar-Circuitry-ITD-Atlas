package Robot.Commands.outtakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.outtakeSubsystem;

public class outViperHangDown extends CommandBase {
    private final outtakeSubsystem subsystem;

    public outViperHangDown(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setVertSlideHangDown();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
