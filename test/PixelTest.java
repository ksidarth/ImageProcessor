import org.junit.Before;
import org.junit.Test;


import model.HSL;
import model.RGB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the PixelTest class.
 */
public class PixelTest {
  private RGB pixel;
  private RGB pixel2;
  private RGB pixel3;

  private RGB pix1;
  private RGB pix2;
  private RGB pix3;
  private RGB pix4;

  @Before
  public void setUp() {
    this.pixel = new RGB(250, 120, 200);
    this.pixel2 = new RGB(200, 100, 10);
    this.pixel3 = new RGB(255, 255, 255, 255);
    this.pix1 = new RGB(123, 234, 60);
    this.pix2 = new RGB(255, 255, 0);
    this.pix3 = new RGB(0, 0, 255);
    this.pix4 = new RGB(123, 234, 60, 255);
  }

  @Test
  public void test() {
    this.pixel.editRGB(1, 0, 0);
    assertEquals(this.pixel.toString(), new RGB(250, 0, 0).toString());
    assertEquals(this.pixel.toString(), "250 0 0");
    this.pixel2.editRGB(0, 1, 0);
    this.pixel2.imageWithBackground(this.pixel3);
    assertEquals(this.pixel2.toString(), new RGB(0, 100, 0).toString());
  }

  @Test
  public void blendTest() {
    RGB newPixel = this.pixel.blendWithBackground(this.pixel2);
    assertEquals(newPixel.toString(), new RGB(50, 20, 190).toString());
  }

  @Test
  public void hslTest() {
    HSL pixel1 = this.pixel.convertRGBtoHSL();
    HSL pixel2 = this.pixel.convertRGBtoHSL();
    HSL pixel3 = pixel1.multiply(pixel2);
    RGB pixelFinal = pixel3.convertHSLtoRGB();
    assertEquals(pixelFinal.toString(), "");
  }

  @Test
  public void testImageWithBackground() {
    assertEquals(this.pix1.imageWithBackground(this.pix2), new RGB(3, 3, 3, 3));
    assertEquals(this.pix2.imageWithBackground(this.pix4), new RGB(3, 3, 3, 3));
  }

  @Test
  public void testEditRGB() {
    RGB oldPixel = pix1;
    this.pix1.editRGB(1, 0, 1);
    assertNotEquals(oldPixel, this.pix1);
    assertEquals(pix1, new RGB(123, 0, 60));
    this.pix2.editRGB(0, 0, 0);
    assertEquals(pix2, new RGB(0, 0, 0));
  }

  @Test
  public void testEditIntensity() {
    pix1.editIntensity(-20);
    assertEquals(pix1, new RGB(103, 214, 40, 255));
    pix3.editIntensity(40);
    assertEquals(pix3, new RGB(40, 40, 255, 255));
    pix4.editIntensity(-255);
    assertEquals(pix4, new RGB(0, 0, 0, 255));
  }

  @Test
  public void testToString() {
    assertEquals(pix4.toString(), "123 234 60");
    assertEquals(pix2.toString(), "255 255 0");
    assertEquals(pix1.toString(), "123 234 60");
  }
}