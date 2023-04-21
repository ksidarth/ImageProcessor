# USE ME:
This is the USEME file. Here, we documented how to properly run the provided jar file and how to 
interact with our user interfaces (text-based or graphic-based).
### How to Run JAR File:
In order to run the given jar file (IMG.jar), open the \res\ folder in terminal (UNIX, LINUX, etc). 
From there, you can type in these three commands-lines to run the jar file:
 - java -jar IMG.jar [path-of-script-file] --after typing the jar file, input the path to a script file you wish to use. All the scripts are located in \res\scripts. When executed, saved files will be present in either \res\script_images or \res\collage_files.
 - java -jar IMG.jar text --by typing "text", you will be able to interact with the text-based interface. 
 - java -jar IMG.jar --by inputting nothing, the GUI will be loaded by default. You can also access the graphical interface by double-clicking the jar file without opening terminal. 
### Running Scripts:
The script files should be located in the \res\scripts folder. Here, there will be five script text 
that do the following:
 - elden-ring-multiply-filter.txt --applies multiply filter to a png file with a purple background
 - elden-ring-screen-filter.txt --applies screen filter to a png file with a purple background
 - pokemon-load-collage.txt --loads a collage file, adds a layer, and save newly edited image
 - tako-blend-filter.txt --applies blend filter to a png file with a blue background
 - tako-green-filter.txt --applies green filter to a png file
### How to Use Interface:
1. Create a new project and enter dimensions. Or you can load a project(select a .collage file).
2. Add a layer. Enter its name. 
3. Add an image to the layer. You should be able to see the image now.
4. You can now set the filter to whatever you want and see how the image changes. 
5. Repeat steps 2-5 to continue adding and changing images.
6. When done, click Save Image or Save Project(some faults here). For project files enter the .collage extension. Save Image supports ppm, png, and jpg file saving.
### Command Lines for Interface:
#### Text-Based Interface:
When loading the text-based interface, the user will be asked to type in certain commands.
The following are the command-lines that are used in the text-based interface:
 - new-project height width --starts a new collage project with a canvas size of inputted height and width.
 - load-project file-path --loads in a collage project from file path.
 - add-layer layer-name --adds a layer with given name to the canvas.
 - set-filter layer-name filter-name --sets the filter of the inputted layer.
 - add-image-to-layer layer-name image-name x-pos y-pos --adds an image to a selected layer at inputted position.
 - save-image file-name --saves canvas as an image.
 - save-project file-path --saves project as a collage file.
 - quit --closes the application.
#### Graphical User Interface:
When loading the graphical user interface, the user will be greeted with a window with buttons acting
as the text-based command lines. Users can then use the provided action buttons to add and edit images.
An example of a workspace using our application will look something like below.
![Alt text](\example_images\GUI-Working.png)
Each action in the interface does the following:
 - New Project: creates a new collage project
 - Load Project: loads in a collage project
 - Save Image: saves the current working canvas as an image
 - Save Project: saves the project as a collage file
 - Add Layer: adds a layer to the canvas (users will see the layer in the layers box next to the "add layer" button)
 - Set Filter: sets the filter of the given layer
 - Add Image: adds an image to the selected layer with inputted positions
 - Quit: Exits the program
#### Filter Components:
There are 8 filters that the user can apply on the images. Below are the filters and their respective functions:
 - red-component: makes the image red
 - green-component: makes the image green
 - blue-component: makes the image blue
 - brighten-value: makes the image white
 - darken-value: makes the image black
 - blend-with-background: inverts the color of the image by making a difference between the current layer and the layer beneath it
 - multiply: darkens the current layer pixels
 - screen: brightens the current layer pixels
### Notes:
First off, saving a project only works for small images. Since .collage files
save as lines of RGB integers and lines of Strings, the saving time is exponentially
slower for large images, so refrain from saving larger projects.

When entering a filter, enter one of the filters listed in the readMe exactly as it is entered. 
The program will give you an error message if it is an invalid filter. 

Some filters have special requirements.
blend-with-background, multiply, and screen require a background layer to actually work, but 
the program will not tell you this. The program won't crash, but it will turn the image entirely white or entirely black.

There is no undo button, but you can set a filter to 'normal' and go back to its initial state. 


