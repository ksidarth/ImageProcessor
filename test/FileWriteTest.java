import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import misc.FileRead;
import misc.FileWrite;
import misc.JPGRead;
import misc.PPMRead;
import model.CollageImageModel;
import model.CollageModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the FileWrite class.
 */
public class FileWriteTest {

  private FileRead fileBefore;

  private FileWrite test1;

  @Before
  public void setUp() throws IOException {
    CollageModel model = new CollageImageModel();
    fileBefore = new PPMRead("tako.ppm");
    fileBefore.readFile();
    test1 = new FileWrite("testFileSaved.png", 800, 600, fileBefore.getArray());
  }

  @Test
  public void writeFile() throws IOException {
    test1.writeFile("jpg");
    File tempFile = new File("testFileSaved.jpg");
    FileRead fileAfter = new JPGRead("testFileSaved.jpg");
    fileAfter.readFile();
    assertEquals(fileBefore.getArray()[0][0].toString(), fileAfter.getArray()[0][0].toString());

    test1.writeFile("ppm");
    fileBefore = new PPMRead("tako.ppm");
    fileBefore.readFile();
    test1 = new FileWrite("testFileSaved.ppm", 800, 600, fileBefore.getArray());
  }
}