package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Kicker extends SubsystemBase {

    private final Spark motor;

    // Magnetic limit sensors (DIO ports)
    private final DigitalInput forwardLimit;  // "kicked" position
    private final DigitalInput reverseLimit;  // "home" position

    // Speeds (tune these)
    private static final double FORWARD_SPEED = 0.5;
    private static final double REVERSE_SPEED = -0.5;

    public Kicker() {
        motor = new Spark(3);

        forwardLimit = new DigitalInput(0);
        reverseLimit = new DigitalInput(1);
    }

    /**
     * NOTE: Many magnetic sensors are opposite
     * If your logic is flipped, remove the !
     */
    public boolean atForwardLimit() {
        return !forwardLimit.get();
    }

    public boolean atReverseLimit() {
        return !reverseLimit.get();
    }

    // ===============================
    // BASIC MOTOR CONTROL
    // ===============================

    public void stop() {
        motor.stopMotor();
    }

    public void moveForward() {
        if (!atForwardLimit()) {
            motor.set(FORWARD_SPEED);
        } else {
            stop();
        }
    }

    public void moveReverse() {
        if (!atReverseLimit()) {
            motor.set(REVERSE_SPEED);
        } else {
            stop();
        }
    }

    // ===============================
    // SMART ACTIONS
    // ===============================

    /** Kick forward until magnet is detected */
    public void kick() {
        moveForward();
    }

    /** Return to home position */
    public void home() {
        moveReverse();
    }


    public Command kickCommand(Kicker kicker) {
    return Commands.run(
        kicker::kick,
        kicker
    ).until(kicker::atForwardLimit);
    }


    public Command homeCommand(Kicker kicker) {
    return Commands.run(
        kicker::home,
        kicker
    ).until(kicker::atReverseLimit)
     .finallyDo(kicker::stop);
    }


    public Command fullKickCycle(Kicker kicker) {
    return Commands.sequence(
        kickCommand(kicker),
        Commands.waitUntil(kicker::atForwardLimit),
        Commands.waitSeconds(0.1), // small pause
        homeCommand(kicker)
    );
    }

    // ===============================
    // DEBUGGING
    // ===============================

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Kicker Forward Limit", atForwardLimit());
        SmartDashboard.putBoolean("Kicker Reverse Limit", atReverseLimit());
    }
}