// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

  private final Spark leftMotor = new Spark(0);
  private final Spark rightMotor = new Spark(1);

  private final DifferentialDrive drive =
      new DifferentialDrive(leftMotor, rightMotor);

  public Drivetrain() {
    rightMotor.setInverted(true);
  }

  public void arcadeDrive(double forward, double rotation) {
    drive.arcadeDrive(forward, rotation);
  }

  public void stop() {
    drive.stopMotor();
  }
}