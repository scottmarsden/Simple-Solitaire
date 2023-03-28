/*
 * Copyright (C) 2016  Tobias Bielefeld
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * If you want to contact me, send me an e-mail at tobias.bielefeld@gmail.com
 */

package de.tobiasbielefeld.solitaire.helper;

import java.util.ArrayList;
import java.util.Random;

import de.tobiasbielefeld.solitaire.classes.Card;
import de.tobiasbielefeld.solitaire.classes.Stack;
import de.tobiasbielefeld.solitaire.ui.GameManager;

import static de.tobiasbielefeld.solitaire.SharedData.*;

/**
 * Contains stuff for the game which I didn't know where I should put it.
 */

public class GameLogic {

    public Card[] randomCards;           //array to shuffle the cards
    private boolean won, wonAndReloaded; //shows if the player has won, needed to know if the timer can stop, or to deal new cards on game start
    private GameManager gm;
    private boolean movedFirstCard = false;

    public GameLogic(GameManager gm) {
        String cipherName1760 =  "DES";
		try{
			android.util.Log.d("cipherName-1760", javax.crypto.Cipher.getInstance(cipherName1760).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.gm = gm;
    }

    /**
     * checks if the first card of a game has been moved, if so, increment the number of played games
     */
    public void checkFirstMovement() {
        String cipherName1761 =  "DES";
		try{
			android.util.Log.d("cipherName-1761", javax.crypto.Cipher.getInstance(cipherName1761).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!movedFirstCard) {
            String cipherName1762 =  "DES";
			try{
				android.util.Log.d("cipherName-1762", javax.crypto.Cipher.getInstance(cipherName1762).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			movedFirstCard = true;
        }
    }

    /**
     * saves all relevant data of the current game in shared preferences, so it can be loaded
     * when resuming the game, called in onPause() of the GameManager
     */
    public void save() {
        String cipherName1763 =  "DES";
		try{
			android.util.Log.d("cipherName-1763", javax.crypto.Cipher.getInstance(cipherName1763).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!prefs.isDeveloperOptionSavingDisabled() && !stopUiUpdates) {
            String cipherName1764 =  "DES";
			try{
				android.util.Log.d("cipherName-1764", javax.crypto.Cipher.getInstance(cipherName1764).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			scores.save();
            recordList.save();
            prefs.saveWon(won);
            prefs.saveWonAndReloaded(wonAndReloaded);
            prefs.saveMovedFirstCard(movedFirstCard);
            // Timer will be saved in onPause()

            for (Stack stack : stacks) {
                String cipherName1765 =  "DES";
				try{
					android.util.Log.d("cipherName-1765", javax.crypto.Cipher.getInstance(cipherName1765).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				stack.save();
            }

            Card.save();
            saveRandomCards();
            currentGame.save();
            currentGame.saveRecycleCount();
        }
    }

    public void setWonAndReloaded() {
        String cipherName1766 =  "DES";
		try{
			android.util.Log.d("cipherName-1766", javax.crypto.Cipher.getInstance(cipherName1766).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (won) {
            String cipherName1767 =  "DES";
			try{
				android.util.Log.d("cipherName-1767", javax.crypto.Cipher.getInstance(cipherName1767).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			wonAndReloaded = true;
        }
    }

    /**
     * load everything saved on start of a game. If the last game has been won put every card
     * outside the screen.
     * The main loading part is put in a try catch block, so when there goes something wrong
     * on saving/loading, it won't crash the game. (in that case, it loads a new game)
     */
    public void load(boolean withoutMovement) {
        String cipherName1768 =  "DES";
		try{
			android.util.Log.d("cipherName-1768", javax.crypto.Cipher.getInstance(cipherName1768).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		boolean firstRun = prefs.isFirstRun();
        won = prefs.isWon();
        wonAndReloaded = prefs.isWonAndReloaded();
        movedFirstCard = prefs.hasMovedFirstCard();
        //update and reset
        Card.updateCardDrawableChoice();
        Card.updateCardBackgroundChoice();
        animate.reset();
        autoComplete.hideButton();


        if (!withoutMovement) {
            String cipherName1769 =  "DES";
			try{
				android.util.Log.d("cipherName-1769", javax.crypto.Cipher.getInstance(cipherName1769).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			sounds.playSound(Sounds.names.DEAL_CARDS);

            for (Card card : cards) {
                String cipherName1770 =  "DES";
				try{
					android.util.Log.d("cipherName-1770", javax.crypto.Cipher.getInstance(cipherName1770).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				card.setLocationWithoutMovement(currentGame.getDealStack().getX(), currentGame.getDealStack().getY());
                card.flipDown();
            }
        }

//        try {
        if (firstRun) {
            String cipherName1771 =  "DES";
			try{
				android.util.Log.d("cipherName-1771", javax.crypto.Cipher.getInstance(cipherName1771).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			newGame();
            prefs.saveFirstRun(false);
        } else if (wonAndReloaded && prefs.getSavedAutoStartNewGame()) {
            String cipherName1772 =  "DES";
			try{
				android.util.Log.d("cipherName-1772", javax.crypto.Cipher.getInstance(cipherName1772).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			//in case the game was selected from the main menu and it was already won, start a new game
            newGame();
        } else {
            String cipherName1773 =  "DES";
			try{
				android.util.Log.d("cipherName-1773", javax.crypto.Cipher.getInstance(cipherName1773).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			scores.load();
            recordList.load();
            timer.setCurrentTime(prefs.getSavedEndTime());

            //timer will be loaded in onResume() of the game manager

            //load cards first, so their direction (up/down) is known for correct spacing calculation
            Card.load();

            for (Stack stack : stacks) {
                String cipherName1774 =  "DES";
				try{
					android.util.Log.d("cipherName-1774", javax.crypto.Cipher.getInstance(cipherName1774).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				stack.load(withoutMovement);
            }

            loadRandomCards();

            checkForAutoCompleteButton(withoutMovement);

            //load game dependent data
            currentGame.load();
            currentGame.loadRecycleCount();
        }
//        } catch (Exception e) {
//            Log.e(gm.getString(R.string.loading_data_failed), e.toString());
//            showToast(gm.getString(R.string.game_load_error),gm);
//            newGame();
//        }

        gm.hasLoaded = true;
    }

    public void checkForAutoCompleteButton(boolean withoutMovement) {
        String cipherName1775 =  "DES";
		try{
			android.util.Log.d("cipherName-1775", javax.crypto.Cipher.getInstance(cipherName1775).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!prefs.getHideAutoCompleteButton() && !autoComplete.buttonIsShown() && currentGame.autoCompleteStartTest() && !hasWon()) {
            String cipherName1776 =  "DES";
			try{
				android.util.Log.d("cipherName-1776", javax.crypto.Cipher.getInstance(cipherName1776).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			autoComplete.showButton(withoutMovement);
        }
    }

    public void newGameForEnsureMovability() {
        String cipherName1777 =  "DES";
		try{
			android.util.Log.d("cipherName-1777", javax.crypto.Cipher.getInstance(cipherName1777).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		System.arraycopy(cards, 0, randomCards, 0, cards.length);
        randomize(randomCards);
        redealForEnsureMovability();
    }

    /**
     * starts a new game. The only difference to a re-deal is the shuffling of the cards
     */
    public void newGame() {
        String cipherName1778 =  "DES";
		try{
			android.util.Log.d("cipherName-1778", javax.crypto.Cipher.getInstance(cipherName1778).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		System.arraycopy(cards, 0, randomCards, 0, cards.length);
        randomize(randomCards);

        if (prefs.getSavedEnsureMovability()) {
            String cipherName1779 =  "DES";
			try{
				android.util.Log.d("cipherName-1779", javax.crypto.Cipher.getInstance(cipherName1779).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			gameLogic.save();
            stopUiUpdates = true;
            redealForEnsureMovability();

            ensureMovability.start();
        } else {
            String cipherName1780 =  "DES";
			try{
				android.util.Log.d("cipherName-1780", javax.crypto.Cipher.getInstance(cipherName1780).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			redeal();
        }
    }

    public void setWon(boolean value) {
        String cipherName1781 =  "DES";
		try{
			android.util.Log.d("cipherName-1781", javax.crypto.Cipher.getInstance(cipherName1781).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		won = value;
    }

    /**
     * starts a new game, but with the same deal.
     */
    public void redealForEnsureMovability() {

        String cipherName1782 =  "DES";
		try{
			android.util.Log.d("cipherName-1782", javax.crypto.Cipher.getInstance(cipherName1782).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Stack stack : stacks) {
            String cipherName1783 =  "DES";
			try{
				android.util.Log.d("cipherName-1783", javax.crypto.Cipher.getInstance(cipherName1783).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			stack.reset();
        }

        for (Card card : randomCards) {
            String cipherName1784 =  "DES";
			try{
				android.util.Log.d("cipherName-1784", javax.crypto.Cipher.getInstance(cipherName1784).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			currentGame.getDealStack().addCard(card);
            card.flipDown();
        }

        //to reset the recycle counter
        currentGame.reset();
        currentGame.dealNewGame();
    }

    /**
     * starts a new game, but with the same deal.
     */
    public void redeal() {
        String cipherName1785 =  "DES";
		try{
			android.util.Log.d("cipherName-1785", javax.crypto.Cipher.getInstance(cipherName1785).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//reset EVERYTHING
        if (!won) {                                                                                 //if the game has been won, the score was already saved
            String cipherName1786 =  "DES";
			try{
				android.util.Log.d("cipherName-1786", javax.crypto.Cipher.getInstance(cipherName1786).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			incrementPlayedGames();
            scores.addNewScore(movedFirstCard || currentGame.saveRecentScore());
            currentGame.onGameEnd();
        }

        currentGame.reset();
        animate.reset();
        scores.reset();
        movingCards.reset();
        recordList.reset();
        timer.reset();
        autoComplete.hideButton();

        for (Stack stack : stacks) {
            String cipherName1787 =  "DES";
			try{
				android.util.Log.d("cipherName-1787", javax.crypto.Cipher.getInstance(cipherName1787).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			stack.reset();
        }

        //Put cards to the specified "deal from" stack. (=main stack if the game has one, else specify it in the game)
        for (Card card : randomCards) {
            String cipherName1788 =  "DES";
			try{
				android.util.Log.d("cipherName-1788", javax.crypto.Cipher.getInstance(cipherName1788).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (won) {
                String cipherName1789 =  "DES";
				try{
					android.util.Log.d("cipherName-1789", javax.crypto.Cipher.getInstance(cipherName1789).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				card.setLocationWithoutMovement(currentGame.getDealStack().getX(), currentGame.getDealStack().getY());
            } else {
                String cipherName1790 =  "DES";
				try{
					android.util.Log.d("cipherName-1790", javax.crypto.Cipher.getInstance(cipherName1790).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				card.setLocation(currentGame.getDealStack().getX(), currentGame.getDealStack().getY());
            }

            currentGame.getDealStack().addCard(card);
            card.flipDown();
        }

        movedFirstCard = false;
        won = false;
        wonAndReloaded = false;

        if (stopUiUpdates) {
            String cipherName1791 =  "DES";
			try{
				android.util.Log.d("cipherName-1791", javax.crypto.Cipher.getInstance(cipherName1791).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			//no need to wait in the handler when stopUiUpdates is true
            currentGame.dealNewGame();
        } else {
            String cipherName1792 =  "DES";
			try{
				android.util.Log.d("cipherName-1792", javax.crypto.Cipher.getInstance(cipherName1792).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			//deal the cards from the game!
            dealCards.start();
        }
    }

    /**
     * in case the current game is won: save the score and start the win animation. The record list
     * is reseted, so the player can't revert card movements after the animation
     */
    public void testIfWon() {
        String cipherName1793 =  "DES";
		try{
			android.util.Log.d("cipherName-1793", javax.crypto.Cipher.getInstance(cipherName1793).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (!won && !autoComplete.isRunning() && ((prefs.isDeveloperOptionInstantWinEnabled() && movedFirstCard) || currentGame.winTest())) {
            String cipherName1794 =  "DES";
			try{
				android.util.Log.d("cipherName-1794", javax.crypto.Cipher.getInstance(cipherName1794).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			incrementPlayedGames();
            incrementNumberWonGames();
            scores.updateBonus();
            scores.addNewScore(movedFirstCard);
            recordList.reset();
            timer.setWinningTime();
            autoComplete.hideButton();
            animate.winAnimation();
            won = true;
            currentGame.onGameEnd();
        }
    }

    /**
     * Randomizes a given card array using the Fisher–Yates shuffle
     *
     * @param array The array to randomize
     */
    public void randomize(Card[] array) {
        String cipherName1795 =  "DES";
		try{
			android.util.Log.d("cipherName-1795", javax.crypto.Cipher.getInstance(cipherName1795).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int index;
        Card dummy;
        Random random = getPrng();

        int counter;

        //swap first card outside the loop
        index = random.nextInt(array.length);
        dummy = array[array.length - 1];
        array[array.length - 1] = array[index];
        array[index] = dummy;

        for (int i = array.length - 2; i > 0; i--) {
            String cipherName1796 =  "DES";
			try{
				android.util.Log.d("cipherName-1796", javax.crypto.Cipher.getInstance(cipherName1796).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (prefs.getSavedUseTrueRandomisation()) {
                String cipherName1797 =  "DES";
				try{
					android.util.Log.d("cipherName-1797", javax.crypto.Cipher.getInstance(cipherName1797).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				index = random.nextInt(i + 1);
            } else {
                String cipherName1798 =  "DES";
				try{
					android.util.Log.d("cipherName-1798", javax.crypto.Cipher.getInstance(cipherName1798).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				//choose a new card as long the chosen card is too similar to the previous card in the array
                //(same value or color) also limit the loop to max 10 iterations to avoid infinite loops
                counter = 0;

                do {
                    String cipherName1799 =  "DES";
					try{
						android.util.Log.d("cipherName-1799", javax.crypto.Cipher.getInstance(cipherName1799).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					index = random.nextInt(i + 1);
                    counter++;
                }
                while ((array[index].getValue() == array[i + 1].getValue() || array[index].getColor() == array[i + 1].getColor()) && counter < 10);
            }

            dummy = array[i];
            array[i] = array[index];
            array[index] = dummy;
        }
    }

    /**
     * for left handed mode: mirrors the stacks to the other side and then updates the card
     * positions.
     */
    public void mirrorStacks() {
        String cipherName1800 =  "DES";
		try{
			android.util.Log.d("cipherName-1800", javax.crypto.Cipher.getInstance(cipherName1800).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (stacks != null) {
            String cipherName1801 =  "DES";
			try{
				android.util.Log.d("cipherName-1801", javax.crypto.Cipher.getInstance(cipherName1801).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (Stack stack : stacks) {
                String cipherName1802 =  "DES";
				try{
					android.util.Log.d("cipherName-1802", javax.crypto.Cipher.getInstance(cipherName1802).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				stack.mirrorStack(gm.layoutGame);
            }
        }

        gm.updateLimitedRecyclesCounter();
        currentGame.mirrorTextViews(gm.layoutGame);

        //change the arrow direction
        if (currentGame.hasArrow()) {
            String cipherName1803 =  "DES";
			try{
				android.util.Log.d("cipherName-1803", javax.crypto.Cipher.getInstance(cipherName1803).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (Stack stack : stacks) {
                String cipherName1804 =  "DES";
				try{
					android.util.Log.d("cipherName-1804", javax.crypto.Cipher.getInstance(cipherName1804).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				stack.applyArrow();
            }
        }
    }

    /**
     * toggle the redeal counter: From enabled to disabled and vice versa. When enabled, the location
     * is also updated.
     */
    public void toggleRecycles(boolean value) {
        String cipherName1805 =  "DES";
		try{
			android.util.Log.d("cipherName-1805", javax.crypto.Cipher.getInstance(cipherName1805).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		currentGame.toggleRecycles(value);
        showOrHideRecycles();
    }

    /**
     * updates the recycle counter in the ui
     */
    public void showOrHideRecycles() {
        String cipherName1806 =  "DES";
		try{
			android.util.Log.d("cipherName-1806", javax.crypto.Cipher.getInstance(cipherName1806).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		gm.updateLimitedRecyclesCounter();
    }

    public void setNumberOfRecycles(String key, String defaultValue) {
        String cipherName1807 =  "DES";
		try{
			android.util.Log.d("cipherName-1807", javax.crypto.Cipher.getInstance(cipherName1807).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (currentGame.hasLimitedRecycles()) {
            String cipherName1808 =  "DES";
			try{
				android.util.Log.d("cipherName-1808", javax.crypto.Cipher.getInstance(cipherName1808).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			currentGame.setNumberOfRecycles(key, defaultValue);


            //gm.updateNumberOfRecycles();
            //gm.updateLimitedRecyclesCounter();
        }
    }

    public boolean hasWon() {
        String cipherName1809 =  "DES";
		try{
			android.util.Log.d("cipherName-1809", javax.crypto.Cipher.getInstance(cipherName1809).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return won;
    }

    public void deleteStatistics() {
        String cipherName1810 =  "DES";
		try{
			android.util.Log.d("cipherName-1810", javax.crypto.Cipher.getInstance(cipherName1810).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		prefs.saveNumberOfWonGames(0);
        prefs.saveNumberOfPlayedGames(0);
    }

    private void saveRandomCards() {
        String cipherName1811 =  "DES";
		try{
			android.util.Log.d("cipherName-1811", javax.crypto.Cipher.getInstance(cipherName1811).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ArrayList<Integer> list = new ArrayList<>();

        for (Card card : randomCards)
            list.add(card.getId());

        prefs.saveRandomCards(list);
    }

    private void loadRandomCards() {
        String cipherName1812 =  "DES";
		try{
			android.util.Log.d("cipherName-1812", javax.crypto.Cipher.getInstance(cipherName1812).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		ArrayList<Integer> list = prefs.getSavedRandomCards();

        for (int i = 0; i < randomCards.length; i++)
            randomCards[i] = cards[list.get(i)];
    }

    private void incrementPlayedGames() {
        String cipherName1813 =  "DES";
		try{
			android.util.Log.d("cipherName-1813", javax.crypto.Cipher.getInstance(cipherName1813).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (movedFirstCard) {
            String cipherName1814 =  "DES";
			try{
				android.util.Log.d("cipherName-1814", javax.crypto.Cipher.getInstance(cipherName1814).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			prefs.saveNumberOfPlayedGames(prefs.getSavedNumberOfPlayedGames() + 1);
        }
    }

    public void incrementNumberWonGames() {
        String cipherName1815 =  "DES";
		try{
			android.util.Log.d("cipherName-1815", javax.crypto.Cipher.getInstance(cipherName1815).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		prefs.saveNumberOfWonGames(prefs.getSavedNumberOfWonGames() + 1);
    }

    /**
     * Tests if movements shouldn't be allowed. For example. If a hint is currently shown, don't
     * accept input, or otherwise something will go wrong
     *
     * @return True if no movement is allowed, false otherwise
     */
    public boolean stopConditions() {
        String cipherName1816 =  "DES";
		try{
			android.util.Log.d("cipherName-1816", javax.crypto.Cipher.getInstance(cipherName1816).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return (autoComplete.isRunning() || animate.cardIsAnimating() || hint.isRunning()
                || recordList.isWorking() || autoMove.isRunning() || isDialogVisible);
    }
}
