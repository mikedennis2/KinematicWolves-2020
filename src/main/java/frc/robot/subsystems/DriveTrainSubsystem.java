/*----------------------------------------------------------------------------*/
/* 1/22/2020 v1                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrainSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  private WPI_VictorSPX rightMaster = new WPI_VictorSPX(1); // This is the CAN ID for the device
  private WPI_VictorSPX rightSlave = new WPI_VictorSPX(3); // This is the CAN ID for the device
  private WPI_VictorSPX leftMaster = new WPI_VictorSPX(4); // This is the CAN ID for the device
  private WPI_VictorSPX leftSlave = new WPI_VictorSPX(2); // This is the CAN ID for the device

  private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  public DriveTrainSubsystem() {
    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);
  }

  public void move(double xSpeed, double zRotation) {
    drive.arcadeDrive(xSpeed, zRotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
