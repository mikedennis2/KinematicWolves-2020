/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Ports on the driver station where the contollers are connected
    public static final int DRIVER_CONTROLLER = 0;
    public static final int MANIPULATOR_CONTROLLER = 1;

    // Places where buttons are on a given controller, create these as JoystickButtons
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int X_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int D_PAD_UP = 5;
    public static final int D_PAD_RIGHT = 6;
    public static final int D_PAD_DOWN = 7;
    public static final int D_PAD_LEFT = 8;
    public static final int L2 = 9;
    public static final int R2 = 10;
    public static final int L1 = 11;
    public static final int R1 = 12;

    // Definition of joystick axes variables for a controller joystick
    public static final int left_x_axis = 0;
    public static final int left_y_axis = 1;

    // Drivetrain controller CAN IDs
  // Used for Chassis Bot
//     public static final int RIGHT_MOTOR_1 = 1;
//     public static final int RIGHT_MOTOR_2 = 3;
//     public static final int LEFT_MOTOR_1 = 2;
//     public static final int LEFT_MOTOR_2 = 4;
    public static final int DRVTRN_SOL_FWD_CHN = 0;
    public static final int DRVTRN_SOL_RVS_CHN = 1;

    public static final int RIGHT_MOTOR_1 = 6;
    public static final int RIGHT_MOTOR_2 = 7;
    public static final int LEFT_MOTOR_1 = 4;
    public static final int LEFT_MOTOR_2 = 5;


    // This does not exist yet!
    public static final int ROTATE_TURRET_MOTOR = 10; 
   
    // Shooter controller CAN IDs
    public static final int ROTATE_SHOOTER_MOTOR_1 = 14;
    public static final int ROTATE_SHOOTER_MOTOR_2 = 15;
    public static final int SPIN_SHOOTER_MOTOR_1 = 12;
    public static final int SPIN_SHOOTER_MOTOR_2 = 13;

    // PWM channels for servo controller linear actuators
    public static final int LINEAR_ACTUATOR_1 = 1;
    public static final int LINEAR_ACTUATOR_2 = 2;

    // Position limits for servo linear actuators
    public static final double UPPER_SERVO_POS_LIMIT = 0.87; // All the way out
    public static final double LOWER_SERVO_POS_LIMIT = 0.17; // All the way in

    // Turret controller CAN IDs


    // Elevation system controller CAN IDs

    // Controller constants
    public static final double visionPID_Clip = 0.4;
    public static final double visionKp = 0.4;
    public static final double visionKi = 0.5;
    public static final double visionKd = 0.25;
    public static final double alignment_x_tolerance = 0.05;

    // Calibration of shooter
    public static final double[] distances = {
      1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    }; // feet, need to change to inches

    // Speed (% output) of the shooter motors
    public static final double[] speeds = {
      0.66, 0.68, 0.70, 0.75, 0.75, 0.75, 0.80, 0.90, 0.90, 0.90, 0.90, 0.90
    };

    // Position (PWM command) of the linear actuators
    public static final double[] position = {
      0.17, 0.27, 0.37, 0.47, 0.57, 0.67, 0.77, 0.75, 0.80, 0.82, 0.85, 0.87
    };

}
