package org.usfirst.frc.team2059.robot.commands.autonomous;
import org.usfirst.frc.team2059.robot.commands.CommandBase;
import org.usfirst.frc.team2059.robot.Robot;
/**
 *
 */
public class AutoResetLower extends CommandBase {
  double speed;
  public AutoResetLower(double s) {
    requires(mainArm);
    speed = s;
  }
  // Called just before this Command runs the first time
  protected void initialize() {
  }
  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    System.out.println("test");
    mainArm.disable();
    mainArm.resetLower(speed);
  }
  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    // Stop when bottom limit switch is hit
    return mainArm.getBottomPressed();
  }
  // Called once after isFinished returns true
  protected void end() {
    mainArm.moveArm(0);
  }
  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}
// vim: sw=2:ts=2:sts=2
