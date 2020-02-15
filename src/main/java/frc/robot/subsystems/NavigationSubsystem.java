/*----------------------------------------------------------------------------*/
/* 01/15/2020 v1                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.trajectory.*;
import frc.robot.Constants;

public class NavigationSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  private TrajectoryConfig config;

  public NavigationSubsystem() {
  }

  public Trajectory getTrajectory(int index) {
    config = new TrajectoryConfig(3.5,3.5); 
    config.setReversed(true);        
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(Constants.PATH_LIST.get(index).getStartPose(),Constants.PATH_LIST.get(index).getWayPoints(),Constants.PATH_LIST.get(index).getEndPose(),config);

    return(trajectory);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}