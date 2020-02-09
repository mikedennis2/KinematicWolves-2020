/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;;
import frc.robot.Utilities;


public class ShootBall extends CommandBase {
  /**
   * Creates a new ShootBall.
   */
  private double speed;
  private double distance;

  double[] distances = {
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
  }; // feet

  double[] speeds = {
    0.66, 0.68, 0.70, 0.75, 0.75, 0.75, 0.80, 0.80, 0.80, 0.80, 0.80, 0.90
  };

  // The subsystem the command runs on
  private final ShooterSubsystem m_shooterSubsystem;
  private final VisionSubsystem m_visionSubsystem;

  public ShootBall(ShooterSubsystem subsystem, VisionSubsystem visionSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooterSubsystem = subsystem;
    m_visionSubsystem = visionSubsystem;
    addRequirements(m_shooterSubsystem);
    addRequirements(m_visionSubsystem);
    // this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    double distance = m_visionSubsystem.getDistance(); // TODO: Units?
    double speed = Utilities.linearInterpolation(distances, speeds, distance);
    
    // double speed = getInterpolatedSpeed;
    m_shooterSubsystem.shootBall(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooterSubsystem.shootBall(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
