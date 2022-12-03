# COMP2042_CW_hcytt2
 This is my 2048 game coursework from the Software Maintance module

Name: Trang Thanh Mai Duyen
Student ID: 20401942

## Running 2048 game in your computer 

After installing Git, you can clone the source code by using this command:

``` 
git clone https://github.com/NecoPottsun/COMP2042_CW_hcytt2.git
```
Or if you are using Github Desktop, simply do: File->Clone Repository->URL and paste the link `https://github.com/NecoPottsun/COMP2042_CW_hcytt2.git` in the 'URL or username/reposity' box

## Javadoc documentation path

the Javadoc documentation is located in:

## List of features that are implemented and are working

- Loading process when open the game.
- Score board of the game: show the high score players achieving from an excel file.
- 2 modes of game: `easy mode`: 5x5 board , `hard mode`: 4x4 board

## List of features that are implemented and are not working properly

- The data can be written and read correctly in the Excel file using `poi` library (tested). However, when I open the excel file, the data is not saved even there is an overwrite excel file method to handle it. But when achieve data again, the data is the same as overwritten data.

## List of features that not yet implemented

- The game time: in the `hard mode`, each move is limited by time (about 30s). If the player doesn't move, it will be moved randomly. 
(This feature haven't implemented yet due to the time limitation)
- Soundtrack: I want to add this feature to the game, to make the player more interested to the game but I don't have time to do it.

## List of new Java classes

### LoadingController.java class

Whenever the player run the game, it will show up a loading screen first, after the progress bar reaches 100%, the system will jump to the Menu Screen.

### MenuController.java class

MenuController.class is created to handle the MenuScene.fxml, which is the Menu Screen of the game. Menu Screen has 1 field text to enter the player’s name, 4 buttons: easy, hard, score board, quit. Click easy button to play a 2048 game with 5x5 board, click hard button to play a more challenging game which is 4x4 board. As the smaller board makes the game harder (harder for achieving scores). Click the score board to jump to the RankScene.fxml which is display a list of player with their scores in descending order.

### EndGameController.java class

EndGameController class is to handle the end game screen at the end. This class is created to as a controller of EndGameScene.fxml which now replace the EndGame.java, The end game screen is now added two more buttons(there are 3 buttons now: `Score boar` button: to jump to the Rank Screen, one is back button: go back the Main Screen, one is quit button: quit the game) and some decorations: background, buttons, text color,icons,….

### RankSceneController.java class

This is to display the scoreboard. When the player click to the `Score board` button, the system will display a list of Account showed the username and the players score in a table in descending order.

### Interface Dao<T>
 
Add new Dao interface as a generic class (which can read any datatype) - Dao<T> with basic methods for achieving, find a specific, delete, show data. 
 
 
### AccountDao.java class

- AccountDao.java implements Dao interface is to handle those functions for the achieving Account List of the game.  The AccountDao follows the Singleton, because it only created once (we only use it as a single object, not multiple objects). 
 - The main purpose of creating AccountDAO.class is to handle the functions that achieve data from the excel file as well as write data to the file so that we can also add and delete a specific account in the file. That satisfy the requirement of creating a permenant high score list.
 
 
## List of modified Java classes 

 ### GameScene.java 
 
 - `passDestination(int i, int j, char, direct)` method, since it is long and hard to understand, so I break it don to make it easier to read. 
   I breakdown it into: `passLeft(args)`, passRight(args), `passUp(args)`, `passDown(args)` 
