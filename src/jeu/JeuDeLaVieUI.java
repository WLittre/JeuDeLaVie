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
    private boolean running = false;
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

        
        JButton playBtn = new JButton("Play");

        playBtn.addActionListener(e -> {
        if(!running) {
            running = true;

            new Thread(() -> {
            while (running) {
                jeu.calculeGenerationSuivante();
                try{
                    Thread.sleep(100);
                }catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            }).start();
        }
        });

        JButton pauseBtn = new JButton("Pause");
        pauseBtn.addActionListener(e -> {
            running = false;
        });

        /* Reset la grille button */
        JButton resetGrilleBtn = new JButton("Reset Grille");
        
        JSlider densite = new JSlider(0,100,10 );
        densite.setMajorTickSpacing(20);
        densite.setMinorTickSpacing(5);
        densite.setPaintTicks(true);
        densite.setPaintLabels(true);
        resetGrilleBtn.addActionListener(e -> {
            jeu.setProba(densite.getValue() / 100.0);
            System.out.println("Avant :" + jeu.getProba());
            jeu.initializeGrille();
            System.out.println("Apres :" + jeu.getProba());
            jeu.notifieObservateurs();
        });
        JButton resetZoomBtn = new JButton("Reset zoom");
        resetZoomBtn.addActionListener(e -> {
            zoomFactor = 1.0;
            offsetX = 0;
            offsetY = 0;
            repaint();
        });
        JPanel topPanel = new JPanel();
        topPanel.add(resetZoomBtn);
        topPanel.add(new JLabel("Densité:"));
        topPanel.add(densite);
        topPanel.add(resetGrilleBtn);
        topPanel.add(playBtn);
        
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
