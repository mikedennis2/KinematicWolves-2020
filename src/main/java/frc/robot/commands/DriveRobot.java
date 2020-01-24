/*----------------------------------------------------------------------------*/
/* 1/23/2020 v1                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveRobot extends CommandBase {
  /**
   * Creates a new DriveRobot.
   */
  private double xSpeed = 0;
  private double zRotation = 0;

  // The subsystem the command runs on
  private final DriveTrainSubsystem m_drivetrainSubsystem;

  public DriveRobot(DriveTrainSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrainSubsystem = subsystem;
    addRequirements(m_drivetrainSubsystem);
  }

  public DriveRobot(DriveTrainSubsystem subsystem, double xSpeed, double zRotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrainSubsystem = subsystem;
    addRequirements(m_drivetrainSubsystem);
    this.xSpeed = xSpeed;
    this.zRotation = zRotation;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrainSubsystem.move(xSpeed,zRotation);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrainSubsystem.move(0.0,0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
