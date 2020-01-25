/*----------------------------------------------------------------------------*/
/* 1/22/2020 v1                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  private final WPI_VictorSPX rightMaster = new WPI_VictorSPX(1); // This is the CAN ID for the device
  private final WPI_VictorSPX rightSlave = new WPI_VictorSPX(3); // This is the CAN ID for the device
  private final WPI_VictorSPX leftMaster = new WPI_VictorSPX(4); // This is the CAN ID for the device
  private final WPI_VictorSPX leftSlave = new WPI_VictorSPX(2); // This is the CAN ID for the device

  // Definition of joystick axes variables for left joystick
  public final int left_x_axis = 0;
  public final int left_y_axis = 1;

  private final DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  public DriveTrainSubsystem() {
    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);
  }

  public void move_with_joysticks(Joystick driver_controller) {

    // Get axis values for speed and rotational speed
    double xSpeed = driver_controller.getRawAxis(left_x_axis);
    double zRotation_rate = driver_controller.getRawAxis(left_y_axis);

    drive.arcadeDrive(xSpeed, zRotation_rate);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
