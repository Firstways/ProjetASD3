import static org.junit.Assert.assertTrue;

import java.awt.Color;

import Projet.Dual;
import Projet.Image;
import Projet.Point;
import Projet.Quadtree;
import java.util.*;


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


        // Image img = new Image(1000, 1000);
        // Color col = Color.RED;
        // img.setRectangle(0, 0, 1000, 1000,col );
        // img.save("square");

        // col = Color.BLUE;
        // img.setRectangle(100, 100, 200, 200,col );
        // img.save("square");
        Color[] colors_region = { Color.RED, Color.GREEN, Color.BLUE, Color.GRAY};
        Point p1 = new Point(500,500,colors_region);
        Point p2 = new Point(250,750,colors_region);
        Point p3 = new Point(400,700,colors_region);
        Point p4 = new Point(450,450,colors_region);

        Point[] points = {p2,p3,p4};

        Quadtree quad_test = new Quadtree(null,null,null,null,p1);
        quad_test.buildQTree(points);



        Quadtree se  = new Quadtree(null,null,null,null,p4);

        Quadtree ne = new Quadtree(null,null,null,null,p3);


        Quadtree so  = new Quadtree(null,ne,null,null,p2);

        Quadtree quad_to_test = new Quadtree(null,null,so,null,p1);

   


    }
}

/*
       Color[] colors_region1 = { Color.RED, Color.GREEN, Color.BLUE, Color.GRAY};
        Color[] colors_region2 = { Color.YELLOW, Color.PINK, Color.ORANGE, Color.CYAN};
        Color[] colors_region3 = { Color.LIGHT_GRAY, Color.MAGENTA, Color.WHITE, Color.BLACK};

        Point p1 = new Point(500,500,colors_region1);
        Point p2 = new Point(400,400,colors_region2);



        Point p3 = new Point(800,100,colors_region3);
        Point p4 = new Point(100,600,colors_region2);

        Point p5 = new Point(150,150,colors_region2);



        
        Quadtree quad_test = new Quadtree(null,null,null,null,p1);
        quad_test.addQTree(p2);
        // quad_test.addQTree(p3);
        // quad_test.addQTree(p4);
        quad_test.addQTree(p5);
        System.out.println("");

        Image img = new Image(1000, 1000);

        quad_test.toImage("square", img);
        try {
            img.save("square");

        } catch (Exception e) {
            e.printStackTrace();
        }
    
 */
