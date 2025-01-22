package pedroPathing.newRobot.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.hardware.HardwareMap;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

public class drivetrainSubsystem extends SubsystemBase {

    public final Follower follower;
    public PathChain pathChain;

    public drivetrainSubsystem(HardwareMap hMap, Pose startingPose, boolean TeleOpEnabled){
        follower = new Follower(hMap);

        Constants.setConstants(FConstants.class, LConstants.class);
        follower.setStartingPose(startingPose);

        if (TeleOpEnabled){
            follower.startTeleopDrive();
        }
    }

    @Override
    public void periodic(){
        follower.update();
        follower.updatePose();
    }

    public void setPathChain(PathChain pathChain){
        this.pathChain = pathChain;
    }

    public PathChain getPathChain(){
        return this.pathChain;
    }

    public void followPath(PathChain pathChain){
        follower.followPath(pathChain);
    }

    public void followPath(){
        follower.followPath(this.pathChain);
    }

    public void setMovementVectors(double forward, double lateral, double heading){
        follower.setTeleOpMovementVectors(forward,lateral,heading);
    }
    public void setRobotCentric(boolean robotCentric){
        follower.setTeleOpMovementVectors(0,0,0,robotCentric);
    }

}
