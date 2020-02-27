/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class IntakeBall extends CommandBase {
  /**
   * Creates a new IntakeBall.
   */
  TurretSubsystem m_turretSubsystem;
  double intakeWheelSpeed;
  double conveyorSpeed;

  public IntakeBall(TurretSubsystem turretSubsystem, double intakeWheelSpeed, double conveyorSpeed) {
    
    this.intakeWheelSpeed = intakeWheelSpeed;
    this.conveyorSpeed = conveyorSpeed;
    m_turretSubsystem = turretSubsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turretSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

      m_turretSubsystem.move_intake_motor(intakeWheelSpeed);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

      m_turretSubsystem.move_lower_conveyor(conveyorSpeed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

      m_turretSubsystem.move_intake_motor(0);
      m_turretSubsystem.move_lower_conveyor(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;
    
  }
}
