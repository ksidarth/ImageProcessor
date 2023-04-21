
# Collage Image Processor

In our project, we aimed to create a GIMP-like application that allows users to easily add and edit images. Some of the functions include:
 - setting filters on images
 - overlapping multiple images
 - loading projects so users can continue where   they left off
 - saving an image as a jpg, png, or ppm filters
Our application has two interfaces, a text-based interface and a graphical interface. Users can choose between these interfaces through terminal (details will be mentioned in USEME.md).

## Requirements
The requirements you need for this code to compile are the following:
 - Java 11 or higher JRE
 - JUnit 4 for running tests

## USEME Location
Our USEME.md gives detailed explanation on how to run our application (.jar file). The USEME.md file is located in the root folder.

## Example Images
When looking at our folder, you'll see a directory labeled example_images. In this directory, we added all the edited images and screenshots from our GUI. Therefore, users can see what the results are when working with our application.

## Code Design
For our design, we implemented a model, a controller, and a view. We also have a misc package that contains all the file reading and writing operations. 

In the model package, we have the following interface and class: 
 - CollageModelState (Interface): an interface that stores in all the basic functions one can do in a model class. 
 - CollageModel (Interface): an interface that extends CollageModelState and contains the ability to start a new project.
 - CollageImageModel (Class): a java class that acts as the workspace for the project.
 - Filter (Enumeration): an enumeration class for filter options so that we can efficiently keep track of the filtering of the layers.
 - Layer (Class): a class that represents a layer that has all necessary components and functions for CollageImageModel to work properly.
 - RGB (Class): a class that represents the standard red, green, blue pixel that other classes reads in. 
 - HSL (Class): a class that represents the hue, saturation, and lightness of a pixel. This class is used when filters screen and multiply are called.

In the controller package, we have the following interface and classes:
 - CollageController (Interface): an interface that contains all the basic functions a controller class should have.
 - CollageControllerText (Class): a class that represents the controller for text-based interface.
 - CollageControllerGUI (Class): a class that represents the controller for graphic interface

 In the view package, we have the following interfaces and classes:
 - SwingGUI (Interface): an interface that has all the basic functions that a graphic view class would need.
 - SwingGUIView (Class): a class that acts as the graphical representation of the model.

In the misc package, we have the following classes:
 - FileRead (Abstract Class): an abstract that covers all the necessary functions and fields for reading a image file.
 - PPMRead (Class): a class that is able to take in a ppm file and turn it into a 2D array of RGB pixels.
 - PNGRead (Class): a class that is able to take in a png file and turn it into a 2D array of RGB pixels.
 - JPGRead (Class): a class that is able to take in a jpg file and turn it into a 2D array of RGB pixels.
 - PPMWrite (Class): a class that is able to create a ppm file given a 2D array of RGB pixels.
 - FileWrite (Class): a class that is able to create a png or jpg file given a 2D array of RGB pixels.
 - CollageRead (Class): a class that is able to take in a collage file and return a 2D array of RGB pixels and a list of layers that the collage file contains.
 - CollageWrite (Class): a class that is able to create a collage file given a 2D array of RGB pixels and a list of layers in the current project.

## Acknowledgements
#### Images
 - [Elden Ring Wallpaper Background](https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.peakpx.com%2Fen%2Fhd-wallpaper-desktop-eodcn&psig=AOvVaw2XYJRi0lu36rsQD14feonM&ust=1682012069155000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCJjN17C9tv4CFQAAAAAdAAAAABAE)
 - [Pokemon ball](https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.kindpng.com%2Ffree%2Fpokeball-pixel%2F&psig=AOvVaw3pieT_UC9eil9BYcE7p9o9&ust=1682012181355000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCLDbgea9tv4CFQAAAAAdAAAAABAE)
 - tako.png and purple-background.png were made through the GUI.
#### Code
 - [Java Swing Option Panel](https://www.tutorialspoint.com/how-can-i-create-a-dialog-box-in-java-with-yes-no-and-cancel-buttons)
 - [Java Swing Dispalying ppm Files](https://stackoverflow.com/questions/41992006/creating-a-ppm-image-to-be-written-to-a-file-java)
 - [Writing ppm Files](https://www.w3schools.com/java/java_files_create.asp)
 - [Writing png and jpg Files](https://docs.oracle.com/javase/tutorial/2d/images/saveimage.html)
#### Other
 - [Creating UML Diagram](https://www.jetbrains.com/help/idea/class-diagram.html#manage_class_diagram)
 - [Awesome Readme Templates](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
 - [Awesome README](https://github.com/matiassingers/awesome-readme)
 - [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)


## Authors
 - [@hoganchoi](https://github.com/piggytoad)
 - [@sidarthkulkarni](https://github.com/ksidarth)

