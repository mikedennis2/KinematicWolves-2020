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
 
  //                                       [Index 0]                  [Index 1]              [Index 2]               [Index 3]
  // Add a boolean array containing each of the ballNotDetectedSensor readings
  public boolean[] ballNotDetectedArray = {ballNotDetectedSensor1, ballNotDetectedSensor2, ballNotDetectedSensor3, ballNotDetectedSensor4};

  DigitalInput ballIndexSensor1 = new DigitalInput(Constants.BALL_INDEX_SENSOR_1_DI_NUM);
  DigitalInput ballIndexSensor2 = new DigitalInput(Constants.BALL_INDEX_SENSOR_2_DI_NUM);
  DigitalInput ballIndexSensor3 = new DigitalInput(Constants.BALL_INDEX_SENSOR_3_DI_NUM);
  DigitalInput ballIndexSensor4 = new DigitalInput(Constants.BALL_INDEX_SENSOR_4_DI_NUM);

  public int nextBallPosition = 2;
  
  public static WPI_TalonSRX lowerConveyoorTalon = new WPI_TalonSRX(Constants.LOWER_CONVEYOR_MOTOR);
  public static WPI_TalonSRX intakeTalon = new WPI_TalonSRX(Constants.INTAKE_MOTOR);
  public static WPI_TalonSRX topConveyorTalon = new WPI_TalonSRX(Constants.TOP_CONVEYOR_MOTOR);

  public ConveyorSubsystem() {
    lowerConveyoorTalon.setNeutralMode(NeutralMode.Coast);
    intakeTalon.setInverted(true);
  }
  
  public void move_top_conveyor(double speed) {
    topConveyorTalon.set(speed);
  }
  
  // Update this method to only move the lower conveyor (no checks in place)
  public void move_lower_conveyor(double speed){
    lowerConveyoorTalon.set(speed);
  }

  /*
  Create a method to adjust the ball position. This will happen after a ball has been
  taken in by the intake (and found by sensor 1). This method will have two different
  scenarios it must handle. 

  1. When it only needs to move the lower conveyor (hint, first ball it takes in)
  2. When it needs to move both conveyors at once (hint, second or third ball that is taken in)
  */
  public void adjust_ball_positioning(double speed){
    if (nextBallPosition == 2){
      // Called when the ball needs to move to position 2
      lowerConveyoorTalon.set(speed);
    }
    else{
      // Called when the ball needs to move to position 3 or 4
      lowerConveyoorTalon.set(speed);
      topConveyorTalon.set(speed);
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

  /*
  We need a method to increment the ball position after one is taken in.
  This will be called after a ball is moved to its desired position.
  */
  public void increment_ball_position(){
    nextBallPosition += 1;
  }

  /*
  We need a method to tell us if the ball is at the desired position. Hint,
  this is why we created the boolean array earlier!
  */
  public boolean ball_at_desired_position(){
    // This method gets called when the intake is running
    // Tells the command when to stop
    nextBallPositionIndex = nextBallPosition - 1;
    return !ballNotDetectedArray[nextBallPositionIndex];
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
