# COMP2042_CW_hcytt2
This is my 2048 game coursework from the Software Maintance module

Name: Trang Thanh Mai Duyen

Student ID: 20401942

## Running 2048 game in your computer 

After installing Git, you can clone the source code by using this command:

``` 
git clone https://github.com/NecoPottsun/COMP2042_CW_hcytt2.git
```
Or if you are using GitHub Desktop, simply do: `File->Clone Repository->URL` and paste the link `https://github.com/NecoPottsun/COMP2042_CW_hcytt2.git` in the `URL or username/reposity` box

Please download the libraries to run the game: 
- JUnit-4.13.2
- Hamcrest-all-1.3
- Hamcret-library-1.3
## Javadoc documentation path

the Javadoc documentation is located in: 
```
COMP2042TrangThanhMaiDuyen\javadoc
```

## List of features that are implemented and are working

- Loading process when open the game.
- The player now can choose the background color for their Game Screen.
- The player now can move the cells either by WASD or key arrows.

  (**Note**: If the key arrows are not working properly, try pressing `ctrl + key arrow`, for example: `ctrl + key down`)
- 4 cells in a same row or a column, that in each 2 cells has the same value now can be merged at a move (for example: 2 2 4 4, move left: 4 8 0 0)
- Time counter of each game.
- Score board of the game: show the high score players achieving from an Excel file.
- 2 modes of game: `easy mode`: 5x5 board , `hard mode`: 4x4 board, and the play time limit is 1 minute.
- If the player has the highest score, he/she will be announced when end the game.
- The player now can listen to the soundtracks when play the game.
- The player can change, pause the soundtrack, can see the progress of soundtrack and adjust volume at any Scene.
## List of features that are implemented and are not working properly

- The data can be written and read correctly in the Excel file using `poi` library (tested). However, when I open the Excel file, the data is not saved even there is an overwrite Excel file method to handle it. But when achieve data again, the data is the same as overwritten data.
  - I have build the project jar file but cannot run it smoothly, it only loads the `LoadingScene` and `MenuScene`

## List of features that not yet implemented

- Change background of the game, I have not done this due to the time limit.

## List of new Java classes

- LoadingController.java
- MenuController.java
- EndGameController.java
- RankSceneController.java
- Interface Dao.java
- AccountDao.java
- AccountDaoTest.java 
- MusicPaneController.java
- SoundtrackDatabase.java
- AccountDaoTest.java
- SoundtrackDatabaseTest.java

## List of modified Java classes 

- Main.java
- Account.java
- EndGame.java
- GameScene.java

In the GameScene, add `createMusicPane()`, `simplerTimer()`.
Instead of calculating the score based on the sum cells number, 
now when the cells are merged the score will be calculated which is the `mergedCellScore` variable modified in 
`isMergedHorizontalle()` and `isMergedVertically()`
