/*----------------------------------------------------------------------------*/
/* 1/22/2020 v1                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrainSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  private final WPI_VictorSPX rightMaster = new WPI_VictorSPX(Constants.RIGHT_MOTOR_1); // This is the CAN ID for the device
  private final WPI_VictorSPX rightSlave = new WPI_VictorSPX(Constants.RIGHT_MOTOR_2); // This is the CAN ID for the device
  private final WPI_VictorSPX leftMaster = new WPI_VictorSPX(Constants.LEFT_MOTOR_1); // This is the CAN ID for the device
  private final WPI_VictorSPX leftSlave = new WPI_VictorSPX(Constants.LEFT_MOTOR_2); // This is the CAN ID for the device

  private final DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  public DriveTrainSubsystem() {      
    leftSlave.setInverted(true);
    leftMaster.setInverted(true);
    
    rightSlave.setInverted(false);
    rightMaster.setInverted(false);

    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);

  }

  public void move_with_joysticks(Joystick driver_controller) {

    // Get axis values for speed and rotational speed
    double xSpeed = driver_controller.getRawAxis(Constants.left_x_axis);
    double zRotation_rate = driver_controller.getRawAxis(Constants.left_y_axis);

    drive.arcadeDrive(xSpeed, zRotation_rate);

    SmartDashboard.putNumber("X speed commanded by driver", driver_controller.getRawAxis(Constants.left_x_axis));
    SmartDashboard.putNumber("zRotation Rate Commanded by driver", driver_controller.getRawAxis(Constants.left_y_axis));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void align_with_target(double zRotation_rate){
    SmartDashboard.putNumber("zRotation commanded by controller", zRotation_rate);
    if (zRotation_rate > 0.4){
      zRotation_rate = 0.6;
    }
    drive.arcadeDrive(0, zRotation_rate);

  }

  
}
