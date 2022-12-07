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

import com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
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

    m_frontRight = Mk4SwerveModuleHelper.createFalcon500(
      Mk4SwerveModuleHelper.GearRatio.L2,
      kFRd,
      kFRs,
      kFRe,
      // Encoder offset
      -0.46
    );

    m_frontLeft = Mk4SwerveModuleHelper.createFalcon500(
      Mk4SwerveModuleHelper.GearRatio.L2,
      kFLd,
      kFLs,
      kFLe,
      // Encoder offset
      -2.823
    );

    m_backRight = Mk4SwerveModuleHelper.createFalcon500(
      Mk4SwerveModuleHelper.GearRatio.L2,
      kBRd,
      kBRs,
      kBRe,
      // Encoder offset
      0.75318456685185
    );

    m_backLeft = Mk4SwerveModuleHelper.createFalcon500(
      Mk4SwerveModuleHelper.GearRatio.L2,
      kBLd,
      kBLs,
      kBLe,
      // Encoder offset
      0.71
    );

    // Initialize swerve drive kinematics
    m_kinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase/2, -kTrackWidth/2),
        new Translation2d(kWheelBase/2, kTrackWidth/2),
        new Translation2d(-kWheelBase/2, -kTrackWidth/2),
        new Translation2d(-kWheelBase/2, kTrackWidth/2)
    );
  }

  public void drive(ChassisSpeeds chassisSpeeds) {
    m_chassisSpeeds = chassisSpeeds;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, kMaxVelocity);

    m_frontRight.set(states[0].speedMetersPerSecond / kMaxVelocity * kMaxVoltage, states[0].angle.getRadians());
    m_frontLeft.set(states[1].speedMetersPerSecond / kMaxVelocity * kMaxVoltage, states[1].angle.getRadians());
    m_backRight.set(states[2].speedMetersPerSecond / kMaxVelocity * kMaxVoltage, states[2].angle.getRadians());
    m_backLeft.set(states[3].speedMetersPerSecond / kMaxVelocity * kMaxVoltage, states[3].angle.getRadians());
  }
}
