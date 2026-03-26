package jeu;

import javax.swing.*;

import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.event.MouseWheelListener;

import java.util.List;

import observateur.Observateur;
import visiteur.VisiteurHighLife;
import visiteur.VisiteurVieSansMort;
import visiteur.VisiteurClassique;
import visiteur.VisiteurCovid;
import visiteur.VisiteurDayNight;

import json.*;

public class JeuDeLaVieUI extends JPanel implements Observateur, MouseWheelListener , MouseListener , MouseMotionListener {
    private static final int CELL_SIZE = 10;
    private JeuDeLaVie jeu;
    private Timer timer;
    private boolean enCours = false;

    private JButton btnPlayPause;
    private JButton btnStep;
    private JSlider sliderVitesse;
    private JComboBox<String> comboRegles;
    private double zoomFactor = 1;
    private int offsetX = 0, offsetY;
    private int lastDragX = 0, lastDragY = 0;

    private boolean modeSelection = false;
    private boolean modePlacement = false;
    private boolean modeDessin = false;
    private boolean dessinEfface = false;
    private List<int[]> structureAPoser;
    private int selX1 = -1, selY1 = -1, selX2 = -1, selY2 = -1;
    private Gestionstructure gestionStructures;
    private JComboBox<String> comboStructures;

    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        this.jeu = jeu;
        setBackground(Color.WHITE);
        timer = new Timer(100, e -> jeu.calculeGenerationSuivante());
        gestionStructures = new Gestionstructure("src/json/structures.txt");

        JFrame frame = new JFrame("Jeu de la Vie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLayout(new BorderLayout());
        

        //Initialisation de la souris
        addMouseWheelListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        frame.add(creerPanneauTop(),BorderLayout.NORTH);
        frame.add(this,BorderLayout.CENTER);
        frame.add(creerPanneauControle(),BorderLayout.SOUTH);
        frame.setVisible(true);
    }
       
    private JPanel creerPanneauTop(){
        JPanel topPanel = new JPanel();
        JLabel labelTaille = new JLabel(jeu.getxMax() + "x" + jeu.getyMax());
        JTextField fieldX = new JTextField(String.valueOf(jeu.getxMax()),5);
        JTextField fieldY = new JTextField(String.valueOf(jeu.getyMax()),5);

        JComboBox<String> structureDepart = new JComboBox<>();
        structureDepart.addItem("Aleatoire");
        for(Structure s: gestionStructures.charger()){
            structureDepart.addItem(s.getNom());
        }
        
        JButton resetZoomButton = new JButton("Recentrer");
        resetZoomButton.addActionListener(e->{
            zoomFactor = 1.0;
            offsetX = 0;
            offsetY = 0;
            repaint();
        });

        JSlider densite = new JSlider(0,100,10);
        densite.setMajorTickSpacing(20);
        densite.setMinorTickSpacing(5);
        densite.setPaintTicks(true);
        densite.setPaintLabels(true);

        JButton resetGrilleBtn = new JButton("Nouvelle Grille");
        resetGrilleBtn.addActionListener(e -> {
            try{
                int newX = Integer.parseInt(fieldX.getText().trim());
                int newY = Integer.parseInt(fieldY.getText().trim());
                if(newX > 0 && newY > 0){
                    jeu.setxMax(newX);
                    jeu.setyMax(newY);
                }
            }catch(NumberFormatException ex){
                System.out.println("Erreur , le format de la grille est invalide");
            }
            int idDepart = structureDepart.getSelectedIndex();
            if(idDepart == 0){
                jeu.setProba(densite.getValue()/100.0);
                jeu.initializeGrille();
            }else{
                List<Structure> structures = gestionStructures.charger();
                jeu.initializeGrilleStrucutre(structures.get(idDepart - 1).getCellules());
            }
            
            labelTaille.setText(jeu.getxMax() + "x" + jeu.getyMax());
            jeu.notifieObservateurs();
        });
        
        JPanel pannelTaille = new JPanel();
        pannelTaille.setLayout(new BoxLayout(pannelTaille, BoxLayout.Y_AXIS));
        labelTaille.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel champsTaille = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        champsTaille.add(fieldX);
        champsTaille.add(new JLabel("x"));
        champsTaille.add(fieldY);
        pannelTaille.add(labelTaille);
        pannelTaille.add(champsTaille);

        topPanel.add(new JLabel("Départ"));
        topPanel.add(structureDepart);
        topPanel.add(resetZoomButton);
        topPanel.add(pannelTaille);
        topPanel.add(new JLabel("Densité"));
        topPanel.add(densite);
        topPanel.add(resetGrilleBtn);
        
        return topPanel;
    }

