package Robot.hardware;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class cachingMotor {
    private DcMotorEx motor;
    public double power = 0, prevPower = 0,cacheTolerance = .01;

    public cachingMotor(DcMotorEx motor){
        this.motor = motor;
    }

    public boolean setPower(double power){
            this.power = power;
        if (this.power > prevPower + cacheTolerance || this.power < prevPower + cacheTolerance){
            this.motor.setPower(this.power);
            this.prevPower = this.power;
            return true;
        }

        return false;
    }

    public double getPower(){
        return this.power;
    }

    public DcMotorEx getMotor(){
        return this.motor;
    }

}
