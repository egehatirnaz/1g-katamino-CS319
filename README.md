# Only Eray Can Judge Me (Group 1.g)
## CS 319 Project - Katamino
## Section - 01

### Project Group Members
1) Yusuf Samsum
2) Fırat Yıldız
3) Mehmet Mert Epsileli
4) Burak Korkmaz
5) Ege Hatırnaz

### About Project
This repository is created for CS-319 2018 fall semester course project. The project's topic is implementing Katamino game with some additional concepts such as leaderboard with respect to time, and a new Challenge mode with an extra piece or pieces to make the game harder.

### What is Katamino?
Katamino is a puzzle game about geometrical perception. The game has 12 unique blocks with different shapes, a guidebook, a separator, and a board. The board is a typical rectangle. The board’s width is filled by 5 squares and its length is filled by 13 squares. There is a guidebook for each level and this book shows which shapes might be used for that level. The separator shows the current level of the game and it determines the limits of the shape. The levels are 3 to 12. The game's aim is to fill the squares with unique blocks which determined by guidebook with respect to current level. Each block consists of 5 cubes and these cubes merged in various types, so they are unique by their way of merging.

### Project Requirements
As mentioned above, the game's purpose is to fill the blank squares with unique blocks.
The player should take the guidebook for deciding which level to play. Then, the player should start with the first level, which is 3, and he/she should use only given blocks which specified by the guidebook. The player should pass each level after he/she finished it. The levels' specifications should be followed by the guidebook.

### The Differences Between The Real Game and Our Project:
In the real “Katamino” game, players play the game without a prize system. In the one we will develop, players may gain points by the using of time efficiently. Moreover, game will have different modes such as there is a precise time for players to complete the whole missions (different expanding sizes of field in each play) by transferring time to the next mission so time
takes a significant role in the game too which is also absent in the real game experiment.

Players will use nicknames in the game and they will be ranked depending on their time usage.

What’s more, players have chance to improve the game while finding new solutions. Otherwise, they will get hints which are kind of helps depend on our solutions. Players also have a chance to either choose their cards which leads them in their game or given a random card by the system.

### What feautures do we plan adding to Katamino?
* **Katamino with multiple players**: Katamino might be played with two players. The game needs to be added another separator so, two separators will be located opposite numbers. For example, if first one is on the 3 another one is on 9. Nonetheless, players can play at the same time and after level determines, players will begin at the same time and the winner will be the quickest player of the current level. After a level finished the separators will close 1 level to each other. That means levels will be 4 and 8. The game's winner will be determined by the number of victories.

* **Joker piece feature**: In this version, players have a set of pieces in their hand and they have a chance to get more pieces. Players may get more points when they finish the level they played earlier or with less action. They will buy the pieces which are useful for them with the help of these points. They can also play the game in the field they wanted with spending these game points.

* **Sending and barter pieces**: Players may also play the game without being rival. They can play individually and support each other to play more by sending gifts like a point or the pieces which are useless. All the pieces may be bartered between the players according to their levels in the game.

* **Katamino with more difficult logic**: Katamino board's width is a constant while its length is dynamic. In this version, the board's width will be dynamic either. That means that a player can play 6x4 version of the game. That creates many combinations for playing types and that creates a necessity the number of cubes of the block for each level. In the classic version, Katamino's blocks consist of 5 cubes but in that version, it can consist of 3,4,5,6... There will be no limit.

* **Making music or picture by playing the game**: All movement or action may represent a sound which are small parts of a song. The song may automatically be separated by the software.

* **Getting hint**: All the pieces may carry a part of an image that is included in the computer but they are invisible while players play the game. In the hard situations, player may get hints from these photo kind of puzzle in order to complete the mission.

### References
[1] - "Description of Katamino". http://en.gigamic.com/game/katamino  [Accessed:9 October,2018]



