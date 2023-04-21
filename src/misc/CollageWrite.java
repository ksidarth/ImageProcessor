package misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Layer;

/**
 * A class that represents creating and writing a collage file.
 */
public class CollageWrite {
  private String fileName;
  private int height;
  private int width;
  private ArrayList<Layer> layers;

  /**
   * Initalizes CollageWrite.
   * @param fileName The name of the file that the user wants.
   * @param height The height of the project.
   * @param width The width of the project.
   * @param layers The list of layers in the project.
   */
  public CollageWrite(String fileName, int height, int width, ArrayList<Layer> layers) {
    this.fileName = fileName;
    this.height = height;
    this.width = width;
    this.layers = layers;
  }

  /**
   * A method that writes a collage file.
   */
  public void writeCollage() {
    File file = new File(this.fileName);
    try {
      FileWriter writer = new FileWriter(file, false);
      writer.write("C1\n" + this.height + " " + this.width + "\n" + 255 + "\n");
      for (Layer i : this.layers) {
        writer.write(i.toString());
        writer.write("\n");
      }
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("File to save is invalid");
    }
  }
}
