package jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.event.MouseWheelListener;

import observateur.Observateur;

public class JeuDeLaVieUI extends JPanel implements Observateur, MouseWheelListener {
    private JeuDeLaVie jeu;
    private double zoomFactor = 1;


    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        this.jeu = jeu;
        JFrame frame = new JFrame("Jeu de la Vie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(jeu.getxMax() * 3, jeu.getyMax() * 3);
        frame.add(this);
        frame.setVisible(true);
        addMouseWheelListener(this);
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
}
