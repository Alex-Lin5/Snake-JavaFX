## Setup
## Feature
- snake moves **smoothly** by sliding in pixels rather than blocks
- snake does not move in opposite direction as last moment
to go through the body
- snake can **rest** on the game, and then keep moving as 
instructed by player
- score is provided to indicate foods the snake has eaten
- game over if snake bites itself, press `ENTER` to **restart** the game
- in-app debugger allows to manipulate the snake back and forth,
even if the snake is dead

## Architecture
This project applies Model-View-Controller design pattern.

[//]: # (#### Model)
- Point is the basic unit indicates the coordination of objects
- Snake and food are instantiated on the board
- Board, debugger, recorder are instantiated on the thread
- Initializer is instantiated as the JavaFX application is started,
and whenever the game is restarted
- Class Trail stores the history behavior of snake in 
length, head, score regarding the time stamp
- Painter will paint the GUI of board, Messenger will provide
information of snake
## Acknowledge
- Professor Nadeem Ghani \
  https://ecs.syracuse.edu/faculty-staff/nadeem-ghani

- snake project by @Subh0m0y \
https://github.com/Subh0m0y/Snake

