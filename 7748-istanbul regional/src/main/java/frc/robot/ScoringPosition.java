// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class ScoringPosition {
    public static Level currentLevel;

    public static enum Level {
        L4,
        L3,
        L2,
        L1
    }

    public ScoringPosition(Level lv) {
        currentLevel = lv;
    }

    public static Level getCurrentLevel() {
        return currentLevel;
    }

    public static void setLevel(Level system) {
        if (getCurrentLevel() != system) {
            System.out.println("New Level setted to: " + getCurrentLevel() + " --> " + system);
            currentLevel = system;
        }
    }
}
