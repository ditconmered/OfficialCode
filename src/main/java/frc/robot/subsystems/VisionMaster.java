// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import java.lang.Math;
// import edu.wpi.first.networktables.NetworkTableType;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
public class VisionMaster extends SubsystemBase {
  /** Creates a new VisionMaster. */

  private static VisionMaster instance;
  private NetworkTable visionTable;
  public NetworkTableEntry s;
  public VisionMaster() {
    visionTable = NetworkTableInstance.getDefault().getTable("vision"); // table
    s = visionTable.getEntry("Port distance");
    

  }
  public static VisionMaster getInstance() {
    if (instance == null) {
        instance = new VisionMaster();
    }
    return instance;
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}




// public class VisionMaster extends SubsystemBase{
//   public static NetworkTableEntry s;


//   public void robotInit(){
//     NetworkTableInstance inst = NetworkTableInstance.getDefault();

//     NetworkTable table = inst.getTable("datatable");

//     s = table.getEntry("Port distance");
//   }
// }