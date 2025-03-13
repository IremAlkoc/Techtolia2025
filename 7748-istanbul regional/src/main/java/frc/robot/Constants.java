// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {

  public static final double MAX_SPEED = Units.feetToMeters(16.6);
  public static final int BLINKIN_LED_CONTROLLER_PORT=0;

  public static final int intakeSensorPort=0;
  public static final int havuzSensorPort=1;
  


  public static class OperatorConstants {

    // Joystick Deadband
    public static final double DEADBAND = 0.15;
    public static final double LEFT_Y_DEADBAND = 0.15;
    public static final double RIGHT_X_DEADBAND = 0.15;
    public static final double TURN_CONSTANT = 6;

    /* operator stick tuşları */
    public static final int level1Button = 1; //asansör 1. seviye tuşu
    public static final int level2Button = 2; //asansör 2. seviye tuşu
    public static final int level3Button = 3; //asansör 3. seviye tuşu
    public static final int level4Button = 4; //asansör 4. seviye tuşu
    public static final int shootButton = 5; // coral bırakma tuşu
    public static final int intakeButton = 6; // coral alma tuşu
    public static final int homeLevelButton = 7; // asansör başlangıç pozisyonu

    /* driver stick tuşları */
    
    public static final int navxResetButton = 1; //asansör 1. seviye tuşu
    public static final int getAlgButton = 6; //asansör 1. seviye tuşu
    public static final int stopAlgButton = 8; //asansör 1. seviye tuşu



    /*joystick bağlantı portları */
    public static final int driveStickPort=0;
    public static final int operatorStickPort=1;


    /*intake alg icin hizi */  
    public static final double intakeAlgSpeed=-0.2;
    
    /*intake coral alma için hızı */
    public static final double humanTakingSpeed=0.2;

    /*Asansör pozisyonları */
    public static final double elevatorLevel1Position=9.3;
    public static final double elevatorLevel2Position=23;
    public static final double elevatorLevel3Position=55;
    public static final double elevatorLevel4Position=55;

    /* arm pozisyonları */
    public static final double armLevel1Position=2;
    public static final double armLevel2Position=3.75;
    public static final double armLevel3Position=2;
    public static final double armLevel4Position=26;

    public static final double armALGPosition=4;

    /* intake coral bırakma hızları  */
    public static final double armLevel1IntakeSpeed=0.4;
    public static final double armLevel2IntakeSpeed=0.2;
    public static final double armLevel3IntakeSpeed=0.2;
    public static final double armLevel4IntakeSpeed=0.2;
    
    /* asansör ek pozisyonları */
    public static final double armIDLEposition=0.65;
    public static final double armHUMANTAKINGposition=0.65;

    

    



  }
}
