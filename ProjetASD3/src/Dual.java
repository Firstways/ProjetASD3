public class Dual {
    public Quadtree quad;
    public String region;
    public TernaireTree ternaire;

    public Dual (Quadtree quad, String r){
        region = r;
        this.quad = quad;
    }

    public Dual (TernaireTree ternaire, String r){
        region = r;
        this.ternaire = ternaire;
    }
}
