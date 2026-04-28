package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    private final Spark leftMotor = new Spark(0);
    private final Spark rightMotor = new Spark(3);

    private final DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);

    public Drivetrain() {
        rightMotor.setInverted(true); // Typical setup
    }

    public void arcadeDrive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }

    public void stop() {
        drive.arcadeDrive(0, 0);
    }
}