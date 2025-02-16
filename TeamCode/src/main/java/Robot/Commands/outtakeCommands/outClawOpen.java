package Robot.Commands.outtakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.outtakeSubsystem;

public class outClawOpen extends CommandBase {

    private final outtakeSubsystem subsystem;

    public outClawOpen(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }

    @Override
    public void initialize() {
        subsystem.openClaw();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
