/*----------------------------------------------------------------------------*/
/* 1/22/2020 v1                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Limelight imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class DriveTrainSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  private final WPI_VictorSPX rightMaster = new WPI_VictorSPX(Constants.RIGHT_MOTOR_1); // This is the CAN ID for the device
  private final WPI_VictorSPX rightSlave = new WPI_VictorSPX(Constants.RIGHT_MOTOR_2); // This is the CAN ID for the device
  private final WPI_VictorSPX leftMaster = new WPI_VictorSPX(Constants.LEFT_MOTOR_1); // This is the CAN ID for the device
  private final WPI_VictorSPX leftSlave = new WPI_VictorSPX(Constants.LEFT_MOTOR_2); // This is the CAN ID for the device

  // Limelight object
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry tx = table.getEntry("tx");
  private NetworkTableEntry ty = table.getEntry("ty");
  private NetworkTableEntry ta = table.getEntry("ta");

  private final DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  public DriveTrainSubsystem() {      
    rightSlave.setInverted(true);
    rightMaster.setInverted(true);
    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);

  }

  public void move_with_joysticks(Joystick driver_controller) {

    // Get axis values for speed and rotational speed
    double xSpeed = driver_controller.getRawAxis(Constants.left_x_axis);
    double zRotation_rate = driver_controller.getRawAxis(Constants.left_y_axis);

    drive.arcadeDrive(xSpeed, zRotation_rate);
  }

  // Limelight x
  public double getX() {
    double x = tx.getDouble(0.0);
    //SmartDashboard.putNumber("LimelightX", x);
    return(x);
  }

  // Limelight y
  public double getY() {
    double y = ty.getDouble(0.0);
    //SmartDashboard.putNumber("LimelightY", y);
    return(y);
  }

  // Limelight a
  public double getA() {
    double a = ta.getDouble(0.0);
    //SmartDashboard.putNumber("LimelightA", a);
    return(a);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
