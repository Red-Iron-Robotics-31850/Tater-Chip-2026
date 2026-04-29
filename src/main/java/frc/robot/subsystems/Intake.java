package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    public enum IntakeSpeed {
        STOP(0),
        INTAKE(0.8),
        OUTTAKE(-0.8);

        private final double percentOutput;

        private IntakeSpeed(double percentOutput) {
            this.percentOutput = percentOutput;
        }

        public Voltage voltage() {
            return Volts.of(percentOutput * 12.0);
        }
    }

    private final Spark intake;

    public Intake() {
        intake = new Spark(2);
    }

    public void set(IntakeSpeed speed) {
        intake.set(speed.percentOutput);
    }

    public Command intakeCommand() {
        return startEnd(
            () -> {
                set(IntakeSpeed.INTAKE);
            },
            () -> set(IntakeSpeed.STOP)
        );
    }

    public Command outtakeCommand() {
        return startEnd(
            () -> {
                set(IntakeSpeed.OUTTAKE);
            },
            () -> set(IntakeSpeed.STOP)
        );
    }

}