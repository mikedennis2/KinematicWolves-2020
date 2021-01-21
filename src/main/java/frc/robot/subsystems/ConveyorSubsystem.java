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
  public boolean ballNotDetectedSensor1 = true;
  public boolean ballNotDetectedSensor2 = true;
  public boolean ballNotDetectedSensor3 = true;
  public boolean ballNotDetectedSensor4 = true;

  DigitalInput ballIndexSensor1 = new DigitalInput(Constants.BALL_INDEX_SENSOR_1_DI_NUM);
  DigitalInput ballIndexSensor2 = new DigitalInput(Constants.BALL_INDEX_SENSOR_2_DI_NUM);
  DigitalInput ballIndexSensor3 = new DigitalInput(Constants.BALL_INDEX_SENSOR_3_DI_NUM);
  DigitalInput ballIndexSensor4 = new DigitalInput(Constants.BALL_INDEX_SENSOR_4_DI_NUM);

  public int nextBallPosition = 2;
  
  public static WPI_TalonSRX lowerConveyoorTalon = new WPI_TalonSRX(Constants.LOWER_CONVEYOR_MOTOR);
  public static WPI_TalonSRX intakeTalon = new WPI_TalonSRX(Constants.INTAKE_MOTOR);

  public ConveyorSubsystem() {

    lowerConveyoorTalon.setNeutralMode(NeutralMode.Coast);
    intakeTalon.setInverted(true);

  }

  public void move_lower_conveyor(double speed){
    
    if (!ballNotDetectedSensor1) {
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

  public int get_next_ball_position() {
    return nextBallPosition;
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    ballNotDetectedSensor1 = ballIndexSensor1.get();
    ballNotDetectedSensor2 = ballIndexSensor2.get();
    ballNotDetectedSensor3 = ballIndexSensor3.get();
    ballNotDetectedSensor4 = ballIndexSensor4.get();
    SmartDashboard.putBoolean("Digital Sensor 1 Value:", ballNotDetectedSensor1);
    SmartDashboard.putBoolean("Digital Sensor 2 Value:", ballNotDetectedSensor2);
    SmartDashboard.putBoolean("Digital Sensor 3 Value:", ballNotDetectedSensor3);
    SmartDashboard.putBoolean("Digital Sensor 4 Value:", ballNotDetectedSensor4);
  }
}
