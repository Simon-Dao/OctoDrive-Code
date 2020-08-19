package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/*
    Credit to Kai and Sho. This code is based off of their skystone 2019 code
    Credit to Alnis. The Mecanum code is based off of his skystone code.

    //TODO test the code on the robot

   TAKE NOTE this code has not been tested on a physical robot yet
   ------------RUN CODE WITH CAUTION--------------------
 */


@TeleOp(name = "TeleOpMain", group = "Iterative Opmode")
public class TeleOpMain extends OpMode {

    private int driveModeIndex = 0;

    private Controller controller1;
    private Robot octoBot;

    @Override
    public void init() {
        octoBot = new Robot(hardwareMap);
        controller1 = new Controller(gamepad1);
        telemetry.addData("staus", "initialized");
    }

    @Override
    public void loop() {
        controller1.update();
        listenForInput();
        octoBot.update();
    }

    private void listenForInput() {
        setDriveMode();
        controlDriveBase();
    }

    private void controlDriveBase() {
        //get inputs from controller
        double x = controller1.left_stick_x;
        double y = controller1.left_stick_y;
        double r = controller1.right_stick_x;

        octoBot.setMotorPower(x, y, r);
    }

    /*
       left/right bumpers set the pointer to point to a mode
       Mecanum Firing Terrain
         ^
         |
       modeIndex

       for example,
       hitting the right bumper will set the mode to Firing
     */
    private void setDriveMode() {
        if (controller1.right_bumper) {

            //makes sure the pointer is in the bounds of the modes array
            if (octoBot.modeIndex < 2)
                octoBot.modeIndex++;

            //servosSet is used to makes sure that
            //the we are not setting the position of the servo every time the robot updates
            octoBot.servosSet = false;
        } else if(controller1.left_bumper) {

            if(octoBot.modeIndex > 0)
                octoBot.modeIndex--;

            octoBot.servosSet = false;
        }
    }
}
