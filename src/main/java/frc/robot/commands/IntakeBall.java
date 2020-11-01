/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyorSubsystem;

public class IntakeBall extends CommandBase {
  /**
   * Creates a new IntakeBall.
   */
  ConveyorSubsystem m_conveyorSubsystem;
  double intakeWheelSpeed;
  double conveyorSpeed;

  public IntakeBall(ConveyorSubsystem conveyorSubsystem, double intakeWheelSpeed, double conveyorSpeed) {
    
    this.intakeWheelSpeed = intakeWheelSpeed;
    this.conveyorSpeed = conveyorSpeed;
    m_conveyorSubsystem = conveyorSubsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(conveyorSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

      m_conveyorSubsystem.move_intake_motor(intakeWheelSpeed);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

      m_conveyorSubsystem.move_lower_conveyor(conveyorSpeed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

      m_conveyorSubsystem.move_intake_motor(0);
      m_conveyorSubsystem.move_lower_conveyor(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;
    
  }
}
