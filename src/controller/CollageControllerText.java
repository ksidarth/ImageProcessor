package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.CollageModel;

/**
 * A class that represents the controller for text view. Represented as a height, width, boolean,
 * and model that holds all functionality.
 */
public class CollageControllerText implements CollageController {
  private int height;
  private int width;
  private boolean canRun;
  private CollageModel model;

  /**
   * Initializes the class CollageControllerText.
   * @param model A CollageModel that the CollageControllerText takes int.
   */
  public CollageControllerText(CollageModel model) {
    this.height = 0;
    this.width = 0;
    this.canRun = true;
    this.model = model;
  }

  @Override
  public void startProgram(Scanner sc) throws IllegalArgumentException {
    while (this.canRun) {
      System.out.println("Enter command");
      String input = sc.next();
      switch (input.toLowerCase()) {
        case "new-project": {
          System.out.println("Enter height");
          this.height = sc.nextInt();
          System.out.println("Enter width");
          this.width = sc.nextInt();
          this.newProject(this.height, this.width);
          System.out.println("Created new project successfully!");
          break;
        }
        case "load-project": {
          System.out.println("Enter a .collage file");
          input = sc.next();
          try {
            this.loadProject(input);
            System.out.println("Loaded project " + input + " successfully!");
          } catch (IllegalArgumentException e) {
            System.out.println("File doesn't exist");
          }
          break;
        }
        case "add-layer": {
          System.out.println("Enter layer name");
          String layerName = sc.next();
          this.addLayer(layerName);
          System.out.println("Created layer " + layerName + " successfully!");
          break;
        }
        case "set-filter": {
          System.out.println("Enter the name of the layer");
          String layerName = sc.next();
          System.out.println("Enter the filter name");
          String filterName = sc.next();
          try {
            this.setFilter(layerName, filterName);
            System.out.println("Set filter on " + layerName + " to " + filterName
                    + " successfully!");
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          break;
        }
        case "add-image-to-layer": {
          System.out.println("Enter layer name:");
          String layerName = sc.next();
          System.out.println("Enter filepath:");
          String path = sc.next();
          System.out.println("Enter x coordinate:");
          int x = sc.nextInt();
          System.out.println("Enter y coordinate:");
          int y = sc.nextInt();
          try {
            this.addImageToLayer(layerName, path, x, y);
            System.out.println("Added image to " + layerName + " successfully!");
          } catch (FileNotFoundException e) {
            System.out.println("Invalid Input");
          } catch (IllegalArgumentException | IllegalStateException | IOException e) {
            System.out.println(e.getMessage());
          }
          break;
        }
        case "save-image": {
          System.out.println("Enter the filepath of the image you want to save");
          String path = sc.next();
          try {
            this.saveImage(path);
            System.out.println("Saved image as " + path + " successfully!");
          } catch (IOException e) {
            throw new IllegalArgumentException("Invalid Filepath");
          }
          break;
        }
        case "save-project": {
          System.out.println("Enter the filepath of the project you want to save");
          String path = sc.next();
          try {
            this.saveProject(path);
            System.out.println("Saved project as " + path + " successfully!");
          } catch (IOException e) {
            throw new RuntimeException("Invalid Filepath");
          }
          break;
        }
        case "quit": {
          canRun = false;
          System.out.println("Program now exiting");
          break;
        }
        default: {
          System.out.println("Invalid input");
          break;
        }
      }
    }
  }

  @Override
  public void addImageToLayer(String currentLayer, String filePath, int parseInt, int parseInt1)
          throws IOException {
    this.model.addImageToLayer(currentLayer, filePath, parseInt, parseInt1);
  }

  @Override
  public void setFilter(String currentLayer, String filter) {
    this.model.setFilter(currentLayer, this.model.getFilter(filter));
  }

  @Override
  public void addLayer(String text) {
    this.model.addLayer(text);
  }

  @Override
  public void saveProject(String absolutePath) throws IOException {
    this.model.saveProject(absolutePath);
  }

  @Override
  public void saveImage(String absolutePath) throws IOException {
    this.model.saveImage(absolutePath);
  }

  @Override
  public void loadProject(String absolutePath) {
    this.model.loadProject(absolutePath);
  }

  @Override
  public void newProject(int height, int width) {
    this.model.newProject(height, width);
  }

  @Override
  public int getHeight() {
    return this.model.getHeight();
  }

  @Override
  public int getWidth() {
    return this.model.getWidth();
  }

  @Override
  public CollageModel getModel() {
    return this.model;
  }

  @Override
  public ArrayList<String> getLayerNames() {
    return this.model.getLayerNames();
  }
}
