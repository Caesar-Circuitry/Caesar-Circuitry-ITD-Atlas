package Robot.Commands.Outtakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.outtakeSubsystem;

public class outClawPivotChamber extends CommandBase {
    private final outtakeSubsystem subsystem;

    public outClawPivotChamber(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setOutClawPivotChamber();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
