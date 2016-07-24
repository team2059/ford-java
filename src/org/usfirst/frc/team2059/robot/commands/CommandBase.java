package org.usfirst.frc.team2059.robot.commands;
import org.usfirst.frc.team2059.robot.subsystems.DriveBase;
import org.usfirst.frc.team2059.robot.subsystems.EncoderBase;
import edu.wpi.first.wpilibj.command.Command;
public abstract class CommandBase extends Command {
  protected static EncoderBase encoderBase;
  protected static DriveBase driveBase;
  public static void init() {
    encoderBase = new EncoderBase();
    driveBase = new DriveBase();
  }
}
// vim: sw=2:ts=2:sts=2
