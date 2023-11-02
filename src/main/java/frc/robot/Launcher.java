package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;




public class Launcher {
    private WPI_TalonFX rightMotor;
    private WPI_TalonFX leftMotor;
    private final double launchkP = 0.09;
    private final double launchkI = 0.0005;
    private final double launchkD = 0.0;

    private final int LEFT_CAN_ID = 30;
    private final int RIGHT_CAN_ID = 26;

    private final double LOW_POSITION = 3000.0;
    private final double HIGH_POSITION = 10000.0;


    public Launcher() {
        // initialize new motor object (at CAN ID 0)
        rightMotor = new WPI_TalonFX(RIGHT_CAN_ID);
        leftMotor = new WPI_TalonFX(LEFT_CAN_ID);

        leftMotor.configFactoryDefault();
        rightMotor.configFactoryDefault();

        rightMotor.setInverted(true);

        // set the nuetral mode of the motor to brake
        rightMotor.setNeutralMode(NeutralMode.Brake);

        // make sure the motor is not moving (set the power to 0)
        
        rightMotor.config_kP(0, launchkP);
        rightMotor.config_kI(0, launchkI);
        rightMotor.config_kD(0, launchkD);
        
        rightMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        rightMotor.selectProfileSlot(0, 0);
        rightMotor.setSelectedSensorPosition(0);

        rightMotor.config_IntegralZone(0, 300.0);

        // rightMotor.setIntegralAccumulator();

        leftMotor.follow(rightMotor);

        rightMotor.set(ControlMode.PercentOutput, 0);
    }

    public void setMotor(double position) {
        // spin the motor at the given power (making sure the value does not exceed 1.0 and doesn't go below -1.0)
        rightMotor.set(ControlMode.Position, position);
    }

    public void setHigh(){
        rightMotor.set(ControlMode.Position, HIGH_POSITION);
    }
    public void setLow(){
        rightMotor.set(ControlMode.Position, LOW_POSITION);
    }

    public double getMotorPosition(){
        // return the current tick value of the motor (a Falcon 500 has 2048 ticks)
        return rightMotor.getSelectedSensorPosition(); // (any value from 0-2048)
    }
}
