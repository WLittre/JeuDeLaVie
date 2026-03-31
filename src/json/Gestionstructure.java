package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe gerant le chargement et la sauvegarde des structures dans un fichier texte
 * @author LWilliam
 * @version 1.0
 */
public class Gestionstructure {
    private String path;
    /**
     * Constructeur avec le chemin vers le fichier de structures
     * @param path le chemin du fichier texte
     */
    public Gestionstructure(String path){
        this.path = path;
    }

    /**
     * Charge toutes les structures depuis le fichier texte
     * @return la liste des structures chargees
     */
    public List<Structure> charger(){
        List<Structure> structures = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(path))){
            String nom = null;
            List<int[]>cellules = new ArrayList<>();
            while(sc.hasNextLine()){
                String line = sc.nextLine().trim();
                if(line.startsWith("#")){
                    if(nom != null) structures.add(new Structure(nom,cellules));
                    nom = line.substring(1);
                    cellules = new ArrayList<>();
                }else if(!line.isEmpty()){
                    String[] p = line.split(",");
                    cellules.add(new int[]{Integer.parseInt(p[0]),Integer.parseInt(p[1])});
                }
            }
            if(nom!=null)structures.add(new Structure(nom,cellules));
        }catch(IOException e){
            System.out.println("Erreur fichier introuvable");
        }
        return structures;
    }

    /**
     * Sauvegarde la liste de structures dans le fichier texte
     * @param structures la liste des structures a sauvegarder
     */
    public void sauvegarder(List<Structure> structures){
        try(PrintWriter pw = new PrintWriter(new FileWriter(path))){
            for(Structure s : structures){
                pw.println("#"+s.getNom());
                for(int[]c:s.getCellules())pw.println(c[0]+","+c[1]);
                pw.println();
            }
        }catch(IOException e){
            System.out.println("Erreur sur la sauvegarde");
        }
    }
}
