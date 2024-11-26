import java.io.*;
import java.util.*;
import java.awt.Color;

public class MyFile {

    private String relative_file_path;
    List<String> fileLine;

    // Les données d'entrée :
    private int windowSize;
    private Point[] points;
    private int borderSize;
    private int nbRecolor;
    private Point[] reColor;

   

    public MyFile(String file_path){
        relative_file_path= file_path;
        fileLine = new ArrayList<>();
        
    }

    public void initNbPoint(int nb){
        this.points = new Point[nb];
    }
    public void initRecolor(int nb){
        this.reColor = new Point[nb];
    }

    public Color parseColor(String s){
        if (s.equals("R")){
            return Color.RED;
        }
        if (s.equals("J")){
            return Color.YELLOW;
        }
        if (s.equals("B")){
            return Color.BLUE;
        }
        if (s.equals("N")){
            return Color.BLACK;
        }
        else{
            return Color.GRAY;
        }
    }

    public Point[] getPoints(){
        int width = getImageSize();
        for (Point p : points){
            p.setY(width-p.getY());
        }
        return points;
    }

    public int getBorderSize(){return borderSize;}
    public int getImageSize(){return windowSize;}
    public Point[] getRecolors(){return reColor;}

    public void readFile() throws IOException{
        try {
            // Ouvrir le fichier
            BufferedReader reader = new BufferedReader(new FileReader(relative_file_path));
            String line;
            

            // Lire chaque ligne du fichier et l'ajouter à la liste
            while ((line = reader.readLine()) != null) {
                fileLine.add(line.trim()); // enlever les espaces inutiles
            }
            reader.close();

            String str = fileLine.get(0);
            windowSize = Integer.parseInt(str);
            
            //System.out.println(windowSize);

            str = fileLine.get(1);
            int nbPoints = Integer.parseInt(str);
            
            //System.out.println(nbPoints);
            initNbPoint(nbPoints);
            for (int i = 2 ; i<nbPoints+2;i=i+1){
                str = fileLine.get(i);
                String[] pointData = str.split(",\\s*"); 

                int x = Integer.parseInt(pointData[0]);
                int y = Integer.parseInt(pointData[1]);
                Color c1 = parseColor(pointData[2]);
                Color c2 = parseColor(pointData[3]);
                Color c3 = parseColor(pointData[4]);

                Color c4 = parseColor(pointData[5]);
                Color[] c = new Color[]{c1, c2, c3, c4};
                points[i-2] = new Point(x,y,c);
            }

                        // Lecture de la taille de la bordure
            borderSize = (Integer.parseInt(fileLine.get(2+nbPoints)));

            // Lecture du nombre de couleurs
            nbRecolor = (Integer.parseInt(fileLine.get(3+nbPoints)));
            initRecolor(nbRecolor);
            for (int i = 0; i < nbRecolor; i++) {
                String[] colorData = fileLine.get(4+nbPoints+i).split(",\\s*");
                int x = Integer.parseInt(colorData[0]);
                int y = Integer.parseInt(colorData[1]);
                Color color = parseColor(colorData[2]);
                reColor[i]= new Point(x,y,color);;
            }


        } catch (IOException e) {
            // Gérer les exceptions liées à l'ouverture ou à la lecture du fichier
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            throw e; // Réémettre l'exception pour que l'appelant soit informé
    
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion de chaîne en nombre
            System.err.println("Erreur de format dans le fichier : " + e.getMessage());
            throw new IOException("Le fichier contient des données mal formatées.", e);
    
        } finally {
           
        }
    }
}
