# COMP2042_CW_hcytt2
This is my 2048 game coursework from the Software Maintance module

Name: Trang Thanh Mai Duyen

Student ID: 20401942

## Running 2048 game in your computer 

After installing Git, you can clone the source code by using this command:

``` 
git clone https://github.com/NecoPottsun/COMP2042_CW_hcytt2.git
```
Or if you are using Github Desktop, simply do: `File->Clone Repository->URL` and paste the link `https://github.com/NecoPottsun/COMP2042_CW_hcytt2.git` in the `URL or username/reposity` box

## Javadoc documentation path

the Javadoc documentation is located in:

## List of features that are implemented and are working

- Loading process when open the game.
- The player now can choose the background color for their Game Screen.
- The player now can move the cells either by WASD or key arrows.

  (**Note**: If the key arrows are not working properly, try pressing `ctrl + key arrow`, for example: `ctrl + key down`)
- Time counter of each game.
- Score board of the game: show the high score players achieving from an excel file.
- 2 modes of game: `easy mode`: 5x5 board , `hard mode`: 4x4 board, and the play time limit is 1 minute.
- If the player has the highest score, he/she will be announced when end the game.
- The player now can listen to the soundtracks when play the game.
- The player can change, pause the soundtrack and adjust volumn at any Scene.
## List of features that are implemented and are not working properly

- The data can be written and read correctly in the Excel file using `poi` library (tested). However, when I open the excel file, the data is not saved even there is an overwrite excel file method to handle it. But when achieve data again, the data is the same as overwritten data.

## List of features that not yet implemented


## List of new Java classes

- LoadingController.java
- MenuController.java
- EndGameController.java
- RankSceneController.java
- Interface Dao<T>.java
- AccountDao.java
- AccountDaoTest.java 
- MusicPaneController.java

## List of modified Java classes 

- Main.java
- Account.java
- EndGame.java
- GameScene.java

In the GameScene, add `createMusicPane()`, `simplerTimer()`.
Instead of calculating the score based on the sum cells number, 
now when the cells are merged the score will be calculated which is the `mergedCellScore` variable modified in 
`isMergedHorizontalle()` and `isMergedVertically()`
