package misc;

import java.io.IOException;

import model.RGB;

/**
 * An abstract class that generalizes reading in a file.
 */
public abstract class FileRead {
  protected String fileName;
  protected int width;
  protected int height;
  protected RGB[][] pixels;

  /**
   * Initializes the class FileRead.
   * @param fileName The name of the file that the class takes in and reads.
   */
  public FileRead(String fileName) {
    this.fileName = fileName;
    this.width = 0;
    this.height = 0;
  }

  public RGB[][] getArray() {
    return this.pixels;
  }

  public abstract void readFile() throws IOException;
}
