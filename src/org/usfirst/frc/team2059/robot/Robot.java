package org.usfirst.frc.team2059.robot;
import org.usfirst.frc.team2059.robot.commands.CommandBase;
import org.usfirst.frc.team2059.robot.commands.autonomous.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class Robot extends IterativeRobot {
  public static OI oi;
  Command autonomousCommand;
  SendableChooser chooser;
  CameraServer cameraServer;
  public void robotInit() {
    CommandBase.init();
    oi = new OI();
    chooser = new SendableChooser();
    cameraServer = CameraServer.getInstance();
    cameraServer.setQuality(50);
    cameraServer.startAutomaticCapture("cam0");
    chooser.addDefault("Time based low bar", new RoutineDriveTime());
    chooser.addObject("Time based defense", new RoutineDefenseTime());
    SmartDashboard.putData("Auto mode", chooser);
    SmartDashboard.putData("MainArm", CommandBase.mainArm.getPIDController());
    SmartDashboard.putData("LeftEncoderController", CommandBase.driveBase.getLeftController());
    SmartDashboard.putBoolean("CompressorEnabled", true);
    //Automatically determine if rolling in or rolling out
    SmartDashboard.putBoolean("SmartRollers", false);
    //Use the limit swithces on the shooter
    SmartDashboard.putBoolean("UseLimitSwitches", true);
  }
  public void disabledInit() {
  }
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }
  public void autonomousInit() {
    autonomousCommand = (Command) chooser.getSelected();
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("ArmAngleRaw", CommandBase.mainArm.getRaw());
    SmartDashboard.putNumber("ArmAngleDegrees", CommandBase.mainArm.getDegrees());
    SmartDashboard.putNumber("tmpRotations", CommandBase.driveBase.getLeftRotations());
  }
  public void teleopInit() {
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    CommandBase.pneumatics.setCompressorEnabled(true);
    CommandBase.pneumatics.setArmStopState(false);
  }
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("ArmAngleRaw", CommandBase.mainArm.getRaw());
    SmartDashboard.putNumber("ArmAngleDegrees", CommandBase.mainArm.getDegrees());
    SmartDashboard.putNumber("tmpRotations", CommandBase.driveBase.getLeftRotations());
    if (Robot.oi.getJoysticks()[1].getRawButton(3)){
      CommandBase.pneumatics.setArmStopState(true);
    } else {
      CommandBase.pneumatics.setArmStopState(false);
    }
    CommandBase.pneumatics.setCompressorEnabled(SmartDashboard.getBoolean("CompressorEnabled"));
    System.out.println(CommandBase.mainArm.getDegrees());
  }
  public void testPeriodic() {
    LiveWindow.run();
  }
}
//  vim: sw=2:ts=2:sts=2
