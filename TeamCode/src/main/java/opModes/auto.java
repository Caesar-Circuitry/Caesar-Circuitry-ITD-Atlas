package opModes;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import Robot.Robot;

public class auto extends OpMode {
    //https://state-factory.gitbook.io/state-factory
    Robot robot;
    StateMachine stateMachine;
    Follower follower;
    @Override
    public void init() {
        robot = new Robot(hardwareMap, Robot.opModeType.AUTO);
        follower = robot.getFollower();
    }

    @Override
    public void loop() {

    }
}
