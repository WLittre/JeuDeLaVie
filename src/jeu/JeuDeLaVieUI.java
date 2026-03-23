package jeu;

import javax.swing.*;
import java.awt.*;

import observateur.Observateur;
import visiteur.Visiteur;
import visiteur.VisiteurClassique;
import visiteur.VisiteurDayNight;

public class JeuDeLaVieUI extends JPanel implements Observateur {
    private JeuDeLaVie jeu;
    private Timer timer;
    private boolean enCours = false;

    private JButton btnPlayPause;
    private JButton btnStep;
    private JSlider sliderVitesse;
    private JComboBox<String> comboRegles;

    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        this.jeu = jeu;
        setBackground(Color.WHITE);
        timer = new Timer(100,e -> jeu.calculeGenerationSuivante());

        JFrame frame = new JFrame("Jeu de la Vie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(jeu.getxMax() * 3 + 40, jeu.getyMax() * 3 + 120);
        frame.setLayout(new BorderLayout());
        frame.add(this,BorderLayout.CENTER);
        frame.add(creerPanneauControle(),BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private JPanel creerPanneauControle(){
        JPanel panneau = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));

        btnPlayPause = new JButton("Play");
        btnPlayPause.addActionListener(e -> {
            if(enCours){
                timer.stop();
                enCours = false;
                btnPlayPause.setText("Play");
                btnStep.setEnabled(true);
            }else{
                timer.start();
                enCours=true;
                btnPlayPause.setText("Pause");
                btnStep.setEnabled(false);
            }
        });

        btnStep=new JButton("Step");
        btnStep.addActionListener(e->jeu.calculeGenerationSuivante());
        sliderVitesse = new JSlider(20, 500, 100);
        sliderVitesse.setMajorTickSpacing(120);
        sliderVitesse.setInverted(true);
        sliderVitesse.setPaintTicks(true);
        sliderVitesse.setPaintLabels(true);
        sliderVitesse.addChangeListener(e -> timer.setDelay(sliderVitesse.getValue()));

        String[] mode = {"Classique" , "Highlife" , "Day n Night"};
        comboRegles = new JComboBox<>(mode);
        comboRegles.addActionListener(e->{
            switch(comboRegles.getSelectedIndex()){
                case 0: jeu.setVisiteur(new VisiteurClassique(jeu));break;
                case 1: jeu.setVisiteur(new VisiteurDayNight(jeu));break;
                case 2: jeu.setVisiteur(new VisiteurDayNight(jeu));break;
            }
        });

        panneau.add(btnPlayPause);
        panneau.add(btnStep);
        panneau.add(new JLabel("Vitesse(en ms)"));
        panneau.add(sliderVitesse);
        panneau.add(new JLabel("Regles"));
        panneau.add(comboRegles);

        return panneau;
    }
    @Override
    public void actualise() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if (jeu.getGrilleXY(x, y).estVivante()) {
                    g.fillOval(x * 3, y * 3, 3, 3);
                }
            }
        }
    }
}
