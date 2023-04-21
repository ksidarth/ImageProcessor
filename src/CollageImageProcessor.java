import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.CollageControllerText;
import controller.CollageControllerGUI;
import model.CollageImageModel;
import model.CollageModel;
import view.SwingGUIView;

import controller.CollageController;

/**
 * The main class that runs the SwingGUI.
 */
public class CollageImageProcessor {
  /**
   * The main function that runs the SwingGUI.
   *
   * @param args The arguments the user inputs.
   */
  public static void main(String[] args) throws FileNotFoundException {
    if (args.length == 0) {
      SwingGUIView.setDefaultLookAndFeelDecorated(false);
      CollageModel model = new CollageImageModel();
      CollageController controller = new CollageControllerGUI(model);
      SwingGUIView frame = new SwingGUIView(controller);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (UnsupportedLookAndFeelException e) {
        // handle exception
      } catch (ClassNotFoundException e) {
        // handle exception
      } catch (InstantiationException e) {
        // handle exception
      } catch (IllegalAccessException e) {
        // handle exception
      } catch (Exception e) {
        // handle exception
      }
    }
    else if (args[0].equals("text")) {
      CollageModel model = new CollageImageModel();
      CollageController controller = new CollageControllerText(model);
      controller.startProgram(new Scanner(System.in));
    }
    else {
      Scanner sc = new Scanner(new FileInputStream(args[0]));
      CollageModel model = new CollageImageModel();
      CollageController controller = new CollageControllerText(model);
      controller.startProgram(sc);
    }
  }
}