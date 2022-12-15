package com.example.demo;


/**
 * Each account will have attributes: id,username,score
 * @author Thanh Mai Duyen Trang-modifed
 */
public class Account implements Comparable<Account>{
    private long id;
    private String username;
    private double score = 0;
    private String playTime = "";


    /**
     * The constructor of Account
     * */
    public Account() {
    }
    /**
     * The constructor of Account
     * @param id the id of this Account
     * @param username the username of this Account
     * */
    public Account(long id, String username) {
        this.id = id;
        this.username = username;
    }
    /**
     * The constructor of Account
     * @param username the username of this Account
     * */
    public Account(String username) {
        this.username = username;
    }

    /**
     * The constructor of Account
     * @param id the id of this Account
     * @param username the username of this Account
     * @param score the total score of this Account
     * */
    public Account(long id, String username, double score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    /**
     * Gets the username of this Account
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this Account
     * @param username the new username of this Account
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the score of this Account
     * @return score
     *<p>
     *     Default value: 0
     *</p>
     */
    public double getScore() {
        return score;
    }

    /**
     * Sets the score of this Account
     * @param score the total score of this Account
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Gets the id of this Account
     * @return id
     */

    public long getId() {
        return id;
    }

    /**
     * Sets the id of this Account
     * @param id the id of this Account
     */

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the playTime of this Account
     * @return playTime
     *<p>
     *     Default value: " "
     *</p>
     */
    public String getPlayTime() {
        return playTime;
    }

    /**
     * Sets the playTime of this Account
     * @param playTime the time playing the game of This Account
     */
    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    /**
     * Compares the score of this Account with another Account
     * @param o the Account to be compared.
     * @return <code>-1</code> if this Account score larger;
     *         <code>0</code> if this Account score equals;
     *         <code>1</code> if this Account score smaller;
     */
    @Override
    public int compareTo(Account o) {
        // Descending order
        return this.score > o.score ? -1 : this.score < o.score ? 1 : 0;
    }

    /**
     * Print the information of this Account
     * @return Account information
     *
     */
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", playTime='" + playTime + '\'' +
                '}';
    }
}

