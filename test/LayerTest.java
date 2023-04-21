import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.Filter;
import model.Layer;
import model.RGB;


import static org.junit.Assert.assertEquals;

/**
 * Tests the LayerTest class.
 */
public class LayerTest {
  private Layer test;
  private RGB[][] canvas;
  private RGB[][] canvas2;

  @Before
  public void setUp() {
    test = new Layer("test", 10, 10);
    canvas = new RGB[10][10];
    canvas2 = new RGB[10][10];
    RGB[][] canvas3 = new RGB[800][600];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        canvas[i][j] = new RGB(255, 255, 255, 255);
        canvas2[i][j] = new RGB(200, 200, 200, 255);
      }
    }
    for (int i = 0; i < 800; i++) {
      for (int j = 0; j < 600; j++) {
        canvas3[i][j] = new RGB(255, 0, 255, 255);
      }
    }
  }

  @Test
  public void test() throws IOException {
    assertEquals(test.getArray()[0][0].toString(),
            new RGB(255, 255, 255, 0).toString());
    test.applyFilter(canvas);
    assertEquals(test.getArray()[0][0].toString(),
            new RGB(255, 255, 255, 0).toString());
    test.setFilter(Filter.BLUE_COMPONENT);
    test.applyFilter(canvas);
    assertEquals(test.getArray()[0][0].toString(),
            new RGB(0, 0, 255, 0).toString());
    test.addImage(0, 0, "tako.ppm");
    test.applyFilter(canvas);
    assertEquals(test.getArray()[0][0].toString(),
            new RGB(0, 0, 151).toString());
    assertEquals(test.getArray()[0][0].getAlpha(), 255);
    RGB[][] newCanvas = test.addToCanvas(canvas);
    assertEquals(newCanvas[0][0].toString(),
            new RGB(0, 0, 151).toString());
  }

  @Test
  public void testBlend() throws IOException {
    test.addImage(0, 0, "tako.ppm");
    test.blendLayer(canvas);
    assertEquals(test.getArray()[0][0].toString(), "151 173 178");
  }

  @Test
  public void testMultiply() throws IOException {
    test.addImage(0, 0, "tako.ppm");
    test.multiply(canvas2);
    assertEquals(test.getArray()[0][0].toString(), "178 151 173");
  }

  @Test
  public void setMultipleFilters() throws IOException {
    test.addImage(0, 0, "tako.ppm");
    test.setFilter(Filter.RED_COMPONENT);
    test.applyFilter(test.getArray());
    assertEquals(test.getImage()[0][0].toString(), "171 151 153");
  }
}