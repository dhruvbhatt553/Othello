# Othello Game

![image](https://github.com/user-attachments/assets/1a01d2ad-5163-4724-a860-a217297d76c1)


## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Rules of Othello](#rules-of-othello)
- [Algorithms](#algorithms)
- [Installation](#installation)
- [Usage](#usage)
- [Future Plans](#future-plans)

## Overview

This is a digital implementation of the classic Othello board game, featuring both Single Player and Double Player modes. In Single Player mode, the CPU makes decisions using the alpha-beta pruning algorithm. The game interface includes a fully interactive game board, scoreboards, and a game log to track moves.

## Features

- **Single Player Mode**: Play against the computer, which uses alpha-beta pruning to decide its moves.
- **Double Player Mode**: Play against another person locally.
- **Interactive UI**: An 8x8 game board, real-time score tracking, and a game log for move history.
- **Planned Enhancements**: Future implementation of advanced algorithms like Monte Carlo Tree Search (MCTS).

## Rules of Othello

Othello is played on an 8x8 grid with black and white discs. Players take turns placing their discs on the board, aiming to enclose one or more of the opponent's discs between their own discs in a straight line. The enclosed discs are flipped to the player's color. The game ends when neither player can make a valid move. The player with the most discs of their color on the board at the end wins.

## Algorithms

### Alpha-Beta Pruning

In Single Player mode, the CPU uses the alpha-beta pruning algorithm, a more efficient version of the minimax algorithm. It reduces the number of nodes that are evaluated by the minimax algorithm in its search tree, optimizing decision-making.

### Monte Carlo Tree Search (MCTS) - *Planned*

MCTS is an advanced algorithm that balances exploration and exploitation. It will simulate many possible outcomes of a move to determine the most effective strategy, offering a stronger and more challenging opponent.

## Installation

To install and run the game locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/dhruvbhatt553/Othello.git
   ```
2. **Navigate to the project directory (Inside Othello folder)**:
   ```bash
   cd bin
   ```
3. **Run OthelloGame**:
   ```bash
   java OthelloGame
   ```

5. **Run the game**:
   - Choose Single Player or Double Player and start to play

## Usage

- **Single Player Mode**: Start the game and choose to play against the CPU.
- **Double Player Mode**: Start the game and select two players to play locally.
- **Scoreboards**: The interface will automatically update the scores as the game progresses.
- **Game Log**: Track all moves made during the game.

## Future Plans

- **Implement Monte Carlo Tree Search (MCTS)**: To provide a more challenging single-player experience.
- **Online Multiplayer Mode**: Play against friends or other players online.
- **Enhanced UI/UX**: Improve the interface for a more engaging experience.
- **AI Difficulty Levels**: Add multiple difficulty settings for the CPU opponent.
