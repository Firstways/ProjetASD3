
import java.io.FileWriter;

public class App {

    /*
     * Compilation :    javac -source 1.7 -target 1.7 -d boyenvalbelouin/bin ProjetASD3/src/*.java
     * Execution:       java -classpath boyenvalbelouin/bin App 1 Entree/args.txt Sortie/
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
        int strategie =  Integer.parseInt(args[0]);
        String entree = args[1]; // nom du fichier
        String sortie = args[2]; // nom du dossier de sortie


        
        
       
        //Recuperation du fichier d'entrée,  et découpe
        if(strategie == 1){
            MyFile myFile = new MyFile(entree,strategie);
            myFile.readFile();
            int image_size = myFile.getImageSize();
            Point[] point_add_to_quad= myFile.getPoints();
            Point[] region_to_recolor = myFile.getRecolors();
            int border_size = myFile.getBorderSize();

            // construction du Q Tree
            Quadtree quad = new Quadtree (point_add_to_quad[0]);
            quad.buildQTree(point_add_to_quad);
            System.out.println(point_add_to_quad.length);

            Image img = new Image(image_size, image_size);
            quad.toImage(sortie+"/square_B", img, border_size);
            try {
                img.save(sortie+"boyenvalbelouinsargs_B");
                FileWriter writer = new FileWriter(sortie+"boyenvalbelouinargs_B.txt");
                String text_quad ="("+quad.toText()+")";
                writer.write(text_quad);
                writer.close();

    
    
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (Point p : region_to_recolor){
                System.out.print("points to recolor"+ p.toString());
                System.out.println();
                quad.reColor(p,p.getColor());
            }
            img = new Image(image_size, image_size);
            quad.toImage(sortie+"/square_R", img, border_size);
            try {
                img.save(sortie+"boyenvalbelouinsargs_R");
                FileWriter writer = new FileWriter(sortie+"boyenvalbelouinsargs_R.txt");
                String text_quad ="("+quad.toText()+")";
                writer.write(text_quad);
                writer.close();

    
    
            } catch (Exception e) {
                e.printStackTrace();
            }



        }
           
    }
}




/*
 * 
 * Color[] c5 = {Color.BLUE, Color.RED,Color.YELLOW,Color.GRAY};
        Point p5 = new Point(540,120,c5);
        Quadtree q5 = new Quadtree(null,null,null,null,p5);


        Color[] c4 = {Color.RED, Color.YELLOW,Color.RED,Color.YELLOW};
        Point p4 = new Point(850,350,c4);
        Quadtree q4 = new Quadtree(null,null,null,null,p4);


        Color[] c3 = {Color.GRAY, Color.RED,Color.BLACK,Color.YELLOW};
        Point p3 = new Point(800,300,c3);
        Quadtree q3 = new Quadtree(null,null,null,q4,p3);


        Color[] c2 = {Color.YELLOW, Color.BLUE,Color.BLACK,Color.BLUE};
        Point p2 = new Point(900,400,c2);
        Quadtree q2 = new Quadtree(q3,null,null,null,p2);

        Color[] c1 = {Color.RED, Color.GRAY,Color.YELLOW,Color.BLUE};
        Point p1 = new Point(600,500,c1);
        Quadtree q1 = new Quadtree(q5,q2,null,null,p1);

        Image img = new Image(1000, 1000);
        q1.toImage("Sortie/output_test",img,10);
        img.save("Sortie/output_test");
        q1.toText();



        
        Color[] c5 = {Color.BLUE, Color.RED,Color.YELLOW,Color.GRAY};
        Point p5 = new Point(540,120,c5);


        Color[] c4 = {Color.RED, Color.YELLOW,Color.RED,Color.YELLOW};
        Point p4 = new Point(850,350,c4);


        Color[] c3 = {Color.GRAY, Color.RED,Color.BLACK,Color.YELLOW};
        Point p3 = new Point(800,300,c3);


        Color[] c2 = {Color.YELLOW, Color.BLUE,Color.BLACK,Color.BLUE};
        Point p2 = new Point(900,400,c2);

        Color[] c1 = {Color.RED, Color.GRAY,Color.YELLOW,Color.BLUE};
        Point p1 = new Point(600,500,c1);
        Quadtree q1 = new Quadtree(p1);

        Point[] points = {p2,p3,p4,p5};
        q1.buildQTree(points);

        Image img = new Image(1000, 1000);
        q1.toImage("Sortie/output_test",img,10);
        img.save("Sortie/output_test");
        q1.toText();

 */