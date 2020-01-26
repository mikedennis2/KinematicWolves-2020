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
    public static final int RIGHT_MOTOR_1 = 1;
    public static final int RIGHT_MOTOR_2 = 2;
    public static final int LEFT_MOTOR_1 = 3;
    public static final int LEFT_MOTOR_2 = 3;

    // Shooter controller CAN IDs


    // Turret controller CAN IDs


    // Elevation system controller CAN IDs


}
