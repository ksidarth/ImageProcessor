import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import misc.FileRead;
import misc.JPGRead;
import misc.PNGRead;
import misc.PPMRead;
import model.RGB;

import static org.junit.Assert.assertEquals;

/**
 * Tests the PPMRead class.
 */
public class PPMReadTest {
  private FileRead test;
  private FileRead test2;
  private FileRead test3;

  @Before
  public void setUp() throws IOException {
    this.test = new PPMRead("tako.ppm");
    this.test2 = new PNGRead("C:\\Users\\ksida\\OneDrive\\Desktop\\CS3500\\IMGFinalFinal" +
          "\\src\\cs3500\\code\\swingdemo\\eldenRing.png");
    this.test3 = new JPGRead("C:\\Users\\ksida\\OneDrive\\Desktop\\CS3500\\" +
          "IMGFinalFinal\\src\\cs3500\\code\\swingdemo\\plant.jpg");
    this.test.readFile();
    this.test2.readFile();
    this.test3.readFile();
  }

  @Test
  public void testReadFile() {
    assertEquals(test3.getArray()[0][0].toString(), "246 246 246");
    assertEquals(this.test2.getArray()[100][100].toString(), "230 208 125");
    assertEquals(this.test.getArray()[0][0].toString(),
          new RGB(173, 179, 151).toString());
    this.test3 = new PPMRead("");
    try {
      test3.readFile();
    } catch (IOException e) {
      // Do nothing, was caught;
    }
  }
}