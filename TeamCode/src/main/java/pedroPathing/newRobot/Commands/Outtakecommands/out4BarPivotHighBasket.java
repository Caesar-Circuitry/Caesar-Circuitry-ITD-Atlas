package pedroPathing.newRobot.Commands.Outtakecommands;

import com.arcrobotics.ftclib.command.CommandBase;

import pedroPathing.newRobot.subsystems.outtakeSubsystem;

public class out4BarPivotHighBasket extends CommandBase {
    private final outtakeSubsystem subsystem;

    public out4BarPivotHighBasket(outtakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(this.subsystem);
    }
    @Override
    public void initialize() {
        subsystem.setOut4BarPivotHighBasket();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
