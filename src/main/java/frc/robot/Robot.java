/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.STICK_CONST.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.swing.plaf.synth.SynthStyle;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.WheelOfDoom;
import frc.robot.Constants.STICK_CONST.*;
import frc.robot.commands.Spin;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private Counter m_LIDAR;
  FileOutputStream writer;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    //LIDAR
    m_LIDAR = new Counter(Constants.LIDAR_PORT);
    m_LIDAR.setMaxPeriod(1.00); // sau 1s thì dừng 
    m_LIDAR.setSemiPeriodMode(true);
    m_LIDAR.reset();
    //đếm lại từ 0
  }

  final double offset = 10;
  //độ lệch (chưa chắc đúng, phải thử bằng thực nghiệm)

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    SmartDashboard.putNumber("foo", SmartDashboard.getNumber("foo", 0) + 1);
    
// Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    //lidar
    double dist; //distance
    if (m_LIDAR.get() < 1) //nếu đọc từ lidar là 0 thì dừng, tránh code lỗi 
      dist = 0;
    else
      dist = (m_LIDAR.getPeriod()*1000000.0/10.0) - offset //tính khoảng cách nhớ trừ độ lệch offset
      //hàm getPeriod() cho đơn vị là giây nên phải đổi về ms (micro second) r tính đc kc
    SmartDashboard.putnumber("distance", dist); // gửi về dashboard khoảng cách đến vật cản gần nhất

    // đơn vị đo là cm
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    if(writer != null){
    try {
      writer.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } //
  }
  }

  @Override
  public void disabledPeriodic() {
    //
    
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

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    //
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
        
    //  m_wod.spin();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    try {
      writer = new FileOutputStream("home/lvuser/path.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  
  }

  /**
   * This function is called periodically during test mode.
   */
  
  @Override
  public void testPeriodic() {
    try {
      ByteBuffer buffer = ByteBuffer.allocate(8);
      buffer.putDouble(RobotContainer.logitech.getRawAxis(1));
      buffer.putDouble(RobotContainer.logitech.getRawAxis(3));
      writer.write(buffer.array());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}