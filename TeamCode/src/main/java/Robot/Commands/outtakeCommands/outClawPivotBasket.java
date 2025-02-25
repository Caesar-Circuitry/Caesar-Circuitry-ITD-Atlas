package Robot.Commands.outtakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.outtakeSubsystem;

public class outClawPivotBasket extends CommandBase {
    private final outtakeSubsystem subsystem;

    public outClawPivotBasket(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setOutClawPivotBasket();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
