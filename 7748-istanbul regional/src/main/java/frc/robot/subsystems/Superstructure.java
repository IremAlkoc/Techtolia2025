// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.math.Conversions;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotStates;
import frc.robot.RobotStates.SuperstructureState;


public class Superstructure extends SubsystemBase {
  /** Creates a new Superstructure. */
  

  public Superstructure() { 
  }

  @Override
  public void periodic() {  

    switch (RobotStates.getSuperstructureState()) {
      case IDLE:
        RobotContainer.arm.setArmPosition(Constants.OperatorConstants.armIDLEposition);
        if (RobotContainer.arm.getArmPosition() < 2) {
          
          RobotContainer.elevator.setElevatorPosition(0);
        }
        RobotContainer.armIntake.setIntakeSpeed(0);
        RobotStates.setScore(false);
      break;

      case HUMANTAKING:
        RobotContainer.arm.setArmPosition(Constants.OperatorConstants.armHUMANTAKINGposition);
        if (RobotContainer.arm.getArmPosition() < 2) {
          RobotContainer.elevator.setElevatorPosition(0);
        }
        RobotContainer.armIntake.setIntakeSpeed(Constants.OperatorConstants.humanTakingSpeed);
       
       
      break;
      case HUMANTAKING_STOP:  
        RobotContainer.armIntake.setIntakeSpeed(0);
      break;

      case ALG:
        RobotContainer.armIntake.setIntakeAlgSpeed(Constants.OperatorConstants.intakeAlgSpeed);
       
      break;
      case ALGstop:
        RobotContainer.armIntake.setIntakeAlgSpeed(0);
       
      break;

      case SCORING:
        switch (RobotStates.getScoringPosition().getCurrentLevel()) {
          case L1:
            RobotContainer.elevator.setElevatorPosition(Constants.OperatorConstants.elevatorLevel1Position);
            RobotContainer.arm.setArmPosition(Constants.OperatorConstants.armLevel1Position);
          break;
          case L2:
            RobotContainer.elevator.setElevatorPosition(Constants.OperatorConstants.elevatorLevel2Position);
            RobotContainer.arm.setArmPosition(Constants.OperatorConstants.armLevel2Position);
          break;
          case L3:
            RobotContainer.elevator.setElevatorPosition(Constants.OperatorConstants.elevatorLevel3Position);
            RobotContainer.arm.setArmPosition(Constants.OperatorConstants.armLevel3Position);
          break;
          case L4:
            RobotContainer.elevator.setElevatorPosition(Constants.OperatorConstants.elevatorLevel4Position);
            RobotContainer.arm.setArmPosition(Constants.OperatorConstants.armLevel4Position);
          break;
        }

        if (!RobotStates.IsScored()) {
          switch (RobotStates.getScoringPosition().getCurrentLevel()) {
            case L1:
              RobotContainer.arm.setArmPosition(Constants.OperatorConstants.armLevel1Position);
              SmartDashboard.putNumber("intake sensor", Constants.OperatorConstants.armLevel1Position);

            break;
            case L2:
              RobotContainer.arm.setArmPosition(Constants.OperatorConstants.armLevel2Position);
              SmartDashboard.putNumber("intake sensor", Constants.OperatorConstants.armLevel2Position);
            break;
            case L3:
              if (Conversions.epsilonEquals(RobotContainer.elevator.getElevatorPosition(), 55, 0.5)) {
                SmartDashboard.putNumber("intake sensor", Constants.OperatorConstants.armLevel3Position);
              }
            break;
            case L4:
              if (Conversions.epsilonEquals(RobotContainer.elevator.getElevatorPosition(), 55, 0.5)) {
                SmartDashboard.putNumber("intake sensor", Constants.OperatorConstants.armLevel4Position);
              }
            break;
          }
        } else {
          switch (RobotStates.getScoringPosition().getCurrentLevel()) {
            case L1:
              RobotContainer.armIntake.setIntakeSpeed(Constants.OperatorConstants.armLevel1IntakeSpeed);
            break;
            case L2:
              RobotContainer.armIntake.setIntakeSpeed(Constants.OperatorConstants.armLevel2IntakeSpeed);
            break;
            case L3:
              RobotContainer.armIntake.setIntakeSpeed(Constants.OperatorConstants.armLevel3IntakeSpeed);
            break;
            case L4:
              RobotContainer.armIntake.setIntakeSpeed(-Constants.OperatorConstants.armLevel4IntakeSpeed);
            break;
          }
        }
      break;

      default:
        RobotStates.setSuperstructureState(SuperstructureState.IDLE);
      break;
    }
  }
}
