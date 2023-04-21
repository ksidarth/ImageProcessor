package misc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.RGB;

/**
 * A class that represents the contents of a ppm file.
 */
public class PPMRead extends FileRead {
  private StringBuilder builder;

  public PPMRead(String fileName) {
    super(fileName);
    this.builder = new StringBuilder();
  }

  /**
   * The method that reads the ppm file.
   * @throws FileNotFoundException Thrown when file doesn't exist.
   */
  public void readFile() throws FileNotFoundException {
    Scanner sc = new Scanner(new FileInputStream(this.fileName));
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    this.width = sc.nextInt();
    this.height = sc.nextInt();
    int maxColor = sc.nextInt();
    this.pixels = new RGB[this.height][this.width];
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        int rScaled = sc.nextInt() * (255 / maxColor);
        int gScaled = sc.nextInt() * (255 / maxColor);
        int bScaled = sc.nextInt() * (255 / maxColor);
        this.pixels[i][j] = new RGB(rScaled, gScaled, bScaled, 255);
      }
    }
  }
}
