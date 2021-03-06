/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveTrainSubsystem;
//import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
  private final DriveTrainSubsystem m_driveTrain = new DriveTrainSubsystem();
  private final VisionSubsystem m_visionSubsystem = new VisionSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  //private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();


  // Controllers
  private final Joystick driverController = new Joystick(Constants.DRIVER_CONTROLLER);
  private final Joystick manipulatorController = new Joystick(Constants.MANIPULATOR_CONTROLLER);  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    setDefaultCommands();
  }

  public Joystick getJoystick() {
    return driverController;
  }

  public void setDefaultCommands(){
    m_driveTrain.setDefaultCommand(new DriveRobotWithJoysticks(m_driveTrain, driverController));
  }

  public void setDisabledState(){
    if (m_driveTrain.isHighGear){
      new ShiftGear(m_driveTrain);
    }
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /**
     * Declare buttons/controller inputs
     * 
     * naming convention: d_* is diver controller, m_* is manipulator controller
     */

    final JoystickButton m_aButton = new JoystickButton(manipulatorController, Constants.A_BUTTON);
    final JoystickButton m_bButton = new JoystickButton(manipulatorController, Constants.B_BUTTON);
    final JoystickButton m_xButton = new JoystickButton(manipulatorController, Constants.X_BUTTON);
    final JoystickButton m_yButton = new JoystickButton(manipulatorController, Constants.Y_BUTTON);
    final JoystickButton m_dPadUp = new JoystickButton(manipulatorController, Constants.D_PAD_UP);
    final JoystickButton m_dPadDown = new JoystickButton(manipulatorController, Constants.D_PAD_DOWN);


    //m_dPadUp.whileHeld(new MoveElevator(m_elevatorSubsystem, Constants.ELEVATOR_SPEED);
    //m_dPadDown.whileHeld(new MoveElevator(m_elevatorSubsystem, -1 * Constants.ELEVATOR_SPEED));
    m_aButton.whileHeld(new IntakeBall(m_turretSubsystem, 0.5, 0.5));
    //m_xButton.whenPressed(new TurnLeftLineUp(m_driveTrain, m_visionSubsystem, m_shooterSubsystem));
    //m_bButton.whenPressed(new TurnRightLineUp(m_driveTrain, m_visionSubsystem, m_shooterSubsystem));
    m_yButton.whileHeld(new ShootBall(m_shooterSubsystem, m_visionSubsystem));
    
    final JoystickButton d_xButton = new JoystickButton(driverController, Constants.X_BUTTON);
    final JoystickButton d_aButton = new JoystickButton(driverController, Constants.A_BUTTON);
    final JoystickButton d_bButton = new JoystickButton(driverController, Constants.B_BUTTON);
    final JoystickButton d_yButton = new JoystickButton(driverController, Constants.Y_BUTTON);

    d_aButton.whenPressed(new ShiftGear(m_driveTrain));
    //d_xButton.whileHeld(new ShootBall(m_shooterSubsystem, m_visionSubsystem));
    // d_xButton.whenPressed(new AimRobotAtTarget(0, m_visionSubsystem, m_driveTrain));
    d_xButton.whenPressed(new TurnLeftLineUp(m_driveTrain, m_visionSubsystem, m_shooterSubsystem));
    d_bButton.whenPressed(new TurnRightLineUp(m_driveTrain, m_visionSubsystem, m_shooterSubsystem));
    //d_yButton.whenPressed(new AdjustShooterAngle(m_shooterSubsystem, m_visionSubsystem));
  }


  // /**
  //  * Use this to pass the autonomous command to the main {@link Robot} class.
  //  *
  //  * @return the command to run in autonomous
  //  */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}