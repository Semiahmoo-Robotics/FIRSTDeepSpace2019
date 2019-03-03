/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleSmallClimb extends InstantCommand {
  public ToggleSmallClimb() {
    requires(Robot.climbPiston);
  }

  @Override
  protected void initialize() {
    if (Robot.climbPiston.smallExtended) {
      Robot.climbPiston.retractSmall();
      Robot.climbPiston.smallExtended = false;
    } else {
      Robot.climbPiston.extendSmall();
      Robot.climbPiston.smallExtended = true;
    }
  }

}
