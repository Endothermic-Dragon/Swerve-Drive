// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.CANID.*;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;

public class Chassis extends SubsystemBase {
  /** Creates a new Chassis. */

  // Resources
  // Video - https://www.youtube.com/watch?v=0Xi9yb1IMyA

  SwerveModule m_frontRight, m_frontLeft, m_backRight, m_backLeft;

  SwerveDriveKinematics m_kinematics;

  ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

  public Chassis() {
    // Initialize swerve modules
    // http://gg.gg/swerve-drivetrain

    m_frontRight = Mk3SwerveModuleHelper.createFalcon500(
      Mk3SwerveModuleHelper.GearRatio.STANDARD,
      kFRd,
      kFRs,
      kFRe,
      // Encoder offset
      0
    );

    m_frontLeft = Mk3SwerveModuleHelper.createFalcon500(
      Mk3SwerveModuleHelper.GearRatio.STANDARD,
      kFLd,
      kFLs,
      kFLe,
      // Encoder offset
      0
    );

    m_backRight = Mk3SwerveModuleHelper.createFalcon500(
      Mk3SwerveModuleHelper.GearRatio.STANDARD,
      kBRd,
      kBRs,
      kBRe,
      // Encoder offset
      0
    );

    m_backLeft = Mk3SwerveModuleHelper.createFalcon500(
      Mk3SwerveModuleHelper.GearRatio.STANDARD,
      kBLd,
      kBLs,
      kBLe,
      // Encoder offset
      0
    );

    // Initialize swerve drive kinematics
    double y = xOffset, x = yOffset;
    m_kinematics = new SwerveDriveKinematics(
        new Translation2d(x, -y), new Translation2d(x, y), new Translation2d(-x, -y), new Translation2d(-x, y));
  }

  public void drive(ChassisSpeeds chassisSpeeds) {
    m_chassisSpeeds = chassisSpeeds;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, maxVelocity);

    m_frontRight.set(states[0].speedMetersPerSecond / maxVelocity * maxVoltage, states[0].angle.getRadians());
    m_frontLeft.set(states[1].speedMetersPerSecond / maxVelocity * maxVoltage, states[1].angle.getRadians());
    m_backRight.set(states[2].speedMetersPerSecond / maxVelocity * maxVoltage, states[2].angle.getRadians());
    m_backLeft.set(states[3].speedMetersPerSecond / maxVelocity * maxVoltage, states[3].angle.getRadians());
  }
}
