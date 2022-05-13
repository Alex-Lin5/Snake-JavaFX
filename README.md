
## Setup
This project is developed under OpenJDK 17,
applied JavaFX 17 library,
using IntelliJ as IDE.
#### Environment

If you just want to play this game, you can download the binary file
in release tab, otherwise you will have to set up the environment. 
For development purpose, please follow the instruction below.

- First, install JDK, you can download from [OpenJDK](http://jdk.java.net/java-se-ri/17)
- Go through the official JavaFX getting started guide from [here](https://openjfx.io/openjfx-docs/#introduction)
  - The guide provide basic hello world demonstration on system terminal
  - If you use certain IDE or build tool, you can skip to specific topic
- For IntelliJ environment
  - include two more modules to compile 
  the project by adding VM options on configurations,
  it is default hidden on the user interface in Windows OS, you can find it
  in the top-right corner modify options
  - In project structure, set the language level to 17 or greater, or default
- Run or debug project!

#### Gaming
- provide a seed to the application, or leave empty let the application 
decide by itself, the random seed is for specific snake spawn point and 
food generation pattern
- set up the initial length of snake
- control the snake by four arrow keys, press `DIGIT 1` to pause moving
- press `DIGIT 0` to enable debugger mode, press `PAGE_UP` and `PAGE_DOWN`
to move snake back and forth
- score is provided to indicate foods the snake has eaten

## Feature
- snake moves **SMOOTHLY** by sliding in pixels rather than blocks
- snake does not move in opposite direction as last moment
to go through the body
- snake can **REST**, and then keep moving as 
instructed by player
- game over if snake bites itself, press `ENTER` to **RESTART** the game
- in-app debugger allows to manipulate the snake back and forth,
even if the snake is dead

## UseCase
This project is designed to be a guide for cross-platform
open-source small game development in Java,
by implementing the object-oriented design methodology and typical
design patterns like model-view-controller, singleton, template etc. 
The basic function of game should be included in all cases,
e.g., game restart and pause, progress save and load, GUI design.

## Roadmap
- Implement multi-function foodBase on board, like invincible status, 
greater score, speed fruit
- Implement snake molting behavior, reduce snake length on regular timely
manner
- Implement multi-snake initialization and gaming, new snake can be added
while the game is running
- Implement game process loading and automatic saving function, 
file I/O involved

More?

## Architecture
This project applies Model-View-Controller, adapter and Singleton design pattern. 

Table below concludes the process of instantiating different objects.

|    Main     |||||||||
|:-----------:|:----------------:|:-----:|:---:|:---:|:---:|:---:|:---:|:---:|
|   &#8595;   |||||||
| Initializer |    &#8594;     |Threadloop|
|| &#8594;|Pen|---|[Color]|||||
|             |    &#8594;     |  Keypad| &#8594; | (Initializer)||  
|             | |   &#8595;   | |||
|             ||Board |&#8594;| Snake|***|RectPoint|+++|BasePoint
||||   &#8594;   | Food|||
|||   &#8595;   ||||
|||   Engine   || || ||
|||   &#8595;   ||||
|||  Recorder   |&#8594;|Trail||
|||   &#8595;   ||||
|||  Debugger   ||||
|||   &#8595;   ||||
|||   Painter   ||||
|||   &#8595;   ||||
|||  Messenger  ||||


### Model-View-Controller
#### - Model
- Package Point is the basic unit indicates the coordination of objects, like
snake and food
- BasePoint provide equals and hashCode method to support the manipulation of 
Collection
- RectPoint is inherited from BasePoint, all objects calls the RectPoint as
their attributes
- Might implement HexPoint inherited from BasePoint as another approach to 
operate model
- Keypad receive keyboard input event and respond to snake and other objects
- snakeList and foodList are instantiated on the board
- Food is enumed as different types of functional food, and stored in foodList
- Snake performs grow, move and molt as it is included in the class file 
#### - View
- Enum Color with several provide color like black, red and white etc., each
one stores a web value as string passed to member method in pen
- Color is passed to snake and food as static object as part of attribute
- Painter will paint the GUI of board, snake body is partially painted, the
head and tail is painted independently for sliding through the grid
- Messenger will provide information of game, like snake info including name,
color, score and status, and debugger status indication


####    - Controller
- Initializer is instantiated as the JavaFX application is started,
and whenever the game is restarted
- Engine initializes and manipulates snake and food in certain rule
- Class Trail stores the history behavior of snake in 
length, head, score, and food location regarding the time stamp
- Recorder controls the process of class trail recording in steps
- debugger is not working if engine is running, the status is indicated by
a boolean value. While the debugger is on, it checks the current status and 
setup the snake and food step by step, then cleans out the history behine the
current step

### Adapter
- Class Pen implement Toolkit interface including the basic method required
for drawing and texting, it isolated the code of project and JavaFX libray code.
- User-defined class Color is provided as enum that passed to the method in 
class Pen

### Singleton
Initializer, engine, recorder, debugger, painter, messenger, pen are instantiated
once in the application in 1 thread. Basically thye are the
objects placed in first and second column of the instantiating table.
## Demonstration
![link](Images/Play.png "Title text") \
Playing video

![link](Images/Game_over.png "Title text") \
Game over

![link](Images/GUI_crashed.png "Title text") \
GUI crashing
## Issues
- GUI might crash after a few minutes of gaming depending on the 
machine, but the logic behind the GUI is working normally.
Restart the application will refresh the GUI and play again.
  - More than 1 objects are running parallel on the background, such as
  Painter and messenger. The application keeps instantiating new
  objects.
- Snake might bite itself head-to-head when user invokes the snake
to move in opposite direction of snake body as snake status changes
from resting to moving
- You might rebuild the entire project, if the IDE throws out
error that certain package is not found

## Acknowledge
- Professor Nadeem Ghani \
  https://ecs.syracuse.edu/faculty-staff/nadeem-ghani
  - Provide suggestion of overriding equals and hashcode method,
  and idea of possible HexPoint implementation as fundamental logic regarding
   Point class
  - Provide idea of instantiating a static board to be referenced
  by other objects

- snake project by @Subh0m0y or @hungrybluedev \
https://github.com/Subh0m0y/Snake
  - Provide architecture of game restart and initialization function
  - provide architecture of point, snake, board class relationship

