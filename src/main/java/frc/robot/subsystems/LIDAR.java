// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
public class LIDAR extends SubsystemBase {
  /** Creates a new LIDAR. */
  public LIDAR() {
   final I2C LIDAR = new I2C(Port.kOnboard, 0x62);
   ByteBuffer buffer = ByteBuffer.allocate(16);
   LIDAR.write(0x00, 0x04);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
