package misc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.RGB;

/**
 * A class that represents reading a JPG file.
 */
public class JPGRead extends FileRead {
  public JPGRead(String fileName) {
    super(fileName);
  }

  /**
   * A method that reads the JPG file.
   * @throws IOException Thrown when file doesn't exist.
   */
  public void readFile() throws IOException {
    BufferedImage image = ImageIO.read(new File(this.fileName));
    this.height = image.getHeight();
    this.width = image.getWidth();
    RGB[][] pixels = new RGB[this.height][this.width];
    for (int x = 0; x < this.width; x++) {
      for (int y = 0; y < this.height; y++) {
        Color tempColor = new Color(image.getRGB(x, y));
        pixels[y][x] = new RGB(tempColor.getRed(), tempColor.getGreen(), tempColor.getBlue(),
              255);
      }
    }
    this.pixels = pixels;
  }
}
