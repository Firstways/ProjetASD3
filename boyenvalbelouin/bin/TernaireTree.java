import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

@SuppressWarnings("unused")

public final class TernaireTree {

    private TernaireTree ouest;
    private TernaireTree nordEst;
    private TernaireTree sudEst;

    private Point point;
    private boolean isEmpty;
    private String region;

    public TernaireTree(TernaireTree ouest, TernaireTree nordEst, TernaireTree sudEst, Point point) {
        this.ouest = ouest;
        this.nordEst = nordEst;
        this.sudEst = sudEst;
        this.point = point;
        this.isEmpty = this.is_empty();
        this.region = null;
    }

    public TernaireTree(Point point) {
        this.ouest = null;
        this.nordEst = null;
        this.sudEst = null;
        this.point = point;
        this.isEmpty = true;
    }



    public TernaireTree getOuest() {
        return this.ouest;
    }
    public TernaireTree getNordEst() {
        return this.nordEst;
    }
    public TernaireTree getSudEst() {
        return this.sudEst;
    }
    public Point getPoint() {
        return this.point;
    }



    public String colorIdentifier(Color color ){
        if (color.equals(Color.RED)) {
            return "R";
        } else if (color.equals(Color.BLUE)) {
            return "B";
        } else if (color.equals(Color.YELLOW)) {
            return "J";
        } else if (color.equals(Color.GRAY)) {
            return "G";
        } else if (color.equals(Color.BLACK)) {
            return "N";
        } else {
            return "INCONNU";
        }
    }


    public Color getColorO(){
        return this.getPoint().getColors()[0];
    }
    public Color getColorNe(){
        return this.getPoint().getColors()[1];
    }
    public Color getColorSe(){
        return this.getPoint().getColors()[2];
    }


