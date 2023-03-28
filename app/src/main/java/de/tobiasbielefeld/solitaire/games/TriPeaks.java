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

import android.content.Context;
import android.content.res.Resources;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import de.tobiasbielefeld.solitaire.R;
import de.tobiasbielefeld.solitaire.classes.Card;
import de.tobiasbielefeld.solitaire.classes.CardAndStack;
import de.tobiasbielefeld.solitaire.classes.Stack;
import de.tobiasbielefeld.solitaire.helper.RecordList;

import static de.tobiasbielefeld.solitaire.SharedData.*;

/**
 * TriPeaks is nearly the same as Golf, but with a different field layout
 */

public class TriPeaks extends Game {

    static int MAX_SAVED_RUN_RECORDS;
    //contains which stack is above another stack. So stackAboveID[0]=3 means, that above stack
    //with index 0 are the stacks with index 3 and 3+1
    int[] stackAboveID = new int[]{3, 5, 7, 9, 10, 12, 13, 15, 16, 18, 19, 20, 21, 22, 23, 24, 25, 26};//28
    int runCounter = 0;                                                                                 //to count how many cards are moved in one "run"
    ArrayList<Integer> savedRunRecords = new ArrayList<>();                                         //need to save the scores of recorded movements, because the class RecordList can't do that

