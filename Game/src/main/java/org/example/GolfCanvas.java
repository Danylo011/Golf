package org.example;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
public class GolfCanvas extends Canvas implements MouseListener{
    private static int initialMouseX;
    private static int initialMouseY;
    private static boolean isDragging = false;
    public static int ballSpeedX = 0;
    public static int ballSpeedY = 0;
    public GolfCanvas() {
        addMouseListener(this);
    }
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        g.drawString("Score: " + GolfGame.score, 10, 20);
        g.setColor(Color.green);
        g.fillRect(50, 50, 700, 500);
        g.setColor(Color.black);
        g.fillOval(GolfGame.holeX - GolfGame.HOLE_RADIUS, GolfGame.holeY - GolfGame.HOLE_RADIUS, 2 * GolfGame.HOLE_RADIUS, 2 * GolfGame.HOLE_RADIUS);
        g.setColor(Color.red);
        g.fillOval(GolfGame.ballX - GolfGame.BALL_RADIUS, GolfGame.ballY - GolfGame.BALL_RADIUS, 2 * GolfGame.BALL_RADIUS, 2 * GolfGame.BALL_RADIUS);
        g.dispose();
        bs.show();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (!GolfGame.isMoving && e.getButton() == MouseEvent.BUTTON1) {
            initialMouseX = e.getX();
            initialMouseY = e.getY();
            isDragging = true;
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (isDragging && e.getButton() == MouseEvent.BUTTON1) {
            int finalMouseX = e.getX();
            int finalMouseY = e.getY();
            double speedMultiplier = 0.50;
            ballSpeedX = (int) ((finalMouseX - initialMouseX) * speedMultiplier);
            ballSpeedY = (int) ((finalMouseY - initialMouseY) * speedMultiplier);
            GolfGame.setMoving(true);
            isDragging = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}