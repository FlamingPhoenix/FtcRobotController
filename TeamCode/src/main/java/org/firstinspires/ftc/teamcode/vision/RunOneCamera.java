package org.firstinspires.ftc.teamcode.vision;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name="one camera", group="none")
public class RunOneCamera extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        double realDistance = 0;

        OpenCvWebcam webcam;
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        PoleDetection myPipeline = new PoleDetection(telemetry);
        webcam.setPipeline(myPipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(544, 288, OpenCvCameraRotation.UPRIGHT);
                FtcDashboard.getInstance().startCameraStream(webcam, 30);
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });


        while (!isStarted() && !isStopRequested()) {
            if (realDistance < myPipeline.Distance())
                realDistance = myPipeline.Distance();
        }

        telemetry.addData("Approximate distance:", realDistance);
    }
}