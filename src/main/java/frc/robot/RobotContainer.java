package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

import edu.wpi.first.math.filter.SlewRateLimiter;

public class RobotContainer {

    private final Drivetrain drivetrain = new Drivetrain();
    private final Intake intake = new Intake();
    private final Joystick controller = new Joystick(0);

    // Limits how fast values can change (units per second)
    private final SlewRateLimiter speedLimiter = new SlewRateLimiter(2.0);
    private final SlewRateLimiter turnLimiter = new SlewRateLimiter(3.0);

    public void teleopPeriodic() {

        double speed = modifyAxis(-controller.getRawAxis(1));
        double rotation = modifyAxis(controller.getRawAxis(4));

        // 🔻 HARD LIMITS (this is huge)
        speed *= 1;       // max 60% speed
        rotation *= 1;   // max 45% turning

        // 🔻 SMOOTH ACCELERATION
        speed = speedLimiter.calculate(speed);
        rotation = turnLimiter.calculate(rotation);

        drivetrain.arcadeDrive(speed, rotation);
        configureBindings();
    }

    private double modifyAxis(double value) {
        value = deadband(value, 0.0); // slightly bigger deadband

        // cubic curve = VERY smooth near center
        return Math.copySign(value * value * value, value);
    }

    private double deadband(double value, double threshold) {
        return Math.abs(value) > threshold ? value : 0.0;
    }

    private void configureBindings() {
    controller.getRawButtonPressed(5);
        new RunCommand(() -> intake.setSpeed(0.5), intake)
            .finallyDo(() -> intake.stopMotor());
  }
}