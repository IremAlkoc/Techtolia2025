// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.OperatorConstants;
import frc.robot.RobotStates.SuperstructureState;
import frc.robot.ScoringPosition.Level;
import swervelib.SwerveInputStream;


public class Robot extends TimedRobot {
  private static Robot instance;
  private Command m_autonomousCommand;
  private DigitalInput intakeSensor;
  private DigitalInput havuzSensor;
  private final RobotContainer m_robotContainer;
  PS4Controller driveStick = new PS4Controller(Constants.OperatorConstants.driveStickPort);
  XboxController operatorStick = new XboxController(Constants.OperatorConstants.operatorStickPort);
  private final PWM leds = new PWM(0); // Rev Blinkin PWM portu 0

 
  public Robot() {
    m_robotContainer = new RobotContainer();
    instance = this;
  }

  public void robotInit() {
    intakeSensor = new DigitalInput(Constants.intakeSensorPort);
    havuzSensor = new DigitalInput(Constants.havuzSensorPort);
  }

  public static Robot getInstance() {
    return instance;
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    boolean intakeSensorState = intakeSensor.get();
    boolean havuzSensorState = havuzSensor.get();
    
    SmartDashboard.putBoolean("intake sensor", intakeSensorState);
    SmartDashboard.putBoolean("havuz sensor", havuzSensorState);
    SmartDashboard.putString("state", RobotStates.getSuperstructureState().toString());

    RobotContainer.arm.setArmPosition(Constants.OperatorConstants.armIDLEposition);


    /* led ışık ayarları */

    /*coral havuzda */
    if ((havuzSensorState == false) && (intakeSensorState == false)) {
      leds.setSpeed(0.57);
    }

    /*coral intake içinde */
    if ((havuzSensorState == true) && (intakeSensorState == false)) {
      leds.setSpeed(0.91);
    }

  

    /*coral yok */
if ((havuzSensorState == true) && (intakeSensorState == true)) {
      leds.setSpeed(0.79);
    }
    

    SwerveInputStream driveAngularVelocity = SwerveInputStream.of(RobotContainer.drivebase.getSwerveDrive(),
        () -> driveStick.getRawAxis(1) * -1,
        () -> driveStick.getRawAxis(0) * -0.8)
        .withControllerRotationAxis(() -> driveStick.getRawAxis(2))
        .deadband(OperatorConstants.DEADBAND)
        .scaleTranslation(0.8)
        .allianceRelativeControl(false);

        

    Command driveFieldOrientedAnglularVelocity = RobotContainer.drivebase.driveFieldOriented(driveAngularVelocity);
    RobotContainer.drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity);

    
    /*navx sıfırlama kodu */
    if (driveStick.getRawButton(Constants.OperatorConstants.navxResetButton)) {
      RobotContainer.drivebase.zeroGyro();
    }


    if (driveStick.getRawButton(Constants.OperatorConstants.getAlgButton)) {
      RobotContainer.arm.setArmPosition(Constants.OperatorConstants.armALGPosition);

    }



    if (operatorStick.getRawButton(Constants.OperatorConstants.level1Button)) {
      SmartDashboard.putString("Asansör Seviyesi:","level1");
      RobotStates.getScoringPosition().setLevel(Level.L1);
    } else if (operatorStick.getRawButton(Constants.OperatorConstants.level2Button)) {
      SmartDashboard.putString("Asansör Seviyesi:","level2");
      RobotStates.getScoringPosition().setLevel(Level.L2);
    } else if (operatorStick.getRawButton(Constants.OperatorConstants.level3Button)) {
      SmartDashboard.putString("Asansör Seviyesi:","level3");
      RobotStates.getScoringPosition().setLevel(Level.L3);
    } else if (operatorStick.getRawButton(Constants.OperatorConstants.level4Button)) {
      RobotStates.getScoringPosition().setLevel(Level.L4);
      SmartDashboard.putString("Asansör Seviyesi:","level4");
    }

    // Human dan coral alma pozisyonuna geçiş
    if (operatorStick.getRawButton(Constants.OperatorConstants.homeLevelButton)) {
      RobotStates.setSuperstructureState(SuperstructureState.IDLE);
    }

    if (operatorStick.getRawButton(Constants.OperatorConstants.shootButton)) {
      RobotStates.setSuperstructureState(SuperstructureState.SCORING);
    }
    // intake çalıştırma (havuz sensörü ve intake sensörü boş ise çalışıyor)
    if (operatorStick.getRawButton(Constants.OperatorConstants.intakeButton)) {
      if ((havuzSensorState == false) && (intakeSensorState == false)) {
        RobotStates.setSuperstructureState(SuperstructureState.HUMANTAKING);
        System.out.println(RobotStates.getSuperstructureState());
      }

    }
    if ((havuzSensorState == true) || (intakeSensorState == true)) {
      if (RobotStates.getSuperstructureState() == SuperstructureState.HUMANTAKING) {
        RobotStates.setSuperstructureState(SuperstructureState.HUMANTAKING_STOP);
        System.out.println(RobotStates.getSuperstructureState());

      }
    }

    if (operatorStick.getRawButton(8) && RobotStates.getSuperstructureState() == SuperstructureState.SCORING) {

       if (havuzSensorState==true && intakeSensorState==false){
      RobotStates.setScore(true);
       }
       else {
       
        RobotStates.setScore(false);
  
        
  
         }


    }

    // if ((havuzSensorState==true && intakeSensorState==true)){
    // RobotStates.setScore(false);
    // SmartDashboard.putBoolean("Set score=", false);
    // }

    if (driveStick.getRawButton(Constants.OperatorConstants.getAlgButton)) {
      RobotStates.setSuperstructureState(SuperstructureState.ALG);
    }
    
    //if (operatorStick.getRawButtonReleased(9)) {
      
    if (driveStick.getRawButton(Constants.OperatorConstants.stopAlgButton)) {
      RobotStates.setSuperstructureState(SuperstructureState.ALGstop);
    }

  }

  @Override

  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {

    if (operatorStick.getRawButton(2)) {
      RobotContainer.elevator.setElevatorPosition(15);
    } else if (operatorStick.getRawButton(4)) {
      RobotContainer.elevator.setElevatorPosition(35);
    }
  }
  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  }
  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
  
}
