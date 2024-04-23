# Drone-Simulation

This project is a drone simulation that displays various drones and other objects moving around an arena using a graphical user interface (GUI). The main goal of this project is to design and implement a JavaFX GUI using the Eclipse IDE.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [OOP Design](#oop-design)
- [Improvements and Extensions](#improvements-and-extensions)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction
The drone simulation project allows users to observe the movement and interaction of drones and obstacles within an arena. The GUI provides a visual representation of the simulation and allows users to interact with the application.

## Features
- Multiple drones and obstacles moving around the arena
- Graphical user interface built with JavaFX
- File handling for saving and loading simulation configurations (partially implemented)
- Homepage, arena view, and about page

## OOP Design
The project follows an object-oriented programming (OOP) design with the following main classes:

- `Drone_type`: Represents an entity that moves around the arena, with attributes such as position, unique identifier, speed, and direction. It includes a `hit()` method to handle collisions between entities.
- `Drone`: Inherits main attributes from `Drone_type` and represents a specific drone in the simulation.
- `Obstacle`: Inherits main attributes from `Drone_type` and represents an obstacle in the arena that does not move.
- `DroneArena`: Represents the arena in which the drones fly, with attributes such as size and a collection of drones. It includes a random number generator for placing drones at random positions and directions.
- `GUI`: Provides a character-based representation of the `DroneArena` and the drones within it, using a 2D array of characters.
- `GUICanvas`: Serves as the main program for the simulator, providing the console interface for user commands and displaying the drone canvas and relevant information.

## Improvements and Extensions
While the project meets the main requirements and is robust and user-friendly, there are several areas for improvement and potential extensions:

- Complete the file handling functionality to enable saving and loading simulation configurations in the GUI.
- Add innovative features and enhancements to make the drone simulation more engaging and unique.
- Implement a specific theme for the simulation, such as a space theme with spaceships and a space background.

## Installation
To run the drone simulation project, follow these steps:

1. Clone the repository: `git clone https://github.com/your-username/drone-simulation.git`
2. Open the project in Eclipse IDE.
3. Make sure you have JavaFX properly set up in your Eclipse environment.
4. Build the project and resolve any dependencies.

## Usage
1. Run the `GUICanvas` class as the main program.
2. Interact with the drone simulation using the provided GUI controls.
3. Explore the different features and observe the movement of drones and obstacles in the arena.

## Contributing
Contributions to the drone simulation project are welcome. If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License
This project is licensed under the [MIT License](LICENSE).
