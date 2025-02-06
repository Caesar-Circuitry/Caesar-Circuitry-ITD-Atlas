package Robot.Commands.outtakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.outtakeSubsystem;

public class out4BarPivotLowBasket extends CommandBase {
    private final outtakeSubsystem subsystem;

    public out4BarPivotLowBasket(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setOut4BarPivotLowBasket();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
