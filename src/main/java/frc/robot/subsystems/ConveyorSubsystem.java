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

  // Keep track of where balls are place
  // emptySlots = {position at sensor 2, position at sensor 3, position at sensor 4}
  public boolean ballNotDetectedSensor1 = true;
  public boolean ballNotDetectedSensor2 = true;
  public boolean ballNotDetectedSensor3 = true;
  public boolean ballNotDetectedSensor4 = true;

  public int nextBallPosition = 2; 

  DigitalInput ballIndexSensor1 = new DigitalInput(Constants.BALL_INDEX_SENSOR_1_DI_NUM);
  DigitalInput ballIndexSensor2 = new DigitalInput(Constants.BALL_INDEX_SENSOR_2_DI_NUM);
  DigitalInput ballIndexSensor3 = new DigitalInput(Constants.BALL_INDEX_SENSOR_3_DI_NUM);
  DigitalInput ballIndexSensor4 = new DigitalInput(Constants.BALL_INDEX_SENSOR_4_DI_NUM);
  
  public static WPI_TalonSRX lowerConveyoorTalon = new WPI_TalonSRX(Constants.LOWER_CONVEYOR_MOTOR);
  public static WPI_TalonSRX topConveyorTalon = new WPI_TalonSRX(Constants.TOP_CONVEYOR_MOTOR);
  public static WPI_TalonSRX intakeTalon = new WPI_TalonSRX(Constants.INTAKE_MOTOR);

  public ConveyorSubsystem() {

    lowerConveyoorTalon.setNeutralMode(NeutralMode.Coast);
    intakeTalon.setInverted(true);

  }

  public void move_top_conveyor(double speed, int nextPosition) {
    if (nextPosition == 3){
      if (!ballNotDetectedSensor3) {
        topConveyorTalon.set(speed);
      } else {
        topConveyorTalon.set(0);
      }

    }
    else if (nextPosition == 4){
      if (!ballNotDetectedSensor4) {
        topConveyorTalon.set(speed);
      } else {
        topConveyorTalon.set(0);
      }
    }
    else{
      topConveyorTalon.set(0);
    }
  }

  public void move_lower_conveyor(double speed){
    // Run until a ball gets to sensor #2
    if (!ballNotDetectedSensor2) {
      lowerConveyoorTalon.set(speed);
    } else {
      lowerConveyoorTalon.set(0);
    }
  }

  public void move_intake_motor(double speed){
    if (is_full()){
      // All three positions have a ball
      intakeTalon.set(0); // Do not let the intake spin
    }
    else{
      intakeTalon.set(speed);  
    }
    
  }

  public void override_Lower_conveyor(double speed){
    lowerConveyoorTalon.set(speed);
  }

  public void override_top_conveyor(double speed){
    topConveyorTalon.set(speed);
  }

  public boolean is_full(){
    // If the next ball should go to "position 5", it is full
    return (nextBallPosition == 5);
  }

  public int getNextBallPosition(){
    return nextBallPosition;
  }

  public void incrementNextBallPosition(){
    nextBallPosition += 1;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    ballNotDetectedSensor1 = ballIndexSensor1.get();
    ballNotDetectedSensor2 = ballIndexSensor2.get();
    ballNotDetectedSensor3 = ballIndexSensor3.get();
    ballNotDetectedSensor4 = ballIndexSensor4.get();

    SmartDashboard.putBoolean("ballNotDetectedSensor1", ballNotDetectedSensor1);
    SmartDashboard.putBoolean("ballNotDetectedSensor2", ballNotDetectedSensor2);
    SmartDashboard.putBoolean("ballNotDetectedSensor3", ballNotDetectedSensor3);
    SmartDashboard.putBoolean("ballNotDetectedSensor4", ballNotDetectedSensor4);
  }
}
