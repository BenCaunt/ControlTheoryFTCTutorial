package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.controller.pid;

@Autonomous
public class liftExample extends LinearOpMode {

    public DcMotor liftMotor;

    // delay in system that we intentionally add or is a known delay
    private double delay_ms = 20;
    private double setPoint = 3300; // position we want our encoder to drive to

    // pid controller
    private pid PID;

    // pid gains
    private final double kp = 0.006;
    private final double ki = 0.000003;
    private final double kd = 0.000004;


    @Override
    public void runOpMode() {

        liftMotor = hardwareMap.get(DcMotor.class, "armMotor");
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        PID = new pid(kp,ki,kd,delay_ms);

        waitForStart();

        while (opModeIsActive()) {

            double current_state = liftMotor.getCurrentPosition();
            double plantOutput = PID.output(setPoint,current_state);
            liftMotor.setPower(plantOutput);

            customSleep(delay_ms);

            telemetry.addData("state: ", current_state);
            telemetry.addData("setpoint: ", setPoint);
            telemetry.addData("error in system: ", setPoint - current_state);
            telemetry.addData("plant output: ", plantOutput);
            telemetry.addData("kp: ",kp);
            telemetry.addData("ki: ",ki);
            telemetry.addData("kd",kd);
            telemetry.update();


        }


    }

    /**
     * nothing wrong with the default 'sleep' method but just out of paranoia i did it this way
     */
    public void customSleep(double delay) {
        long startOfDelayTime = System.currentTimeMillis();

        while ((startOfDelayTime + delay > System.currentTimeMillis()) && opModeIsActive()) {

        }


    }


}
