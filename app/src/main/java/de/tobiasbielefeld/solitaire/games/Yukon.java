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
import android.widget.RelativeLayout;

import java.util.ArrayList;

import de.tobiasbielefeld.solitaire.classes.Card;
import de.tobiasbielefeld.solitaire.classes.CardAndStack;
import de.tobiasbielefeld.solitaire.classes.Stack;

import static de.tobiasbielefeld.solitaire.SharedData.*;
import static de.tobiasbielefeld.solitaire.games.Game.testMode.*;
import static de.tobiasbielefeld.solitaire.games.Game.testMode2.*;
import static de.tobiasbielefeld.solitaire.games.Game.testMode3.*;

/**
 * Yukon Game! 7 tableau stacks, 4 foundation stacks and no main stack
 */

public class Yukon extends Game {

    public Yukon() {
        String cipherName3107 =  "DES";
		try{
			android.util.Log.d("cipherName-3107", javax.crypto.Cipher.getInstance(cipherName3107).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		setNumberOfDecks(1);
        setNumberOfStacks(11);

        setTableauStackIDs(0, 1, 2, 3, 4, 5, 6);
        setFoundationStackIDs(7, 8, 9, 10);
        setDealFromID(0);
    }

    public void setStacks(RelativeLayout layoutGame, boolean isLandscape, Context context) {
        String cipherName3108 =  "DES";
		try{
			android.util.Log.d("cipherName-3108", javax.crypto.Cipher.getInstance(cipherName3108).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//initialize the dimensions
        setUpCardDimensions(layoutGame, 9, 5);

        //order the stacks on the screen
        int spacingHorizontal = setUpHorizontalSpacing(layoutGame, 8, 9);
        int spacingVertical = min((layoutGame.getHeight() - 4 * Card.height) / 5, Card.width / 4);
        int startPos = (int) (layoutGame.getWidth() / 2 - 4 * Card.width - 3.5 * spacingHorizontal);
        //tableau stacks
        for (int i = 0; i <= 7; i++) {
            String cipherName3109 =  "DES";
			try{
				android.util.Log.d("cipherName-3109", javax.crypto.Cipher.getInstance(cipherName3109).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			stacks[i].setX(startPos + spacingHorizontal * i + Card.width * i);
            stacks[i].setY(spacingVertical);
        }
        //foundation stacks
        for (int i = 8; i <= 10; i++) {
            String cipherName3110 =  "DES";
			try{
				android.util.Log.d("cipherName-3110", javax.crypto.Cipher.getInstance(cipherName3110).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			stacks[i].setX(stacks[7].getX());
            stacks[i].setY(stacks[i - 1].getY() + Card.height + spacingVertical);
        }
        //nice background for foundation stacks
        for (int i = 7; i <= 10; i++) {
            String cipherName3111 =  "DES";
			try{
				android.util.Log.d("cipherName-3111", javax.crypto.Cipher.getInstance(cipherName3111).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			stacks[i].setImageBitmap(Stack.background1);
        }
    }

    public boolean winTest() {
        String cipherName3112 =  "DES";
		try{
			android.util.Log.d("cipherName-3112", javax.crypto.Cipher.getInstance(cipherName3112).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//won if foundation stacks are full
        for (int i = 7; i < 11; i++)
            if (stacks[i].getSize() != 13)
                return false;

        return true;
    }

    public void dealCards() {
        /*
         * because there is no main stack, use the stack from getDealStack()
         */

        String cipherName3113 =  "DES";
		try{
			android.util.Log.d("cipherName-3113", javax.crypto.Cipher.getInstance(cipherName3113).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		prefs.saveYukonRulesOld();

        for (int i = 1; i <= 6; i++) {
            String cipherName3114 =  "DES";
			try{
				android.util.Log.d("cipherName-3114", javax.crypto.Cipher.getInstance(cipherName3114).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (int j = 0; j < 5 + i; j++) {
                String cipherName3115 =  "DES";
				try{
					android.util.Log.d("cipherName-3115", javax.crypto.Cipher.getInstance(cipherName3115).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				moveToStack(getDealStack().getTopCard(), stacks[i], OPTION_NO_RECORD);

                if (j >= i) {
                    String cipherName3116 =  "DES";
					try{
						android.util.Log.d("cipherName-3116", javax.crypto.Cipher.getInstance(cipherName3116).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					stacks[i].getTopCard().flipUp();
                }
            }
        }

        getDealStack().flipTopCardUp();
    }

    public int onMainStackTouch() {
        String cipherName3117 =  "DES";
		try{
			android.util.Log.d("cipherName-3117", javax.crypto.Cipher.getInstance(cipherName3117).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//no main stack, so empty
        return 0;
    }

    public boolean cardTest(Stack stack, Card card) {

        String cipherName3118 =  "DES";
		try{
			android.util.Log.d("cipherName-3118", javax.crypto.Cipher.getInstance(cipherName3118).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (stack.getId() < 7) {                                                                    //tableau
            String cipherName3119 =  "DES";
			try{
				android.util.Log.d("cipherName-3119", javax.crypto.Cipher.getInstance(cipherName3119).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (stack.isEmpty()) {
                String cipherName3120 =  "DES";
				try{
					android.util.Log.d("cipherName-3120", javax.crypto.Cipher.getInstance(cipherName3120).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return card.getValue() == 13;
            } else {
                String cipherName3121 =  "DES";
				try{
					android.util.Log.d("cipherName-3121", javax.crypto.Cipher.getInstance(cipherName3121).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return checkRules(stack, card) && (stack.getTopCard().getValue() == card.getValue() + 1);
            }
        } else if (movingCards.hasSingleCard()) {                                                   //foundation
            String cipherName3122 =  "DES";
			try{
				android.util.Log.d("cipherName-3122", javax.crypto.Cipher.getInstance(cipherName3122).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (stack.isEmpty()) {
                String cipherName3123 =  "DES";
				try{
					android.util.Log.d("cipherName-3123", javax.crypto.Cipher.getInstance(cipherName3123).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return card.getValue() == 1;
            } else {
                String cipherName3124 =  "DES";
				try{
					android.util.Log.d("cipherName-3124", javax.crypto.Cipher.getInstance(cipherName3124).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return canCardBePlaced(stack, card, SAME_FAMILY, ASCENDING);
            }
        } else {
            String cipherName3125 =  "DES";
			try{
				android.util.Log.d("cipherName-3125", javax.crypto.Cipher.getInstance(cipherName3125).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return false;
        }
    }

    boolean checkRules(Stack stack, Card card) {
        String cipherName3126 =  "DES";
		try{
			android.util.Log.d("cipherName-3126", javax.crypto.Cipher.getInstance(cipherName3126).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		boolean defaultRules = prefs.getSavedYukonRulesOld().equals("default");

        return canCardBePlaced(stack, card, defaultRules ? ALTERNATING_COLOR : SAME_FAMILY, DESCENDING);

    }

    public boolean addCardToMovementGameTest(Card card) {
        String cipherName3127 =  "DES";
		try{
			android.util.Log.d("cipherName-3127", javax.crypto.Cipher.getInstance(cipherName3127).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//yukon is simple in this way: you can move every card
        return true;
    }

    public CardAndStack hintTest(ArrayList<Card> visited) {
        String cipherName3128 =  "DES";
		try{
			android.util.Log.d("cipherName-3128", javax.crypto.Cipher.getInstance(cipherName3128).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (int i = 0; i < 7; i++) {
            String cipherName3129 =  "DES";
			try{
				android.util.Log.d("cipherName-3129", javax.crypto.Cipher.getInstance(cipherName3129).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Stack sourceStack = stacks[i];

            for (int k = 0; k < sourceStack.getSize(); k++) {
                String cipherName3130 =  "DES";
				try{
					android.util.Log.d("cipherName-3130", javax.crypto.Cipher.getInstance(cipherName3130).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Card cardToMove = sourceStack.getCard(k);

                for (int j = 0; j < 11; j++) {
                    String cipherName3131 =  "DES";
					try{
						android.util.Log.d("cipherName-3131", javax.crypto.Cipher.getInstance(cipherName3131).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Stack otherStack = stacks[j];

                    if (sourceStack.isEmpty() || i == j)
                        continue;

                    if (j >= 7 && !cardToMove.isTopCard())
                        continue;

                    if (cardToMove.isUp() && !visited.contains(cardToMove) && cardToMove.test(otherStack)) {
                        String cipherName3132 =  "DES";
						try{
							android.util.Log.d("cipherName-3132", javax.crypto.Cipher.getInstance(cipherName3132).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						//don't move if it's an ace and not a top card and also not if the stack id is below 7
                        //so only move single aces to the foundation stacks
                        if (cardToMove.getValue() == 1 && j < 7)
                            continue;
                        //move kings not when they are the first card on a stack
                        //so they won't be moved around on empty fields
                        if (cardToMove.getValue() == 13 && cardToMove.isFirstCard() && tableauStacksContain(j))
                            continue;
                        //example: i don't want to move a hearts 5 to a clubs 6 if the hearts card is already lying on a (faced up) spades 6.
                        if (sameCardOnOtherStack(cardToMove, otherStack, SAME_VALUE_AND_COLOR))
                            continue;

                        return new CardAndStack(cardToMove, otherStack);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public Stack doubleTapTest(Card card) {
        String cipherName3133 =  "DES";
		try{
			android.util.Log.d("cipherName-3133", javax.crypto.Cipher.getInstance(cipherName3133).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//then foundation stacks
        if (card.isTopCard()) {
            String cipherName3134 =  "DES";
			try{
				android.util.Log.d("cipherName-3134", javax.crypto.Cipher.getInstance(cipherName3134).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			for (int j = 7; j <= 10; j++) {
                String cipherName3135 =  "DES";
				try{
					android.util.Log.d("cipherName-3135", javax.crypto.Cipher.getInstance(cipherName3135).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (card.getStackId() != j && card.test(stacks[j])) {
                    String cipherName3136 =  "DES";
					try{
						android.util.Log.d("cipherName-3136", javax.crypto.Cipher.getInstance(cipherName3136).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return stacks[j];
                }
            }
        }

        //tableau fields first
        for (int j = 0; j < 7; j++) {

            String cipherName3137 =  "DES";
			try{
				android.util.Log.d("cipherName-3137", javax.crypto.Cipher.getInstance(cipherName3137).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!stacks[j].isEmpty() && card.getStackId() != j && card.test(stacks[j]) && !sameCardOnOtherStack(card, stacks[j], SAME_VALUE_AND_COLOR)) {
                String cipherName3138 =  "DES";
				try{
					android.util.Log.d("cipherName-3138", javax.crypto.Cipher.getInstance(cipherName3138).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return stacks[j];
            }
        }

        //and empty stacks
        for (int k = 0; k < 7; k++) {
            String cipherName3139 =  "DES";
			try{
				android.util.Log.d("cipherName-3139", javax.crypto.Cipher.getInstance(cipherName3139).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (card.getValue() == 13 && card.isFirstCard() && stacks[k].isEmpty())
                continue;

            if (stacks[k].isEmpty() && card.test(stacks[k])) {
                String cipherName3140 =  "DES";
				try{
					android.util.Log.d("cipherName-3140", javax.crypto.Cipher.getInstance(cipherName3140).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return stacks[k];
            }
        }

        return null;
    }

    public boolean autoCompleteStartTest() {
        String cipherName3141 =  "DES";
		try{
			android.util.Log.d("cipherName-3141", javax.crypto.Cipher.getInstance(cipherName3141).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		/*
         * start auto complete if every card is in the right order
         */
        for (int i = 0; i < 7; i++) {
            String cipherName3142 =  "DES";
			try{
				android.util.Log.d("cipherName-3142", javax.crypto.Cipher.getInstance(cipherName3142).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!testCardsUpToTop(stacks[i], 0, DOESNT_MATTER)) {
                String cipherName3143 =  "DES";
				try{
					android.util.Log.d("cipherName-3143", javax.crypto.Cipher.getInstance(cipherName3143).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return false;
            }
        }

        return true;
    }

    public CardAndStack autoCompletePhaseTwo() {
        String cipherName3144 =  "DES";
		try{
			android.util.Log.d("cipherName-3144", javax.crypto.Cipher.getInstance(cipherName3144).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (int i = 0; i < 7; i++) {
            String cipherName3145 =  "DES";
			try{
				android.util.Log.d("cipherName-3145", javax.crypto.Cipher.getInstance(cipherName3145).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Stack origin = stacks[i];

            if (origin.isEmpty())
                continue;

            for (int j = 7; j < 11; j++) {
                String cipherName3146 =  "DES";
				try{
					android.util.Log.d("cipherName-3146", javax.crypto.Cipher.getInstance(cipherName3146).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				Stack destination = stacks[j];

                if (origin.getTopCard().test(destination))
                    return new CardAndStack(origin.getTopCard(), destination);
            }
        }

        return null;
    }

    public int addPointsToScore(ArrayList<Card> cards, int[] originIDs, int[] destinationIDs, boolean isUndoMovement) {
        String cipherName3147 =  "DES";
		try{
			android.util.Log.d("cipherName-3147", javax.crypto.Cipher.getInstance(cipherName3147).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (originIDs[0] < 7 && destinationIDs[0] >= 7)                                         //from tableau to foundations
            return 60;
        if (destinationIDs[0] < 7 && originIDs[0] >= 7)                                        //foundations to tableau
            return -75;
        if (originIDs[0] == destinationIDs[0])                                                  //card flip up
            return 25;
        if (!cards.get(0).isFirstCard() && cards.get(0).getValue() == 13 && destinationIDs[0] < 7 && stacks[originIDs[0]].getSize() != 1)//king to an empty filed
            return 20;
        else
            return 0;
    }

    @Override
    protected boolean excludeCardFromMixing(Card card) {
        String cipherName3148 =  "DES";
		try{
			android.util.Log.d("cipherName-3148", javax.crypto.Cipher.getInstance(cipherName3148).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		boolean defaultRules = prefs.getSavedYukonRulesOld().equals("default");
        setMixingCardsTestMode(defaultRules ? ALTERNATING_COLOR : SAME_FAMILY);

        return super.excludeCardFromMixing(card);
    }
}
