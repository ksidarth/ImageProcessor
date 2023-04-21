package view;

import java.io.FileNotFoundException;

/**
 * An interface that represents the graphical interface. Contains all the functions needed in a
 * graphical interface class.
 */
public interface SwingGUI {
  /**
   * Updates the canvas to have current images and pixels.
   * @throws FileNotFoundException if the file to update the canvas with is not found.
   */
  void updateCanvas() throws FileNotFoundException;
}
