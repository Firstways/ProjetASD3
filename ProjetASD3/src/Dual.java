

public class Dual {
    public Quadtree quad;
    public String region;
    public TernaireTree ternaire;
    public Dual (Quadtree quad, String r){
        this.region = r;
        this.quad = quad;
    }
    public Dual (TernaireTree ternaire, String r){
        this.region = r;
        this.ternaire = ternaire;
    }
}
