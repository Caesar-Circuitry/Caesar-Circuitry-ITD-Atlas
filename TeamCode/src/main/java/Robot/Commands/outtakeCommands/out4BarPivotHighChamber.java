package Robot.Commands.outtakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.outtakeSubsystem;

public class out4BarPivotHighChamber extends CommandBase {
    private final outtakeSubsystem subsystem;

    public out4BarPivotHighChamber(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setOut4BarPivotHighChamber();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
