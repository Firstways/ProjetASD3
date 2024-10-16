import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Image img = new Image(1000, 1000);
        Color col = Color.BLUE;
        img.setRectangle(0, 1000, 0, 1000,col );
        img.save("square");
    }
}
