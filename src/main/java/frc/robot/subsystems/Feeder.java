package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake.IntakeSpeed;

public class Feeder extends SubsystemBase {
    Intake intake = new Intake();
    public enum Speed {
        STOP(0),
        FEED(0.8),
        REVERSEFEED(-0.8);

        private final double percentOutput;

        private Speed(double percentOutput) {
            this.percentOutput = percentOutput;
        }

        public Voltage voltage() {
            return Volts.of(percentOutput * 12.0);
        }
    }

    private final Spark feeder;

    public Feeder() {
        feeder = new Spark(4);
    }

    public void set(Speed speed) {
        feeder.set(speed.percentOutput);
    }

    public Command outtakeCommand() {
        return startEnd(
            () -> {
                set(Speed.REVERSEFEED);
                intake.set(IntakeSpeed.OUTTAKE);
            },
            () -> set(Speed.STOP)
        );
    }

   
}