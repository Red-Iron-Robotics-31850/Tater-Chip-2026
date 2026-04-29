package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    private final Spark leftMotor = new Spark(0);
    private final Spark rightMotor = new Spark(1);

    private final DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);

    public Drivetrain() {
        rightMotor.setInverted(true);
    }

    public void arcadeDrive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }

    public void stop() {
        drive.stopMotor();
    }
}