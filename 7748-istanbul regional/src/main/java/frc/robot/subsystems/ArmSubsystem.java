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

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  private SparkMax motor;
  SparkMaxConfig motorConfig = new SparkMaxConfig();
  
  
  private SparkClosedLoopController closedLoopController;
  private RelativeEncoder encoder;
  
  public ArmSubsystem() {
    motor = new SparkMax(22, MotorType.kBrushless);
    closedLoopController = motor.getClosedLoopController();
  

    motorConfig
    .smartCurrentLimit(60)
    .idleMode(IdleMode.kBrake);

    encoder = motor.getEncoder();






    motorConfig.encoder
        .positionConversionFactor(1);
  
    motorConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        .p(2)
        .i(0)
        .d(0)
        .outputRange(-1, 1);

    motorConfig.closedLoop.maxMotion
        .maxVelocity(7000)
        .maxAcceleration(4000)
        .allowedClosedLoopError(1);

    encoder.setPosition(0);
    motor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  public double getArmPosition() {
    return encoder.getPosition();
  }

  public void setArmPosition(double pos) {
    closedLoopController.setReference(pos, ControlType.kMAXMotionPositionControl, ClosedLoopSlot.kSlot0);
    SmartDashboard.putNumber("gelen arm pos deðer", pos);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Arm Position", getArmPosition());

  }
}
