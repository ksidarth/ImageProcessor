package misc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.RGB;

/**
 * A class that represents the contents of a png file.
 */
public class PNGRead extends FileRead {
  public PNGRead(String fileName) {
    super(fileName);
  }

  /**
   * The method that reads the ppm file.
   * @throws IOException Thrown when file doesn't exist.
   */
  public void readFile() throws IOException {
    BufferedImage image = ImageIO.read(new File(this.fileName));
    this.height = image.getHeight();
    this.width = image.getWidth();
    RGB[][] pixels = new RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        Color tempColor = new Color(image.getRGB(j, i));
        pixels[i][j] = new RGB(tempColor.getRed(), tempColor.getGreen(), tempColor.getBlue(),
                tempColor.getAlpha());
      }
    }
    this.pixels = pixels;
  }
}
