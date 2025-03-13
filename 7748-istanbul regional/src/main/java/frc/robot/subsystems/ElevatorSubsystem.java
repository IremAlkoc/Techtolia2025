// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  public SparkMax leftLeader;
  public SparkMax leftFollower;
  SparkMaxConfig globalConfig = new SparkMaxConfig();
  SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();

  private SparkClosedLoopController closedLoopController;
  private RelativeEncoder encoder;

  public ElevatorSubsystem() {
    leftLeader = new SparkMax(20, MotorType.kBrushless);
    leftFollower = new SparkMax(21, MotorType.kBrushless);

    globalConfig
        .smartCurrentLimit(60)
        .idleMode(IdleMode.kBrake);

    leftFollowerConfig
        .apply(globalConfig)
        .follow(leftLeader);

    closedLoopController = leftLeader.getClosedLoopController();

    encoder = leftLeader.getEncoder();

    globalConfig.encoder
        .positionConversionFactor(1);

    globalConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        .p(0.20)
        .i(0)
        .d(0.05)
        .outputRange(-1, 1);

    globalConfig.closedLoop.maxMotion
        .maxVelocity(3000)
        .maxAcceleration(2500)
        .allowedClosedLoopError(1);

    encoder.setPosition(0);

    leftLeader.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    leftFollower.configure(leftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public double getElevatorPosition() {
    return encoder.getPosition();
  }

  public void setElevatorPosition(double pos) {
    closedLoopController.setReference(pos, ControlType.kMAXMotionPositionControl, ClosedLoopSlot.kSlot0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Elevator Position", getElevatorPosition());
  }
}
