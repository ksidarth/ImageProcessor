import org.junit.Before;
import org.junit.Test;

import model.HSL;
import model.RGB;

import static org.junit.Assert.assertEquals;

/**
 * Tests the HSL class.
 */
public class HSLTest {
  private model.HSL pix1;
  private model.HSL pix2;
  private model.HSL pix3;
  private model.HSL pix4;

  @Before
  public void setUp() {
    pix1 = new model.HSL(0, 0, 0);
    pix2 = new model.HSL(60, 0, 0.4);
    pix3 = new model.HSL(270, 0.5, 0);
    pix4 = new model.HSL(360, 1, 1);
  }

  @Test
  public void multiply() {
    pix2.multiply(pix1);
    assertEquals(pix2.toString(), "60.0 0.0 0.0");
    pix2 = new HSL(60, 0, 0.4);
    pix4.multiply(pix2);
    assertEquals(pix4.toString(), "360.0 1.0 0.4");
  }

  @Test
  public void screen() {
    pix1.screen(pix2);
    assertEquals(pix1.toString(), "0.0 0.0 0.4");
    pix4.screen(pix3);
    assertEquals(pix4.toString(), "360.0 1.0 1.0");
  }

  @Test
  public void convertHSLtoRGB() {
    assertEquals(pix1.convertHSLtoRGB(), new model.RGB(0, 0, 0));
    assertEquals(pix3.convertHSLtoRGB(), new model.RGB(34, 45, 56));
    assertEquals(pix4.convertHSLtoRGB(), new RGB(253, 134, 123));
  }
}