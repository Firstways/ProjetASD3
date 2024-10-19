package Projet;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

import org.junit.runners.BlockJUnit4ClassRunner;
@SuppressWarnings("unused")

public class Quadtree {

    private Quadtree Ne;
    private Quadtree Se ;
    private Quadtree No ;
    private Quadtree So ;

    private Point point;
    private boolean is_empty;
    private List<Point> region;

    /*
     * Un quadtree No un arbre dans laquelle chaque noeuds
     * a quatre fils
     * https://fr.wikipedia.org/wiki/Quadtree#:~:text=Un%20quadtree%20ou%20arbre%20quaternaire,subdivisant%20récursivement%20en%20quatre%20nœuds.
     */

    public Quadtree(Quadtree no, Quadtree ne , Quadtree so, Quadtree se,Point point){
        this.No = no;
        this.Se = se ;
        this.Ne = ne ;
        this.So = so ;
        this.point = point;
        this.region =  new ArrayList<>();
        if (this.is_empty()){
            is_empty = true;
        }
    }

    public Quadtree getNo(){
        return this.No;
    }
    public Quadtree getNe(){
        return this.Ne;
    }
    public Quadtree getSe(){
        return this.So;
    }
    public Quadtree getSo(){
        return this.Se;
    }

    public void setRegion(Point hg,Point hd,Point bg, Point bd){
        region.add(hg);
        region.add(hd);
        region.add(bd);
        region.add(bg);
    }
    public boolean is_empty(){
        if ((this.Ne ==null)&&(this.No ==null)&&(this.Se ==null)&&(this.So ==null)){
            return true;
        }
        else {
            return false;
        }
    }
    /*
     * Retourne la feuille à laquel le point appartient
     */
    public Quadtree searchQTree(Quadtree current_quad, Point precherche){
        if (current_quad.is_empty){
            // appartient a la region 1
            Point p = current_quad.point;
            if (precherche.getX() < p.getX() && precherche.getY() < p.getY()) {
                System.out.println("REGION 1");

                return current_quad.No;
            }

            // appartient a la region 2
            if (precherche.getX() > p.getX() && precherche.getY() < p.getY()) {
                System.out.println("REGION 2");

                return current_quad.Ne;

            }
            // appartient a la region 3
            if (precherche.getX() > p.getX() && precherche.getY() > p.getY()) {
                System.out.println("REGION 3");

                return current_quad.Se;

            }
            // appartient a la region 4
            else  {
                System.out.println("REGION 4");

                return current_quad.So;

            }
        }else {
            if (current_quad.No != null){
                System.out.println("NO");

                return searchQTree(current_quad.No, precherche);
            }

            if (current_quad.Ne != null){
                System.out.println("Ne");

                return searchQTree(current_quad.Ne, precherche);

            }

            if (current_quad.Se != null){
                System.out.println("sE");

                return searchQTree(current_quad.Se, precherche);
            } 

            else {
                System.out.println("sO");

                return searchQTree(current_quad.So, precherche);
            }
        }
      
        


    }

  
    /*
     * A partir d'un nombre de point
     * creer le Qtree final avec tous les points
     */
    public Quadtree addQTree(Point p,Quadtree q){
        // Si le quadtree n'a pas de point deja placé alors
        // il ne sert a rien de recherche sa region
        int X_max = 1000;
        int Y_max = 1000;
        int X_min = 0;
        int Y_min = 0;
        // Si c'est le premier point
        if (searchQTree(q,p)==null){
            Quadtree quad = new Quadtree(null, null, null, null, p);
            Point no = new Point(0,0);
            Point ne = new Point(1000,0);
            Point se = new Point(1000,1000);
            Point so = new Point(0,1000);

            quad.setRegion(no, ne, se, so);
            return quad;
        }else {
            
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
        Image img = new Image(1000,1000);

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


    public boolean is_equal(Quadtree quadtree_test){
      return true;
    }
}
