# appimageprocessing
### Objective:
To demonstrate multi-threading concepts by building useful app [ImageProcessing App]

Concepts:
1. Thread Creation:
2. Thread Synchronization
3. Thread Pooling
4. Thread Communication
5. Thread Interruption
6. Thread Safety in Shared Data

By applying these concept, we can provider faster results and a smoother user experience.
It’s generally a good practice to organize your code into separate classes based on their responsibilities and functionality. This helps in code maintenance, reusability, and readability. 

#### structure of classes

1. Main Class: This class serves as the entry point of the application. It can handle initialization, user input, and manage the overall flow of the program.
2. ImageProcessingApp Class: This class represents the image processing application itself. It contains methods for loading images, applying filters, displaying the GUI, and handling user interactions. It can also manage the multi threading aspects of image processing.
3. ImageProcessor class: this class encapsulates the logic for processing individual images. ImageProcessor class implements runnable interface.  It can have methods for applying different filters or transformations to an image. The ImageProcessor class can be used by the ImageProcessingApp class to process images concurrently using multiple threads.

In this. Project, the GUI is created using Swing components.
When the user clicks the "Upload Image" button, a file chooser dialog is displayed. Once the user selects an image file, it is passed to the processImage method, where the image processing logic is applied, and the processed image is saved.The output is displayed in the outputTextArea component.

