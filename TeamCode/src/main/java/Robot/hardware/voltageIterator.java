package Robot.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class voltageIterator {
  public static HardwareMap hmap;
  public static VoltageSensor voltageSensor;
  public static boolean voltageSensorEnabled = false;
  public static ElapsedTime voltageTimer;
  public static double voltage = 12;

  public voltageIterator(HardwareMap hardwareMap) {
    hmap = hardwareMap;
  }

  public boolean enableVoltageSensor() {
    try {
      voltageSensor = hmap.voltageSensor.iterator().next();
      voltageTimer = new ElapsedTime();
      voltageTimer.reset();
      voltageSensorEnabled = true;
      return true;
    } catch (Exception e) {
      voltageSensorEnabled = false;
      return false;
    }
  }

  public static void periodic() {
    if (voltageSensorEnabled) {
      if (voltageTimer.time() > 5) {
        voltage = voltageSensor.getVoltage();
        voltageTimer.reset();
      }
    }
  }

  public double getVoltage() {
    return voltage;
  }

  public static void setHmap(HardwareMap hardwareMap) {
    hmap = hardwareMap;
  }
}
