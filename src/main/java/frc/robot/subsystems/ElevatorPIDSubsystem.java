/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.ElevatorFeedforward;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class ElevatorPIDSubsystem extends PIDSubsystem {

  private final WPI_TalonFX m_elevatorMotor = new WPI_TalonFX(Constants.ELEVATOR_TALON_FX);
  private final Encoder m_elevatorEncoder = new Encoder(Constants.ELEVATOR_ENCODER_channel1A,
      Constants.ELEVATOR_ENCODER_channel1B);

  double setpoint;

  // private final ElevatorFeedforward m_elevatorFeedforward =
  //     new ElevatorFeedforward(Constants.kS,
  //                                Constants.kG, Constants.kV, Constants.kA);
                                 
  /**

   * The Elevator subsystem for the robot.

   */

  // EncoderResolution is 2048, not 4096
  // Docs: https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/controller/ElevatorFeedforward.html


  public ElevatorPIDSubsystem() {
    super(new PIDController(Constants.ELEVATOR_kP, Constants.ELEVATOR_kI, Constants.ELEVATOR_kD));
    getController().setTolerance(Constants.ELEVATOR_TOLERANCE);
    setSetpoint(Constants.ElevatorInitialPosition);

  }

  // public double moveElevatorSetpoint(double input){
  //   setpoint += input; // Input comes from the controller
  // }

  @Override
  public void useOutput(double output, double setpoint) {
    // m_elevatorMotor.setVoltage(output + m_elevatorFeedforward.calculate(setpoint));
    m_elevatorMotor.set(ControlMode.Position, output); // Ouput is encoder ticks
    SmartDashboard.putNumber("Elevator Encoder Distance", getMeasurement());
  }

  @Override
  public double getMeasurement() {
    return m_elevatorEncoder.getDistance();
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }

  public void runElevator(double speed) {
    m_elevatorMotor.set(speed);
  }

  public void stopElevator() {
    m_elevatorMotor.set(0);
  }

}