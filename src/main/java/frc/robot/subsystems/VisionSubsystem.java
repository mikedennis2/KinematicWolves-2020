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

public class VisionSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  
  // Limelight object
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry tx = table.getEntry("tx");
  private NetworkTableEntry ty = table.getEntry("ty");
  private NetworkTableEntry ta = table.getEntry("ta");
  private NetworkTableEntry tv = table.getEntry("tv");

  public VisionSubsystem() {      
  }

  // Limelight x
  public double getX() {
    double x = tx.getDouble(0.0);
    return(x);
  }

  // Limelight y
  public double getY() {
    double y = ty.getDouble(0.0);
    return(y);
  }

  // Limelight area
  public double getA() {
    double a = ta.getDouble(0.0);
    return(a);
  }

  // Limelist target detected flag
  public boolean getV() {
    boolean v = tv.getBoolean(false);
    return(v);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // SmartDashboard.putNumber("LimelightX", getX());
    // SmartDashboard.putNumber("LimelightY", getY());
    // SmartDashboard.putNumber("LimelightA", getA());
    // SmartDashboard.putBoolean("LimelightV", getV());
  }
}
