/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class ShooterPIDSubsystem extends PIDSubsystem {
  /**
   * Creates a new ShooterPIDSubsystem.
   */

  public static WPI_TalonSRX rotateShooterTalon_1 = new WPI_TalonSRX(Constants.ROTATE_SHOOTER_MOTOR_1); // This is the CAN ID for the device 
  public static WPI_TalonSRX rotateShooterTalon_2 = new WPI_TalonSRX(Constants.ROTATE_SHOOTER_MOTOR_2); // This is the CAN ID for the device 
  public static WPI_TalonSRX spinShooterTalon = new WPI_TalonSRX(Constants.SPIN_SHOOTER_MOTOR); // This is the CAN ID for the device 

  public ShooterPIDSubsystem() {
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

  public void shootBall(double speed){
    spinShooterTalon.set(speed);
  }
}
