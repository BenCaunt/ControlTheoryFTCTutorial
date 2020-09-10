package org.firstinspires.ftc.teamcode.controller;


/**
 * WARNING this is FOR EDUCATIONAL PURPOSES AND EDUCATIONAL PURPOSES ONLY, the bangBang Controller
 * can be wildly unstable and result in huge oscillations around the setpoint and could entirely damage
 * your robot or yourself, please be careful if you intend on using this for whatever reason
 */
public class bangBangController {

    public double expected_output;


    /**
     * constructs bang bang controller with the desired output speed hard coded because bang bang controller things
     * @param expected_output
     */
    public bangBangController(double expected_output) {
        this.expected_output = expected_output;
    }


    public double output(double setPoint, double currentState) {

        // apply the proper output to move towards setpoint

        if (setPoint > currentState) {
            return expected_output;
        } else if (setPoint < currentState) {
            return -expected_output;
        } else {
            return 0;
        }

    }



}
