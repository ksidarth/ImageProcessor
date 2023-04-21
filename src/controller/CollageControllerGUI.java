package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.CollageImageModel;
import model.CollageModel;

/**
 * A class that represents the controller for the graphical interface. It is represented as a width
 * and height, which are the dimensions of the project, along with a Model that holds all
 */
public class CollageControllerGUI implements CollageController {
  private int height;
  private int width;
  private CollageModel model;

  /**
   * Creates an instance of the Controller.
   * @param model the CollageModel to take in. Holds functionality for the controller.
   */
  public CollageControllerGUI(CollageModel model) {
    this.model = model;
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  public CollageModel getModel() {
    return this.model;
  }

  /**
   * A method that creates a new project in model.
   * @param height The height of the project.
   * @param width The width of the proejct.
   */
  public void newProject(int height, int width) {
    this.model = new CollageImageModel();
    this.height = height;
    this.width = width;
    this.model.newProject(height, width);
  }

  /**
   * A method that loads a collage project into the model.
   * @param fileName The file name of the collage project.
   */
  public void loadProject(String fileName) {
    this.model.loadProject(fileName);
    this.height = this.model.getHeight();
    this.width = this.model.getWidth();
  }

  public void saveProject(String fileName) throws IOException {
    this.model.saveProject(fileName);
  }

  public void saveImage(String imageName) throws IOException {
    this.model.saveImage(imageName);
  }

  @Override
  public void startProgram(Scanner sc) throws IllegalArgumentException {
    // Does nothing.
  }

  public void addImageToLayer(String layerName, String imageName, int x, int y)
          throws IOException {
    this.model.addImageToLayer(layerName, imageName, x, y);
  }

  @Override
  public void setFilter(String currentLayer, String filter) {
    this.model.setFilter(currentLayer, this.model.getFilter(filter));
  }

  public void addLayer(String layerName) {
    this.model.addLayer(layerName);
  }

  /**
   * A method that returns all the names of the layers as a list.
   * @return An ArrayList of strings.
   */
  public ArrayList<String> getLayerNames() {
    return this.model.getLayerNames();
  }
}
