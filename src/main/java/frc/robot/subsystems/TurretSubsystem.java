/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.*;

public class TurretSubsystem extends SubsystemBase {
  /**
   * Creates a new TurretSubsystem.
   */

  public static WPI_TalonSRX shooterTalon = new WPI_TalonSRX(0); // This is the CAN ID for the device 

  public TurretSubsystem() {

  }

  public void shootBall(double speed){
    shooterTalon.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
