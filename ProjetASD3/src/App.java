import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Image img = new Image(1000, 1000);
        img.setRectangle(200, 200, 1000, 1000, Color.getColor("RED"));
        img.save("square");
    }
}
