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
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class ConveyorSubsystem extends SubsystemBase {
  /**
   * Creates a new ConveyorSubsystem.
   */
  public boolean BallNotDetected = true;
  DigitalInput ball_index_sensor = new DigitalInput(Constants.BALL_INDEX_SENSOR_DI_NUM);
  public static WPI_TalonSRX lowerConveyoorTalon = new WPI_TalonSRX(Constants.LOWER_CONVEYOR_MOTOR);
  public static WPI_TalonSRX intakeTalon = new WPI_TalonSRX(Constants.INTAKE_MOTOR);

  public ConveyorSubsystem() {

    lowerConveyoorTalon.setNeutralMode(NeutralMode.Coast);
    intakeTalon.setInverted(true);

  }

  public void move_lower_conveyor(double speed){
    
    if (!BallNotDetected) {
			lowerConveyoorTalon.set(speed);
		} else {
			lowerConveyoorTalon.set(0);
    }

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
    BallNotDetected = ball_index_sensor.get();
    SmartDashboard.putBoolean("Digital Sensor Value:", BallNotDetected);
  }
}
