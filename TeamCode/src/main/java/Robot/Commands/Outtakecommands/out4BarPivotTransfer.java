package Robot.Commands.Outtakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.outtakeSubsystem;

public class out4BarPivotTransfer extends CommandBase {
    private final outtakeSubsystem subsystem;

    public out4BarPivotTransfer(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setOut4BarPivotTransfer();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
