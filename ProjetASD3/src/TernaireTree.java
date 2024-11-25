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
    public Dual searchTTree(Point pointEnfant) {
        Point pointParent = this.getPoint();

        int X_parent = pointParent.getX();
        int Y_parent = pointParent.getY();

        int X_enfant = pointEnfant.getX();
        int Y_enfant = pointEnfant.getY();

        System.out.println("Coordonnées du point parent : (" + X_parent + ", " + Y_parent + ")");
        System.out.println("Coordonnées du point enfant : (" + X_enfant + ", " + Y_enfant + ")");

        if (X_enfant <= X_parent) {
            if (this.ouest != null) {
                if (this.ouest.getPoint().equals(pointEnfant)) {
                    return new Dual(this, "ouest");
                } else {
                    return this.getOuest().searchTTree(pointEnfant);
                }
            } else {
                System.out.println("ouest");
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
                System.out.println("nordEst");
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
                System.out.println("sudEst");
                return new Dual(this, "sudEst");
            }
        } else {
            throw new IllegalArgumentException("Région non trouvée. Vérifiez les coordonnées du point.");
        }
    }

    /*
     * Ajoute un point au ternaire tree.
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
     */
    public void buildTTree(Point[] points) {
        for (int k = 1; k < points.length; k++) {
            System.out.println("point " + k);
            this.addTTree(points[k]);
        }
    }


    public void toImageEncaps(String filename,Image img,int Xmin, int Ymin, int Xmax, int Ymax){
        if (this == null){
            return;
        }else { 
            img.setRectangle(Xmin, Ymin, this.getPoint().getX(), this.getPoint().getY(), getColorO());
            img.setRectangle( this.getPoint().getX(),Ymin,Xmax, this.getPoint().getY(), getColorNe());
            img.setRectangle(this.point.getX(),this.point.getY(), Xmax, Ymax, getColorSe());
           
            if (this.ouest != null){
                System.out.println("O");
                this.ouest.toImageEncaps(filename, img, Xmin, Ymin, this.getPoint().getX(), this.getPoint().getY());
            }

            if (this.nordEst != null){
                System.out.println("Ne");
                this.nordEst.toImageEncaps(filename, img, this.getPoint().getX(), Ymin, Xmax, this.getPoint().getY());
            }

            if (this.sudEst != null){
                System.out.println("Se");
                this.sudEst.toImageEncaps(filename, img, this.getPoint().getX(), this.getPoint().getY(), Xmax, Ymax);
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
     * Représentation textuelle de l'arbre (parcours symétrique).
     */
    public void toText() {
        if (this.getOuest() == null) {
            System.out.print("OUEST(" + this.getPoint().getX() + "," + this.getPoint().getY() + "), ");
        }
        if (this.getNordEst() == null) {
            System.out.print("NORDEST(" + this.getPoint().getX() + "," + this.getPoint().getY() + "), ");
        }
        if (this.getSudEst() == null) {
            System.out.print("SUDEST(" + this.getPoint().getX() + "," + this.getPoint().getY() + "), ");
        }

        if (this.getOuest() != null) {
            System.out.print("(");
            this.getOuest().toText();
            System.out.print(")");
        }
        if (this.getNordEst() != null) {
            System.out.print("(");
            this.getNordEst().toText();
            System.out.print(")");
        }
        if (this.getSudEst() != null) {
            System.out.print("(");
            this.getSudEst().toText();
            System.out.print(")");
        }
    }

    public void reColor(Point p, Color color){
        // appelle search colors
        // modifie la colors
        Dual dual = searchTTree(p);

        if (dual.region=="ouest"){
            this.getPoint().setColor(color,0);
        }else if (dual.region=="nordEst"){
            this.getPoint().setColor(color,1);

        }
        else if (dual.region=="sudEst"){
            this.getPoint().setColor(color,2);

        }else {
            throw new IllegalArgumentException(" Erreur inconnue merci de prendre contact avec les programmeurs");
        }
        compressTTree(dual);
    }

    public void compressTTree(Dual dual){
        Point p2 = this.getPoint();
        if (dual.region=="ouest"){
            if (dual.ternaire.is_empty()){
                if ((dual.ternaire.getColorO()==dual.ternaire.getColorNe())&&(dual.ternaire.getColorSe()==dual.ternaire.getColorO())){
                    dual.ternaire.ouest= null;
                }
            }
           
        }
        else if (dual.region=="nordEst"){
            if (dual.ternaire.is_empty()){
                if ((dual.ternaire.getColorO()==dual.ternaire.getColorNe())&&(dual.ternaire.getColorSe()==dual.ternaire.getColorO())){
                    dual.ternaire.nordEst= null;
                }
            }
           
        }else if (dual.region=="sudEst"){
            if (dual.ternaire.is_empty()){
                if ((dual.ternaire.getColorO()==dual.ternaire.getColorNe())&&(dual.ternaire.getColorSe()==dual.ternaire.getColorO())){
                    dual.ternaire.sudEst= null;
                }
            }
        }else {
            throw new IllegalArgumentException(" Erreur inconnue merci de prendre contact avec les programmeurs");
        }

    }
}
