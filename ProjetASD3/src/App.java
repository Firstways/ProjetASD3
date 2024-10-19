import java.awt.Color;

import Projet.Image;
import Projet.Point;
import Projet.Quadtree;


@SuppressWarnings("unused")

public class App {

    /*
     * Compilation :javac -source 1.7 -target 1.7 -d boyenvalbelouin/bin ProjetASD3/src/*.java
     * Execution: java -classpath boyenvalbelouin/bin/ App 1 Entree/args.txt Sortie/
Les armuments sont :
     */
    public static void main(String[] args) throws Exception {
        // System.out.println("nombre arguments :" + args.length);
        // if ((args.length != 0 )|| (args.length != 3)){
        //     System.err.println("Fin du programme: Nombre d'argument incorrect");

        //     System.exit(1);
        // }
        // System.out.println("Les armuments sont :");
        // for (String arg : args){
        //     System.out.println(arg);
        // }
        // String strategie = args[0];
        // String entree = args[1];
        // String sortie = args[2];


        Image img = new Image(1000, 1000);
        Color col = Color.GRAY;
        img.setRectangle(0, 1000, 0, 1000,col );
        img.save("square");

 

        Point p1 = new Point(100,100);
        Point p2 = new Point(50,50);
        Point p3 = new Point(75,15);
        Point p4 = new Point(60,34);

        Quadtree regionAB = new Quadtree(null,null,null,null,p3);

        Quadtree regionA = new Quadtree(null,regionAB,null,null,p2);

        Quadtree quad_final = new Quadtree(regionA,null,null,null,p1);

        System.out.println("p1"+regionA.getNe());
        Quadtree result = quad_final.searchQTree(quad_final,p4);
        System.out.println("P2"+result);

        
        
    }
}
