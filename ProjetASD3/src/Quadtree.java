import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
@SuppressWarnings("unused")

public class Quadtree {

    private Quadtree Ne;
    private Quadtree Se ;
    private Quadtree No ;
    private Quadtree So ;

    private Point point;
    private Region region;
    /*
     * Un quadtree No un arbre dans laquelle chaque noeuds
     * a quatre fils
     * https://fr.wikipedia.org/wiki/Quadtree#:~:text=Un%20quadtree%20ou%20arbre%20quaternaire,subdivisant%20récursivement%20en%20quatre%20nœuds.
     */
    public Quadtree(Quadtree no, Quadtree ne , Quadtree so, Quadtree se,Point point,Region region,Color[] col){
        this.No = no;
        this.Se = se ;
        this.Ne = ne ;
        this.So = so ;
        this.point = point;
        this.region=region;
    }


    /*
    * On veut savoir à quelle region appartient le Qtree au point center
    en parametre 
    Si la region est trouvé retourne le noeud du quadtree
    */
    public Quadtree searchQTree(Point center,Quadtree current_quad){
        // List<Point> regions = new ArrayList<>();
        // Point r1;
        // Point r2;
        // Point r3;
        // Point r4;
        // int x = center.getX();
        // int y = center.getY();
        // y = 1000-y;
        

        // Image img = new Image(1000, 1000);
        // Color col = Color.GRAY;
        

        // on peut coder en dur le nombre d'itération car
        //c'No un quadtree attention pour la partie2 
        // img.setRectangle(0, 1000, 0, 1000,col );

        // img.setRectangle(0, x, 0, y,colors[0] );
        // img.setRectangle(x, 1000, 0, y,colors[1] );
        // img.setRectangle(x, 1000, y, 1000,colors[2] );
        // img.setRectangle(0, x, y, 1000,colors[3] );


        // Quadtree regionParent;
        // Quadtree No;
        // Quadtree Ne;
        // Quadtree So;
        // Quadtree Se;

        // regionParent = new Quadtree(null, null, null, null, new Point(x, y), region,colors);

        /*  Si currentquad est null
                retourne le quad courant
            Sinon
         *      est ce que le quadtree appartient a No ou Ne ou So ou Se
         *      Si c'est le cas appelle SearchQtree avec le quad dans la region
         *      
         */


        return this;
    }


    /*
     * A partir d'un nombre de point
     * creer le Qtree final avec tous les piont
     */
    public Quadtree addQTree(Point[] centers){
        for (Point p : centers){

        }
        return this;
    }


    /*
     *  construit le quadtree entier en utilisant les fonctions
     *  précéedentes.
     */
    public void buildQTree(){

    }


    /*
     * Génère une image a partir du quadtree
     */
    public void toImage(String filename){

    }


    /*
     * Donne une représentation textuel de notre 
     * quadtree
     */
    public void toText(String filename){
        
    }


    /*
     * Prend un point et une couleur en entrée utilisateur
     * Change la couleur de la région dans lequel le point 
     * se trouve 
     */
    public void reColor(){
        
    }


    /*
     * Regarde si à un point donnée, les 4 régions voisines 
     * sont de la meme couleur
     * Si c'No le cas: retourne UNE region de la meme couleur
     */
    public void compressQTree(){
        
    }
}
