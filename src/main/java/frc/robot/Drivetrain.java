package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain {
    private WPI_TalonFX leftFront;
    private WPI_TalonFX leftBack;
    private WPI_TalonFX rightFront;
    private WPI_TalonFX rightBack;

    private final int LEFT_FRONT_CAN_ID = 21;
    private final int LEFT_BACK_CAN_ID = 3;
    private final int RIGHT_FRONT_CAN_ID = 4;
    private final int RIGHT_BACK_CAN_ID = 2;

    public Drivetrain() {
        leftFront = new WPI_TalonFX(LEFT_FRONT_CAN_ID);
        leftBack = new WPI_TalonFX(LEFT_BACK_CAN_ID);
        rightFront = new WPI_TalonFX(RIGHT_FRONT_CAN_ID);
        rightBack = new WPI_TalonFX(RIGHT_BACK_CAN_ID);

        // config Factory Default
        leftFront.configFactoryDefault();
        leftBack.configFactoryDefault();
        rightFront.configFactoryDefault();
        rightBack.configFactoryDefault();

        leftFront.setNeutralMode(NeutralMode.Brake);
        leftBack.setNeutralMode(NeutralMode.Coast);
        rightFront.setNeutralMode(NeutralMode.Brake);
        rightBack.setNeutralMode(NeutralMode.Coast);

        // invert because the left side
        leftFront.setInverted(true);
        leftBack.setInverted(true);

        // do the same as the front motor
        leftBack.follow(leftFront);
        
        // do the same as the front motor
        rightBack.follow(rightFront);
    }

    public void drive(double rightPower, double leftPower) {
        SmartDashboard.putNumber("Left Front Motor Pos", leftFront.getSelectedSensorPosition());
        SmartDashboard.putNumber("Left Back Motor Pos", leftBack.getSelectedSensorPosition());
        SmartDashboard.putNumber("Right Front Motor Pos", rightFront.getSelectedSensorPosition());
        SmartDashboard.putNumber("Right Back Motor Pos", rightBack.getSelectedSensorPosition());
        // just set the front motor on the left because the rear will copy
        leftFront.set(ControlMode.PercentOutput, leftPower);

        // just set the front motor on the right because the rear will copy
        rightFront.set(ControlMode.PercentOutput, rightPower);
    }
}