    public String getColorOString() {
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

    public boolean is_empty(){
        if ((this.ouest == null )&&(this.nordEst == null)&&(this.sudEst == null)){
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * Recherche le noeud auquel appartient un point donné.
     
     */
	/*
	Pire cas : O(h(T))
	*/
    public Dual searchTTree(Point pointEnfant) {
        Point pointParent = this.getPoint();

        int X_parent = pointParent.getX();
        int Y_parent = pointParent.getY();

        int X_enfant = pointEnfant.getX();
        int Y_enfant = pointEnfant.getY();

        if (X_enfant <= X_parent) {
            if (this.ouest != null) {
                if (this.ouest.getPoint().equals(pointEnfant)) {
                    return new Dual(this, "ouest");
                } else {
                    return this.getOuest().searchTTree(pointEnfant);
                }
            } else {
                return new Dual(this, "ouest");
            }
        } else if (X_enfant > X_parent && Y_enfant <= Y_parent) {
            if (this.nordEst != null) {
                if (this.nordEst.getPoint().equals(pointEnfant)) {
                    return new Dual(this, "nordEst");
                } else {
                    return this.getNordEst().searchTTree(pointEnfant);
                }
            } else {
                return new Dual(this, "nordEst");
            }
        } else if (X_enfant > X_parent && Y_enfant > Y_parent) {
            if (this.sudEst != null) {
                if (this.sudEst.getPoint().equals(pointEnfant)) {
                    return new Dual(this, "sudEst");
                } else {
                    return this.getSudEst().searchTTree(pointEnfant);
                }
            } else {
                return new Dual(this, "sudEst");
            }
        } else {
            throw new IllegalArgumentException("Région non trouvée. Vérifiez les coordonnées du point.");
        }
    }

    /*
     * Ajoute un point au ternaire tree.
     	Pire cas : O(h(T))
     */
    public void addTTree(Point p) {
        Dual region_recherche = searchTTree(p);

        if (region_recherche.region.equals("ouest")){
            region_recherche.ternaire.ouest = new TernaireTree(p);
        }else if (region_recherche.region.equals("nordEst")){
            region_recherche.ternaire.nordEst = new TernaireTree(p);
        }else if (region_recherche.region.equals("sudEst")){
            region_recherche.ternaire.sudEst = new TernaireTree(p);
        }else {
            throw new IllegalArgumentException(" Erreur inconnue merci de prendre contact avec les programmeurs");
        }
    }

    /*
     * Construit le ternaire tree entier à partir d'un tableau de points.
     	Pire cas : O(h(T)*n) ou n est le nombre de points
     */
    public void buildTTree(Point[] points) {
        for (int k = 1; k < points.length; k++) {
            this.addTTree(points[k]);
        }
    }

	/*
	Pire cas : O(n)
	*/
    public void printBorderVertical(Image img, int border,int Xmin,int Ymin, int Xmax, int Ymax){
        //img.setRectangle(Xmin,Ymin,Xmax,Ymax+border,Color.BLACK);
        img.setRectangle(Xmin,Ymin,Xmax+border,Ymax,Color.BLACK);


        if (this.ouest != null){
            this.ouest.printBorderVertical(img,border,this.ouest.getPoint().getX(),Ymin,this.ouest.getPoint().getX(),Ymax);
        }

        if (this.nordEst != null){
            this.nordEst.printBorderVertical(img,border,this.nordEst.getPoint().getX(),Ymin,this.nordEst.getPoint().getX(),this.getPoint().getY());
        }

        if (this.sudEst != null){
            this.sudEst.printBorderVertical(img,border,this.sudEst.getPoint().getX(),this.getPoint().getY(),this.sudEst.getPoint().getX(),Ymax);
        } 

    }

	/*
	Pire cas : O(n)
	*/
    public void printBorderHorizontal(Image img, int border,int Xmin,int Ymin, int Xmax, int Ymax){
        if (this == null){
            return;
        }else { 
            img.setRectangle(Xmin,Ymin,Xmax,Ymax+border,Color.BLACK);
            if (this.ouest != null){
                this.ouest.printBorderHorizontal(img,border,this.ouest.getPoint().getX(),this.ouest.getPoint().getY(),this.getPoint().getX(),this.ouest.getPoint().getY()); 
            }

            if (this.nordEst != null){
                this.nordEst.printBorderHorizontal(img,border,this.nordEst.getPoint().getX(),this.nordEst.getPoint().getY(),Xmax,this.nordEst.getPoint().getY());
            }

            if (this.sudEst != null){
                this.sudEst.printBorderHorizontal(img,border,this.sudEst.getPoint().getX(),this.sudEst.getPoint().getY(),Xmax,this.sudEst.getPoint().getY());
            }
        }
    
    }

	/*
	Pire cas : O(n)
	*/
    public void toImageEncaps(String filename,Image img,int Xmin, int Ymin, int Xmax, int Ymax){
        if (this == null){
            return;
        }else { 
            img.setRectangle(Xmin, Ymin, Xmax, Ymax, getColorO());
            img.setRectangle(this.getPoint().getX(),Ymin,Xmax, this.getPoint().getY(), getColorNe());
            img.setRectangle(this.getPoint().getX(),this.getPoint().getY(), Xmax, Ymax, getColorSe());
           
            if (this.ouest != null){
                this.ouest.toImageEncaps(filename, img, Xmin, Ymin, this.getPoint().getX(), Ymax);
            }

            if (this.nordEst != null){
                this.nordEst.toImageEncaps(filename, img, this.getPoint().getX(), Ymin, Xmax, this.getPoint().getY());
            }

            if (this.sudEst != null){
                this.sudEst.toImageEncaps(filename, img, this.getPoint().getX(), this.getPoint().getY(), Xmax, Ymax);
            }         
        }
 
    }
    /*
     * Génère une image a partir du quadtree
     * Attention 0,0 = haut gauche
     */
     	/*
	Pire cas : O(n)
	*/
    public void toImage(String filename,Image img, int border){
      toImageEncaps(filename, img, 0, 0, img.width(), img.height());
      printBorderVertical(img,  border, this.getPoint().getX(),0,                       this.getPoint().getX(), img.width()); 
      printBorderHorizontal(img,border, this.getPoint().getX(),this.getPoint().getY(),  img.width(),this.getPoint().getY()); 
    }


    /*
     * Représentation textuelle de l'arbre (parcours symétrique).
     */
     	/*
	Pire cas : O(n)
	*/
    public String toText(){
        /*
        * Si c'est une feuille j'écris la couleur
        * Sinon j'ouvre une parenthèse et je descends de 1
        */
        String s1 ="";
        if (this.getOuest()==null){
            s1 +=this.getColorOString();
        } else {
            s1+="(";
            s1+=this.getOuest().toText();
            s1+=")";
        }
        if (this.getNordEst()==null){
            s1 +=this.getColorNeString();
        }else {
            s1+="(";
            s1+=this.getNordEst().toText();
            s1+=")";
        }
        if (this.getSudEst()==null){
            s1 +=this.getColorSeString();

        }else {
            s1+="(";            

            s1+=this.getSudEst().toText();
            s1+=")";
        }
        return s1;
    }

	/*
	Pire cas : O(h(T))
	*/
    public void reColor(Point p, Color color){
        // appelle search colors
        // modifie la colors
        Dual dual = searchTTree(p);

        if (dual.region=="ouest"){

            dual.ternaire.getPoint().setColor(color,0);
        }else if (dual.region=="nordEst"){
            dual.ternaire.getPoint().setColor(color,1);
        }
        else if (dual.region=="sudEst"){
            dual.ternaire.getPoint().setColor(color,2);

        }else {
            throw new IllegalArgumentException(" Erreur inconnue merci de prendre contact avec les programmeurs");
        }
        compressTTree(dual);
    }

	/*
	Pire cas : O(h(T))
	*/
    public void compressTTree(Dual dual){
         if (dual.ternaire.is_empty()){
            if (dual.ternaire.getColorO().equals(dual.ternaire.getColorNe()) &&
                dual.ternaire.getColorSe().equals(dual.ternaire.getColorO())) {
                System.out.println("true");

                Dual parent = searchTTree(dual.ternaire.getPoint());
                System.out.println("parent "+ parent.ternaire.getPoint().toString());
                System.out.println("parent "+ parent.region);
                if (parent.region.equals("ouest")){
                    parent.ternaire.ouest = null;
                    parent.ternaire.getPoint().setColor(dual.ternaire.getColorO(), 0);
                }
                if (parent.region.equals("nordEst")){
                    parent.ternaire.nordEst = null;
                    parent.ternaire.getPoint().setColor(dual.ternaire.getColorO(), 1);
                }
                if (parent.region.equals("sudEst")){
                    parent.ternaire.sudEst = null;
                    parent.ternaire.getPoint().setColor(dual.ternaire.getColorO(), 2);
                }
            }         
        }
    }
}
