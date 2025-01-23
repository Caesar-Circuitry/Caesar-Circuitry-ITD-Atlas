package pedroPathing.newRobot.Commands.Outtakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import pedroPathing.newRobot.subsystems.outtakeSubsystem;

public class outClawPivotTransfer extends CommandBase {
    private final outtakeSubsystem subsystem;

    public outClawPivotTransfer(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setOutClawPivotTransfer();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
