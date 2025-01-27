package Robot.Commands.Outtakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import Robot.subsystems.outtakeSubsystem;

public class outViperHighBasket extends CommandBase {
    private final outtakeSubsystem subsystem;

    public outViperHighBasket(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setVertSlideHighBasket();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
