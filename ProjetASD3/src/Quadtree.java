import java.awt.*;

public class Quadtree {

    private Quadtree Nord;
    private Quadtree Sud ;
    private Quadtree Est ;
    private Quadtree Ouest ;

    private int val;

    /*
     * Un quadtree est un arbre dans laquelle chaque noeuds
     * a quatre fils
     * https://fr.wikipedia.org/wiki/Quadtree#:~:text=Un%20quadtree%20ou%20arbre%20quaternaire,subdivisant%20récursivement%20en%20quatre%20nœuds.
     */
    public Quadtree(Quadtree n, Quadtree s , Quadtree e, Quadtree o,int val){
        this.Nord = n ;
        this.Sud = s ;
        this.Est = e ;
        this.Ouest = o ;
        this.val = val;
    }


    /*( fonction recursive)
    * Retourne les 4 région subdivié 
    * avec le point comme centre (point intersection des rectangles)
    */
    public Quadtree searchQTree(Point center){
        return this;
    }


    /*
     * A partir d'un nombre de point
     * creer le Qtree final avec tous les piont
     */
    public Quadtree addQTree(Point[] center){
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
     * Si c'est le cas: retourne UNE region de la meme couleur
     */
    public void compressQTree(){
        
    }
}
