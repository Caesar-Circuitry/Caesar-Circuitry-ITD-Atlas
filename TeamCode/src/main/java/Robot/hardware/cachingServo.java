package Robot.hardware;

import com.qualcomm.robotcore.hardware.Servo;

public class cachingServo {
    private Servo servo;
    private double servoPos = 0, prevServoPos = 0;

    public cachingServo (Servo servo){
        this.servo = servo;
    }

    public void setServoPos(double pos){
        this.servoPos = pos;
        if(this.servoPos == this.prevServoPos) {
            servo.setPosition(servoPos);
        }
        this.prevServoPos = servoPos;
    }

    public double getServoPos(){
        return this.servoPos;
    }


}
