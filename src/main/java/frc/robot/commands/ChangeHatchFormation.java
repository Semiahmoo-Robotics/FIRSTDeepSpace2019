/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ChangeHatchFormation extends Command {
  
  private boolean stop = false;

  public ChangeHatchFormation() {
    requires(Robot.hatchHolder);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (!Robot.hatchHolder.HolderUp) Robot.hatchHolder.SetHolder();
    else Robot.hatchHolder.ReverseSetHolder();
    setTimeout(2);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (isTimedOut()) stop = true;
    else stop = false;
    return stop;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if (Robot.hatchHolder.HolderUp) Robot.hatchHolder.HolderUp = false;
    else Robot.hatchHolder.HolderUp = true;
    Robot.hatchHolder.StopHolder();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
