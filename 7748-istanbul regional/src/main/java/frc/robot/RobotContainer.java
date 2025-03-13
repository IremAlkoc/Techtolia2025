// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmIntakeSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Superstructure;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve/neo"));
  public static ArmIntakeSubsystem armIntake = new ArmIntakeSubsystem();
  public static ArmSubsystem arm = new ArmSubsystem();
  public static ElevatorSubsystem elevator = new ElevatorSubsystem();
  public static Superstructure superstructure = new Superstructure();


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    
  }

  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
