package pedroPathing.newRobot.Commands.Outtakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import pedroPathing.newRobot.subsystems.outtakeSubsystem;

public class outClawClose extends CommandBase {
    private final outtakeSubsystem subsystem;

    public outClawClose(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }

    @Override
    public void initialize() {
        subsystem.closeClaw();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
