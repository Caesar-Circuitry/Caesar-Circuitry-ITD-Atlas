package Robot;

import com.arcrobotics.ftclib.command.Robot;
import com.pedropathing.localization.Pose;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

import Robot.constants.opModeType;
import Robot.subsystems.drivetrainSubsystem;
import Robot.subsystems.intakeSubsystem;
import Robot.subsystems.outtakeSubsystem;

public class robotContainer extends Robot {
    public drivetrainSubsystem drivetrainSubsystem;
    public intakeSubsystem intakeSubsystem;
    public outtakeSubsystem outtakeSubsystem;
    private static Pose robotPose = new Pose(0,0,0);
    private HardwareMap hardwareMap;
    private List<LynxModule> allHubs;
    private boolean TeleOpEnabled = false;

    public robotContainer(HardwareMap hmap, opModeType opModeType){
        this.hardwareMap = hmap;
        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        if (opModeType.equals(constants.opModeType.TELEOP)) {
            this.drivetrainSubsystem = new drivetrainSubsystem(hmap, robotPose, true);
            TeleOpEnabled = true;
        }else{
            this.drivetrainSubsystem = new drivetrainSubsystem(hmap, robotPose, false);
        }
        this.intakeSubsystem = new intakeSubsystem(hmap, this);
        this.outtakeSubsystem = new outtakeSubsystem(hmap, this);
    }


    public void periodic(){
        drivetrainSubsystem.periodic();
//        intakeSubsystem.periodic();
//        outtakeSubsystem.periodic();
        for (LynxModule hub : allHubs) {
            hub.clearBulkCache();
        }
    }

    public double getVoltage(){
        return drivetrainSubsystem.follower.getVoltage();
    }

}
