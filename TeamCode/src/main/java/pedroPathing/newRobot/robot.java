package pedroPathing.newRobot;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.Robot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class robot extends Robot {
    private LinearOpMode opMode;
    private CommandScheduler scheduler;
    public enum OpModeType {
        TELEOP, AUTO
    }

    public robot(OpModeType type, LinearOpMode opMode) {
        if (type == OpModeType.TELEOP) {
            initTele();
        } else {
            initAuto();
        }
    }

    /*
     * Initialize teleop or autonomous, depending on which is used
     */


    public void initTele() {

    }

    public void initAuto() {

    }
}
