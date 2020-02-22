/*----------------------------------------------------------------------------*/
/* 1/22/2020 v1                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.SlewRateLimiter;;

public class DriveTrainSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  
  public SlewRateLimiter rotationFilter = new SlewRateLimiter(Constants.SLEW_RATE_LIMIT_ROTATE);
  public SlewRateLimiter accelerationFilter = new SlewRateLimiter(Constants.SLEW_RATE_LIMIT_ACCEL);

  public double gear_for_traj_following = Constants.GR2; // Assume we want to do trajectory following in second gear
  public double robot_wheel_radius = Units.inchesToMeters(Constants.WheelRadius); // Convert from inches to meters
  public boolean isHighGear = false; // Initialize to low gear

  private final WPI_TalonFX rightMaster = new WPI_TalonFX(Constants.RIGHT_MOTOR_1); // This is the CAN ID for the device
  private final WPI_TalonFX rightSlave = new WPI_TalonFX(Constants.RIGHT_MOTOR_2); // This is the CAN ID for the device
  private final WPI_TalonFX leftMaster = new WPI_TalonFX(Constants.LEFT_MOTOR_1); // This is the CAN ID for the device
  private final WPI_TalonFX leftSlave = new WPI_TalonFX(Constants.LEFT_MOTOR_2); // This is the CAN ID for the device
  private final ADIS16448_IMU imu = new ADIS16448_IMU();
  private final Encoder leftEncoder = new Encoder(Constants.LEFT_ENCODER_channel1A, Constants.LEFT_ENCODER_channel1B);
  private final Encoder rightEncoder = new Encoder(Constants.RIGHT_ENCODER_channel1A, Constants.RIGHT_ENCODER_channel1B);

  private final DoubleSolenoid DriveTrainSwitch = new DoubleSolenoid(Constants.DRVTRN_SOL_FWD_CHN, Constants.DRVTRN_SOL_RVS_CHN); // This is the definition of the solenoid for switching gears in the drivetrain 

  private final DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(Constants.TrackWidth));
  private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(GetAngle());
  private Pose2d pose = new Pose2d();

  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Constants.RobotCharacterization_kS, Constants.RobotCharacterization_kV, Constants.RobotCharacterization_kA);
  private final PIDController leftPIDController = new PIDController(Constants.Traj_Following_Feedback_P_Gain, 0, 0);
  private final PIDController rightPIDController = new PIDController(Constants.Traj_Following_Feedback_P_Gain, 0, 0);

  public DriveTrainSubsystem() {      

    leftSlave.setInverted(false);
    leftMaster.setInverted(false);
    
    rightSlave.setInverted(false);
    rightMaster.setInverted(false);

    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);

    leftEncoder.setDistancePerPulse(2 * Math.PI / Constants.EncoderResolution);
    rightEncoder.setDistancePerPulse(2 * Math.PI / Constants.EncoderResolution);

    leftEncoder.reset();
    rightEncoder.reset();
    imu.reset();

  }

  public Rotation2d GetAngle() {

    return Rotation2d.fromDegrees(-imu.getAngle());

  }

  public DifferentialDriveWheelSpeeds GetSpeeds() {

    double left_wheel_speed; // m/s
    double right_wheel_speed; // m/s

    // Calculate wheel speeds
    left_wheel_speed = (leftEncoder.getRate() / gear_for_traj_following) * robot_wheel_radius;
    right_wheel_speed = (rightEncoder.getRate() / gear_for_traj_following) * robot_wheel_radius;

    // Return DifferentialDriveWheelSpeeds object
    return new DifferentialDriveWheelSpeeds(left_wheel_speed, right_wheel_speed);

  }

  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  public Pose2d getPose() {

    return pose;

  }

  public SimpleMotorFeedforward getFeedforward() {

    return feedforward;

  }

  public PIDController getLeftPIDController() {

    return leftPIDController;

  }

  public PIDController getRightPIDController() {

    return rightPIDController;

  }

  public void setOutputVolts(double leftVolts, double rightVolts) {

    leftMaster.set(ControlMode.PercentOutput, leftVolts / 12);
    rightMaster.set(ControlMode.PercentOutput, rightVolts / 12);

  }

  public void reset() {

    odometry.resetPosition(new Pose2d(), GetAngle());

  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
    double leftDistance;
    double rightDistance;

    // Update distance traveled
    leftDistance = (leftEncoder.getDistance() / gear_for_traj_following) * robot_wheel_radius; // m
    rightDistance = (rightEncoder.getDistance() / gear_for_traj_following) * robot_wheel_radius; // m

    // Update pose
    pose = odometry.update(GetAngle(), leftDistance, rightDistance);

  }

  public void move_with_joysticks(Joystick driver_controller) {

    // Get axis values for speed and rotational speed
    double xSpeed = driver_controller.getRawAxis(Constants.left_y_axis);
    double zRotation_rate = -1*driver_controller.getRawAxis(Constants.left_x_axis);

    accelerationFilter.calculate(xSpeed);
    rotationFilter.calculate(zRotation_rate);

    // Drive Robot with commanded linear velocity and yaw rate commands
    drive.arcadeDrive(xSpeed, zRotation_rate);

    SmartDashboard.putNumber("X speed commanded by driver", driver_controller.getRawAxis(Constants.left_x_axis));
    SmartDashboard.putNumber("zRotation Rate Commanded by driver", driver_controller.getRawAxis(Constants.left_y_axis));
  }

  public void rotateDrivetrain(double zRotation_rate){
    drive.arcadeDrive(0, zRotation_rate);
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

  public void align_with_target(double zRotation_rate){

    double clip_pid_speed = Constants.visionPID_Clip;

    SmartDashboard.putNumber("zRotation commanded by controller", zRotation_rate);

    if (zRotation_rate > clip_pid_speed){
      zRotation_rate = clip_pid_speed;
    }

    if (zRotation_rate < -clip_pid_speed){
      zRotation_rate = -clip_pid_speed;
    }

    drive.arcadeDrive(0, zRotation_rate);

  }
  
}
