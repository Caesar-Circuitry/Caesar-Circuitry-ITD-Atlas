package customFunctions;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PDFL {
  private double kP, kD, kF, kL;
  private double errorChange = 0;
  private double error = 0;
  private double reference = 0;
  private double lastReference = reference;

  private double lastError = 0;

  private double a = 0.8; // a can be anything from 0 < a < 1
  private double previousFilterEstimate = 0;
  private double currentFilterEstimate = 0;

  // Elapsed timer class from SDK, please use it, it's epic
  private ElapsedTime timer = new ElapsedTime();

  public PDFL(double kP, double kD, double kF, double kL) {
    this.kP = kP;
    this.kD = kD;
    this.kF = kF;
    this.kL = kL;
    timer.reset();
  }

  public double calculate(double pv, double sp) {
    reference = sp;
    error = reference - pv;

    errorChange = (error - lastError);

    // filter out hight frequency noise to increase derivative performance
    currentFilterEstimate = (a * previousFilterEstimate) + (1 - a) * errorChange;
    previousFilterEstimate = currentFilterEstimate;

    // rate of change of the error
    double derivative = currentFilterEstimate / timer.seconds();

    double out = (kP * error) + (kD * derivative) + (kF * sp) + (kL * Math.signum(error));
    return out;
  }

  public void reset() {
    timer.reset();
  }

  public void setCoefficients(double kP, double kD, double kF, double kL) {
    this.kP = kP;
    this.kD = kD;
    this.kF = kF;
    this.kL = kL;
  }
}
