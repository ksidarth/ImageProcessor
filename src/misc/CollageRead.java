package misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Layer;
import model.RGB;

/**
 * A class that takes in a collage file and is able to read it.
 */
public class CollageRead {
  private String fileName;
  private int width;
  private int height;
  private int maxValue;
  private RGB[][] canvas;
  private ArrayList<Layer> layers;

  /**
   * Reads in a .collage file. Made for loading in existing projects.
   * @param fileName - the name of the file to load in.
   * @throws IllegalArgumentException if file is not a collage file.
   */
  public CollageRead(String fileName) {
    if (!fileName.endsWith(".collage")) {
      throw new IllegalArgumentException("File is not .collage");
    }
    this.fileName = fileName;
    this.width = 0;
    this.height = 0;
    this.maxValue = 0;
    this.layers = new ArrayList<>();
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  public ArrayList<Layer> getLayers() {
    return this.layers;
  }

  /**
   * For all the layers in the collage file, adds it to the canvas of the model.
   * @return A 2D RGB array.
   */
  public RGB[][] addLayersToCanvas() {
    this.canvas = new RGB[canvas.length][canvas[0].length];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.canvas[i][j] = new RGB(255, 255, 255, 255);
      }
    }
    for (int i = 0; i < this.layers.size(); i++) {
      this.canvas = this.layers.get(i).addToCanvas(this.canvas);
    }
    return this.canvas;
  }

  /**
   * Reads in a collage file.
   * @throws IllegalArgumentException if the file does not exist/has invalid construction.
   */
  public void readCollage() throws IllegalArgumentException {
    File collageTxt = new File(this.fileName);
    try {
      Scanner sc = new Scanner(collageTxt);
      String token;
      token = sc.next();
      if (!token.equals("C1")) {
        throw new IllegalArgumentException("Invalid Collage file: plain RAW file should begin " +
                "with C1");
      }
      this.height = sc.nextInt();
      this.width = sc.nextInt();
      RGB[][] pixels = new RGB[this.height][this.width];
      this.canvas = new RGB[this.height][this.width];
      this.maxValue = sc.nextInt();
      while (sc.hasNext()) {
        String layerName = sc.next();
        String layerFilter = sc.next();
        for (int i = 0; i < this.height; i++) {
          for (int j = 0; j < this.width; j++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            pixels[i][j] = new RGB(r, g, b);
          }
        }
        Layer layer_temp = new Layer(layerName, pixels, layerFilter);
        this.layers.add(layer_temp);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Given file does not exist.");
    }
  }
}
