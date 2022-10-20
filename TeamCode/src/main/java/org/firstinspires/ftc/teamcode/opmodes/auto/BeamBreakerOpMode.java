package org.firstinspires.ftc.teamcode.opmodes.auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.components.ArmSystem;
import org.firstinspires.ftc.teamcode.components.DriveSystem;
import org.firstinspires.ftc.teamcode.components.PixyCam;
import org.firstinspires.ftc.teamcode.components.Vuforia;
@Autonomous (name = "beambreaking", group = "Autonomous")
public class BeamBreakerOpMode extends OpMode {

    protected PixyCam pixycam;
    private boolean stopRequested;
    private PixyCam.Block block;
    private ElapsedTime time;
    private ArmSystem.Intake intake;


    /** Initialization */
    public void init() {
        stopRequested = false;
        // Timeouts to determine if stuck in loop
        this.msStuckDetectInit = 20000;
        this.msStuckDetectInitLoop = 20000;
        // Initialize motors
        time = new ElapsedTime();
        intake = new ArmSystem.Intake(hardwareMap.get(DigitalChannel.class, "beambreaker"), hardwareMap.get(DcMotor.class, "intake"));
    }


    /** Initialize Vuforia object with given camera
     * @param cameraChoice
     */

    /** Returns if a stop has been requested or if execution is
     */
    public final boolean isStopRequested() {
        return this.stopRequested || Thread.currentThread().isInterrupted();
    }
    public void loop(){
        telemetry.addData("cone????", intake.isBeamBroken());
        telemetry.addData("time elapsed: ", time.seconds());
        telemetry.update();

        if(gamepad1.a){
            intake.intake();
        }

        if(gamepad1.b){
            intake.outtake(time);
        }
    }
    public void stop() {
        stopRequested = true;
        super.stop();
    }
}

