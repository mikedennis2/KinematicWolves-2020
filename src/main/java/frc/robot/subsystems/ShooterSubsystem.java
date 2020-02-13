/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */

  public static WPI_TalonSRX feederTalon_1 = new WPI_TalonSRX(Constants.ROTATE_SHOOTER_MOTOR_1); // This is the CAN ID for the device 
  public static WPI_TalonSRX feederTalon_2 = new WPI_TalonSRX(Constants.ROTATE_SHOOTER_MOTOR_2); // This is the CAN ID for the device 
  public static WPI_TalonSRX spinShooterTalon_1 = new WPI_TalonSRX(Constants.SPIN_SHOOTER_MOTOR_1); // This is the CAN ID for the device 
  public static WPI_TalonSRX spinShooterTalon_2 = new WPI_TalonSRX(Constants.SPIN_SHOOTER_MOTOR_2); // This is the CAN ID for the device 

  public ShooterSubsystem(){

  }

  public void shootBall(double speed){
    spinShooterTalon_1.set(speed);
    spinShooterTalon_2.set(speed);
  }

  public void spinFeederWheels(double speed){
    feederTalon_1.set(speed);
    feederTalon_2.set(-speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}