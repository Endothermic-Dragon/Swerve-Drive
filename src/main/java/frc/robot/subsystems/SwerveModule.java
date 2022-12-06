// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase {
  /** Creates a new SwerveModule. */
  public final TalonSRX driveMotor, d;
  public final TalonSRX steerMotor, s;
  public final CANCoder cancoder, e;

  public SwerveModule(int drive, int steer, int encoder) {
    driveMotor = new TalonSRX(drive);
    d = driveMotor;
    steerMotor = new TalonSRX(steer);
    s = steerMotor;
    cancoder = new CANCoder(encoder);
    e = cancoder;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
