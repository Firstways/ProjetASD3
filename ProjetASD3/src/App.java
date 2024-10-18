import java.awt.Color;


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


    

          
        
    }
}
