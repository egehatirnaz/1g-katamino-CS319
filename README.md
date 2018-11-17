# Only Eray Can Judge Me (Group 1G)
## CS 319 Project - Katamino
## Section - 01

### Project Group Members
1) Yusuf Samsum
2) Fırat Yıldız
3) Mehmet Mert Epsileli
4) Burak Korkmaz
5) Ege Hatırnaz

### Project Topic
This repository is created for CS-319 2018 fall semester course project. The project's topic is implementing Katamino game with some additional concepts such as leaderboard with respect to time, and a new Challenge mode with an extra piece or pieces to make the game harder.

### What is original Katamino?
Katamino is a puzzle game about geometrical perception. The game has 12 unique blocks with different shapes, a guidebook, a separator, and a board. The board is a typical rectangle. The board’s width is filled by 5 squares and its length is filled by 13 squares. There is a guidebook for each level and this book shows which shapes might be used for that level. The separator shows the current level of the game and it determines the limits of the shape. The levels are 3 to 12. The game's aim is to fill the squares with unique blocks which determined by guidebook with respect to current level. Each block consists of 5 cubes and these cubes merged in various types, so they are unique by their way of merging.
The game's purpose is to fill the blank squares with unique blocks.The player should take the guidebook for deciding which level to play. Then, the player should start with the first level, which is 3, and he/she should use only given blocks which specified by the guidebook. The player should pass each level after he/she finished it. The levels' specifications should be followed by the guidebook.[1]

### Project Requirements
* Java 8 (Language)
* Visual Paradigm (Diagrams)
* Balsamiq (Mockups)
* PostgreSQL (Database)
* WebGL (GUI)

### What will we do?
Java 8 will be used during the project as programming language. Eclipse will be used as IDE for implementing the program. Also, diagrams will be drawn on Visual Paradigm for reports and mockups will be drawn on Balsamiq 3 for user interface. PostgreSQL will be used for database system that will be used in program. Our plan is to run the program on any web browser. Therefore, GUI will be implemented by JavaScript Graphics Library(WebGL).  

### The Differences Between The Real Game and Our Project:
Time: We are planning to add time feature in order to compare the success of different users. The time will be shown on the upper right side of the screen. It will work like a timekeeper. In some modes of game, players will be given total time to complete the whole missions(different expanding sizes of field in each play). The ones who use their time efficiently may transfer time for the next game. That is also included the challange mode and makes the game more fun!

Nickname: Every user will have a unique nickname in order to be shown in leaderboard. Nickname will have to be determined before the game starts by the player. 

Leaderboard: The leaderboard will contain nicknames and their times. Sorting of the users will be determined with respect to their times.

Refresh button: As mentioned, every level has more than one solution. Our game does not contain a guideline as original game. Our game will assign a random solution set instead of showing a guideline. In addition, players can refresh their solution, which means that the player can demand a new solution set by clicking refresh button. However, if a user clicks the refresh button, the time will be increased 5 seconds as a punishment.

Challenge mode: Game usually assign a solution set with a proper number of blocks for players. In this mode, game will assign a solution set with 2 addition useless blocks. The challenge mode’s leaderboard will be different from normal game mode’s leaderboard.

Giving Hints: Players who don't succeed to find the solution are given hints like putting a piece into it's place. That offers the player one of the specific solutions defined by designers.

Improve the Game: Players have chance to add new solutions which did not forsee by designers. That also makes the game interactive. 

Game's Learning Machine: The machine inside the game will learn the player's actions and offer them new games according to their preferences. Also that will help players to add new solutions. Some solutions which is learned from some (more intellegent) players will expand the game solutions. These solutions will also be offered other players and make game more user-centered. 


### References
[1] "Description of Katamino". http://en.gigamic.com/game/katamino [Accessed: 9 October,2018]


[2] "Gigamic Katamino Classic Puzzle and Game". https://www.tarquingroup.com/gifts/gigamic-katamino-classic-puzzle-and-game.html [Accessed: 8 October,2018]



[3] "Visual-spatial Games for Active Brains". https://www.imacs.org/blog/2011/07/visual-spatial-games/ [Accessed: 8 October,2018]



[4] "The Theory of Tetris". http://liacs.leidenuniv.nl/~kosterswa/tetris/tot.pdf [Accessed: 9 October,2018]



[5] "A CLOSER LOOK AT TETRIS: ANALYSIS OF A VARIANT GAME". http://euclid.trentu.ca/aejm/V4N1/Tsuruda.V4N1.pdf [Accessed: 9 October,2018]




