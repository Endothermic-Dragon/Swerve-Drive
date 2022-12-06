// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  /** Creates a new Chassis. */

  // Resources
  // TalonSRX - https://store.ctr-electronics.com/content/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_talon_s_r_x.html
  // CANCoder - https://store.ctr-electronics.com/content/api/java/html/classcom_1_1ctre_1_1phoenix_1_1sensors_1_1_c_a_n_coder.html

  SwerveModule frontRight, frontLeft, backRight, backLeft;

  SwerveDriveKinematics m_kinematics;
  
  public Chassis() {
    // Initialize swerve modules
    frontRight = new SwerveModule(Constants.CANID.kFRd, Constants.CANID.kFRs, Constants.CANID.kFRe);
    frontRight.d.setSelectedSensorPosition(0);
    frontRight.s.setSelectedSensorPosition(0);

    frontLeft = new SwerveModule(Constants.CANID.kFLd, Constants.CANID.kFLs, Constants.CANID.kFLe);
    frontLeft.d.setSelectedSensorPosition(0);
    frontLeft.s.setSelectedSensorPosition(0);

    backRight = new SwerveModule(Constants.CANID.kBRd, Constants.CANID.kBRs, Constants.CANID.kBRe);
    backRight.d.setSelectedSensorPosition(0);
    backRight.s.setSelectedSensorPosition(0);

    backLeft = new SwerveModule(Constants.CANID.kBLd, Constants.CANID.kBLs, Constants.CANID.kBLe);
    backLeft.d.setSelectedSensorPosition(0);
    backLeft.s.setSelectedSensorPosition(0);

    // Initialize swerve drive kinematics
    double y = Constants.CANID.xOffset, x = Constants.CANID.yOffset;
    m_kinematics = new SwerveDriveKinematics(
      new Translation2d(x, y), new Translation2d(x, -y), new Translation2d(-x, y), new Translation2d(-x, -y)
    );
  }

  public void setMovement(double xVelocity, double yVelocity, double turnRate){
    ChassisSpeeds speeds = new ChassisSpeeds(xVelocity, yVelocity, turnRate);
    SwerveModuleState[] moduleStates = m_kinematics.toSwerveModuleStates(speeds);
    
    // Front left module state
    SwerveModuleState frontLeftState = moduleStates[0];
    frontLeftState = SwerveModuleState.optimize(frontLeftState, new Rotation2d(frontLeft.e.getPosition()));
    Rotation2d a = frontLeftState.angle;
    double b = frontLeftState.speedMetersPerSecond;
    
    // Front right module state
    SwerveModuleState frontRightState = moduleStates[1];
    frontRightState = SwerveModuleState.optimize(frontRightState, new Rotation2d(frontRight.e.getPosition()));
    
    // Back left module state
    SwerveModuleState backLeftState = moduleStates[2];
    backLeftState = SwerveModuleState.optimize(backLeftState, new Rotation2d(backLeft.e.getPosition()));
    
    // Back right module state
    SwerveModuleState backRightState = moduleStates[3];
    backRightState = SwerveModuleState.optimize(backRightState, new Rotation2d(backRight.e.getPosition()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
