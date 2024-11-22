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

         /*
          * 
          il faut ajouter le cas ou on souhaite recherche un point 
          qui n'est pas intermédiaire.
          */
        Point point_parent = this.getPoint();
        
        int X_parent = point_parent.getX();
        int Y_parent= point_parent.getY();

        int X_enfant = point_enfant.getX();
        int Y_enfant = point_enfant.getY();;
    
        System.out.println("Coordonnées du point parent : (" + X_parent + ", " + Y_parent + ")");
        System.out.println("Coordonnées du point enfant : (" + X_enfant + ", " + Y_enfant + ")");
        if (X_enfant <=X_parent && Y_enfant < Y_parent) {
        
            if (this.No !=null){
                if (this.No.getPoint().equals(point_enfant)){
                    return new Dual(this,"No");
                }else{
                return this.getNo().searchQTree(point_enfant);
                }

            }else {
                System.out.println("No");
                return new Dual(this,"No");
            }
            
        }
        // appartient a la region 2
        else if (X_enfant >X_parent &&Y_enfant < Y_parent ) {
            if (this.Ne !=null){
                if (this.Ne.getPoint().equals(point_enfant)){
                    return new Dual(this,"Ne");
                }else{
                return this.getNe().searchQTree(point_enfant);
                }
            }else {
                System.out.println("Ne");
                return new Dual(this,"Ne");
            }
        }
        // appartient a la region 3
        else if (X_enfant> X_parent && Y_enfant > Y_parent) {
            if (this.Se !=null){
                if (this.Se.getPoint().equals(point_enfant)){
                    return new Dual(this,"Se");
                }else{
                return this.getSe().searchQTree(point_enfant);
                }
            }else {
                System.out.println("Se");
                return new Dual(this,"Se");
            }
        }
        // appartient a la region 4
        else  if (X_enfant< X_parent && Y_enfant > Y_parent) {
            if (this.So !=null){
                if (this.So.getPoint().equals(point_enfant)){
                    return new Dual(this,"So");
                }else{
                return this.getSo().searchQTree(point_enfant);
                }
            }else {
                System.out.println("So");
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
        if (region_recherche.region.equals("No")){
            region_recherche.quad.No = new Quadtree(p);
        }else if (region_recherche.region.equals("Ne")){
            region_recherche.quad.Ne = new Quadtree(p);
        }
        else if (region_recherche.region.equals("Se")){
            region_recherche.quad.Se = new Quadtree(p);
        }else if (region_recherche.region.equals("So")){
            region_recherche.quad.So = new Quadtree(p);
        }else {
            throw new IllegalArgumentException(" Erreur inconnue merci de prendre contact avec les programmeurs");
        }

    }


    /*
     *  construit le quadtree entier en utilisant les fonctions
     *  précéedentes.
     */
    public void buildQTree(Point[] points){
        for (int k = 1 ; k < points.length;k++){
            System.out.println("Point" + k );
            this.addQTree(points[k]);
        }
    }

    public void printBorderHorizontal(Image img, int border,int Xmin,int Ymin, int Xmax, int Ymax){
        System.out.println(Xmin);
        System.out.println(Ymin);

        System.out.println(Xmax+border);
        System.out.println(Ymax);

        System.out.println("quad X " +this.getPoint().getX());
        System.out.println("quad Y "+this.getPoint().getY());

        img.setRectangle(Xmin,Ymin,Xmax,Ymax+border,Color.BLACK);

        if (this.No != null){
            System.out.println("NO");
            this.No.printBorderHorizontal(img,border,Xmin,this.No.getPoint().getY(),this.getPoint().getX(),this.No.getPoint().getY());

            
        }
        if (this.So != null){
            System.out.println("sO");
            this.So.printBorderHorizontal(img,border,Xmin, this.So.getPoint().getY(),this.getPoint().getX(),this.So.getPoint().getY() );

        }

        if (this.Ne != null){
            System.out.println("Ne");
            this.Ne.printBorderHorizontal(img,border, this.getPoint().getX(), this.Ne.getPoint().getY(),Xmax,this.Ne.getPoint().getY() );


        }

        if (this.Se != null){
            System.out.println("sE");
            this.Se.printBorderHorizontal(img,border, this.getPoint().getX(), this.Se.getPoint().getY(),Xmax,this.Se.getPoint().getY());

        } 

    }

    public void printBorderVertical(Image img, int border,int Xmin,int Ymin, int Xmax, int Ymax){
        if (this == null){
            return;
        }else { 


            img.setRectangle(Xmin,Ymin,Xmax+border,Ymax,Color.BLACK);



            if (this.No != null){
                System.out.println("NO");
                this.No.printBorderVertical(img,border,this.No.getPoint().getX(), Ymin,this.No.getPoint().getX(),this.getPoint().getY()  );

                
            }
            if (this.So != null){
                System.out.println("sO");
                this.So.printBorderVertical(img,border,this.So.getPoint().getX(), this.getPoint().getY(),this.So.getPoint().getX(),Ymax );

            }

            if (this.Ne != null){
                System.out.println("Ne");
                this.Ne.printBorderVertical(img,border, this.Ne.getPoint().getX(),Ymin,this.Ne.getPoint().getX(),this.getPoint().getY() );


            }

            if (this.Se != null){
                System.out.println("sE");
                this.Se.printBorderVertical(img,border,this.Se.getPoint().getX(),this.getPoint().getY(),this.Se.getPoint().getX(),Ymax);

            } 


          
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
                System.out.println("no");
                this.No.toImageEncaps(filename, img, Xmin, Ymin, this.getPoint().getX(), this.getPoint().getY());
            }

            if (this.Ne != null){
                System.out.println("ne");
                this.Ne.toImageEncaps(filename, img, this.getPoint().getX(), Ymin, Xmax, this.getPoint().getY());
            }

            if (this.Se != null){
                System.out.println("se");
                this.Se.toImageEncaps(filename, img, this.getPoint().getX(), this.getPoint().getY(), Xmax, Ymax);
            } 

            if (this.So != null){
                System.out.println("so");
                this.So.toImageEncaps(filename, img, Xmin, this.getPoint().getY(), this.getPoint().getX(), Ymax);
            }
          
        }
    
 
    }
    /*
     * Génère une image a partir du quadtree
     * Attention 0,0 = haut gauche
     */
    public void toImage(String filename,Image img,int border){
      toImageEncaps(filename, img, 0, 0, img.width(), img.height()); 
      printBorderVertical(img ,border,this.getPoint().getX(),0,this.getPoint().getX(), img.width()); 
      printBorderHorizontal(img ,border,0,this.getPoint().getY(),img.width(),this.getPoint().getY()); 

    }

  
    /*
     * Donne une représentation textuel de notre 
     * Parcours symétrique
     */
    public String toText(){
        /*
            * Si c'est une feuille j'écris la couleur
            * Sinon j'ouvre une parenthèse et je descends de 1
            */
        if (this.is_empty()){
            String s ="";
            
                s+= this.getColorNoString()+",";

            
                s+= this.getColorNeString()+",";


                s+= this.getColorSeString()+",";

                s+=(this.getColorSoString())+",";
                
                return s;

            
        }else {
            String s ="";

            if (this.getNo()!=null){
                s+= "("+this.getNo().toText()+")";
            }
            if (this.getNe()!=null){

                s+= "("+this.getNe().toText()+")";

            }
            if (this.getSe()!=null){
                

                s+= "("+this.getSe().toText()+")";

            }
            if (this.getSo()!=null){

                s+= "("+this.getSo().toText()+")";

            }
        return s;
        }
    }

    /*
     * Prend un point et une couleur en entrée utilisateur
     * Change la couleur de la région dans lequel le point 
     * se trouve 
     * 
     * Précondition: le point n'appartient pas au QuadTree
     */
    public void reColor(Point p, Color color){
        // appelle search colors
        // modifie la colors
        Dual dual = searchQTree(p);

        if (dual.region=="No"){
            this.getPoint().setColor(color,0);
        }else if (dual.region=="Ne"){
            this.getPoint().setColor(color,1);

        }
        else if (dual.region=="Se"){
            this.getPoint().setColor(color,2);

        }else if (dual.region=="So"){
            this.getPoint().setColor(color,3);

        }else {
            throw new IllegalArgumentException(" Erreur inconnue merci de prendre contact avec les programmeurs");
        }
        compressQTree(dual);
    }


    /*
     * Regarde si à un point donnée, les 4 régions voisines 
     * sont de la meme couleur
     * Si c'No le cas: retourne UNE region de la meme couleur
     * 
     * Précondition : le point appartient au quad tree
     */
    public void compressQTree(Dual dual){
        Point p2 = this.getPoint();
        if (dual.region=="No"){
            if (dual.quad.is_empty()){
                if ((dual.quad.getColorNo()==dual.quad.getColorNe())&&(dual.quad.getColorSe()==dual.quad.getColorSo())&&(dual.quad.getColorNo()==dual.quad.getColorSe())){
                    dual.quad.No= null;
                }
            }
           
        }
        else if (dual.region=="Ne"){
            if (dual.quad.is_empty()){
                if ((dual.quad.getColorNo()==dual.quad.getColorNe())&&(dual.quad.getColorSe()==dual.quad.getColorSo())&&(dual.quad.getColorNo()==dual.quad.getColorSe())){
                    dual.quad.Ne= null;
                }
            }
           
        }else if (dual.region=="Se"){
            if (dual.quad.is_empty()){
                if ((dual.quad.getColorNo()==dual.quad.getColorNe())&&(dual.quad.getColorSe()==dual.quad.getColorSo())&&(dual.quad.getColorNo()==dual.quad.getColorSe())){
                    dual.quad.Se= null;
                }
            }
        }else if (dual.region=="So"){
            if (dual.quad.is_empty()){
                if ((dual.quad.getColorNo()==dual.quad.getColorNe())&&(dual.quad.getColorSe()==dual.quad.getColorSo())&&(dual.quad.getColorNo()==dual.quad.getColorSe())){
                    dual.quad.So= null;
                }
            }
           
        }else {
            throw new IllegalArgumentException(" Erreur inconnue merci de prendre contact avec les programmeurs");
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
