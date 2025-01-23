package pedroPathing.newRobot.hardware;

import static com.arcrobotics.ftclib.util.MathUtils.clamp;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class cachingMotor {
    private DcMotorEx motor;
    public double power = 0, prevPower = 0,cacheTolerance = .01;
    private boolean voltageCompensation;

    public cachingMotor(DcMotorEx motor, boolean voltageCompensation){
        this.motor = motor;
        this.voltageCompensation = voltageCompensation;
    }

    public boolean setPower(double power){
        if(voltageCompensation) {
            this.power = (clamp(power, -1, 1) * 12) / voltageIterator.voltage;
        }else{
            this.power = clamp(power, -1, 1);
        }
        if (this.power > prevPower + cacheTolerance || prevPower - cacheTolerance < this.power){
            this.motor.setPower(this.power);
            this.prevPower = this.power;
            return true;
        }

        return false;
    }

    public double getPower(){
        return this.power;
    }
    public void enableVoltageCompensation(){
        this.voltageCompensation = true;
    }
    public void disableVoltageCompensation(){
        this.voltageCompensation = false;
    }

}
