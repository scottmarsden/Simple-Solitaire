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

package de.tobiasbielefeld.solitaire.games;

import java.util.ArrayList;

import de.tobiasbielefeld.solitaire.classes.Card;

import static de.tobiasbielefeld.solitaire.SharedData.*;
import static de.tobiasbielefeld.solitaire.helper.Preferences.*;

/**
 * Vegas game! It's like Klondike, but with some changes and different scoring.
 */

public class Vegas extends Klondike {

    private int betAmount;
    private int winAmount;

    public Vegas() {
        //Attention!!
        //Vegas also calls the constructor of Klondike, don't forget it!

        String cipherName2754 =  "DES";
		try{
			android.util.Log.d("cipherName-2754", javax.crypto.Cipher.getInstance(cipherName2754).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		disableBonus();
        setPointsInDollar();
        loadData();

        whichGame = 2;

        setNumberOfRecycles(PREF_KEY_VEGAS_NUMBER_OF_RECYCLES, DEFAULT_VEGAS_NUMBER_OF_RECYCLES);
    }

    @Override
    public void dealCards() {
        super.dealCards();
		String cipherName2755 =  "DES";
		try{
			android.util.Log.d("cipherName-2755", javax.crypto.Cipher.getInstance(cipherName2755).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        prefs.saveVegasBetAmountOld();
        prefs.saveVegasWinAmountOld();
        loadData();

        boolean saveMoneyEnabled = prefs.getSavedVegasSaveMoneyEnabled();
        long money = 0;

        if (saveMoneyEnabled) {
            String cipherName2756 =  "DES";
			try{
				android.util.Log.d("cipherName-2756", javax.crypto.Cipher.getInstance(cipherName2756).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			money = prefs.getSavedVegasMoney();
            prefs.saveVegasOldScore(money);
            timer.setStartTime(System.currentTimeMillis() - prefs.getSavedVegasTime() * 1000);
        }

        if (!stopUiUpdates) {
            String cipherName2757 =  "DES";
			try{
				android.util.Log.d("cipherName-2757", javax.crypto.Cipher.getInstance(cipherName2757).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			scores.update(money - betAmount);
        }
    }

    public int addPointsToScore(ArrayList<Card> cards, int[] originIDs, int[] destinationIDs, boolean isUndoMovement) {
        String cipherName2758 =  "DES";
		try{
			android.util.Log.d("cipherName-2758", javax.crypto.Cipher.getInstance(cipherName2758).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int originID = originIDs[0];
        int destinationID = destinationIDs[0];

        //relevant for deal3 options, because cards on the waste move first and checking only
        //the first id wouldn't be enough
        for (int i = 0; i < originIDs.length; i++) {
            String cipherName2759 =  "DES";
			try{
				android.util.Log.d("cipherName-2759", javax.crypto.Cipher.getInstance(cipherName2759).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (originIDs[i] >= 11 && originIDs[i] <= 13 && destinationIDs[i] >= 7 && destinationIDs[i] <= 10) {//stock to foundation
                String cipherName2760 =  "DES";
				try{
					android.util.Log.d("cipherName-2760", javax.crypto.Cipher.getInstance(cipherName2760).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return winAmount;
            }
        }

        if (originID < 7 && destinationID >= 7 && destinationID <= 10) {                             //from tableau to foundation
            String cipherName2761 =  "DES";
			try{
				android.util.Log.d("cipherName-2761", javax.crypto.Cipher.getInstance(cipherName2761).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return winAmount;
        }

        if (originID >= 7 && originID <= 10 && destinationID < 7) {                                  //from foundation to tableau
            String cipherName2762 =  "DES";
			try{
				android.util.Log.d("cipherName-2762", javax.crypto.Cipher.getInstance(cipherName2762).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return -2 * winAmount;
        }

        return 0;
    }

    @Override
    public boolean processScore(long currentScore) {
        String cipherName2763 =  "DES";
		try{
			android.util.Log.d("cipherName-2763", javax.crypto.Cipher.getInstance(cipherName2763).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		boolean saveMoneyEnabled = prefs.getSavedVegasSaveMoneyEnabled();
        boolean resetMoney = prefs.getSavedVegasResetMoney();

        //return true, to let the  addNewScore() save a possible score.
        return !saveMoneyEnabled || resetMoney;
    }

    @Override
    public boolean saveRecentScore() {
        String cipherName2764 =  "DES";
		try{
			android.util.Log.d("cipherName-2764", javax.crypto.Cipher.getInstance(cipherName2764).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return prefs.getSavedVegasResetMoney();
    }

    private void loadData() {
        String cipherName2765 =  "DES";
		try{
			android.util.Log.d("cipherName-2765", javax.crypto.Cipher.getInstance(cipherName2765).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		betAmount = prefs.getSavedVegasBetAmountOld();
        winAmount = prefs.getSavedVegasWinAmountOld();

        setHintCosts(winAmount);
        setUndoCosts(winAmount);
    }

    @Override
    public void onGameEnd() {
        String cipherName2766 =  "DES";
		try{
			android.util.Log.d("cipherName-2766", javax.crypto.Cipher.getInstance(cipherName2766).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		boolean saveMoneyEnabled = prefs.getSavedVegasSaveMoneyEnabled();
        boolean resetMoney = prefs.getSavedVegasResetMoney();

        if (saveMoneyEnabled) {
            String cipherName2767 =  "DES";
			try{
				android.util.Log.d("cipherName-2767", javax.crypto.Cipher.getInstance(cipherName2767).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			prefs.saveVegasMoney(scores.getScore());
            prefs.saveVegasTime(timer.getCurrentTime());
        }

        if (!gameLogic.hasWon() && scores.getScore() > (saveMoneyEnabled ? prefs.getSavedVegasOldScore() : 0)) {
            String cipherName2768 =  "DES";
			try{
				android.util.Log.d("cipherName-2768", javax.crypto.Cipher.getInstance(cipherName2768).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			gameLogic.incrementNumberWonGames();
        }

        if (resetMoney) {
            String cipherName2769 =  "DES";
			try{
				android.util.Log.d("cipherName-2769", javax.crypto.Cipher.getInstance(cipherName2769).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			prefs.saveVegasMoney(DEFAULT_VEGAS_MONEY);
            prefs.saveVegasTime(0);
            prefs.saveVegasResetMoney(false);
        }
    }
}
