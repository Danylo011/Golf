package org.example;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Timer;
public class GolfGame {
    public static boolean isMoving = false;
    public static int score = 0;
    private static boolean isDragging = false;
    public static int ballX = 400;
    public static int ballY = 300;
    public static final int BALL_RADIUS = 10;
    public static int holeX = 500;
    public static int holeY = 300;
    public static final int HOLE_RADIUS = 20;
    private static final int FIELD_X = 50;
    private static final int FIELD_Y = 50;
    private static final int FIELD_WIDTH = 700;
    private static final int FIELD_HEIGHT = 500;
    private static final double ballSpeedMultiplier = 0.99;
    private static final double stopThreshold = 0.1;
    public static void main(String[] args) {
        GolfDisplay.create(800, 600, "Golf Game");
        Timer ti = new Timer(1000 / 60, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                GolfDisplay.render();
                if (isMoving) {
                    moveBall();
                }
            }
        });
        ti.setRepeats(true);
        ti.start();
    }
    public static void setMoving(boolean moving) {
        isMoving = moving;
        if (!moving && !isMoving) {
            Timer stopTimer = new Timer(2000, new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    if (isBallInHole()) {
                        resetBall();
                    }
                }
            });
            stopTimer.setRepeats(false);
            stopTimer.start();
        }
    }
    public static boolean isBallInHole() {
        int distanceToHole = (int) Math.sqrt(Math.pow(ballX - holeX, 2) + Math.pow(ballY - holeY, 2));
        return distanceToHole <= BALL_RADIUS + HOLE_RADIUS;
    }
    private static void resetBall() {
        ballX = 400;
        ballY = 300;
        isDragging = false;
        score++;
        GolfCanvas.ballSpeedX = 0;
        GolfCanvas.ballSpeedY = 0;
        holeX = (int) (FIELD_X + Math.random() * FIELD_WIDTH);
        holeY = (int) (FIELD_Y + Math.random() * FIELD_HEIGHT);
    }
    private static void moveBall() {
        if (isDragging) {
            return;
        }
        int nextBallX = ballX + GolfCanvas.ballSpeedX;
        int nextBallY = ballY + GolfCanvas.ballSpeedY;
        if (nextBallX - GolfGame.BALL_RADIUS < FIELD_X || nextBallX + GolfGame.BALL_RADIUS > FIELD_X + FIELD_WIDTH) {
            GolfCanvas.ballSpeedX = -GolfCanvas.ballSpeedX;
        }
        if (nextBallY - GolfGame.BALL_RADIUS < FIELD_Y || nextBallY + GolfGame.BALL_RADIUS > FIELD_Y + FIELD_HEIGHT) {
            GolfCanvas.ballSpeedY = -GolfCanvas.ballSpeedY;
        }
        GolfCanvas.ballSpeedX *= ballSpeedMultiplier;
        GolfCanvas.ballSpeedY *= ballSpeedMultiplier;
        if (Math.abs(GolfCanvas.ballSpeedX) < stopThreshold && Math.abs(GolfCanvas.ballSpeedY) < stopThreshold) {
            GolfCanvas.ballSpeedX = 0;
            GolfCanvas.ballSpeedY = 0;
        }
        if (isBallInHole()) {
            setMoving(false);
        } else {
            ballX = nextBallX;
            ballY = nextBallY;
        }
    }
}