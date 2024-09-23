package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JPanel {
    private List<GameObject> objects = new ArrayList<>();
    private GameObject selectedObject;

    public Main() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        // Create a sample object
        GameObject obj = new GameObject(100, 100);
        obj.loadSprite("resources/MySheep.png"); // Replace with your sprite path
        objects.add(obj);
        selectedObject = obj;

        // Key listener for movement and rotation
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (selectedObject != null) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            selectedObject.move(-5, 0);
                            break;
                        case KeyEvent.VK_RIGHT:
                            selectedObject.move(5, 0);
                            break;
                        case KeyEvent.VK_UP:
                            selectedObject.rotate(5); // Rotate clockwise
                            break;
                        case KeyEvent.VK_DOWN:
                            selectedObject.rotate(-5); // Rotate counterclockwise
                            break;
                    }
                    repaint(); // Redraw the panel
                }
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameObject obj : objects) {
            obj.draw(g);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulation");
        Main simulation = new Main();
        frame.add(simulation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class GameObject {
    private int x, y; // Position
    private double rotation; // Rotation in degrees
    private BufferedImage sprite; // Sprite image

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.rotation = 0;
    }

    public void loadSprite(String path) {
        try {
            sprite = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public void rotate(double angle) {
        rotation += angle;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(rotation), x + sprite.getWidth() / 2, y + sprite.getHeight() / 2);
        g2d.drawImage(sprite, x, y, null);
        g2d.rotate(-Math.toRadians(rotation), x + sprite.getWidth() / 2, y + sprite.getHeight() / 2);
    }
}
