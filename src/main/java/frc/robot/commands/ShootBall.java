/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;
import frc.robot.subsystems.TurretSubsystem;

public class ShootBall extends CommandBase {
  /**
   * Creates a new ShootBall.
   */
  private double speed = 0.8;

  // The subsystem the command runs on
  private final TurretSubsystem m_turretSubsystem;

  public ShootBall(TurretSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_turretSubsystem = subsystem;
    addRequirements(m_turretSubsystem);
  }

  public ShootBall(TurretSubsystem subsystem, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_turretSubsystem = subsystem;
    addRequirements(m_turretSubsystem);
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_turretSubsystem.shootBall(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_turretSubsystem.shootBall(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
