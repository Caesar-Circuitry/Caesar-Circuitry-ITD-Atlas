//package opModes;
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.pathgen.BezierLine;
//import com.pedropathing.pathgen.PathBuilder;
//import com.pedropathing.pathgen.Point;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.sfdev.assembly.state.StateMachine;
//import com.sfdev.assembly.state.StateMachineBuilder;
//
//import Robot.Robot;
//
//public class auto extends OpMode {
//    StateMachine stateMachine;
//    Follower follower;
//    @Override
//    public void init() {
//    }
//
//    @Override
//    public void loop() {
//
//    }
//
//    private void stateMachineBuilder(){
//        this.stateMachine = new StateMachineBuilder()
//
//                .build();
//    }
//
//    private void path(){
//        PathBuilder builder = new PathBuilder();
//
//        builder
//                .addPath(
//                        // Line 1
//                        new BezierLine(
//                                new Point(7.000, 81.000, Point.CARTESIAN),
//                                new Point(39.000, 77.000, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(180))
//                .addPath(
//                        // Line 2
//                        new BezierLine(
//                                new Point(39.000, 77.000, Point.CARTESIAN),
//                                new Point(25.000, 39.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-40))
//                .addPath(
//                        // Line 3
//                        new BezierLine(
//                                new Point(25.000, 39.000, Point.CARTESIAN),
//                                new Point(32.000, 33.000, Point.CARTESIAN)
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(-40))
//                .addPath(
//                        // Line 4
//                        new BezierLine(
//                                new Point(32.000, 33.000, Point.CARTESIAN),
//                                new Point(32.000, 33.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(-40), Math.toRadians(-135))
//                .addPath(
//                        // Line 5
//                        new BezierLine(
//                                new Point(32.000, 33.000, Point.CARTESIAN),
//                                new Point(32.000, 22.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(-40))
//                .addPath(
//                        // Line 6
//                        new BezierLine(
//                                new Point(32.000, 22.000, Point.CARTESIAN),
//                                new Point(32.000, 22.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(-40), Math.toRadians(-140))
//                .addPath(
//                        // Line 7
//                        new BezierLine(
//                                new Point(32.000, 22.000, Point.CARTESIAN),
//                                new Point(32.000, 13.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(-140), Math.toRadians(-40))
//                .addPath(
//                        // Line 8
//                        new BezierLine(
//                                new Point(32.000, 13.000, Point.CARTESIAN),
//                                new Point(32.000, 13.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(-40), Math.toRadians(-140))
//                .addPath(
//                        // Line 9
//                        new BezierLine(
//                                new Point(32.000, 13.000, Point.CARTESIAN),
//                                new Point(21.000, 34.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(-140), Math.toRadians(-135))
//                .addPath(
//                        // Line 10
//                        new BezierLine(
//                                new Point(21.000, 34.000, Point.CARTESIAN),
//                                new Point(38.000, 60.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180))
//                .addPath(
//                        // Line 11
//                        new BezierLine(
//                                new Point(38.000, 60.000, Point.CARTESIAN),
//                                new Point(21.000, 34.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-135))
//                .addPath(
//                        // Line 12
//                        new BezierLine(
//                                new Point(21.000, 34.000, Point.CARTESIAN),
//                                new Point(38.000, 61.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180))
//                .addPath(
//                        // Line 13
//                        new BezierLine(
//                                new Point(38.000, 61.000, Point.CARTESIAN),
//                                new Point(21.000, 34.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-135))
//                .addPath(
//                        // Line 14
//                        new BezierLine(
//                                new Point(21.000, 34.000, Point.CARTESIAN),
//                                new Point(38.000, 62.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180))
//                .addPath(
//                        // Line 15
//                        new BezierLine(
//                                new Point(38.000, 62.000, Point.CARTESIAN),
//                                new Point(21.000, 34.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-135))
//                .addPath(
//                        // Line 16
//                        new BezierLine(
//                                new Point(21.000, 34.000, Point.CARTESIAN),
//                                new Point(38.000, 63.000, Point.CARTESIAN)
//                        )
//                )
//                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180));
//
//    }
//}
