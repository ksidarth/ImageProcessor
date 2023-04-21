package model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * An interface that represents CollageModel and starting collage model. All it can do is create a
 * new project.
 */
public interface CollageModel {

  /**
   * Creates a new project with the given height and width.
   * @param height - The height of the new project.
   * @param width - The width of the new project.
   */
  void newProject(int height, int width);

  /**
   * Adds a layer to the project.
   * @param layerName - the user given name of the layer to add.
   */
  void addLayer(String layerName);

  /**
   * Adds an image to an existing layer.
   * @param layer - the name of the layer to add an image to.
   * @param image - the image to add to the layer.
   * @param x - the x coordinate to add it to.
   * @param y - the y coordinate to add it to.
   * @throws IOException - If the file cannot be saved for some reason.
   */
  void addImageToLayer(String layer, String image, int x, int y) throws IOException;

  /**
   * Loads a .collage file in as a project.
   * @param path - the path of the file to laod in.
   */
  void loadProject(String path);

  /**
   * Saves the existing project as a .collage file.
   * @param path - the path to save it to.
   * @throws IOException - if the path is invalid.
   */
  void saveProject(String path) throws IOException;

  /**
   * Saves the existing project as an image file.
   * @param fileName - the name to save it to.
   * @throws IOException - if the name is invalid.
   */
  void saveImage(String fileName) throws IOException;

  /**
   * Returns the Filter enum representation of a string filter name.
   * @param filterName - the string representation of the filter.
   * @return - the Filter.
   */
  model.Filter getFilter(String filterName);

  /**
   * Returns all layers in the current project, by name.
   * @return - a list of strings representing all layers.
   */
  ArrayList<String> getLayerNames();

  /**
   * Sets a given layer to a given filter.
   * @param layer - the layer name to set.
   * @param option - the filter to set it to.
   */
  void setFilter(String layer, Filter option);

  /**
   * Quits the program.
   */
  void quit();

  /**
   * Returns the height of the project.
   * @return - the int height.
   */
  int getHeight();

  /**
   * Returns the width of the project.
   * @return an int representation of the width.
   */
  int getWidth();

  /**
   * Returns the canvas as a 2d list of RGBs.
   * @return a 2d list of whatever pixels are in the project.
   */
  RGB[][] getCanvas();

  /**
   * Returns all layers in the project.
   * @return a list of Layers that are used in the project.
   */
  ArrayList<Layer> getLayers();
}
