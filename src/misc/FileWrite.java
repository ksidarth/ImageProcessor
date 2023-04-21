package misc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.RGB;

/**
 * A class that represents writing a general file (png or jpg).
 */
public class FileWrite {
  protected String fileName;
  protected int height;
  protected int width;
  protected RGB[][] image;

  /**
   * Initializes the class FileWrite.
   * @param fileName The name of the file.
   * @param height The height of the project.
   * @param width The width of the project.
   * @param image The 2D array representing the image.
   */
  public FileWrite(String fileName, int height, int width, RGB[][] image) {
    this.fileName = fileName;
    this.height = height;
    this.width = width;
    this.image = image;
  }

  /**
   * The method that writes the file.
   * @param extension The extension of the file (jpg or png).
   * @throws IOException Throws IOException when directory is not found.
   */
  public void writeFile(String extension) throws IOException {
    if (!extension.equals("png") && !extension.equals("jpg")) {
      throw new IllegalArgumentException("File type is invalid.");
    }
    BufferedImage fileToSave = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        RGB currPixel = image[i][j];
        if (extension.equals("png")) {
          int argb = currPixel.getAlpha() << 24;
          argb |= currPixel.getRed() << 16;
          argb |= currPixel.getGreen() << 8;
          argb |= currPixel.getBlue();
          fileToSave.setRGB(j, i, argb);
        }
        if (extension.equals("jpg")) {
          Color tempColor = new Color(currPixel.getRed(), currPixel.getGreen(),
                  currPixel.getBlue());
          fileToSave.setRGB(j, i, tempColor.getRGB());
        }
      }
    }
    File savedFile = new File(fileName);
    ImageIO.write(fileToSave, extension, savedFile);
  }
}
