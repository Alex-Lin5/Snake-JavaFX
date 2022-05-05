
## Setup
#### Environment
- This project is developed under OpenJDK 17
- Applied JavaFX 17 library
- Using IDE of IntelliJ

If you just want to play this game, you can download the binary file
in release tab, otherwise you will have to set up the environment.

#### Gaming
- provide a seed to the application, or leave empty let the application 
decide by itself
- set up the initial length of snake
## Roadmap
- Implement multi-snake initialization and gaming
- Write the trail of snake to hard disk in certain strategy

## Feature
- snake moves **SMOOTHLY** by sliding in pixels rather than blocks
- snake does not move in opposite direction as last moment
to go through the body
- snake can **REST**, and then keep moving as 
instructed by player
- score is provided to indicate foods the snake has eaten
- game over if snake bites itself, press `ENTER` to **RESTART** the game
- in-app debugger allows to manipulate the snake back and forth,
even if the snake is dead

## Architecture
This project applies Model-View-Controller, and Singleton design pattern. 

Table below concludes the process of instantiating different objects.

|    Main     |||||
|:-----------:|:----------------:|:-----:|:---:|:---:|
|   &#8595;   |||||
| Initializer |    &#8594;     | Threadloop|  |
|             | |   &#8595;   | |
|||Board|&#8594;| Snake|
||||&#8594;| Food|
|||&#8595;|||
|||Recorder|&#8594;|Trail|
|||&#8595;|||
|||Painter|||
|||&#8595;|||
|||Debugger|||
|||&#8595;|||
|||Messenger|||


<!-- |    Main     |||||
|:-----------:|:----------------:|:-----:|:---:|:---:|
|   &#8595;   |||||
| Initializer |    &#8594;     | Threadloop|  |
|             | |   &#8595;   | |
|||Board|&#8594;| Snake|
||||&#8594;| Food|
|||&#8595;|||
|||Recorder|&#8594;|Trail|
|||&#8595;|||
|||Painter|||
|||&#8595;|||
|||Debugger|||
|||&#8595;|||
|||Messenger||| -->

#### Model-View-Controller
- Point is the basic unit indicates the coordination of objects
- Snake and food are instantiated on the board
- Board, debugger, recorder are instantiated on the thread
- Initializer is instantiated as the JavaFX application is started,
and whenever the game is restarted
- Class Trail stores the history behavior of snake in 
length, head, score regarding the time stamp
- Painter will paint the GUI of board, Messenger will provide
information of snake

#### Singleton
## Demo
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
- Snake might bite itself head-to-head when user invokes the snake
to move in opposite direction of snake body as snake status changes
from resting to moving

## Acknowledge
- Professor Nadeem Ghani \
  https://ecs.syracuse.edu/faculty-staff/nadeem-ghani

- snake project by @Subh0m0y \
https://github.com/Subh0m0y/Snake

