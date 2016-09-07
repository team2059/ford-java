package org.usfirst.frc.team2059.robot.commands;
import org.usfirst.frc.team2059.robot.subsystems.DriveBase;
import org.usfirst.frc.team2059.robot.subsystems.EncoderBase;
import org.usfirst.frc.team2059.robot.subsystems.MainArm;
import edu.wpi.first.wpilibj.command.Command;
public abstract class CommandBase extends Command {
  protected static EncoderBase encoderBase;
  protected static DriveBase driveBase;
  protected static MainArm mainArm;
  public static void init() {
    encoderBase = new EncoderBase();
    driveBase = new DriveBase();
    mainArm = new MainArm();
  }
}
// vim: sw=2:ts=2:sts=2
