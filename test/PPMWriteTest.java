import org.junit.Before;
import org.junit.Test;

import misc.PPMWrite;
import model.RGB;

import static org.junit.Assert.assertEquals;

/**
 * Tests the PPMWrite class.
 */
public class PPMWriteTest {
  private PPMWrite transparentImage;
  private RGB[][] canvas;

  @Before
  public void setUp() {
    this.canvas = new RGB[100][100];
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        this.canvas[i][j] = new RGB(255, 0, 255, 125);
      }
    }
    this.transparentImage = new PPMWrite("transparent-image.ppm", 100, 100,
          this.canvas);
  }

  @Test
  public void writePPM() {
    this.transparentImage.writeFile("ppm");
    assertEquals(this.canvas, "");
  }
}