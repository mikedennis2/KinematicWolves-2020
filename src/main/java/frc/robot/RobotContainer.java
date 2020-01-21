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
import frc.robot.commands.ShootBall;
import frc.robot.subsystems.TurretSubsystem;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();

  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  // Controllers
  private final Joystick driverController = new Joystick(0);
  private final Joystick manipulatorController = new Joystick(1);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
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
    // final JoystickButton d_aButton = new JoystickButton(driverController, 1);
    // final JoystickButton d_bButton = new JoystickButton(driverController, 2);
    // final JoystickButton d_xButton = new JoystickButton(driverController, 3);
    // final JoystickButton d_yButton = new JoystickButton(driverController, 4);
    // final JoystickButton d_dpadUp = new JoystickButton(driverController, 5);
    // final JoystickButton d_dpadRight = new JoystickButton(driverController, 6);
    // final JoystickButton d_dpadDown = new JoystickButton(driverController, 7);
    // final JoystickButton d_dpadLeft = new JoystickButton(driverController, 8);
    // final JoystickButton d_l2 = new JoystickButton(driverController, 9);
    // final JoystickButton d_r2 = new JoystickButton(driverController, 10);
    // final JoystickButton d_l1 = new JoystickButton(driverController, 11);
    // final JoystickButton d_r1 = new JoystickButton(driverController, 12);

    final JoystickButton m_aButton = new JoystickButton(manipulatorController, 1);
    final JoystickButton m_bButton = new JoystickButton(manipulatorController, 2);
    final JoystickButton m_xButton = new JoystickButton(manipulatorController, 3);
    final JoystickButton m_yButton = new JoystickButton(manipulatorController, 4);
    // final JoystickButton m_dpadUp = new JoystickButton(manipulatorController, 5);
    // final JoystickButton m_dpadRight = new JoystickButton(manipulatorController, 6);
    // final JoystickButton m_dpadDown = new JoystickButton(manipulatorController, 7);
    // final JoystickButton m_dpadLeft = new JoystickButton(manipulatorController, 8);
    // final JoystickButton m_l2 = new JoystickButton(manipulatorController, 9);
    // final JoystickButton m_r2 = new JoystickButton(manipulatorController, 10);
    // final JoystickButton m_l1 = new JoystickButton(manipulatorController, 11);
    // final JoystickButton m_r1 = new JoystickButton(manipulatorController, 12);
    m_yButton.whileHeld(new ShootBall(m_turretSubsystem, 1.0));
    m_aButton.whileHeld(new ShootBall(m_turretSubsystem, 0.8));
    m_bButton.whileHeld(new ShootBall(m_turretSubsystem, 0.6));
    m_xButton.whileHeld(new ShootBall(m_turretSubsystem, 0.4));
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
