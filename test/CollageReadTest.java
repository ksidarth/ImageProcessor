import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Layer;
import misc.CollageRead;

import static org.junit.Assert.assertEquals;

/**
 * Tests CollageReadTest.
 */
public class CollageReadTest {

  private misc.CollageRead reader;

  @Before
  public void setUp() {
    reader = new CollageRead("tako_test.collage");
    reader.readCollage();
  }

  @Test
  public void testGetWidthAndHeight() {
    assertEquals(reader.getHeight(), 10);
    assertEquals(reader.getWidth(), 10);
  }

  @Test
  public void testGetLayers() {
    assertEquals(reader.getLayers(), new ArrayList<>(List.of(
          new Layer("testLayer1", 10, 10),
          new Layer("testLayer2", 10, 10))));
  }

  @Test
  public void addLayersToCanvas() {
    assertEquals(reader.addLayersToCanvas()[0][0].toString(), "0 0 151");
  }
}