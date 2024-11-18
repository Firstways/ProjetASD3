
@SuppressWarnings("unused")

public final class TernaireTree {

    private TernaireTree Ouest;
    private TernaireTree NordEst;
    private TernaireTree SudEst;

    private Point pt;
    private boolean isEmpty;
    private String region;

    public TernaireTree(TernaireTree ouest, TernaireTree nordEst, TernaireTree sudEst, Point pt) {
        this.Ouest = ouest;
        this.NordEst = nordEst;
        this.SudEst = sudEst;
        this.pt = pt;
        this.isEmpty = this.isEmpty();
        this.region = null;
    }

    public TernaireTree(Point pt) {
        this.Ouest = null;
        this.NordEst = null;
        this.SudEst = null;
        this.pt = pt;
        this.isEmpty = true;
    }

    public TernaireTree getOuest() {
        return this.Ouest;
    }

    public TernaireTree getNordEst() {
        return this.NordEst;
    }

    public TernaireTree getSudEst() {
        return this.SudEst;
    }

    public Point getpt() {
        return this.pt;
    }

    public boolean isEmpty() {
        return this.Ouest == null && this.NordEst == null && this.SudEst == null;
    }

    /*
     * Recherche le noeud auquel appartient un pt donné.
     */
    public Dual searchTTree(Point ptEnfant) {
        Point ptParent = this.getpt();

        int X_parent = ptParent.getX();
        int Y_parent = ptParent.getY();

        int X_enfant = ptEnfant.getX();
        int Y_enfant = ptEnfant.getY();

        System.out.println("Coordonnées du pt parent : (" + X_parent + ", " + Y_parent + ")");
        System.out.println("Coordonnées du pt enfant : (" + X_enfant + ", " + Y_enfant + ")");

        if (X_enfant <= X_parent) {
            if (this.Ouest != null) {
                if (this.Ouest.getpt().equals(ptEnfant)) {
                    return new Dual(this, "Ouest");
                } else {
                    return this.getOuest().searchTTree(ptEnfant);
                }
            } else {
                System.out.println("Ouest");
                return new Dual(this, "Ouest");
            }
        } else if (X_enfant > X_parent && Y_enfant <= Y_parent) {
            if (this.NordEst != null) {
                if (this.NordEst.getpt().equals(ptEnfant)) {
                    return new Dual(this, "NordEst");
                } else {
                    return this.getNordEst().searchTTree(ptEnfant);
                }
            } else {
                System.out.println("NordEst");
                return new Dual(this, "NordEst");
            }
        } else if (X_enfant > X_parent && Y_enfant > Y_parent) {
            if (this.SudEst != null) {
                if (this.SudEst.getpt().equals(ptEnfant)) {
                    return new Dual(this, "SudEst");
                } else {
                    return this.getSudEst().searchTTree(ptEnfant);
                }
            } else {
                System.out.println("SudEst");
                return new Dual(this, "SudEst");
            }
        } else {
            throw new IllegalArgumentException("Région non trouvée. Vérifiez les coordonnées du pt.");
        }
    }

    /*
     * Ajoute un pt au ternaire tree.
     */
    public void addTTree(Point p) {
        Dual regionRecherche = searchTTree(p);

        switch (regionRecherche.region) {
            case "Ouest":
                regionRecherche.ternaire.Ouest = new TernaireTree(p);
                break;
            case "NordEst":
                regionRecherche.ternaire.NordEst = new TernaireTree(p);
                break;
            case "SudEst":
                regionRecherche.ternaire.SudEst = new TernaireTree(p);
                break;
            default:
                throw new IllegalArgumentException("Erreur inconnue, contactez les programmeurs.");
        }
    }

    /*
     * Construit le ternaire tree entier à partir d'un tableau de pts.
     */
    public void buildTTree(Point[] pts) {
        for (int k = 1; k < pts.length; k++) {
            System.out.println("pt " + k);
            this.addTTree(pts[k]);
        }
    }

    /*
     * Représentation textuelle de l'arbre (parcours symétrique).
     */
    public void toText() {
        if (this.getOuest() == null) {
            System.out.print("OUEST(" + this.getpt().getX() + "," + this.getpt().getY() + "), ");
        }
        if (this.getNordEst() == null) {
            System.out.print("NORDEST(" + this.getpt().getX() + "," + this.getpt().getY() + "), ");
        }
        if (this.getSudEst() == null) {
            System.out.print("SUDEST(" + this.getpt().getX() + "," + this.getpt().getY() + "), ");
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
}
