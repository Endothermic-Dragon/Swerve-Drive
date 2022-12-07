// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;

public class JoystickDriving extends CommandBase {
  Chassis m_chassis;
  Joystick m_driveJoystick, m_steerJoystick;

  /** Creates a new JoystickDriving. */
  public JoystickDriving(Chassis chassis, Joystick driveJoystick, Joystick steerJoystick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_chassis = chassis;
    m_driveJoystick = driveJoystick;
    m_steerJoystick = steerJoystick;
    addRequirements(chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    new ChassisSpeeds(
      m_driveJoystick.getX() * Constants.CANID.kMaxVelocity,
      m_driveJoystick.getY() * Constants.CANID.kMaxVelocity,
      m_steerJoystick.getX() * Constants.CANID.kMaxAngularVelocity
    );
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
