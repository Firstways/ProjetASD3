import java.awt.*;

public class Point {
    private int x,y;
    private Color[] colors;
    private Color color;

    public Point(int x , int y ){
        this.x = x ;
        this.y = y ;
    }
      public Point(int x , int y, Color col ){
        this.x = x ;
        this.y = y ;
        this.color = col ;
    }
    public Point(int x , int y, Color[] colors){
        this.x = x ;
        this.y = y ;
        this.colors = new Color[4];
        if (colors.length != 4){
            throw new IllegalArgumentException("Le nombre de couleurs doit être de 4");
        }else{
            for (int i = 0; i < colors.length ; i++){
                this.colors[i] = colors[i];
            }
        }
    }

    public Color getColor(){
        return this.color;
    }
    public int getX(){
        return this.x;

    }

    public int getY(){
        return this.y ;
    }


    public void setY(int y){
        this.y = y ;
    }

    public Color[] getColors(){
        return this.colors;
    }
    public void setColor(Color color,int region){
        colors[region]=color;
    }



    public boolean equals(Point point_recherche){
        if ((this.getX()==point_recherche.getX()) && (this.getY()==point_recherche.getY())){
            return true ;
        }else {
            return false;
        }
    }

    public String toString(){

        return ("X :"+ this.x+" | Y :"+ this.y);

    }
}
