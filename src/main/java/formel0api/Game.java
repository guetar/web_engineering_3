/**
 * <copyright>
 *
 * Copyright (c) 2013 http://www.big.tuwien.ac.at All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */
package formel0api;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Class representing a Formel 0 game
 */
@ManagedBean(name="game")
@SessionScoped
public class Game {

    private static final int LAST_FIELD = 6;
    /**
     * Player playing the game
     */
    private Player player;
    /**
     * Computer opponent
     */
    private Player computer;
    /**
     * Dice that is used in this game
     */
    private Dice dice;
    /**
     * Specifies if the game is over (
     * <code>true</code>) or not (
     * <code>false</code)
     */
    private boolean gameOver;
    /**
     * Starting time of the game
     */
    private long gamestarttime;
    /**
     * Time already spent in this game
     */
    private long spenttime;
    private int round;

    public Game() {
    }

    /**
     * Constructs a new {@link Game}
     */
    public Game(Player player, Player computer) {
        this.player = player;
        this.computer = computer;
    }

    public void init(String playerName, String computerName) {
        player = new Player(playerName, "");
        computer = new Player(computerName, "");
        dice = new Dice();
        gameOver = false;
        gamestarttime = System.currentTimeMillis();
        round = 0;
    }

    /**
     * Specifies whether this game is over or not
     *
     * @return <code>true</code> if this game is over, <code>false</code>
     * otherwise
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * Returns the time already spent on this game
     *
     * @return the time already spent on this game
     */
    public long getSpentTime() {
        if (!gameOver) {
            spenttime = System.currentTimeMillis() - this.gamestarttime;
        }
        return spenttime;
    }

    public String getSpentTimeString() {
        Date date = new Date(getSpentTime());
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(date);
    }

    /**
     * Rolls the dice for the player and updates the position of the player's
     * car according to the score
     *
     * @param player Player who rolls the dice
     * @return score
     */
    public int rollthedice(Player player) {
        int score = dice.roll();
        player.setScore(score);

        int position = player.getPosition();

        /**
         * Move on field
         */
        int newposition = Math.min(position + score, LAST_FIELD);
        player.setPosition(newposition);

        /**
         * Test if deadly field was reached
         */
        if (newposition == 2 || newposition == 5) {
            newposition = 0;
            player.setPosition(newposition);
        }

        /**
         * Test if the figure of the player reached the end and the game is over
         */
        if (newposition == LAST_FIELD) { // player reached end
            gameOver = true;
        }

        return score;
    }

    public void rollthedice() {
        if(gameOver) {
            return;
        }
        
        round++;
        rollthedice(computer);
        rollthedice(player);
    }

    /**
     * Returns the currently leading player
     *
     * @return the currently leading player
     */
    public Player getLeader() {
        if (player.getPosition() > computer.getPosition()) {
            return player;
        } else if (computer.getPosition() > player.getPosition()) {
            return computer;
        } else {
            return null;
        }
    }

    /**
     * Returns the player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the computer
     *
     * @return computer
     */
    public Player getComputer() {
        return computer;
    }

    public int getRound() {
        return round;
    }
    
    public int getDiceEyes (){
        return dice.getEyes();
    }
    
    public String getDiceEyesString() {
        return dice.getEyesString();
    }
    
}