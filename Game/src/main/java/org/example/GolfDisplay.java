package org.example;
import javax.swing.*;
import java.awt.*;
public class GolfDisplay extends JFrame {
    private static boolean created = false;
    public static GolfCanvas canvas;
    public static void create(int width, int height, String title) {
        if (created)
            return;
        GolfDisplay window = new GolfDisplay();
        canvas = new GolfCanvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.addMouseListener(canvas);
        window.setResizable(false);
        window.getContentPane().add(canvas);
        window.pack();
        window.setTitle(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        created = true;
    }
    public static void render() {
        if (canvas == null)
            return;
        canvas.render();
    }
}