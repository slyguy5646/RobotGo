package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;



public class Motor {
    private WPI_TalonFX motor;

    public Motor() {
        // initialize new motor object (at CAN ID 0)
        motor = new WPI_TalonFX(0);

        // set the nuetral mode of the motor to brake
        motor.setNeutralMode(NeutralMode.Brake);

        // make sure the motor is not moving (set the power to 0)
        motor.set(ControlMode.PercentOutput, 0);
    }

    public void spinMotor(double power) {
        // spin the motor at the given power (making sure the value does not exceed 1.0 and doesn't go below -1.0)
        motor.set(ControlMode.PercentOutput, Math.max(-1.0, Math.min(1.0, power)));
    }

    public double getEncoderPosition(){
        // return the current tick value of the motor (a Falcon 500 has 2048 ticks)
        return motor.getSelectedSensorPosition(); // (any value from 0-2058)
    }

}
