/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.PIDDeltaElevator;
import frc.robot.utils.EncoderInitialization;

/**
 * Add your docs here.
 */
public class PIDElevator extends Subsystem implements PIDOutput {

  private final Spark m_elevator;
  private final Encoder m_liftcoder;

  public final PIDController m_heightController;

  public final double MULTIPLYER = 0.1;
  public final double KP = 0.5;
  public final double KI = 0;
  public final double KD = 0;
  public final double KF = 0;  


  public PIDElevator() {

    m_elevator = new Spark(RobotMap.ELEVATOR);
    m_liftcoder = new Encoder(RobotMap.LIFTCODER_CHA, RobotMap.LIFTCODER_CHB, false, EncodingType.k2X);
    m_heightController = new PIDController(KP, KI, KD, m_liftcoder, this);

    EncoderInitialization.initLiftEncoder(m_liftcoder);

    //PID stuff
    m_heightController.setInputRange(0, 100);
    m_heightController.setOutputRange(-0.5, 0.5);
    m_heightController.setAbsoluteTolerance(0.02); //7.2 degrees
    m_heightController.setContinuous(false);

  }


  public void Set(double set) {
    m_elevator.set(set);
  } 

  public void Stop() {
    m_elevator.set(0);
  }

  /**
   * Set's the PID control loop's set point.
   * @param setpoint The value to set the height to
   */
  public void ChangeSetPoint(double setpoint) {
    m_heightController.setPID(KP, KI, KD);
    m_heightController.setSetpoint(setpoint);
    m_heightController.enable();
  }

  /**
   * Set's the PID control loop's set point based on the current setpoint.
   * @param delta Rhe amount to change the PID set point of the elevator.
   */
  public void DeltaSetPoint(double delta) {
    double initial = m_heightController.getSetpoint();
    m_heightController.setPID(KP, KI, KD);
    m_heightController.setSetpoint(initial + delta);
    m_heightController.enable();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new PIDDeltaElevator());
  }

  @Override
  public void pidWrite(double output) {
    Set(output);
  }

  public double getCurrentCount() {
    return m_liftcoder.getDistance();
  }
}
