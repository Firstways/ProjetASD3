public class Ternaire {

    public Ternaire left;
    public Ternaire hr;
    public Ternaire br ;
    public Point point;

    

    public Ternaire(Point p){
        left = null;
        hr= null;
        br = null;
        point = p;

    }

    public Ternaire(Ternaire left, Ternaire hr, Ternaire br,Point p){
        left = left;
        hr= hr;
        br = br;
        point = p;

    }

    private boolean is_empty(){
        if (( left ==null )&&( hr == null )&& (br== null)){
            return true;
        } else {
            return false;
        }
    }

    public Dual searhTernaire(Point p){

        
    }
}