    private JPanel creerPanneauControle(){
        JPanel panneau = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));

        JPanel sectionSim = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        sectionSim.setBorder(BorderFactory.createTitledBorder("Simulation"));
        btnPlayPause = new JButton("Play");
        btnPlayPause.addActionListener(e -> {
            if(enCours){
                timer.stop();
                enCours = false;
                btnPlayPause.setText("Play");
                btnStep.setEnabled(true);
            }else{
                timer.start();
                enCours = true;
                btnPlayPause.setText("Pause");
                btnStep.setEnabled(false);
            }
        });

        btnStep = new JButton("Step");
        btnStep.addActionListener(e -> jeu.calculeGenerationSuivante());

        JButton btnDessin = new JButton("Dessiner");
        btnDessin.addActionListener(e -> {
            modeDessin = !modeDessin;
            btnDessin.setText(modeDessin ? "Arr\u00EAter" : "Dessiner");
        });

        sectionSim.add(btnPlayPause);
        sectionSim.add(btnStep);
        sectionSim.add(btnDessin);

        JPanel sectionVitesse = new JPanel();
        sectionVitesse.setLayout(new BoxLayout(sectionVitesse, BoxLayout.Y_AXIS));
        sectionVitesse.setBorder(BorderFactory.createTitledBorder("Vitesse (ms)"));

        sliderVitesse = new JSlider(20, 500, 100);
        sliderVitesse.setMajorTickSpacing(120);
        sliderVitesse.setInverted(true);
        sliderVitesse.setPaintTicks(true);
        sliderVitesse.setPaintLabels(true);
        sliderVitesse.addChangeListener(e -> timer.setDelay(sliderVitesse.getValue()));

        sectionVitesse.add(sliderVitesse);

        JPanel sectionRegles = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        sectionRegles.setBorder(BorderFactory.createTitledBorder("R\u00E8gles"));

        String[] mode = {"Classique", "HighLife", "Day & Night" , "Immortel" , "Plague"};
        comboRegles = new JComboBox<>(mode);
        comboRegles.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                switch(comboRegles.getSelectedIndex()){
                    case 0: jeu.setVisiteur(new VisiteurClassique(jeu)); break;
                    case 1: jeu.setVisiteur(new VisiteurHighLife(jeu)); break;
                    case 2: jeu.setVisiteur(new VisiteurDayNight(jeu)); break;
                    case 3: jeu.setVisiteur(new VisiteurVieSansMort(jeu)); break;
                    case 4: jeu.setVisiteur(new VisiteurCovid(jeu)); break;
                }
            }
        });

        sectionRegles.add(comboRegles);

        JPanel sectionStructures = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        sectionStructures.setBorder(BorderFactory.createTitledBorder("Structures"));

        comboStructures = new JComboBox<>();
        rafraichirComboStructures();

        JButton btnPlacer = new JButton("Placer");
        btnPlacer.addActionListener(e -> {
            if (modePlacement) {
                modePlacement = false;
                structureAPoser = null;
                btnPlacer.setText("Placer");
            } else {
                int idx = comboStructures.getSelectedIndex();
                if(idx < 0) return;
                List<Structure> structures = gestionStructures.charger();
                structureAPoser = structures.get(idx).getCellules();
                modePlacement = true;
                btnPlacer.setText("Annuler");
            }
        });

        JButton savebtn = new JButton("Sauvegarder");
        savebtn.addActionListener(e -> {
            modeSelection = true;
            selX1 = -1;
            selY1 = -1;
            JOptionPane.showMessageDialog(this, "Cliquez sur 2 points de la grille pour d\u00E9finir la zone.");
        });

        sectionStructures.add(comboStructures);
        sectionStructures.add(btnPlacer);
        sectionStructures.add(savebtn);

        panneau.add(sectionSim);
        panneau.add(sectionVitesse);
        panneau.add(sectionRegles);
        panneau.add(sectionStructures);

        return panneau;
    }

    private void rafraichirComboStructures(){
        comboStructures.removeAllItems();
        for(Structure s : gestionStructures.charger()){
            comboStructures.addItem(s.getNom());
        }
    }
    @Override
    public void actualise() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform at = g2.getTransform();

        int grilleW = jeu.getxMax() * CELL_SIZE;
        int grilleH = jeu.getyMax() * CELL_SIZE;
        int centreX = (getWidth() - (int)(grilleW * zoomFactor)) / 2;
        int centreY = (getHeight() - (int)(grilleH * zoomFactor)) / 2;

        g2.translate(centreX + offsetX, centreY + offsetY);
        g2.scale(zoomFactor, zoomFactor);

        g2.setColor(Color.LIGHT_GRAY);
        for (int x = 0; x <= jeu.getxMax(); x++) {
            g2.drawLine(x * CELL_SIZE, 0, x * CELL_SIZE, jeu.getyMax() * CELL_SIZE);
        }
        for (int y = 0; y <= jeu.getyMax(); y++) {
            g2.drawLine(0, y * CELL_SIZE, jeu.getxMax() * CELL_SIZE, y * CELL_SIZE);
        }

        g2.setColor(Color.BLACK);
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if (jeu.getGrilleXY(x, y).estVivante()) {
                    g2.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
        if (modeSelection && selX1 != -1) {
            g2.setColor(Color.RED);
            g2.drawRect(selX1 * CELL_SIZE, selY1 * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        g2.setTransform(at);
    }
    private int[] screenToGrid(int sx, int sy) {
        int centreX = (getWidth() - (int)(jeu.getxMax() * CELL_SIZE * zoomFactor)) / 2;
        int centreY = (getHeight() - (int)(jeu.getyMax() * CELL_SIZE * zoomFactor)) / 2;
        int gx = (int) ((sx - centreX - offsetX) / zoomFactor / CELL_SIZE);
        int gy = (int) ((sy - centreY - offsetY) / zoomFactor / CELL_SIZE);
        return new int[]{gx, gy};
    }

    private void dessinerCellule(int gx, int gy) {
        if (gx >= 0 && gx < jeu.getxMax() && gy >= 0 && gy < jeu.getyMax()) {
            if (dessinEfface) {
                jeu.getGrilleXY(gx, gy).meurt();
            } else {
                jeu.getGrilleXY(gx, gy).vit();
            }
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastDragX = e.getX();
        lastDragY = e.getY();
        if (modeDessin) {
            dessinEfface = SwingUtilities.isRightMouseButton(e);
            int[] g = screenToGrid(e.getX(), e.getY());
            dessinerCellule(g[0], g[1]);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (modeDessin) {
            int[] g = screenToGrid(e.getX(), e.getY());
            dessinerCellule(g[0], g[1]);
        } else {
            int dx = e.getX() - lastDragX;
            int dy = e.getY() - lastDragY;
            offsetX += dx;
            offsetY += dy;
            lastDragX = e.getX();
            lastDragY = e.getY();
            repaint();
        }
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

    @Override
    public void mouseClicked(MouseEvent e) {
        int[] coords = screenToGrid(e.getX(), e.getY());
        int gx = coords[0];
        int gy = coords[1];

        if (modePlacement) {
            jeu.placeStructure(structureAPoser, gx, gy);
            return;
        }

        if (!modeSelection) return;
        if (selX1 == -1) {
            selX1 = gx;
            selY1 = gy;
            repaint();
        } else {
            selX2 = gx;
            selY2 = gy;
            String nom = JOptionPane.showInputDialog(this, "Nom de la structure :");
            if (nom != null && !nom.trim().isEmpty()) {
                java.util.List<int[]> cellules = jeu.extractStructure(selX1, selY1, selX2, selY2);
                java.util.List<Structure> structures = gestionStructures.charger();
                structures.add(new Structure(nom.trim(), cellules));
                gestionStructures.sauvegarder(structures);
                rafraichirComboStructures();
            }
            selX1 = -1; selY1 = -1; selX2 = -1; selY2 = -1;
            modeSelection = false;
            repaint();
        }
    }
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
