package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase{

    PhotonCamera camera;
    Transform3d targetData;
    
    public Vision(PhotonCamera camera1) {
        camera = camera1;
        PortForwarder.add(5800, "photonvision", 5800);
        camera.setPipelineIndex(0);
    }

    //returns whether a target (AprilTag) has been detected
    public boolean targetDetected() {
        List<PhotonPipelineResult> results = camera.getAllUnreadResults();
        for (PhotonPipelineResult result : results) {
            if (result.hasTargets()) {
                return true;
            }
        }
        return false;
    }

    // public double getYaw() {
    //     List<PhotonPipelineResult> results = camera.getAllUnreadResults();
    //     double yaw = 0.0;
    //     for (PhotonPipelineResult result : results) {
    //         PhotonTrackedTarget target = result.getBestTarget();
    //         yaw = target.getYaw();
    //     }
    //     return yaw;
    // }
    // //gets target data such as x and y offset, rotational offset, and returns everything as a Transform3d 
    // public Transform3d getTargetData() {
    //     List<PhotonPipelineResult> results = camera.getAllUnreadResults();
    //     if (targetDetected()) {
    //         for (PhotonPipelineResult result : results) {
    //             PhotonTrackedTarget target = result.getBestTarget();
    //             if (target != null) {
    //                 return target.getBestCameraToTarget();
    //             }
    //         }
    //     }
    //     return null;
    //     }
    
    // //returns the current horizontal displacement with respect to the AprilTag (uses getY() because the Y offset in PhotonVision is the horizontal axis)
    // public double getHorizontalDisplacement() {
    //     if (targetDetected()) {
    //         return targetData.getY();
    //     }
    //     else return 0;
    // }

    @Override
    public void periodic() {
        //update targetData with current info
        // targetData = getTargetData();

        //output values to SmartDashboard/Shuffleboard
        SmartDashboard.putBoolean("Target Detected", targetDetected());
        // SmartDashboard.putNumber("Yaw Angle", getYaw());
    }

}