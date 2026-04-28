package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

    private final Spark intake = new Spark(2);

    public Intake() {}

    public void setSpeed(double speed) {
        intake.set(speed);
    }

    public void stopMotor() {
        intake.stopMotor();
    }
}