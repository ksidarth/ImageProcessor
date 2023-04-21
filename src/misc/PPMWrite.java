package misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.RGB;

/**
 * A class that represents writing a ppm file.
 */
public class PPMWrite extends FileWrite {
  public PPMWrite(String fileName, int height, int width, RGB[][] image) {
    super(fileName, height, width, image);
  }

  @Override
  public void writeFile(String extension) {
    File file = new File(this.fileName);
    try {
      FileWriter writer = new FileWriter(file, false);
      writer.write("P3\n" + this.width + " " + this.height + "\n" + 255 + "\n");
      for (RGB[] i : this.image) {
        for (RGB j : i) {
          writer.write(j.toString());
          writer.write(" ");
        }
      }
      for (RGB[] i : this.image) {
        for (RGB j : i) {
          writer.write(j.toString() + " ");
        }
        writer.write("\n");
      }
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("File to save is invalid");
    }
  }
}
