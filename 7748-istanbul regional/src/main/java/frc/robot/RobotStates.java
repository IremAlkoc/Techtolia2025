// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.ScoringPosition.Level;

/** Add your docs here. */
public class RobotStates {

    public static enum SuperstructureState {
        IDLE,
        HUMANTAKING,
        SCORING, HUMANTAKING_STOP, ALG, ALGstop
    }

    public static Boolean Scored;

    public static void setScore(boolean sc) {
        Scored = sc;
    }

    public static boolean IsScored() {
        return Scored;
    }

    public static ScoringPosition currentScoringPosition = new ScoringPosition(Level.L1);

    public static ScoringPosition getScoringPosition() {
        return currentScoringPosition;
    }

    public static SuperstructureState currentSuperstructureState = SuperstructureState.IDLE;

    public static void setSuperstructureState(SuperstructureState system) {
        if (RobotStates.getSuperstructureState() != system) {
            System.out.println("New Robot State setted to: " + RobotStates.getSuperstructureState() + " --> " + system);
            currentSuperstructureState = system;
        }
    }

    public static SuperstructureState getSuperstructureState() {
        return currentSuperstructureState;
    }
}
