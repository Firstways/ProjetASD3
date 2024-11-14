import java.io.*;
import java.util.*;
import java.awt.Color;

public class MyFile {

    private String relative_file_path;
    Donnees donnees ;
    List<String> dataList;


    public MyFile(String file_path){
        Donnees donnees = new Donnees();
        relative_file_path= file_path;
        dataList = new ArrayList<>();
    }


    public Donnees readFile(){
        try {
            // Ouvrir le fichier
            BufferedReader reader = new BufferedReader(new FileReader(relative_file_path));
            String line;
            int lineIndex = 0;
            

            // Lire chaque ligne du fichier et l'ajouter à la liste
            while ((line = reader.readLine()) != null) {
                dataList.add(line.trim()); // enlever les espaces inutiles
            }
            reader.close();

            // Parcourir les données et les ajouter à l'objet Donnees
            Iterator<String> iterator = dataList.iterator();


            // Lecture des données et stockage de celles-ci dans la structure associée
            

            donnees.setTailleFenetre(Integer.parseInt(iterator.next())); // Lecture de la taille de la fenêtre            
            donnees.setNbPoints(Integer.parseInt(iterator.next())); // Lecture du nombre de points

            // Lecture des points
            for (int i = 0; i < donnees.getNBPoints(); i++) {
                String[] pointData = iterator.next().split(",\\s*"); // séparer par la virgule
                int x = Integer.parseInt(pointData[0]);
                int y = Integer.parseInt(pointData[1]);
                Color[] colors = new Color[pointData.length - 2];

                // Ajouter les couleurs aux points
                for (int j = 2; j < pointData.length; j++) {
                    colors[j - 2] = parseColor(pointData[j]);
                }

                Point point = new Point(x, y, colors);
                donnees.addPoint(point);
            }

            // Lecture de la taille de la bordure
            donnees.setTailleBordure(Integer.parseInt(iterator.next()));

            // Lecture du nombre de couleurs
            donnees.setNbCouleurs(Integer.parseInt(iterator.next()));

            // Lecture des couleurs

            for (int i = 0; i < donnees.getNBCouleurs(); i++) {
                String[] couleurData = iterator.next().split(",\\s*");
                int x = Integer.parseInt(couleurData[0]);
                int y = Integer.parseInt(couleurData[1]);
                Color couleur = parseColor(couleurData[2]);
                Point point = new Point(x,y,couleur);
                donnees.addReColor(point);
            }

            return donnees;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
try {
    // Ouvrir le fichier
    BufferedReader reader = new BufferedReader(new FileReader(relative_file_path));
    String line;
    List<String> dataList = new ArrayList<>();

    // Lire chaque ligne du fichier et l'ajouter à la liste
    while ((line = reader.readLine()) != null) {
        dataList.add(line);
    }
    reader.close();

    // Convertir la liste en un tableau
    String[] dataArray = dataList.toArray(new String[0]);

    // Afficher les données du tableau
    System.out.println("\nFichier d'entrée :");
    for (String data : dataArray) {
        System.out.println(data);
    }
    System.out.println("\n");
*/