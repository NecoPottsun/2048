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
- The player now can choose the background color for their Game Screen.
- The player now can move the cells either by WASD or key arrows.
  (Note: If the key arrows not working properly, try pressing `ctrl + key arrow`, for example: `ctrl + key down`)
- Score board of the game: show the high score players achieving from an excel file.
- 2 modes of game: `easy mode`: 5x5 board , `hard mode`: 4x4 board

## List of features that are implemented and are not working properly

- The data can be written and read correctly in the Excel file using `poi` library (tested). However, when I open the excel file, the data is not saved even there is an overwrite excel file method to handle it. But when achieve data again, the data is the same as overwritten data.

## List of features that not yet implemented

- The game time: in the `hard mode`, each move is limited by time (about 30s). If the player doesn't move, it will be moved randomly. 
(This feature haven't implemented yet due to the time limitation)
- Soundtrack: I want to add this feature to the game, to make the player more interested to the game but I don't have time to do it.

## List of new Java classes

- LoadingController.java
- MenuController.java
- MenuController.java
- EndGameController.java
- RankSceneController.java
- Interface Dao<T>.java
- AccountDao.java
- AccountDaoTest.java 
 
## List of modified Java classes 

- Main.java
- GameScene.java 
- Account.java
- EndGame.java
