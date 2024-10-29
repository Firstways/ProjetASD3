package Projet;
import static org.junit.Assert.assertTrue;

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
    private String region;

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
        if (this.is_empty()){
            is_empty = true;
        }
        this.region = null;
    }
    public Quadtree(Point point){
        this.No = null;
        this.Se = null ;
        this.Ne = null;
        this.So = null ;
        this.point = point;
        this.is_empty = true;
    
    }

    public Quadtree getNo(){
        return this.No;
    }
    public Quadtree getNe(){
        return this.Ne;
    }
    public Quadtree getSe(){
        return this.Se;
    }
    public Quadtree getSo(){
        return this.So;
    }
    public Point getPoint(){
        return this.point;
    }

    public String colorIdentifier(Color color ){
        if (color.equals(Color.RED)) {
            return "ROUGE";
        } else if (color.equals(Color.BLUE)) {
            return "BLEU";
        } else if (color.equals(Color.GREEN)) {
            return "VERT";
        } else if (color.equals(Color.YELLOW)) {
            return "JAUNE";
        } else if (color.equals(Color.CYAN)) {
            return "CYAN";
        } else if (color.equals(Color.MAGENTA)) {
            return "MAGENTA";
        } else if (color.equals(Color.GRAY)) {
            return "GRIS";
        } else if (color.equals(Color.PINK)) {
            return "ROSE";
        } else if (color.equals(Color.ORANGE)) {
            return "ORANGE";
        } else {
            return "INCONNU";
        }
    }

    public Color getColorNo(){
        return this.getPoint().getColors()[0];
    }
    public Color getColorNe(){
        return this.getPoint().getColors()[1];
    }
    public Color getColorSo(){
        return this.getPoint().getColors()[3];
    }
    public Color getColorSe(){
        return this.getPoint().getColors()[2];
    }


    public String getColorNoString() {
        Color c = this.getPoint().getColors()[0];
        return colorIdentifier(c);
    }
    public String getColorNeString() {
        Color c = this.getPoint().getColors()[1];
        return colorIdentifier(c);
    }
    public String getColorSeString() {
        Color c = this.getPoint().getColors()[2];
        return colorIdentifier(c);
    }
    public String getColorSoString() {
        Color c = this.getPoint().getColors()[3];
        return colorIdentifier(c);
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
     * Précondition quadtree!= null
     */
    public Dual searchQTree(Point point_enfant){
        /*
         * Si le quad a 4 feuilles vide, je regarde à quelle region appartient le point
         * et je retourne le pointeur de région
         * Sinon je regarde à quelle région appartient le point et je descend
         * 
         * 
         * Je regarde à quelle région appartient le point
         * Si le quad à 4 feuilles vide je retourne le pointeur
         * Sinon je descend
         */
        Point point_parent = this.getPoint();
        
        int X_parent = point_parent.getX();
        int Y_parent= point_parent.getY();

        int X_enfant = point_enfant.getX();
        int Y_enfant = point_enfant.getY();

  
   
           
        if (X_enfant <X_parent && Y_enfant < Y_parent) {
            if (this.No !=null){
                return this.getNo().searchQTree(point_enfant);

            }else {
                System.out.println("No");
                return new Dual(this,"No");
            }
            
        }
        // appartient a la region 2
        else if (X_enfant >X_parent &&Y_enfant < Y_parent ) {
            if (this.Ne !=null){
                return this.getNe().searchQTree(point_enfant);

            }else {
                System.out.println("No");
                return new Dual(this,"Ne");
            }
        }
        // appartient a la region 3
        else if (X_enfant> X_parent && Y_enfant > Y_parent) {
            if (this.Se !=null){
                return this.getSe().searchQTree(point_enfant);

            }else {
                System.out.println("No");
                return new Dual(this,"Se");
            }
        }
        // appartient a la region 4
        else  if (X_enfant< X_parent && Y_enfant > Y_parent) {
            if (this.So !=null){
                return this.getSo().searchQTree(point_enfant);

            }else {
                System.out.println("No");
                return new Dual(this,"So");
            }
        }
        else {
            throw new IllegalArgumentException("Region non trouvée: merci de mettre de traiter les cas ou deux points ne sont pas sur le meme axe en X ou Y");
        }
    }

  
    /*
     * A partir d'un nombre de point
     * creer le Qtree final avec tous les points
     * Précondition: A != null
     */
    public void addQTree(Point p){
        // Si le quadtree n'a pas de point deja placé alors
        // il ne sert a rien de recherche sa region
        Dual region_recherche = searchQTree(p);
        if (region_recherche.region=="No"){
            region_recherche.quad.No = new Quadtree(p);
        }else if (region_recherche.region=="Ne"){
            region_recherche.quad.Ne = new Quadtree(p);
        }
        else if (region_recherche.region=="Se"){
            region_recherche.quad.Se = new Quadtree(p);
        }else if (region_recherche.region=="So"){
            region_recherche.quad.So = new Quadtree(p);
        }else {
            throw new IllegalArgumentException(" Erreur inconnue merci de prendre contact avec les programmeurs");
        }

    }
/* 
        Point p2 = this.getPoint();
        if (this.is_empty()){
            System.out.println("point recherche :"+ p.getX()+":"+p.getY());
            System.out.println("region No,Ne,Se,So :"+  this.No+this.Ne +this.Se+this.So);

            if (p.getX() < p2.getX() && p.getY() < p2.getY() && this.No == null) {
                System.out.println("No");
                this.No=new Quadtree(p);
            }
            // appartient a la region 2
            else if (p.getX() > p2.getX() && p.getY() < p2.getY()&& this.Ne == null ) {
                this.Ne=new Quadtree(p);

            }
            // appartient a la region 3
            else if (p.getX() > p2.getX() && p.getY() > p2.getY()&& this.Se == null) {
                this.Se=new Quadtree(p);

            }
           // appartient a la region 4
            else  if (p.getX() < p2.getX() && p.getY() > p2.getY()&& this.So == null) {
                this.So=new Quadtree(p);
            }
            else {
                throw new IllegalArgumentException("Region non trouvée: merci de mettre de traiter les cas ou deux points ne sont pas sur le meme axe en X ou Y");
            }
        }else{
            System.out.println("pas empty");

            if (p.getX() < p2.getX() && p.getY() < p2.getY() && this.No !=null) {
                this.getNo().addQTree( p);
                 System.out.println("région 1");

            }
            // appartient a la region 2
            else if (p.getX() > p2.getX() && p.getY() < p2.getY() && this.Ne!=null) {
                this.getNe().addQTree( p);
                 System.out.println("région 2");

            }
            // appartient a la region 3
            else if (p.getX() > p2.getX() && p.getY() > p2.getY() && this.Se !=null) {
                this.getSe().addQTree(p);
                 System.out.println("région 3");

            }
            // appartient a la region 4
            else  {
                this.getSo().addQTree( p);
                 System.out.println("région 4");

            }
        }
    }
    */


    /*
     *  construit le quadtree entier en utilisant les fonctions
     *  précéedentes.
     */
    public void buildQTree(Point[] points){
        for (Point point : points){
            this.addQTree(point);
        }
    }


    

    public void toImageEncaps(String filename,Image img,int Xmin, int Ymin, int Xmax, int Ymax){

        if (this == null){
            return;
        }else { 
            img.setRectangle(Xmin, Ymin, this.getPoint().getX(), this.getPoint().getY(), getColorNo());
            img.setRectangle( this.getPoint().getX(),Ymin,Xmax, this.getPoint().getY(), getColorNe());
            img.setRectangle(this.point.getX(),this.point.getY(), Xmax, Ymax, getColorSe());
            img.setRectangle(Xmin,this.point.getY(),this.getPoint().getX(), Ymax, getColorSo());
           
                if (this.No != null){
                    System.out.println("NO");
            

                    this.No.toImageEncaps(filename, img, Xmin, Ymin, this.getPoint().getX(), this.getPoint().getY());
                }

                if (this.Ne != null){
                    System.out.println("Ne");
            
                    this.Ne.toImageEncaps(filename, img, this.getPoint().getX(), Ymin, Xmax, this.getPoint().getY());
                }

                if (this.Se != null){
                    System.out.println("sE");


                    this.Se.toImageEncaps(filename, img, this.getPoint().getX(), this.getPoint().getY(), Xmax, Ymax);
                } 

                if (this.So != null){
                    System.out.println("sO");
  

                    this.So.toImageEncaps(filename, img, Xmin, this.getPoint().getY(), this.getPoint().getX(), Ymax);
                }
          
        }
 
    }
    /*
     * Génère une image a partir du quadtree
     * Attention 0,0 = haut gauche
     */
    public void toImage(String filename,Image img){
      

      toImageEncaps(filename, img, 0, 0, img.width(), img.height());  
            
    }

  
    /*
     * Donne une représentation textuel de notre 
     * Parcours symétrique
     */
    public void toText(){
            /*
             * Si c'est une feuille j'écris la couleur
             * Sinon j'ouvre une parenthèse et je descends de 1
             */
             
            if (this.getNo()==null){
                System.out.print(this.getColorNoString());
                System.out.print(",");

            }
            if (this.getNe()==null){
                System.out.print(this.getColorNeString());
                System.out.print(",");

            }

            if (this.getSe()==null){
                System.out.print(this.getColorSeString());
                System.out.print(",");

            }
            if (this.getSo()==null){
                System.out.print(this.getColorSoString());
                System.out.print(",");

            }

            if (this.getNo()!=null){
                System.out.print("(");
                this.getNo().toText();
                System.out.print(")");

            }
            if (this.getNe()!=null){
                System.out.print("(");

                this.getNe().toText();
                System.out.print(")");

            }
            if (this.getSe()!=null){
                System.out.print("(");
                

                this.getSe().toText();
                System.out.print(")");

            }
            if (this.getSo()!=null){
                System.out.print("(");

                this.getSo().toText();
                System.out.print(")");

            }

    }

    /*
     * Prend un point et une couleur en entrée utilisateur
     * Change la couleur de la région dans lequel le point 
     * se trouve 
     */
    public void reColor(Point p, Color[] colors){
        // appelle search colors
        // modifie la colors

        Dual dual = this.searchQTree(p);
        dual.quad.getPoint().setColors(colors);
    }


    /*
     * Regarde si à un point donnée, les 4 régions voisines 
     * sont de la meme couleur
     * Si c'No le cas: retourne UNE region de la meme couleur
     */
    public void compressQTree(Point p){
        
        Point p2 = this.getPoint();
        if (this.is_empty){
            if ((this.getColorNo()==this.getColorNe())&&(this.getColorSe()==this.getColorSo())&&(this.getColorNo()==this.getColorSe())){
                this.No = null;
                this.So = null;

                this.Se = null;

                this.So = null;

            }
        }else{
            if (this.No != null){
                this.getNo().compressQTree(p);
            }
            if (this.Ne != null){
                this.getNe().compressQTree(p);
            }
            if (this.Se != null){
                this.getSe().compressQTree(p);
            }
            if (this.So != null){
                this.getSo().compressQTree(p);
            }
        }
    

    }


    public boolean equals(Quadtree quadtree_test){
            if (quadtree_test == null && this ==null){
                return true;
            }
            if (quadtree_test == null && this !=null){
                return false;
            }

            if (quadtree_test != null && this ==null){
                return false;
            }
            if (!(quadtree_test.getPoint().equals(this.getPoint()))){
                System.out.print("si");

                return false;
            }else {
                System.out.print("sinon");
                if ( this.getNo()!=null){
                    System.out.print("NO");
                    if (!this.getNo().equals(quadtree_test.getNo())|| (this.getNo()==null)){

                        return false;
                    }
                }
                if (this.getNe()!=null){
                    System.out.print("NE");

                    if (!this.getNe().equals(quadtree_test.getNe())||( this.getNe()==null)){
                        return false;
                    }
                }
                if (this.getSe()!=null){
                    System.out.print("SE");

                    if (!this.getSe().equals(quadtree_test.getSe())||( this.getSe()==null)){
                        return false;
                    }
                }
                if (this.getSo()!=null){
                    System.out.print("sO");

                    if (!this.getSo().equals(quadtree_test.getSo())||( this.getSo()==null)){
                        return false;
                    }
                }
                return true;

                }
            }
    
        }      
