# 🎮 Tic Tac Toe Game (Java Console)

A clean and modular **Tic Tac Toe game** built in Java using **Object-Oriented Design** and the **Facade Design Pattern**.

This project demonstrates good software design practices including **Separation of Concerns**, and basic **AI using Minimax Algorithm**.

---

## 🚀 Features

* ✅ Human vs Human mode
* ✅ Human vs Computer mode
* ✅ Smart AI (Minimax Algorithm)
* ✅ Input validation with custom exceptions
* ✅ Clean architecture using design patterns
* ✅ Console-based UI

---

## 🏗️ Design Patterns Used

### 1. Facade Pattern

* `GameFacade` simplifies the interaction with the system
* Handles game setup, player creation, and flow control

---

## 📂 Project Structure

```
com.amitesh.tictactoe
│
├── facade
│   └── GameFacade.java
│
├── model
│   ├── Board.java
│   └── Player.java
│
├── strategy
│   ├── MoveStrategy.java
│   ├── HumanMoveStrategy.java
│   └── ComputerMoveStrategy.java
│
├── service
│   ├── Game.java
│   └── InputHandler.java
│
├── util
│   └── Validator.java
│
├── exception
│   └── InvalidInputException.java
│
└── TicTacToeGame.java (Main class)
```

---

## 🧠 AI Logic (Minimax)

The computer player uses the **Minimax algorithm** to:

* Maximize its chances of winning
* Minimize the opponent’s chances
* Always play optimally

---

## ▶️ How to Run

### 1. Clone the repository

```
git clone https://github.com/your-username/tic-tac-toe.git
```

### 2. Compile

```
javac com/amitesh/tictactoe/TicTacToeGame.java
```

### 3. Run

```
java com.amitesh.tictactoe.TicTacToeGame
```

---

## 🎯 Game Flow

1. Choose game mode
2. Enter player names
3. Choose symbol (X/O)
4. Play turns until:

   * One player wins 🏆
   * Or game ends in draw 🤝

---

## ⚠️ Validation Rules

* Names must contain only letters
* Positions must be between 1–9
* Cannot play on already occupied cells

---

## 💡 Future Enhancements

* Difficulty levels (Easy / Medium / Hard)
* GUI version (JavaFX / Swing)
* Multiplayer (Network-based)
* Score tracking system
* Unit testing (JUnit)

---

## 👨‍💻 Author

**Amitesh**

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!
