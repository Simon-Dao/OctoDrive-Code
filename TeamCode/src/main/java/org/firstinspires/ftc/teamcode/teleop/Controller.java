package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;

class Controller {

    private Gamepad controller;

    public boolean a = false;
    public boolean b = false;
    public boolean x = false;
    public boolean y = false;
    public boolean back = false;
    public boolean dpad_up = false;
    public boolean dpad_down = false;
    public boolean dpad_left = false;
    public boolean dpad_right = false;
    public boolean guide = false;
    public boolean left_bumper = false;
    public boolean left_stick_button = false;
    public boolean right_stick_button = false;
    public boolean right_bumper = false;
    public boolean start = false;

    public float left_stick_x  = 0;
    public float left_stick_y  = 0;
    public float left_trigger  = 0;
    public float right_stick_x = 0;
    public float right_stick_y = 0;
    public float right_trigger = 0;

    public Controller(Gamepad controller) {
        this.controller = controller;
    }

    //called every tick in main loop
    public void update() {
        a = controller.a;
        b = controller.b;
        x = controller.x;
        y = controller.y;

        back = controller.back;
        start = controller.start;
        guide = controller.guide;

        dpad_up = controller.dpad_up;
        dpad_down = controller.dpad_down;
        dpad_left = controller.dpad_left;
        dpad_right = controller.dpad_right;

        left_bumper = controller.left_bumper;
        left_stick_button = controller.left_stick_button;

        right_bumper = controller.right_bumper;
        right_stick_button = controller.right_stick_button;

        left_stick_x  = controller.left_stick_x;
        left_stick_y  = controller.left_stick_y;

        right_stick_x = controller.right_stick_x;
        right_stick_y = controller.right_stick_y;

        left_trigger = controller.left_trigger;
        right_trigger = controller.right_trigger;
    }
}
