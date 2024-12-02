
import java.io.FileWriter;


public class MonBoTablo {

    /*
     * Compilation :    javac -source 1.7 -target 1.7 -d boyenvalbelouin/bin ProjetASD3/src/*.java
     * Execution:       java -classpath boyenvalbelouin/bin App 1 Entree/args.txt Sortie/

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

        String[] segments = entree.split("/");
        String nomFichier = segments[segments.length - 1];
        
        
       
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

            Image img = new Image(image_size, image_size);
            quad.toImage( img, border_size);
            try {
                img.save(sortie+"boyenvalbelouin"+nomFichier+"_B");
                FileWriter writer = new FileWriter(sortie+"boyenvalbelouin"+nomFichier+"_B.txt");
                String text_quad ="("+quad.toText()+")";
                writer.write(text_quad);
                writer.close();

    
    
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (Point p : region_to_recolor){

                quad.reColor(p,p.getColor());
            }
            img = new Image(image_size, image_size);
            quad.toImage(img, border_size);
            try {
                img.save(sortie+"boyenvalbelouin"+nomFichier+"_R");
                FileWriter writer = new FileWriter(sortie+"boyenvalbelouin"+nomFichier+"_R.txt");
                String text_quad ="("+quad.toText()+")";
                writer.write(text_quad);
                writer.close();

    
    
            } catch (Exception e) {
                e.printStackTrace();
            }

            

        }

        else if(strategie == 2){
            // Récuperation du fichiers d'entrée + découpe
            MyFile myFile = new MyFile(entree, strategie);
            myFile.readFile();
            int image_size = myFile.getImageSize();
            Point[] point_add_to_ternaire= myFile.getPoints();
            Point[] region_to_recolor = myFile.getRecolors();
            int border_size = myFile.getBorderSize();


            // Contruction de TernaireTree 
            TernaireTree ternaire = new TernaireTree(point_add_to_ternaire[0]);
            ternaire.buildTTree(point_add_to_ternaire);

            Image img = new Image(image_size, image_size);
            ternaire.toImage(sortie+"/ternaire_B", img, border_size);
            try {
                img.save(sortie+"boyenvalbelouin"+nomFichier+"_B");
                FileWriter writer = new FileWriter(sortie+"boyenvalbelouin"+nomFichier+"_B.txt");
                String text_ternaire ="("+ternaire.toText()+")";
                writer.write(text_ternaire);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Point p : region_to_recolor){
                ternaire.reColor(p,p.getColor());
            }
            img = new Image(image_size, image_size);
            ternaire.toImage(sortie+"/ternaire_R", img, border_size);
            try {
                img.save(sortie+"boyenvalbelouin"+nomFichier+"_R");
                FileWriter writer = new FileWriter(sortie+"boyenvalbelouin"+nomFichier+"_R.txt");
                String text_ternaire ="("+ternaire.toText()+")";
                writer.write(text_ternaire);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
           
    }
}



