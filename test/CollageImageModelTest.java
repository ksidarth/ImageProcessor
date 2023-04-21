import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.CollageImageModel;
import model.Filter;
import model.Layer;
import model.RGB;

import static org.junit.Assert.assertEquals;

/**
 * Tests the CollageImageModelTest class.
 */
public class CollageImageModelTest {
  private CollageImageModel collage;
  private CollageImageModel collage2;
  private CollageImageModel collage3;

  @Before
  public void setUp() {
    collage = new CollageImageModel();
    collage.newProject(10, 10);
    collage2 = new CollageImageModel();
    collage2.newProject(800, 600);
    collage3 = new CollageImageModel();
  }

  @Test
  public void testLayer() throws IOException {
    assertEquals(collage.getCanvas()[0][0].toString(),
            new RGB(255, 255, 255).toString());
    collage.addLayer("test");
    assertEquals(collage.getCanvas()[0][0].toString(),
            new RGB(255, 255, 255).toString());
    assertEquals(collage.getLayers().get(0).toString(),
            new Layer("test", 10, 10).toString());
    collage.addImageToLayer("test", "tako.ppm", 0, 0);
    assertEquals(collage.getLayers().get(0).toString(),
            new Layer("test", 10, 10).toString());
  }

  @Test
  public void testSaveImage() throws IOException {
    collage.addLayer("make-blue");
    collage.setFilter("make-blue", Filter.BLUE_COMPONENT);
    collage.addImageToLayer("make-blue", "tako.ppm", 0, 0);
    collage.saveImage("tako_test.ppm");
    collage.saveProject("tako_test.collage");
    assertEquals(collage.getCanvas(), "");
  }

  @Test
  public void testSaveProject() throws IOException {
    collage2.addLayer("make-red");
    collage2.setFilter("make-red", Filter.RED_COMPONENT);
    collage2.addImageToLayer("make-red", "tako.ppm", 0, 0);
    collage2.addLayer("make-blue");
    collage2.setFilter("make-blue", Filter.BLUE_COMPONENT);
    collage2.addImageToLayer("make-blue", "tako.ppm", 100, 100);
    collage2.saveImage("tako_test.ppm");
    assertEquals(collage.getCanvas(), "");
  }

  @Test
  public void test() throws IOException {
    this.collage3.loadProject("collage_test.collage");
    this.collage3.saveImage("test_test.ppm");
    assertEquals(collage.getCanvas(), "");
  }

  @Test
  public void testNew() throws IOException {
    this.collage.addLayer("testLayer1");
    this.collage.setFilter("testLayer1", Filter.RED_COMPONENT);
    this.collage.addImageToLayer("testLayer1", "tako.ppm", 0, 0);
    this.collage.addLayer("testLayer2");
    this.collage.addImageToLayer("testLayer2", "tako.ppm", 0, 0);
    this.collage.setFilter("testLayer2", Filter.BLEND_BACKGROUND);
    assertEquals(this.collage.getCanvas()[0][0], "");
    //assertEquals(this.collage.getBeneathImage(0)[0][0], "");
  }
}