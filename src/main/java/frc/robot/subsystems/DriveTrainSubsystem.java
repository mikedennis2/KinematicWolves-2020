/*----------------------------------------------------------------------------*/
/* 1/22/2020 v1                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  public boolean isHighGear = false; // Initialize to low gear

  private final WPI_VictorSPX rightMaster = new WPI_VictorSPX(Constants.RIGHT_MOTOR_1); // This is the CAN ID for the device
  private final WPI_VictorSPX rightSlave = new WPI_VictorSPX(Constants.RIGHT_MOTOR_2); // This is the CAN ID for the device
  private final WPI_VictorSPX leftMaster = new WPI_VictorSPX(Constants.LEFT_MOTOR_1); // This is the CAN ID for the device
  private final WPI_VictorSPX leftSlave = new WPI_VictorSPX(Constants.LEFT_MOTOR_2); // This is the CAN ID for the device
  
  private final DoubleSolenoid DriveTrainSwitch = new DoubleSolenoid(Constants.DRVTRN_SOL_FWD_CHN, Constants.DRVTRN_SOL_RVS_CHN); // This is the definition of the solenoid for switching gears in the drivetrain 

  private final DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  public DriveTrainSubsystem() { 

    // Set motor slave relationships
    rightSlave.setInverted(true);
    rightMaster.setInverted(true);
    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);

  }

  public void move_with_joysticks(Joystick driver_controller) {

    // Get axis values for speed and rotational speed
    double xSpeed = driver_controller.getRawAxis(Constants.left_x_axis);
    double zRotation_rate = driver_controller.getRawAxis(Constants.left_y_axis);

    // Drive Robot with commanded linear velocity and yaw rate commands
    drive.arcadeDrive(xSpeed, zRotation_rate);
  }

	public void shiftGear() {

    // Shift gears logic (if we are high gear, downshift, otherwise upshift)
		if (isHighGear) {
			shiftToLowGear();
		} else {
			shiftToHighGear();
    }
    
	}

  private void shiftToHighGear() {
    // Set solenoid switch to forward
		DriveTrainSwitch.set(Value.kForward);
		isHighGear = true;
	}

	private void shiftToLowGear() {
    // Set solenoid switch to reverse
		DriveTrainSwitch.set(Value.kReverse);
		isHighGear = false;
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
