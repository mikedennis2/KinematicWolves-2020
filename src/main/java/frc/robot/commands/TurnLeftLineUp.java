
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;


public class TurnLeftLineUp extends SequentialCommandGroup {

    public TurnLeftLineUp(DriveTrainSubsystem m_drivetrain, VisionSubsystem m_visionSubsystem,
            ShooterSubsystem m_shooter) {
        addCommands(
            new TurnUntilTargetFound(m_drivetrain, m_visionSubsystem, false, 0.5),
            new AimRobotAtTarget(0, m_visionSubsystem, m_drivetrain),
            new AdjustShooterAngle(m_shooter, m_visionSubsystem)
        );
    }
}