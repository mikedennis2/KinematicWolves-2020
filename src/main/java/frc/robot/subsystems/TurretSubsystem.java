/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TurretSubsystem extends SubsystemBase {
  /**
   * Creates a new TurretSubsystem.
   */

  public static WPI_TalonSRX lowerConveyoorTalon = new WPI_TalonSRX(Constants.LOWER_CONVEYOR_MOTOR);
  public static WPI_TalonSRX intakeTalon = new WPI_TalonSRX(Constants.INTAKE_MOTOR);

  public TurretSubsystem() {
    lowerConveyoorTalon.setNeutralMode(NeutralMode.Coast);
    intakeTalon.setInverted(true);
  }

  

  public void move_lower_conveyor(double speed){
    lowerConveyoorTalon.set(speed);

  }

  public void move_intake_motor(double speed){
    intakeTalon.set(speed);

  }

  public void override_Lower_conveyor(double speed){
    lowerConveyoorTalon.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
