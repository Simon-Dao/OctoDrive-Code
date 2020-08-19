package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;

class Robot {

    private HardwareMap hardwareMap;

    //MOTORS
    private DcMotor motor_left_back;
    private DcMotor motor_left_front;
    private DcMotor motor_right_back;
    private DcMotor motor_right_front;

    //DRIVE MODES ARRAY
    public int modeIndex = 0;
    public boolean servosSet = true;
    //Mecanum, Firing, Terrain
    private DriveMode[] modes = new DriveMode[3];

    //MOTOR POWER
    /*
        both motors on the left side will be running at the same power
        and same with motors on the right side
     */
    public double motors_left_power = 0;
    public double motors_right_power = 0;

    //controller input
    private double x;
    private double y;
    private double r;

    //SERVOS
    /*
        keep note that both servos rotate 200 degrees to lift up the center wheels
     */
    private Servo servo_left;
    private Servo servo_right;

    //SERVO POWER
    public double servo_left_angle = 0;
    public double servo_right_angle = 0;

    public Robot(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        setMotors();
        setServos();
        setModes();
    }

    private void setMotors() {
        motor_left_back = hardwareMap.dcMotor.get("motor_left_back");
        motor_left_front = hardwareMap.dcMotor.get("motor_left_front");
        motor_right_back = hardwareMap.dcMotor.get("motor_right_back");
        motor_right_front = hardwareMap.dcMotor.get("motor_right_front");

        /*
            all motor directions temporarily set to foward but will be changed later
         */
        motor_left_back.setDirection(DcMotor.Direction.FORWARD);
        motor_left_front.setDirection(DcMotor.Direction.REVERSE);
        motor_right_back.setDirection(DcMotor.Direction.FORWARD);
        motor_right_front.setDirection(DcMotor.Direction.REVERSE);
    }

    private void setServos() {
        servo_left = hardwareMap.servo.get("servo_left");
        servo_right = hardwareMap.servo.get("servo_right");

        servo_left.setDirection(Servo.Direction.FORWARD);
        servo_right.setDirection(Servo.Direction.FORWARD);
    }

    private void setModes() {
        modes[0] = new Mecanum();
        modes[1] = new Firing();
        modes[2] = new Terrain();

        //set the default mode as mecanum
        modes[modeIndex].awake(this);
    }

    public void update() {
        motor_left_front.setPower(motors_left_power);
        motor_left_back.setPower(motors_left_power);

        motor_right_front.setPower(motors_right_power);
        motor_right_back.setPower(motors_right_power);

        //set mode
        if (!servosSet) modes[modeIndex].awake(this);
    }

    public void setMotorPower(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;

        double[] power = scalePowers();

        motor_right_front.setPower(power[0]);
        motor_left_front.setPower(power[1]);
        motor_left_back.setPower(power[2]);
        motor_right_back.setPower(power[3]);
    }

    private double[] scalePowers() {

        double[] inputs =
                {
                        y - x + r,
                        y + x - r,
                        y - x + r,
                        y + x + r
                };

        double max = getMaxMag(inputs);

        if (max > 1) {
            for (int i = 0; i < inputs.length; i++)
                inputs[i] *= 1 / max;
        }

        return inputs;
    }

    //returns the largest magnitude
    private double getMaxMag(double[] inputs) {

        Arrays.sort(inputs);

        return inputs[inputs.length - 1];
    }
}
