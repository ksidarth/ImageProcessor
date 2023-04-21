package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.CollageModel;

/**
 * An interface that represents all controller classes (text based and graphical user based).
 */
public interface CollageController {
  /**
   * Starts the program.
   * @param sc - Scanner to read input.
   * @throws IllegalArgumentException if something in the Model throws an exception.
   */
  void startProgram(Scanner sc) throws IllegalArgumentException;

  /**
   * Adds an image to the selected layer.
   * @param currentLayer - the layer to add the image to.
   * @param filePath - the filename of the image.
   * @param x - the x coordinate to add it to.
   * @param y - the y coordinate to add it to.
   * @throws IOException - if the file cannot be read.
   */
  void addImageToLayer(String currentLayer, String filePath, int x, int y) throws IOException;

  /**
   * Sets a layers filter.
   * @param currentLayer - the name of the layer to set
   * @param filter - what Filter to set it to
   */
  void setFilter(String currentLayer, String filter);

  /**
   * Adds a layer to the project.
   * @param text - the name of the layer to add.
   */
  void addLayer(String text);

  /**
   * Saves a project as a .collage file.
   * @param absolutePath - the path to save it to, including the name.
   * @throws IOException - if there is an issue saving the project.
   */
  void saveProject(String absolutePath) throws IOException;

  /**
   * Saves an image to a path.
   * @param absolutePath - the path to save the image to.
   * @throws IOException - if there is an issue saving the file
   */
  void saveImage(String absolutePath) throws IOException;

  /**
   * Loads a .collage file in as a project.
   * @param absolutePath - the path of the .collage file to load in.
   */
  void loadProject(String absolutePath);

  /**
   * Creates a new project with the given height and width.
   * @param height - the height of the image to add.
   * @param width - the width of the image to add.
   */
  void newProject(int height, int width);

  /**
   * Gets the height of the project.
   * @return the height of the project.
   */
  int getHeight();

  /**
   * Returns the width of the project.
   * @return the width of the project.
   */
  int getWidth();

  /**
   * Returns the model that the controller is using.
   * @return the CollageModel.
   */
  CollageModel getModel();

  /**
   * Returns a list of all valid layers the user has entered.
   * @return An ArrayList of the layers.
   */
  ArrayList<String> getLayerNames();
}
