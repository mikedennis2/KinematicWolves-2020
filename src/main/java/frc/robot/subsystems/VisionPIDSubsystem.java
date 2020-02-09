/*----------------------------------------------------------------------------*/
/* 1/22/2020 v1                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Limelight imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

import java.lang.Math;

public class VisionPIDSubsystem extends PIDSubsystem {
  /**
   * Creates a new ExampleSubsystem.
   */
  
  // Limelight object
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry tx = table.getEntry("tx");
  private NetworkTableEntry ty = table.getEntry("ty");
  private NetworkTableEntry ta = table.getEntry("ta");
  private NetworkTableEntry tv = table.getEntry("tv");

  private final float A1 = (float)0.0;     // Measure, move to other class?
  private final float H1 = (float)5.01;     // Measure, move to other class?
  private final float H2 = (float)6.1;     // Measure, move to other class?

  public VisionPIDSubsystem() { 
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));     
  }
  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
  
  // Limelight x
  public double getHorizontalAngle() {
    double x = tx.getDouble(0.0);
    return(x);
  }

  // Limelight y
  public double getVerticalAngle() {
    double y = ty.getDouble(0.0);
    return(y);
  }

  // Limelight area
  public double getTargetArea() {
    double a = ta.getDouble(0.0);
    return(a);
  }

  // Limelight target detected flag
  public double getCaptureStatus() {
    double v = tv.getDouble(0.0);
    return(v);
  }

  // Get distance to target
  public double getDistance() {
    double dist = -1;
    
    if (getCaptureStatus() == 1.0) {
        dist = (H2-H1)/Math.tan(A1+getVerticalAngle());
    }

    return(dist);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("LimelightHorizontalAngle", getHorizontalAngle());
    SmartDashboard.putNumber("LimelightVerticalAngle", getVerticalAngle());
    SmartDashboard.putNumber("LimelightArea", getTargetArea());
    SmartDashboard.putNumber("LimelightCaptureStatus", getCaptureStatus());
    SmartDashboard.putNumber("LimelightCaptureDistance", getDistance());
  }
}