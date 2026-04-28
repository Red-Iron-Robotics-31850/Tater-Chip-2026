// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Add your docs here. */
public class Intake extends SubsystemBase{
    private final Spark intake = new Spark(3);

    public Intake() {}

    public Command intakeCommand() {
        return startEnd(
                    () -> intake.set(0.5),
                    () -> intake.stopMotor()
        );
    }
}
