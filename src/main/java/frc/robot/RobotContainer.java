package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class RobotContainer {

  // Controller (USB port 0)
  private final CommandJoystick driver = new CommandJoystick(0);

  // Subsystem
  private final Drivetrain drive = new Drivetrain();
  private final Intake intake = new Intake();

  public RobotContainer() {

    // Default command = runs constantly while robot is enabled
    drive.setDefaultCommand(
      new RunCommand(() ->
        drive.arcadeDrive(
          -driver.getRawAxis(0),  // forward/back
          driver.getRawAxis(5)   // turning
        ),
        drive
      )
    );

    configureBindings();
  }

  private void configureBindings() {
    driver.button(5).toggleOnTrue(intake.intakeCommand());
  }
}