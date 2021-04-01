package frc.robot.commands;



import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LIDARCommand extends CommandBase {
  /** Creates a new LIDARCommand. */
  public LIDARCommand() {
    addRequirements(RobotContainer.lidarSubsystem);
  }

  @Override
  public void initialize() {
    try{
      RobotContainer.lidarSubsystem.initLIDAR(new DigitalInput(Constants.DIO.LIDAR_PORT));
    }
    catch(Exception e){
      
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("LIDAR Distance", RobotContainer.lidarSubsystem.getDistanceIn(true));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
