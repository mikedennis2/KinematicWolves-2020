/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonLineUpShootBall extends SequentialCommandGroup {
  /**
   * Creates a new AutonLineUpShootBall.
   */
  public AutonLineUpShootBall(DriveTrainSubsystem drivetrain, VisionSubsystem visionSubsystem,
      ShooterSubsystem m_shooter, TurretSubsystem m_turretSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new AutonDriveForward(drivetrain),
      new TurnLeftLineUp(drivetrain, visionSubsystem, m_shooter),
      new AutonShootBall(m_shooter, visionSubsystem, m_turretSubsystem)

    );
  }
}