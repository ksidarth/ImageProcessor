package model;

import java.io.IOException;
import java.util.ArrayList;

import misc.CollageRead;
import misc.CollageWrite;
import misc.FileWrite;
import misc.PPMWrite;

/**
 * A class that represents the collage model. A model is represented as a boolean, height and width
 * of the current project, pixels on the project, and the layers of the project.
 */
public class CollageImageModel implements CollageModel {
  private boolean running;
  private int height;
  private int width;
  private model.RGB[][] canvas;
  private ArrayList<model.Layer> layers;

  /**
   * Initializes the class collage model.
   */
  public CollageImageModel() {
    this.running = false;
    this.height = 0;
    this.width = 0;
    this.layers = new ArrayList<>();
  }

  @Override
  public void newProject(int height, int width) {
    this.running = true;
    this.height = height;
    this.width = width;
    this.canvas = new model.RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.canvas[i][j] = new model.RGB(255, 255, 255, 255);
      }
    }
  }

  public model.RGB[][] getCanvas() {
    return this.canvas;
  }

  @Override
  public ArrayList<String> getLayerNames() {
    ArrayList<String> layerNames = new ArrayList<>();
    for (int i = 0; i < this.getLayers().size(); i++) {
      layerNames.add(this.getLayers().get(i).getLayerName());
    }
    return layerNames;
  }

  public ArrayList<model.Layer> getLayers() {
    return this.layers;
  }

  @Override
  public void loadProject(String fileName) {
    CollageRead collageFile = new CollageRead(fileName);
    collageFile.readCollage();
    this.height = collageFile.getHeight();
    this.width = collageFile.getWidth();
    this.canvas = new model.RGB[height][width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.canvas[i][j] = new model.RGB(255, 255, 255, 255);
      }
    }
    this.canvas = collageFile.addLayersToCanvas();
    this.layers = collageFile.getLayers();
    this.running = true;
  }

  @Override
  public void addLayer(String layerName) {
    model.Layer addLayer = new model.Layer(layerName, this.height, this.width);
    for (int i = 0; i < this.layers.size(); i++) {
      if (this.layers.get(i).layerEquals(addLayer)) {
        throw new IllegalArgumentException("Layer already exists.");
      }
    }
    this.layers.add(addLayer);
  }

  /**
   * Updates the canvas of the model.
   */
  public void updateCanvas() {
    for (int i = 0; i < this.layers.size(); i++) {
      this.canvas = this.layers.get(i).addToCanvas(this.canvas);
    }
  }

  /**
   * Returns the index of a layer given the layer's name.
   * @param layerName The name of the layer.
   * @return The index of the layer within the list.
   */
  public int getLayer(String layerName) {
    model.Layer layerTemp = new Layer(layerName, this.height, this.width);
    for (int i = 0; i < this.layers.size(); i++) {
      if (this.layers.get(i).layerEquals(layerTemp)) {
        return i;
      }
    }
    throw new IllegalArgumentException("Given layer doesn't exist.");
  }

  /**
   * Returns a 2D array containing the image beneath a selected layer.
   * @param layerIndex The layer that the user selected.
   * @return The image beneath the layer selected.
   */
  public model.RGB[][] getBeneathImage(int layerIndex) {
    model.RGB[][] tempCanvas = new model.RGB[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        tempCanvas[i][j] = new RGB(255, 255, 255);
      }
    }
    for (int i = 0; i < layerIndex; i++) {
      tempCanvas = this.layers.get(i).addToCanvas(tempCanvas);
    }
    return tempCanvas;
  }

  @Override
  public void addImageToLayer(String layerName, String imagePath, int x, int y)
          throws IOException, IllegalStateException {
    if (this.height == 0 || this.width == 0 || this.canvas == null) {
      throw new IllegalStateException("Project hasn't been created or loaded.");
    }
    if (this.layers == null) {
      throw new IllegalStateException("No layers have yet been created.");
    }
    else {
      int layerIndex = this.getLayer(layerName);
      this.layers.get(layerIndex).addImage(x, y, imagePath);
      this.layers.get(layerIndex).applyFilter(this.getBeneathImage(layerIndex));
      this.updateCanvas();
    }
  }

  @Override
  public model.Filter getFilter(String filterName) {
    if (model.Filter.findByAbbr(filterName) == null) {
      throw new IllegalArgumentException("Filter doesn't exist.");
    }
    else {
      return model.Filter.findByAbbr(filterName);
    }
  }

  @Override
  public void setFilter(String layerName, Filter filterName) {
    int layerIndex = this.getLayer(layerName);
    this.layers.get(layerIndex).setFilter(filterName);
    this.layers.get(layerIndex).applyFilter(this.getBeneathImage(layerIndex));
    this.updateCanvas();
  }

  @Override
  public void saveProject(String path) {
    CollageWrite file = new CollageWrite(path, this.height, this.width, this.layers);
    file.writeCollage();
  }

  @Override
  public void saveImage(String fileName) throws IOException {
    if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("ppm")) {
      PPMWrite file = new PPMWrite(fileName, this.height, this.width, this.canvas);
      file.writeFile("");
      return;
    }
    FileWrite file = new FileWrite(fileName, this.height, this.width, this.canvas);
    file.writeFile(fileName.substring(fileName.lastIndexOf(".") + 1));
  }

  @Override
  public void quit() {
    if (!this.running) {
      throw new IllegalStateException("Project hasn't started yet.");
    }
    else {
      this.running = false;
    }
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }
}
