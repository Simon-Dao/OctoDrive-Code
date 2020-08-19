package org.firstinspires.ftc.teamcode.teleop;

public class Terrain extends DriveMode {

    private final double position = 360;

    @Override
    void awake(Robot robot) {
        robot.servosSet = true;
        robot.servo_left_angle  = position;
        robot.servo_right_angle = position;
    }
}
