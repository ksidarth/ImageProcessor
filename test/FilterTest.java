import org.junit.Test;

import model.Filter;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Filter Enumeration.
 */
public class FilterTest {
  @Test
  public void testGetRepresentation() {
    assertEquals(model.Filter.NORMAL.getRepresentation(), "normal");
    assertEquals(model.Filter.RED_COMPONENT.getRepresentation(), "red-component");
    assertEquals(model.Filter.BLUE_COMPONENT.getRepresentation(), "blue-component");
  }

  @Test
  public void testFindByAbbr() {
    assertEquals(model.Filter.findByAbbr("normal"), model.Filter.NORMAL);
    assertEquals(model.Filter.findByAbbr("darken-value"), model.Filter.DARKEN_VALUE);
    assertEquals(model.Filter.findByAbbr("red-component"), Filter.RED_COMPONENT);
  }
}