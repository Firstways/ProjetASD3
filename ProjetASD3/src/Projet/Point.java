package Projet;
import java.awt.*;

public class Point {
    private int x,y;
    private Color[] colors;

    public Point(int x , int y ){
        this.x = x ;
        this.y = y ;
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

    public int getX(){
        return this.x;

    }

    public int getY(){
        return this.y ;
    }

    public Color[] getColors(){
        return this.colors;
    }
    public void setColors(Color color,String region){
        if (region == "No"){
             this.colors[0]= color;
        }else if (region == "Ne"){
             this.colors[1]= color;

        }else if (region == "Se"){
             this.colors[2]= color;

        }else if (region == "So"){
             this.colors[3]= color;
        }
        else {
             throw new IllegalArgumentException("Region: No, Ne, Se,So");
        }
    }

    public void setColors(Color[] colors){
        this.colors=colors;
    }


    public boolean equals(Point point_recherche){
        if ((this.getX()==point_recherche.getX()) && (this.getY()==point_recherche.getY())){
            return true ;
        }else {
            return false;
        }
    }
}
