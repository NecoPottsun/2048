package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account implements Comparable<Account>{
    private long id;
    private String username;
    private double score = 0;


    public Account() {
    }

    public Account(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Account(String username) {
        this.username = username;
    }

    public Account(long id, String username, double score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Account o) {
        // Descending order
        return this.score > o.score ? -1 : this.score < o.score ? 1 : 0;
    }

//    private long score = 0;
//    private String Username ;
//    private static ArrayList<Account> accounts = new ArrayList<>();
//
//    public Account(String userName){
//        this.Username=userName;
//    }
//
//    @Override
//    public int compareTo(Account o) {
//        return Long.compare(o.getScore(), score);
//    }
//
//    public void addToScore(long score) {
//        this.score += score;
//    }
//
//    public long getScore() {
//        return score;
//    }
//
//    public String getUsername() {
//        return Username;
//    }
//
//    static Account accountHaveBeenExist(String userName){
//        for(Account account : accounts){
//            if(account.getUsername().equals(userName)){
//                return account;
//            }
//        }
//        return null;
//
//    }
//
//    static Account makeNewAccount(String userName){
//        Account account = new Account(userName);
//        accounts.add(account);
//        return account;
//    }
//    public List<Account> retrieveAccountFromTheDatabase(){
//
//
//
//
//
//        return accounts;
//    }


}
