import java.io.*;
import java.util.*;

public class File {

    private String relative_file_path;

    public File(String file_path){
        relative_file_path= file_path;
    }

    public void readFile(){
        try {
            // Ouvrir le fichier
            BufferedReader reader = new BufferedReader(new FileReader(relative_file_path));
            String line;
            List<String> dataList = new ArrayList<>();

            // Lire chaque ligne du fichier et l'ajouter à la liste
            while ((line = reader.readLine()) != null) {
                dataList.add(line);
            }
            reader.close();

            // Convertir la liste en un tableau
            String[] dataArray = dataList.toArray(new String[0]);

            // Afficher les données du tableau
            for (String data : dataArray) {
                System.out.println(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     
    }
          
        
    
}
