# Snake Game

This project implements the classic Snake game in Java, featuring a graphical user interface (GUI) and various game mechanics.

## Overview

The game allows players to control a snake that moves around the screen, eating apples to grow in length. The objective is to grow the longest snake possible without colliding with the game boundaries or the snake's own body.

## Files and Descriptions

- **Apple.java**: Represents a basic game object that appears at a random position on the game court. It is displayed as a red circle.
- **Direction.java**: Contains an enumeration called `Direction`, which is used in `GameObj.java` to indicate the direction an object should move after it collides with another object.
- **Game.java**: The main entry point of the game. This file contains the main method that initializes the game and starts the game loop.
- **GameCourt.java**: Manages all the actions that occur on the game grid. This includes handling user input, updating game objects, and checking for collisions.
- **GameObj.java**: Defines the basic properties of an object in the game, such as position, velocity, size, and bounds. It also manages the movement and ensures that objects stay within their defined bounds.
- **GameTest.java**: Contains JUnit tests that validate the functionality of various game components, ensuring the game operates as expected.
- **RunSnake.java**: Defines the main GUI components, specifying the frame and widgets of the game interface.
- **Snake.java**: Represents the snake object, starting in the upper left corner of the game court. The snake is displayed as a green rectangle and grows in length as it consumes apples.

## Features

- **2D Game Grid**: The game board is represented by a 2D grid, where each cell can contain a part of the snake, an apple, or empty space.
- **Dynamic Snake Movement**: The snake moves in a specified direction and grows longer when it eats an apple. The game ends if the snake collides with the boundaries or with itself.
- **Score Tracking**: The player's score increases with the length of the snake. The highest score is stored between game sessions using file I/O.
- **JUnit Testing**: The game includes unit tests for key components to ensure stability and correct functionality.

## How to Run

To run the Snake game:

1. Compile all Java files: `javac *.java`
2. Execute the game using: `java Game`

Ensure that you have Java Development Kit (JDK) installed.

## Future Improvements

- **Enhanced Graphics**: Implement graphical enhancements for a more visually appealing game.
- **Multiplayer Mode**: Introduce a multiplayer mode where players can compete on the same screen.
- **AI Integration**: Add an AI-controlled snake to compete against the player.

## License

No license

## Contributing

Contributions are welcome! If you find bugs or have ideas for new features, feel free to fork the repository and submit a pull request.
