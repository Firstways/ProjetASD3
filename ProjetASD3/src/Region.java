public class Region{


    private Point pointHG;
    private Point pointBD;


    public Region(Point hg, Point bd){
        this.pointHG =hg ;
        this.pointBD=bd;

    }

    public Point getHG(){

        return pointHG;
    }
    public Point getBD(){

        return pointBD;
    }

}