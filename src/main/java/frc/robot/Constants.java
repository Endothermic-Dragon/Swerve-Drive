// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class CANID {
        // FIND THESE LATER

        // Front right drive/steer/encoder
        public static final int kFRd = 11;
        public static final int kFRs = 21;
        public static final int kFRe = 31;

        // Front left drive/steer/encoder
        public static final int kFLd = 14;
        public static final int kFLs = 24;
        public static final int kFLe = 34;

        // Back right drive/steer/encoder
        public static final int kBRd = 12;
        public static final int kBRs = 22;
        public static final int kBRe = 32;

        // Back left drive/steer/encoder
        public static final int kBLd = 13;
        public static final int kBLs = 23;
        public static final int kBLe = 33;

        // xOffset is horizontal offset from center
        // yOffset is vertical offset from center
        public static final double xOffset = 0.4;
        public static final double yOffset = 0.3;
    }
}
