package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Drivetrain {
    private WPI_TalonFX leftFront;
    private WPI_TalonFX leftBack;
    private WPI_TalonFX rightFront;
    private WPI_TalonFX rightBack;

    private final int LEFT_FRONT_CAN_ID = 0;
    private final int LEFT_BACK_CAN_ID = 1;
    private final int RIGHT_FRONT_CAN_ID = 2;
    private final int RIGHT_BACK_CAN_ID = 3;

    public Drivetrain() {
        leftFront = new WPI_TalonFX(LEFT_FRONT_CAN_ID);
        leftBack = new WPI_TalonFX(LEFT_BACK_CAN_ID);

        // invert because the left side
        leftFront.setInverted(true);
        leftBack.setInverted(true);

        // do the same as the front motor
        leftBack.follow(leftFront);

        rightFront = new WPI_TalonFX(RIGHT_FRONT_CAN_ID);
        rightBack = new WPI_TalonFX(RIGHT_BACK_CAN_ID);

        rightBack.follow(rightFront);
    }

    public void drive(double rightPower, double leftPower) {
        // just set the front motor on the left because the rear will copy
        leftFront.set(ControlMode.PercentOutput, leftPower);

        // just set the front motor on the right because the rear will copy
        rightFront.set(ControlMode.PercentOutput, rightPower);
    }
}
