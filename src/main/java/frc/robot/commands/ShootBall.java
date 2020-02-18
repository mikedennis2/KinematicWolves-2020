/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Utilities;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;


public class ShootBall extends CommandBase {
  /**
   * Creates a new ShootBall.
   */


  // The subsystem the command runs on
  private final ShooterSubsystem m_shooterSubsystem;
  private final VisionSubsystem m_visionSubsystem;

  public ShootBall(ShooterSubsystem shooterSubsystem, VisionSubsystem visionSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooterSubsystem = shooterSubsystem;
    m_visionSubsystem = visionSubsystem;
    addRequirements(m_shooterSubsystem);
    addRequirements(m_visionSubsystem);
    // this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooterSubsystem.move_top_conveyor(0.5);
    double distance = m_visionSubsystem.getDistance(); // TODO: Units
//     double distance = 10; // feet
    double speed = Utilities.linearInterpolation(Constants.distances, Constants.speeds, distance);
    System.out.print("Speed calculated by table:");
    System.out.print(speed);
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
