package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  // Controller (USB port 0)
  private final XboxController driver = new XboxController(0);

  // Subsystem
  private final Drivetrain drive = new Drivetrain();

  public RobotContainer() {

    // Default command = runs constantly while robot is enabled
    drive.setDefaultCommand(
      new RunCommand(() ->
        drive.arcadeDrive(
          -driver.getLeftY(),  // forward/back
          driver.getRightX()   // turning
        ),
        drive
      )
    );

    configureBindings();
  }

  private void configureBindings() {
    
  }
}