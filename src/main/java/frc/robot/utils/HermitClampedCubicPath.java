package frc.robot.utils;

import java.util.ArrayList;
import edu.wpi.first.wpilibj.geometry.*;

public class HermitClampedCubicPath {
    private Pose2d startPose;
    private Pose2d endPose;
    private ArrayList<Translation2d> interiorWaypoints;

    public HermitClampedCubicPath(Pose2d startPose, ArrayList<Translation2d> interiorWaypoints, Pose2d endPose) {
        this.startPose = startPose;
        this.endPose = endPose;
        this.interiorWaypoints = interiorWaypoints;
    }

    public Pose2d getStartPose() {
        return(startPose);
    }

    public Pose2d getEndPose() {
        return(endPose);
    }

    public Translation2d getWayPoint(int index) {
        return(interiorWaypoints.get(index));
    }

    public ArrayList<Translation2d> getWayPoints() {
        return(interiorWaypoints);
    }

    // Returns path length including terminal points
    public int getLength() {
        return(interiorWaypoints.size()+2);
    }
}