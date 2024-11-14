import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Donnees {

    // Les données d'entrée :
    private int tailleFenetre;
    private int nbPoints;
    private List<Point> points;
    private int tailleBordure;
    private int nbCouleurs;
    private List<Point> reColor;

    public Donnees() {
        points = new ArrayList<>();
        reColor = new ArrayList<>();
    }

    // Setters :
    public void setTailleFenetre(int tailleFenetre) {this.tailleFenetre = tailleFenetre;}
    public void setNbPoints(int nbPoints) {this.nbPoints = nbPoints;}
    public void setTailleBordure(int tailleBordure) {this.tailleBordure = tailleBordure;}
    public void setNbCouleurs(int nbCouleurs) {this.nbCouleurs = nbCouleurs;}

    public void addPoint(Point point) { //ajoute un nouveau point à la liste de points
        points.add(point);
    }

    public void addReColor(Point point) { //ajoute une nouvelle couleur à la liste de couleurs
        couleurs.add(couleur);
    }

    // Getters :
    public int getTailleFenetre(){return tailleFenetre;}
    public int getNBPoints(){return nbPoints;}
    public List<Point> getPoints(){return points;}
    public int getTailleBordure(){return tailleBordure;}
    public int getNBCouleurs(){return nbCouleurs;}
    public List<Point> getReColor(){return reColor;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Taille fenêtre: " + tailleFenetre + "\n");
        sb.append("Nombre de points: " + nbPoints + "\n");
        for (Point point : points) {
            sb.append(point + "\n");
        }
        sb.append("Taille bordure: " + tailleBordure + "\n");
        sb.append("Nombre de couleurs: " + nbCouleurs + "\n");
        for (Point p : reColor) {
            sb.append(reColor.toString() + "\n");
        }
        return sb.toString();
    }
}
