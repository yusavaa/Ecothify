package util;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OpenImage {

    public static void setImage(ImageView imgView, String imgPath) {
        Path path = Paths.get(imgPath);
        try {
            BufferedImage bufferedImage = ImageIO.read(path.toFile());
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgView.setImage(image);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
