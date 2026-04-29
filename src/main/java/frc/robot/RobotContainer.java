package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.drive.ArcadeDriveCommand;
import frc.robot.subsystems.drive.Drivetrain;

public class RobotContainer {

    private final Drivetrain drivetrain = new Drivetrain();
    private final Intake intake = new Intake();
    private final Feeder feeder = new Feeder();
    private final CommandPS4Controller driver = new CommandPS4Controller(0);

    public RobotContainer() {
        drivetrain.setDefaultCommand(
            new ArcadeDriveCommand(drivetrain, driver)
        );
        configureButtonBindings();
    }

    public void configureButtonBindings() {
        driver.R1().toggleOnTrue(intake.intakeCommand());
        driver.L1().toggleOnTrue(intake.outtakeCommand());
        driver.L2().toggleOnTrue(feeder.outtakeCommand());
    }
}