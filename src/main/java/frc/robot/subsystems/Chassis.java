// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.CANID.*;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.sensors.CANCoder;
import com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;

public class Chassis extends SubsystemBase {
  /** Creates a new Chassis. */

  // Resources
  // Video - https://www.youtube.com/watch?v=0Xi9yb1IMyA

  SwerveModule m_frontRight, m_frontLeft, m_backRight, m_backLeft;

  SwerveDriveKinematics m_kinematics;

  ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

  // these are for the encoders and are used for finding the offsets
  // private final CANCoder m_frontLeftModuleEncoder;
  // private final CANCoder m_frontRightModuleEncoder;
  // private final CANCoder m_backLeftModuleEncoder;
  // private final CANCoder m_backRightModuleEncoder;

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
    SwerveDriveKinematics.desaturateWheelSpeeds(states, kMaxVelocity);

    m_frontRight.set(states[0].speedMetersPerSecond / kMaxVelocity * kMaxVoltage, states[0].angle.getRadians());
    m_frontLeft.set(states[1].speedMetersPerSecond / kMaxVelocity * kMaxVoltage, states[1].angle.getRadians());
    m_backRight.set(states[2].speedMetersPerSecond / kMaxVelocity * kMaxVoltage, states[2].angle.getRadians());
    m_backLeft.set(states[3].speedMetersPerSecond / kMaxVelocity * kMaxVoltage, states[3].angle.getRadians());
  }
}



/*

{
    "workbench.colorTheme": "Visual Studio Dark",
    "editor.suggestSelection": "first",
    "vsintellicode.modify.editor.suggestSelection": "automaticallyOverrodeDefaultValue",
    "files.exclude": {
        "**\/.classpath": true,
        "**\/.project": true,
        "**\/.settings": true,
        "**\/.factorypath": true
    },
    "java.jdt.ls.java.home": "/Library/Java/JavaVirtualMachines/jdk-17.0.1.jdk/Contents/Home",
    "java.configuration.runtimes": [
        {
            "name": "JavaSE-11",
            "path": "/Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home",
        }
    ],
    "java.help.firstView": "gettingStarted",
    "files.autoSave": "afterDelay",
    "liveshare.presence": true,
    "git.autofetch": true,
    "git.confirmSync": false,
    "[javascript]": {
        "editor.defaultFormatter": "esbenp.prettier-vscode"
    },
    "window.zoomLevel": 1,
    "editor.unicodeHighlight.nonBasicASCII": false,
    "editor.bracketPairColorization.enabled": false,
    "sonarlint.rules": {
        "javascript:S3776": {
            "level": "off"
        },
        "python:S1192": {
            "level": "off"
        },
        "javascript:S2814": {
            "level": "off"
        },
        "python:S3776": {
            "level": "off"
        },
        "python:S117": {
            "level": "off"
        },
        "Web:S5254": {
            "level": "off"
        },
        "python:S2208": {
            "level": "off"
        },
        "javascript:S125": {
            "level": "off"
        },
        "python:S5797": {
            "level": "off"
        },
        "javascript:S2703": {
            "level": "off"
        },
        "python:S1542": {
            "level": "off"
        },
        "python:S5869": {
            "level": "off"
        },
        "python:S1854": {
            "level": "off"
        },
        "python:S125": {
            "level": "off"
        },
        "javascript:S3504": {
            "level": "off"
        },
        "java:S106": {
            "level": "off"
        },
        "java:S1220": {
            "level": "off"
        },
        "java:S2140": {
            "level": "off"
        },
        "java:S1192": {
            "level": "off"
        },
        "Web:ImgWithoutAltCheck": {
            "level": "off"
        },
        "java:S1659": {
            "level": "off"
        },
        "java:S116": {
            "level": "off"
        }
    },
    "[python]": {
        "editor.defaultFormatter": "ms-python.python"
    },
    "[html]": {
        "editor.defaultFormatter": "esbenp.prettier-vscode"
    },
    "[scss]": {
        "editor.defaultFormatter": "esbenp.prettier-vscode"
    },
    "emmet.includeLanguages": {"jinja-html": "html"},
    "editor.defaultFormatter": "vscode.emmet",
    "files.associations": {
        "*.html": "jinja-html"
    },
    "[css]": {
        "editor.defaultFormatter": "esbenp.prettier-vscode"
    },
    "terminal.integrated.defaultProfile.windows": "Command Prompt",
    "[jsonc]": {
        "editor.defaultFormatter": "vscode.json-language-features"
    },
    "manim-sideview.runOnSave": true,
    "manim-sideview.outputToTerminal": true,
    "[jinja-html]": {
        "editor.defaultFormatter": "esbenp.prettier-vscode"
    },
    "workbench.editorAssociations": {
        "*.csv": "default"
    },
    "java.inlayHints.parameterNames.enabled": "none",
    "[java]": {
        "editor.defaultFormatter": "redhat.java"
    },
    "[cpp]": {
        "editor.defaultFormatter": "ms-vscode.cpptools"
    },
    "[json]": {
        "editor.defaultFormatter": "esbenp.prettier-vscode"
    }
}

*/