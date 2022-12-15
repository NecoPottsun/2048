package com.example.demo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test soundtrackDatabase functions
 * @author Thanh Mai Duyen Trang
 */
public class SoundtrackDatabaseTest {
    private SoundtrackDatabase soundtrackDatabase = SoundtrackDatabase.getInstance();

    /**
     * Checks the index of current song that is playing successfully
     * <p>
     *     Default value: 0
     * </p>
     */
    @Test
    public void getCurrentPlayIndexPass() {
       assertEquals(0,soundtrackDatabase.getCurrentPlayIndex());
    }

    /**
     * Checks the index of current song that is playing failed
     * <p>
     *     Default value: 0
     * </p>
     */
    @Test
    public void getCurrentPlayIndexFail() {
        assertNotEquals(1,soundtrackDatabase.getCurrentPlayIndex());
    }

    /**
     * Checks set the song currently play to another song successfully
     */
    @Test
    public void setCurrentPlayIndexPass() {
        int index = 2;
        soundtrackDatabase.setCurrentPlayIndex(index);
        assertEquals(index, soundtrackDatabase.getCurrentPlayIndex());
    }

    /**
     * Checks set the song currently play to another song Failed
     */
    @Test
    public void setCurrentPlayIndexFail() {
        int index = 2;
        soundtrackDatabase.setCurrentPlayIndex(index);
        assertNotEquals(0, soundtrackDatabase.getCurrentPlayIndex());
    }

    /**
     * Gets soundtrack at a particular position successfully
     */
    @Test
    public void getSoundtrackAtPositionPass() {
        int index = 0;
        String soundtrackPath = soundtrackDatabase.getSoundtrackAtPosition(0);
        assertEquals("music/soundtrack1.mp3", soundtrackPath);
    }
    /**
     * Gets soundtrack at a particular position failed
     */
    @Test
    public void getSoundtrackAtPositionFail() {
        int index = 2;
        String soundtrackPath = soundtrackDatabase.getSoundtrackAtPosition(2);
        assertNotEquals("music/soundtrack1.mp3", soundtrackPath);
    }

    /**
     * Checks current soundtrack is the next soundtrack in the
     * soundtrack list of SoundtrackDatabase successfully
     */
    @Test
    public void nextSoundtrackPass() {
        int currentPlayIndex = soundtrackDatabase.getCurrentPlayIndex();
        soundtrackDatabase.nextSoundtrack();
        assertEquals(currentPlayIndex+1, soundtrackDatabase.getCurrentPlayIndex());
    }
    /**
     * Checks current soundtrack is the next soundtrack in the
     * soundtrack list of SoundtrackDatabase failed
     */
    @Test
    public void nextSoundtrackFail() {
        int currentPlayIndex = soundtrackDatabase.getCurrentPlayIndex();
        soundtrackDatabase.nextSoundtrack();
        assertNotEquals(0, soundtrackDatabase.getCurrentPlayIndex());
    }

    /**
     * Checks current soundtrack is the previous soundtrack in the
     * soundtrack list of SoundtrackDatabase successfully
     */

    @Test
    public void previousSoundtrackPass() {
        int currentPlayIndex = 2;
        soundtrackDatabase.setCurrentPlayIndex(currentPlayIndex);
        soundtrackDatabase.previousSoundtrack();
        assertEquals(currentPlayIndex-1, soundtrackDatabase.getCurrentPlayIndex());
    }
    /**
     * Checks current soundtrack is the previous soundtrack in the
     * soundtrack list of SoundtrackDatabase failed
     */
    @Test
    public void previousSoundtrackFail() {
        int currentPlayIndex = 2;
        soundtrackDatabase.setCurrentPlayIndex(currentPlayIndex);
        soundtrackDatabase.previousSoundtrack();
        assertNotEquals(currentPlayIndex, soundtrackDatabase.getCurrentPlayIndex());
    }

    /**
     * Tests the check current play index in the case of exceed the soundtrack list size
     * successfully
     */
    @Test
    public void checkCurrentPlayIndexPass1() {
        int position = 3;
        int currentPlayerIndex = soundtrackDatabase.checkCurrentPlayIndex(position);
        assertEquals(0, currentPlayerIndex);

    }

    /**
     * Tests the check current play index in the case of exceed the soundtrack list size
     * failed
     */
    @Test
    public void checkCurrentPlayIndexFail1() {
        int position = 3;
        int currentPlayerIndex = soundtrackDatabase.checkCurrentPlayIndex(position);
        assertNotEquals(1, currentPlayerIndex);

    }
    /**
     * Tests the check current play index in the case of smaller than 0
     * successfully
     */
    @Test
    public void checkCurrentPlayIndexPass2() {
        int position = -1;
        int currentPlayerIndex = soundtrackDatabase.checkCurrentPlayIndex(position);
        int size =soundtrackDatabase.getSoundtrackList().size();
        assertEquals(size-1, currentPlayerIndex);

    }
    /**
     * Tests the check current play index in the case of smaller than 0
     * failed
     */
    @Test
    public void checkCurrentPlayIndexFail2() {
        int position = -1;
        int currentPlayerIndex = soundtrackDatabase.checkCurrentPlayIndex(position);
        int size =soundtrackDatabase.getSoundtrackList().size();
        assertNotEquals(size-2, currentPlayerIndex);

    }

    /**
     * Tests if it deletes the soundtrack inside the soundtrack list successfully
     */
    @Test
    public void deleteSoundtrackPass() {
        int firstSize = soundtrackDatabase.getSoundtrackList().size();
        soundtrackDatabase.deleteSoundtrack("music/soundtrack3.mp3");
        int afterDeleteSize = soundtrackDatabase.getSoundtrackList().size();
        assertEquals(firstSize-1,afterDeleteSize);
        soundtrackDatabase.addSoundtrack("music/soundtrack3.mp3");
        soundtrackDatabase.printSoundtrackList();
    }
    /**
     * Tests if it deletes the soundtrack inside the soundtrack list failed
     * because of the soundtrack that wanted to delete doesn't in the
     * soundtrack list
     */
    @Test
    public void deleteSoundtrackFail() {
        int firstSize = soundtrackDatabase.getSoundtrackList().size();
        soundtrackDatabase.deleteSoundtrack("music/abc.mp3");
        int afterDeleteSize = soundtrackDatabase.getSoundtrackList().size();
        assertNotEquals(firstSize-1,afterDeleteSize);
    }
}