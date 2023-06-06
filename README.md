# tic tac toe
A classic game of tic tac toe with multiple gamemodes and difficulties
---
#### Gamemodes available
---
* Player vs Player
* Player vs AI
* AI vs AI
---
#### AI difficulties
---
* `easy` AI => the AI chooses random coordinates on the board if the space is available
* `medium` AI => the AI chooses random coordinates on the board if the space is available and also there's no one winning; if the opponent almost won (is 1 symbol away from winning) the `medium` AI will block it and it will also place a symbol for itself to win if it is 1 symbol away from winning
* `hard` AI => the AI uses the minimax algorithm to get its coordinates, choosing the best move possible for the given board; (you as a human, its near impossible to win, at best you can make a DRAW)
---
#### Here is an example game being played by a real player and a hard AI
---
```
Input command: > start hard user
Making move level "hard"
---------
|       |
| X     |
|       |
---------
Enter the coordinates: > 2 2
---------
|       |
| X O   |
|       |
---------
Making move level "hard"
---------
|   X   |
| X O   |
|       |
---------
Enter the coordinates: > 3 2
---------
|   X   |
| X O   |
|   O   |
---------
Making move level "hard"
---------
| X X   |
| X O   |
|   O   |
---------
Enter the coordinates: > 3 1
---------
| X X   |
| X O   |
| O O   |
---------
Making move level "hard"
---------
| X X X |
| X O   |
| O O   |
---------
X wins

Input command: > exit
```
