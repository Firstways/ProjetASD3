import java.awt.Color;
import java.util.*;

public class App {

    /*
     * Compilation :    javac -source 1.7 -target 1.7 -d boyenvalbelouin/bin ProjetASD3/src/*.java
     * Execution:       java -classpath boyenvalbelouin/bin/ App 1 Entree/args.txt Sortie/
     * ajout de javac -Xlint:-options pour reduire l'affichage :    -source 1.7 -target 1.7 -Xlint:-options -d boyenvalbelouin/bin ProjetASD3/src/*.java

Les armuments sont :
     */
    public static void main(String[] args) throws Exception {
        
        if (args.length != 3){
            System.err.println("Fin du programme: Nombre d'argument incorrect");
            System.exit(1);
        }
        System.out.println("Les armuments sont :");
        for (String arg : args){
         System.out.println(arg);
        }
        // Récuperation des arguments
        String strategie = args[0];
        String entree = args[1]; // nom du fichier
        String sortie = args[2];
       
       // Recuperation du fichier d'entrée et découpe
        MyFile myFile = new MyFile(entree);
        Donnees data = new Donnees();
        data = myFile.readFile();

        int image_size = data.getTailleFenetre();
        ArrayList<Point> point_add_to_quad= data.getPoints();
        ArrayList<Point> region_to_recolor = data.getReColor();
        int border_size = data.getTailleBordure();

        

        Quadtree quad = new Quadtree (point_add_to_quad[0]);
        quad.buildQTree(point_add_to_quad);

        for (Point p : region_to_recolor()){
            quad.reColor(p,p.getColor());
        }
    }
}


