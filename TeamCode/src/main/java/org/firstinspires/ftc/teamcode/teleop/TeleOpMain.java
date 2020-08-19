package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/*
    based off of Kai and Sho's skystone teleOp code

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
        listenForInput();
        controller1.update();
        octoBot.update();
    }

    private void listenForInput() {

        //move like ball drive
        octoBot.motors_left_power  = Range.clip(controller1.left_stick_y + controller1.right_stick_x,-1d,1d);
        octoBot.motors_right_power = Range.clip(controller1.left_stick_y - controller1.right_stick_x,-1d,1d);

        setDriveMode();
    }

    private void setDriveMode() {
        if(controller1.right_bumper) {

            if(octoBot.modeIndex < 2)
                octoBot.modeIndex++;

            octoBot.servosSet = false;
        }
        else if(controller1.left_bumper) {

            if(octoBot.modeIndex > 0)
                octoBot.modeIndex--;

            octoBot.servosSet = false;
        }

    }
}