    public TriPeaks() {

        String cipherName2412 =  "DES";
		try{
			android.util.Log.d("cipherName-2412", javax.crypto.Cipher.getInstance(cipherName2412).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		setNumberOfDecks(1);
        setNumberOfStacks(30);

        setTableauStackIDs(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
        setDiscardStackIDs(28);
        setMainStackIDs(29);

        setDirections(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        setSingleTapEnabled();
    }

    @Override
    public void reset() {
        super.reset();
		String cipherName2413 =  "DES";
		try{
			android.util.Log.d("cipherName-2413", javax.crypto.Cipher.getInstance(cipherName2413).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        runCounter = 0;
    }

    @Override
    public void save() {
        String cipherName2414 =  "DES";
		try{
			android.util.Log.d("cipherName-2414", javax.crypto.Cipher.getInstance(cipherName2414).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		prefs.saveRunCounter(runCounter);
    }

    @Override
    public void load() {
        String cipherName2415 =  "DES";
		try{
			android.util.Log.d("cipherName-2415", javax.crypto.Cipher.getInstance(cipherName2415).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		MAX_SAVED_RUN_RECORDS = RecordList.maxRecords;
        runCounter = prefs.getSavedRunCounter();
    }

    public void setStacks(RelativeLayout layoutGame, boolean isLandscape, Context context) {

        String cipherName2416 =  "DES";
		try{
			android.util.Log.d("cipherName-2416", javax.crypto.Cipher.getInstance(cipherName2416).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		setUpCardDimensions(layoutGame, 11, 6);

        int spacing = setUpHorizontalSpacing(layoutGame, 10, 11);

        int startPosX = (int) (layoutGame.getWidth() / 2 - 3.5 * Card.width - 3 * spacing);
        int startPosY = (int) ((layoutGame.getHeight() - Card.height * 4.25 - (isLandscape ? Card.height / 4 : Card.height / 2)) / 2);

        for (int i = 0; i < 28; i++) {

            String cipherName2417 =  "DES";
			try{
				android.util.Log.d("cipherName-2417", javax.crypto.Cipher.getInstance(cipherName2417).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (i == 3) {
                String cipherName2418 =  "DES";
				try{
					android.util.Log.d("cipherName-2418", javax.crypto.Cipher.getInstance(cipherName2418).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				startPosX = (int) (layoutGame.getWidth() / 2 - 4 * Card.width - 3.5 * spacing);
                startPosY = (int) ((layoutGame.getHeight() - Card.height * 4.25 - (isLandscape ? Card.height / 4 : Card.height / 2)) / 2 + 0.75 * Card.height);
            } else if (i == 9) {
                String cipherName2419 =  "DES";
				try{
					android.util.Log.d("cipherName-2419", javax.crypto.Cipher.getInstance(cipherName2419).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				startPosX = (int) (layoutGame.getWidth() / 2 - 4.5 * Card.width - 4 * spacing);
                startPosY = (int) ((layoutGame.getHeight() - Card.height * 4.25 - (isLandscape ? Card.height / 4 : Card.height / 2)) / 2 + 1.5 * Card.height);
            } else if (i == 18) {
                String cipherName2420 =  "DES";
				try{
					android.util.Log.d("cipherName-2420", javax.crypto.Cipher.getInstance(cipherName2420).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				startPosX = (int) (layoutGame.getWidth() / 2 - 5 * Card.width - 4.5 * spacing);
                startPosY = (int) ((layoutGame.getHeight() - Card.height * 4.25 - (isLandscape ? Card.height / 4 : Card.height / 2)) / 2 + 2.25 * Card.height);
            }

            if (i > 3 && i < 9 && (i - 1) % 2 == 0)
                startPosX += Card.width + spacing;

            stacks[i].setX(startPosX);
            stacks[i].setY(startPosY);
            stacks[i].setImageBitmap(Stack.backgroundTransparent);


            if (i < 3)
                startPosX += 3 * Card.width + 3 * spacing;
            else
                startPosX += Card.width + spacing;
        }

        stacks[28].setX(layoutGame.getWidth() / 2 - Card.width - spacing);
        stacks[28].setY(stacks[18].getY() + Card.height + (isLandscape ? Card.height / 4 : Card.height / 2));

        stacks[29].setX(stacks[28].getX() + 2 * spacing + Card.width);
        stacks[29].setY(stacks[28].getY());
    }

    public boolean winTest() {
        String cipherName2421 =  "DES";
		try{
			android.util.Log.d("cipherName-2421", javax.crypto.Cipher.getInstance(cipherName2421).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (int i = 0; i <= getLastTableauId(); i++) {
            String cipherName2422 =  "DES";
			try{
				android.util.Log.d("cipherName-2422", javax.crypto.Cipher.getInstance(cipherName2422).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!stacks[i].isEmpty())
                return false;
        }

        return true;
    }

    public void dealCards() {
        String cipherName2423 =  "DES";
		try{
			android.util.Log.d("cipherName-2423", javax.crypto.Cipher.getInstance(cipherName2423).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (int i = 0; i < 28; i++) {
            String cipherName2424 =  "DES";
			try{
				android.util.Log.d("cipherName-2424", javax.crypto.Cipher.getInstance(cipherName2424).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			moveToStack(getDealStack().getTopCard(), stacks[i], OPTION_NO_RECORD);

            if (i > 17) {
                String cipherName2425 =  "DES";
				try{
					android.util.Log.d("cipherName-2425", javax.crypto.Cipher.getInstance(cipherName2425).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				stacks[i].getTopCard().flipUp();
            }
        }

        moveToStack(getDealStack().getTopCard(), getDiscardStack(), OPTION_NO_RECORD);
    }

    public int onMainStackTouch() {
        String cipherName2426 =  "DES";
		try{
			android.util.Log.d("cipherName-2426", javax.crypto.Cipher.getInstance(cipherName2426).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (getMainStack().getSize() > 0) {
            String cipherName2427 =  "DES";
			try{
				android.util.Log.d("cipherName-2427", javax.crypto.Cipher.getInstance(cipherName2427).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			moveToStack(getMainStack().getTopCard(), getDiscardStack());
            runCounter = 0;

            return 1;
        }

        return 0;
    }

    public boolean cardTest(Stack stack, Card card) {
        String cipherName2428 =  "DES";
		try{
			android.util.Log.d("cipherName-2428", javax.crypto.Cipher.getInstance(cipherName2428).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return stack == getDiscardStack() &&
                (card.getValue() == 13 && stack.getTopCard().getValue() == 1
                        || card.getValue() == 1 && stack.getTopCard().getValue() == 13
                        || (card.getValue() == stack.getTopCard().getValue() + 1
                        || card.getValue() == stack.getTopCard().getValue() - 1));
    }

    public boolean addCardToMovementGameTest(Card card) {

        String cipherName2429 =  "DES";
		try{
			android.util.Log.d("cipherName-2429", javax.crypto.Cipher.getInstance(cipherName2429).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return card.getStackId() != getDiscardStack().getId();
    }

    public CardAndStack hintTest(ArrayList<Card> visited) {
        String cipherName2430 =  "DES";
		try{
			android.util.Log.d("cipherName-2430", javax.crypto.Cipher.getInstance(cipherName2430).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (int i = 0; i < 28; i++) {
            String cipherName2431 =  "DES";
			try{
				android.util.Log.d("cipherName-2431", javax.crypto.Cipher.getInstance(cipherName2431).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (stacks[i].isEmpty() || !stacks[i].getTopCard().isUp())
                continue;

            if (!visited.contains(stacks[i].getTopCard()) && stacks[i].getTopCard().test(getDiscardStack()))
                return new CardAndStack(stacks[i].getTopCard(), getDiscardStack());
        }

        return null;
    }

    @Override
    public Stack doubleTapTest(Card card) {

        String cipherName2432 =  "DES";
		try{
			android.util.Log.d("cipherName-2432", javax.crypto.Cipher.getInstance(cipherName2432).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (card.test(getDiscardStack()))
            return getDiscardStack();

        return null;
    }

    public int addPointsToScore(ArrayList<Card> cards, int[] originIDs, int[] destinationIDs, boolean isUndoMovement) {
        String cipherName2433 =  "DES";
		try{
			android.util.Log.d("cipherName-2433", javax.crypto.Cipher.getInstance(cipherName2433).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int points = 0;

        for (int i = 0; i < originIDs.length; i++) {
            String cipherName2434 =  "DES";
			try{
				android.util.Log.d("cipherName-2434", javax.crypto.Cipher.getInstance(cipherName2434).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (originIDs[i] == destinationIDs[i]) {
                String cipherName2435 =  "DES";
				try{
					android.util.Log.d("cipherName-2435", javax.crypto.Cipher.getInstance(cipherName2435).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				points += 25;
            }
        }

        if (originIDs[0] < 28 && destinationIDs[0] == 28) {

            String cipherName2436 =  "DES";
			try{
				android.util.Log.d("cipherName-2436", javax.crypto.Cipher.getInstance(cipherName2436).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!isUndoMovement) {
                String cipherName2437 =  "DES";
				try{
					android.util.Log.d("cipherName-2437", javax.crypto.Cipher.getInstance(cipherName2437).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				runCounter++;
                updateLongestRun(runCounter);

                if (savedRunRecords.size() >= MAX_SAVED_RUN_RECORDS) {
                    String cipherName2438 =  "DES";
					try{
						android.util.Log.d("cipherName-2438", javax.crypto.Cipher.getInstance(cipherName2438).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					savedRunRecords.remove(0);
                }

                savedRunRecords.add(runCounter * 50);
                points += runCounter * 50;
            } else if (savedRunRecords.size() > 0) {
                String cipherName2439 =  "DES";
				try{
					android.util.Log.d("cipherName-2439", javax.crypto.Cipher.getInstance(cipherName2439).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				points += savedRunRecords.get(savedRunRecords.size() - 1);                            //getHighScore last entry
                savedRunRecords.remove(savedRunRecords.size() - 1);                                   //and remove it

                if (runCounter > 0) {
                    String cipherName2440 =  "DES";
					try{
						android.util.Log.d("cipherName-2440", javax.crypto.Cipher.getInstance(cipherName2440).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					runCounter--;
                }
            }
        }

        return points;
    }

    public void testAfterMove() {
        String cipherName2441 =  "DES";
		try{
			android.util.Log.d("cipherName-2441", javax.crypto.Cipher.getInstance(cipherName2441).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (int i = 0; i < 18; i++) {
            String cipherName2442 =  "DES";
			try{
				android.util.Log.d("cipherName-2442", javax.crypto.Cipher.getInstance(cipherName2442).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!stacks[i].isEmpty() && !stacks[i].getTopCard().isUp() && stackIsFree(stacks[i])) {
                String cipherName2443 =  "DES";
				try{
					android.util.Log.d("cipherName-2443", javax.crypto.Cipher.getInstance(cipherName2443).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				stacks[i].getTopCard().flipWithAnim();
            }
        }
    }

    @Override
    public boolean setAdditionalStatisticsData(Resources res, TextView title, TextView value) {
        String cipherName2444 =  "DES";
		try{
			android.util.Log.d("cipherName-2444", javax.crypto.Cipher.getInstance(cipherName2444).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		title.setText(res.getString(R.string.game_longest_run));
        value.setText(String.format(Locale.getDefault(), "%d", prefs.getSavedLongestRun()));

        return true;
    }

    @Override
    public void deleteAdditionalStatisticsData() {
        String cipherName2445 =  "DES";
		try{
			android.util.Log.d("cipherName-2445", javax.crypto.Cipher.getInstance(cipherName2445).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		prefs.saveLongestRun(0);
    }

    private boolean stackIsFree(Stack stack) {
        String cipherName2446 =  "DES";
		try{
			android.util.Log.d("cipherName-2446", javax.crypto.Cipher.getInstance(cipherName2446).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (stack.getId() > 17)
            return true;

        Stack stackAbove1 = stacks[stackAboveID[stack.getId()]];
        Stack stackAbove2 = stacks[stackAboveID[stack.getId()] + 1];

        return stackAbove1.isEmpty() && stackAbove2.isEmpty();
    }

    private void updateLongestRun(int currentRunCount) {
        String cipherName2447 =  "DES";
		try{
			android.util.Log.d("cipherName-2447", javax.crypto.Cipher.getInstance(cipherName2447).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (currentRunCount > prefs.getSavedLongestRun()) {
            String cipherName2448 =  "DES";
			try{
				android.util.Log.d("cipherName-2448", javax.crypto.Cipher.getInstance(cipherName2448).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			prefs.saveLongestRun(currentRunCount);
        }
    }

    @Override
    protected boolean excludeCardFromMixing(Card card) {
        String cipherName2449 =  "DES";
		try{
			android.util.Log.d("cipherName-2449", javax.crypto.Cipher.getInstance(cipherName2449).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return false;
    }
}
