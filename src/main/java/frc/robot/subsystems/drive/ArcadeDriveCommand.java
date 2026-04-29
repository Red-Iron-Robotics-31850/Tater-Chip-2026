// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ArcadeDriveCommand extends Command {
  /** Creates a new ArcadeDriveCommand. */
    private final Drivetrain drivetrain;
    private final CommandPS4Controller controller;

    public ArcadeDriveCommand(Drivetrain drivetrain, CommandPS4Controller controller) {
        this.drivetrain = drivetrain;
        this.controller = controller;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double speed = -controller.getLeftY();   // Forward/back
        double rotation = controller.getRightX(); // Turning

        drivetrain.arcadeDrive(speed, rotation);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}
