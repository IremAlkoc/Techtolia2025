// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmIntakeSubsystem extends SubsystemBase {
  /** Creates a new ArmIntakeSubsystem. */
  SparkMax armIntakeMotor = new SparkMax(23, MotorType.kBrushless);
  SparkMax armIntakeMotor2 = new SparkMax(24, MotorType.kBrushless);
  

  public ArmIntakeSubsystem() {

    SparkMaxConfig leftConfig = new SparkMaxConfig();
    leftConfig.smartCurrentLimit(40).idleMode(IdleMode.kCoast).inverted(true);
    armIntakeMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SparkMaxConfig rightConfig = new SparkMaxConfig();
    rightConfig.smartCurrentLimit(40).idleMode(IdleMode.kCoast).inverted(false);
    armIntakeMotor2.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void setIntakeSpeed(double speed) {
    armIntakeMotor.set(speed);
    armIntakeMotor2.set(speed);
  }

  public void setIntakeAlgSpeed(double speed) {
    armIntakeMotor.set(-speed);
    armIntakeMotor2.set(-speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
