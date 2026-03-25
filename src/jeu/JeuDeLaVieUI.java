package jeu;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.event.MouseWheelListener;

import observateur.Observateur;

public class JeuDeLaVieUI extends JPanel implements Observateur, MouseWheelListener , MouseListener , MouseMotionListener {
    private JeuDeLaVie jeu;
    private double zoomFactor = 1;
    private int offsetX = 0, offsetY;
    private int lastDragX = 0, lastDragY = 0;

    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        this.jeu = jeu;
        JFrame frame = new JFrame("Jeu de la Vie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(jeu.getxMax() * 3, jeu.getyMax() * 3);
        frame.add(this);
        frame.setVisible(true);

        //Initialisation de la souris
        addMouseWheelListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        JButton resetZoomBtn = new JButton("Reset");
        resetZoomBtn.addActionListener(e -> {
            zoomFactor = 1.0;
            offsetX = 0;
            offsetY = 0;
            repaint();
        });
        JPanel topPanel = new JPanel();
        topPanel.add(resetZoomBtn);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(this, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    @Override
    public void actualise() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform at = g2.getTransform();
        g2.translate(offsetX, offsetY);
        g2.scale(zoomFactor,zoomFactor);
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if (jeu.getGrilleXY(x, y).estVivante()) {
                    g.fillOval(x * 3, y * 3, 3, 3);
                }
            }
        }

        g2.setTransform(at);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        lastDragX = e.getX();
        lastDragY = e.getY();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - lastDragX;
        int dy = e.getY() - lastDragY;
        offsetX += dx;
        offsetY += dy;
        lastDragX = e.getX();
        lastDragY = e.getY();
        repaint();
    }
    public void mouseWheelMoved(MouseWheelEvent e){
    //Zoom in
    if (e.getWheelRotation() < 0) {
        zoomFactor *= 1.1;
    }
    //Zoom out
    if (e.getWheelRotation() > 0) {
        zoomFactor /= 1.1;
    }
    repaint();
    }

    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
