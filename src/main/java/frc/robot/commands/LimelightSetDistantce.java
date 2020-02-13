/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class LimelightSetDistantce extends PIDCommand {
  /**
   * Creates a new LimelightSetDistantce.
   */
  public LimelightSetDistantce(DriveTrainSubsystem m_dDriveTrainSubsystem, VisionSubsystem m_VisionSubsystem, double distance) {
    super(
        // The controller that the command will use
        new PIDController(0, 0, 0),
        // This should return the measurement
        m_VisionSubsystem::getDistance,
        // This should return the setpoint (can also be a constant)
        distance,
        // This uses the output
        output -> {
          // Use the output here
          m_dDriveTrainSubsystem.drive_to_distance(0.3);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_dDriveTrainSubsystem);
    addRequirements(m_VisionSubsystem);

    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
        return getController().atSetpoint();
  }
}
