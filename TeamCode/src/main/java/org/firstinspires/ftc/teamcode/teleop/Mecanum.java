package org.firstinspires.ftc.teamcode.teleop;

class Mecanum extends DriveMode {

    //goes all servos up
    private final double position = 0;

    @Override
    void awake(Robot robot) {
        robot.servosSet = true;
        robot.servo_left_angle  = position;
        robot.servo_right_angle = position;
    }
}
