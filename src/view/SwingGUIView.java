package view;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.CollageController;

/**
 * A class that represents the SwingGUIView.
 */
public class SwingGUIView extends JFrame implements ActionListener, ListSelectionListener,
      SwingGUI {
  private String currentLayer;
  private boolean imageSaved;
  private boolean projectSaved;
  private JList<String> layers;
  private DefaultListModel<String> dataForListOfStrings;
  private JPanel canvasPanel;
  private JLabel canvasImage;
  private CollageController controller;

  /**
   * Initializes the GUI view.
   *
   * @param controller The controller.
   */
  public SwingGUIView(CollageController controller) {
    super();
    this.controller = controller;
    this.imageSaved = true;
    this.projectSaved = true;
    setUp();
  }

  /**
   * Adds layers visually.
   *
   * @param layerInput The string layer name to add.
   */
  private void addLayers(String layerInput) {
    this.dataForListOfStrings.addElement(layerInput);
    SwingUtilities.updateComponentTreeUI(this);
  }

  @Override
  public void updateCanvas() throws FileNotFoundException {
    BufferedImage canvasImage = new BufferedImage(600, 800, BufferedImage.TYPE_INT_RGB);
    this.controller.getModel().getCanvas();
    for (int i = 0; i < 800; i++) {
      for (int j = 0; j < 600; j++) {
        if (i < this.controller.getHeight() && j < this.controller.getWidth()) {
          Color tempColor;
          tempColor = new Color(this.controller.getModel().getCanvas()[i][j].getRed(),
                this.controller.getModel().getCanvas()[i][j].getGreen(),
                this.controller.getModel().getCanvas()[i][j].getBlue());
          canvasImage.setRGB(j, i, tempColor.getRGB());
        } else {
          canvasImage.setRGB(j, i, (new Color(255, 255, 255).getRGB()));
        }
      }
    }
    ImageIcon canvasIcon = new ImageIcon(canvasImage);
    this.canvasImage.setIcon(canvasIcon);
    this.canvasPanel.add(this.canvasImage);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "New Project": {
        this.imageSaved = false;
        this.projectSaved = false;
        JLabel heightBox = new JLabel("Height");
        JLabel widthBox = new JLabel("Width");
        String heightResponse = JOptionPane.showInputDialog("Enter Height:");
        if (heightResponse != null && heightResponse.length() > 0) {
          String widthResponse = JOptionPane.showInputDialog("Enter Width:");
          if (widthResponse != null && widthResponse.length() > 0) {
            heightBox.setText(heightResponse);
            widthBox.setText(widthResponse);
            this.controller.newProject(Integer.parseInt(heightBox.getText()),
                  Integer.parseInt(widthBox.getText()));
            this.dataForListOfStrings.removeAllElements();
            try {
              this.updateCanvas();
            } catch (FileNotFoundException ex) {
              throw new RuntimeException(ex);
            }
          }
        }
        break;
      }
      case "Load Project": {
        this.imageSaved = false;
        this.projectSaved = false;
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
              ".collage Files", "collage");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingGUIView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          this.controller.loadProject(f.getAbsolutePath());
          this.dataForListOfStrings.removeAllElements();
          ArrayList<String> layerNames = this.controller.getLayerNames();
          for (int i = 0; i < layerNames.size(); i++) {
            this.dataForListOfStrings.addElement(layerNames.get(i));
          }
          try {
            this.updateCanvas();
          } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
          }
          SwingUtilities.updateComponentTreeUI(this);
        }
        break;
      }
      case "Save Image": {
        this.imageSaved = true;
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "PPM Files", "ppm");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showSaveDialog(SwingGUIView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          try {
            this.controller.saveImage(f.getAbsolutePath());
          } catch (IOException | IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file!",
                  "File Save error", JOptionPane.ERROR_MESSAGE);
          }
          SwingUtilities.updateComponentTreeUI(this);
        }
        break;
      }
      case "Save Project": {
        this.projectSaved = true;
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
              ".collage Files", "collage");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showSaveDialog(SwingGUIView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          try {
            this.controller.saveProject(f.getAbsolutePath());
          } catch (IOException | IllegalStateException ex) {
            System.out.println(ex.getMessage());
          }
          SwingUtilities.updateComponentTreeUI(this);
        }
        break;
      }
      case "Add Layer": {
        this.imageSaved = false;
        this.projectSaved = false;
        JLabel layerInput = new JLabel("Layer Name");
        String layerResponse = JOptionPane.showInputDialog("Enter Layer Name");
        if (layerResponse != null && layerResponse.length() > 0) {
          layerInput.setText(layerResponse);
          if (layerInput.getText().length() > 0) {
            try {
              this.controller.addLayer(layerInput.getText());
              this.addLayers(layerInput.getText());
            } catch (IllegalArgumentException | IllegalStateException exception) {
              JOptionPane.showMessageDialog(this, "Layer already exists!",
                    "Layer Error Message", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
        break;
      }
      case "Add Image": {
        this.imageSaved = false;
        this.projectSaved = false;
        String filePath = "";
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter(
              "PNG Files", "png");
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(
              "JPG Files", "JPG");
        FileNameExtensionFilter ppmFilter = new FileNameExtensionFilter(
              "PPM Files", "PPM");
        fchooser.addChoosableFileFilter(pngFilter);
        fchooser.addChoosableFileFilter(jpgFilter);
        fchooser.addChoosableFileFilter(ppmFilter);
        int retvalue = fchooser.showOpenDialog(SwingGUIView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          filePath = f.getAbsolutePath();
        }
        JLabel xBox = new JLabel("x-offset");
        JLabel yBox = new JLabel("y-offset");
        String xOffset = JOptionPane.showInputDialog("Enter x-coordinate:");
        if (xOffset != null && xOffset.length() > 0) {
          String yOffset = JOptionPane.showInputDialog("Enter y-coordinates:");
          if (yOffset != null && yOffset.length() > 0) {
            xBox.setText(xOffset);
            yBox.setText(yOffset);
            try {
              File tempFile = new File(filePath);
              this.controller.addImageToLayer(this.currentLayer, tempFile.getAbsolutePath(),
                    Integer.parseInt(xBox.getText()), Integer.parseInt(yBox.getText()));
              this.updateCanvas();
              SwingUtilities.updateComponentTreeUI(this);
            } catch (IOException | IllegalStateException ex) {
              JOptionPane.showMessageDialog(this, "Image cannot be added!",
                    "Load image error", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
        break;
      }
      case "Set Filter": {
        this.imageSaved = false;
        this.projectSaved = false;
        JLabel filterBox = new JLabel("Filter Name");
        String filterResponse = JOptionPane.showInputDialog("Set Filter Component");
        if (filterResponse != null && filterResponse.length() > 0) {
          filterBox.setText(filterResponse);
          try {
            this.controller.setFilter(this.currentLayer, filterBox.getText());
          } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, "Filter does not exist!",
                  "Set filter error", JOptionPane.ERROR_MESSAGE);
          }
          try {
            this.updateCanvas();
          } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
          }
        }
        break;
      }
      case "Quit":
        if (this.imageSaved || this.projectSaved) {
          System.exit(0);
        } else {
          JLabel quitBox = new JLabel("Image and/or project hasn't been saved. " +
                "Do you still wish to exit?");
          int quitResponse = JOptionPane.showConfirmDialog(null, quitBox,
                "Exit Message", JOptionPane.YES_NO_OPTION);
          if (quitResponse == 0) {
            System.exit(0);
          }
        }
        break;
      default:
        break;
    }
  }



  @Override
  public void valueChanged(ListSelectionEvent e) {
    this.currentLayer = this.layers.getSelectedValue();
  }

  /**
   * Sets up the GUI.
   */
  private void setUp() {
    setTitle("Image Collage Processor");
    setSize(1000, 1000);

    // The panel on top of the main frame.
    ///////////////////////////////////////////////////////////////////////////////////////////////
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new FlowLayout());
    mainPanel.setBackground(Color.GRAY);

    // Start New Project Button
    JButton newButton = new JButton("New Project");
    newButton.setActionCommand("New Project");
    newButton.addActionListener(this);
    mainPanel.add(newButton);

    // Load Project Button
    JButton loadButton = new JButton("Load Project");
    loadButton.setActionCommand("Load Project");
    loadButton.addActionListener(this);
    mainPanel.add(loadButton);

    // Save Image Button
    JButton saveButton = new JButton("Save Image");
    saveButton.setActionCommand("Save Image");
    saveButton.addActionListener(this);
    mainPanel.add(saveButton);

    // Save Project Button
    JButton projectButton = new JButton("Save Project");
    projectButton.setActionCommand("Save Project");
    projectButton.addActionListener(this);
    mainPanel.add(projectButton);

    // Quit Button
    JButton quitButton = new JButton("Quit");
    quitButton.setActionCommand("Quit");
    quitButton.addActionListener(this);
    mainPanel.add(quitButton);

    add(mainPanel, BorderLayout.NORTH);
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Initializes the Canvas.
    ////////////////////////////////////////////////////////////////////////////////////////////////
    this.canvasPanel = new JPanel();
    this.canvasImage = new JLabel();
    this.canvasPanel.setLayout(new FlowLayout());

    // Creates the initial image on canvas.
    BufferedImage image = new BufferedImage(600, 800, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        image.setRGB(j, i, (new Color(255, 255, 255)).getRGB());
      }
    }
    ImageIcon canvasIcon = new ImageIcon(image);
    this.canvasImage.setIcon(canvasIcon);
    this.canvasPanel.add(this.canvasImage);
    add(this.canvasPanel, BorderLayout.WEST);
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Creates the editor panel on the right.
    ////////////////////////////////////////////////////////////////////////////////////////////////
    JPanel editorPanel = new JPanel();
    editorPanel.setLayout(new FlowLayout());
    editorPanel.setBackground(Color.LIGHT_GRAY);

    // Adds Add Image To Layer Button.
    JButton addLayerButton = new JButton("Add Layer");
    addLayerButton.setActionCommand("Add Layer");
    addLayerButton.addActionListener(this);
    editorPanel.add(addLayerButton);

    // Creates layer selection list.
    JPanel layersPanel = new JPanel();
    layersPanel.setBorder(BorderFactory.createTitledBorder("Layers:"));
    layersPanel.setLayout(new BoxLayout(layersPanel, BoxLayout.X_AXIS));

    this.dataForListOfStrings = new DefaultListModel<>();
    this.layers = new JList<>(this.dataForListOfStrings);
    this.layers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.layers.addListSelectionListener(this);

    layersPanel.add(new JScrollPane(this.layers));
    layersPanel.setLayout(new BoxLayout(layersPanel, BoxLayout.PAGE_AXIS));
    editorPanel.add(layersPanel);

    // Add set-filter button.
    JButton filterButton = new JButton("Set Filter");
    filterButton.setActionCommand("Set Filter");
    filterButton.addActionListener(this);
    editorPanel.add(filterButton);

    // Add add-to-layer button.
    JButton addImageButton = new JButton("Add Image");
    addImageButton.setActionCommand("Add Image");
    addImageButton.addActionListener(this);
    editorPanel.add(addImageButton);

    add(editorPanel);
  }
}
