package Robot.hardware;

import com.qualcomm.robotcore.hardware.Servo;

public class dualCachingServo {
  private Servo servo1, servo2;
  private double servo1Pos = 0, servo1PrevPos = 0, servo2Pos = 0, servo2PrevPos = 0;

  public dualCachingServo(Servo servo1, Servo servo2) {
    this.servo1 = servo1;
    this.servo2 = servo2;
  }

  public void setPos(double servo1Pos, double servo2Pos) {
    this.servo1Pos = servo1Pos;
    this.servo2Pos = servo2Pos;
    if (this.servo1Pos == servo1PrevPos) {
      servo1.setPosition(this.servo1Pos);
      this.servo1PrevPos = this.servo1Pos;
    }
    if (this.servo2Pos == servo2PrevPos) {
      servo2.setPosition(this.servo2Pos);
      this.servo2PrevPos = this.servo2Pos;
    }
  }
}
