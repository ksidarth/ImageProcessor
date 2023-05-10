package model;

import java.io.IOException;

import misc.FileRead;
import misc.JPGRead;
import misc.PNGRead;
import misc.PPMRead;

/**
 * A class that represents the layer of the collage.
 */
public class Layer {
  private String name;
  private RGB[][] image;
  private RGB[][] pixels;
  private Filter filter;

  /**
   * Initializes a Layer class.
   * @param name The name of the Layer.
   * @param height The height of the Layer.
   * @param width The width of the Layer.
   */
  public Layer(String name, int height, int width) {
    this.name = name;
    this.image = new model.RGB[height][width];
    this.pixels = new model.RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.image[i][j] = new model.RGB(255, 255, 255, 0);
        this.pixels[i][j] = new model.RGB(255, 255, 255, 0);
      }
    }
    this.filter = model.Filter.NORMAL;
  }

  /**
   * Makes layer when given a collage file.
   * @param name Name of layer.
   * @param pixels 2D array of pixels.
   * @param filterName The filter of layer.
   */
  public Layer(String name, model.RGB[][] pixels, String filterName) {
    this.name = name;
    this.image = new model.RGB[pixels.length][pixels[0].length];
    this.pixels = new model.RGB[pixels.length][pixels[0].length];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        this.image[i][j] = pixels[i][j];
        this.pixels[i][j] = pixels[i][j];
      }
    }
    this.filter = model.Filter.findByAbbr(filterName);
  }

  public model.RGB[][] getImage() {
    return this.image;
  }

  public model.RGB[][] getArray() {
    return this.pixels;
  }

  public String getLayerName() {
    return this.name;
  }

  /**
   * Checks if layer is equal to name of layer.
   * @param object layer name.
   * @return true or false.
   */
  public boolean layerEquals(Object object) {
    if (!(object instanceof Layer)) {
      return false;
    }
    if (object == this) {
      return true;
    }
    else {
      Layer temp = (Layer) object;
      return this.name.equals(temp.name);
    }
  }

  /**
   * Adds the layer to the model's canvas.
   * @param canvas 2D array of pixels.
   * @return 2D array of pixels.
   */
  public model.RGB[][] addToCanvas(model.RGB[][] canvas) {
    model.RGB[][] newCanvas = new model.RGB[canvas.length][canvas[0].length];
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        newCanvas[i][j] = this.pixels[i][j].imageWithBackground(canvas[i][j]);
      }
    }
    return newCanvas;
  }

  /**
   * Copies the original pixels (normal filter) to the field of pixels.
   */
  public void copyImage() {
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[0].length; j++) {
        int originalRed = this.image[i][j].getRed();
        int originalGreen = this.image[i][j].getGreen();
        int originalBlue = this.image[i][j].getBlue();
        int originalAlpha = this.image[i][j].getAlpha();
        this.pixels[i][j] = new model.RGB(originalRed, originalGreen, originalBlue, originalAlpha);
      }
    }
  }

  /**
   * Adds the image to layer.
   * @param x the x-offset.
   * @param y the y-offset.
   * @param fileName the name of the image.
   * @throws IOException Thrown when file is corrupted.
   */
  public void addImage(int x, int y, String fileName) throws IOException {
    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
    FileRead image;
    switch (fileExtension) {
      case "png": {
        image = new PNGRead(fileName);
        break;
      }
      case "jpg": {
        image = new JPGRead(fileName);
        break;
      }
      case "ppm": {
        image = new PPMRead(fileName);
        break;
      }
      default:
        throw new IllegalArgumentException("Given file is not ppm, jpg, or png.");
    }
    try{
      image.readFile();
    } catch (IOException e){
      throw new IOException("Image could not be read");
    }
    model.RGB[][] imagePixels = image.getArray();
    for (int i = y; i < this.pixels.length; i++) {
      for (int j = x; j < this.pixels[0].length; j++) {
        if ((i - y) < imagePixels.length && (j - x) < imagePixels[i].length) {
          this.image[i][j] = imagePixels[i - y][j - x].imageWithBackground(this.image[i][j]);
          this.pixels[i][j] = imagePixels[i - y][j - x].imageWithBackground(this.pixels[i][j]);
        }
        else {
          break;
        }
      }
    }
  }

  public void setFilter(Filter filter) {
    this.filter = filter;
  }

  /**
   * Edits the layer.
   * @param rVal The new red val.
   * @param gVal The new green val.
   * @param bVal The new blue val.
   */
  public void editLayer(int rVal, int gVal, int bVal) {
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        this.pixels[i][j].editRGB(rVal, gVal, bVal);
      }
    }
  }

  /**
   * Edits the intensity.
   * @param value Value that needs to be equal to.
   */
  public void editLayerIntensity(int value) {
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        this.pixels[i][j].editIntensity(value);
      }
    }
  }

  /**
   * Blends the layer to beneath.
   * @param pixels The beneath pixels.
   */
  public void blendLayer(model.RGB[][] pixels) {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        this.pixels[i][j] = this.pixels[i][j].blendWithBackground(pixels[i][j]);
      }
    }
  }

  /**
   * Multiplies values of layer.
   * @param pixels The beneath pixels.
   */
  public void multiply(model.RGB[][] pixels) {
    model.HSL[][] currentLayer = this.getHSLArray(this.getArray());
    model.HSL[][] convertedLayers = this.getHSLArray(pixels);
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        currentLayer[i][j] = currentLayer[i][j].multiply(convertedLayers[i][j]);
      }
    }
    this.pixels = this.getRGBArray(currentLayer);
  }

  /**
   * Screens the layer.
   * @param pixels The beneath pixels.
   */
  public void screen(model.RGB[][] pixels) {
    model.HSL[][] currentLayer = this.getHSLArray(this.getArray());
    model.HSL[][] convertedLayers = this.getHSLArray(pixels);
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        currentLayer[i][j] = currentLayer[i][j].screen(convertedLayers[i][j]);
      }
    }
    this.pixels = this.getRGBArray(currentLayer);
  }

  /**
   * Gets a 2D array of HSL pixels.
   * @param pixels The current RGB pixels.
   * @return HSL pixels.
   */
  public model.HSL[][] getHSLArray(model.RGB[][] pixels) {
    model.HSL[][] hslArray = new model.HSL[pixels.length][pixels[0].length];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        hslArray[i][j] = pixels[i][j].convertRGBtoHSL();
      }
    }
    return hslArray;
  }

  /**
   * Gets a 2D array of RGB pixels.
   * @param pixels Current HSL pixels.
   * @return RGB pixels.
   */
  public model.RGB[][] getRGBArray(HSL[][] pixels) {
    model.RGB[][] rgbArray = new model.RGB[pixels.length][pixels[0].length];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        rgbArray[i][j] = pixels[i][j].convertHSLtoRGB();
      }
    }
    return rgbArray;
  }

  /**
   * Applies filter to layer.
   * @param pixels Previous layers.
   */
  public void applyFilter(model.RGB[][] pixels) {
    this.copyImage();
    switch (this.filter) {
      case NORMAL: {
        this.editLayer(1, 1, 1);
        break;
      }
      case RED_COMPONENT: {
        this.editLayer(1, 0, 0);
        break;
      }
      case GREEN_COMPONENT: {
        this.editLayer(0, 1, 0);
        break;
      }
      case BLUE_COMPONENT: {
        this.editLayer(0, 0, 1);
        break;
      }
      case DARKEN_VALUE: {
        this.editLayerIntensity(0);
        break;
      }
      case BRIGHTEN_VALUE: {
        this.editLayerIntensity(255);
        break;
      }
      case BLEND_BACKGROUND: {
        this.blendLayer(pixels);
        break;
      }
      case MULTIPLY: {
        this.multiply(pixels);
        break;
      }
      case SCREEN: {
        this.screen(pixels);
        break;
      }
      default: {
        break;
      }
    }
  }

  /**
   * Writes the layer into a string.
   * @return String version of layer.
   */
  public String toString() {
    String layerStr = this.name + " " + this.filter.getRepresentation() + "\n";
    for (model.RGB[] i : this.pixels) {
      for (RGB j : i) {
        layerStr = layerStr + j.toString();
        layerStr = layerStr + " ";
      }
      layerStr = layerStr + "\n";
    }
    return layerStr;
  }
}
