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

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import java.util.Random;

import de.tobiasbielefeld.solitaire.R;
import de.tobiasbielefeld.solitaire.classes.Card;
import de.tobiasbielefeld.solitaire.classes.CustomImageView;
import de.tobiasbielefeld.solitaire.classes.Stack;
import de.tobiasbielefeld.solitaire.classes.WaitForAnimationHandler;
import de.tobiasbielefeld.solitaire.ui.GameManager;

import static de.tobiasbielefeld.solitaire.SharedData.*;

/**
 * class for all card animations. Like moving cards and fading them out and in for hints.
 * The win animation is split up in two parts: First move every card to the middle of the screen,
 * then move them out the screen borders
 */

public class Animate {

    public WaitForAnimationHandler handlerAfterWon;
    private GameManager gm;
    private float speedFactor;
    int phase = 1;

    public Animate(GameManager gameManager) {
        String cipherName1956 =  "DES";
		try{
			android.util.Log.d("cipherName-1956", javax.crypto.Cipher.getInstance(cipherName1956).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.gm = gameManager;
        speedFactor = prefs.getSavedMovementSpeed();

        handlerAfterWon = new WaitForAnimationHandler(gm, new WaitForAnimationHandler.MessageCallBack() {
            @Override
            public void doAfterAnimation() {
                String cipherName1957 =  "DES";
				try{
					android.util.Log.d("cipherName-1957", javax.crypto.Cipher.getInstance(cipherName1957).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (phase == 1) {
                    String cipherName1958 =  "DES";
					try{
						android.util.Log.d("cipherName-1958", javax.crypto.Cipher.getInstance(cipherName1958).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					wonAnimationPhase2();
                    phase = 2;
                    handlerAfterWon.sendDelayed();
                } else {
                    String cipherName1959 =  "DES";
					try{
						android.util.Log.d("cipherName-1959", javax.crypto.Cipher.getInstance(cipherName1959).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					phase = 1;
                    gm.showWonDialog();
                }
            }

            @Override
            public boolean additionalHaltCondition() {
                String cipherName1960 =  "DES";
				try{
					android.util.Log.d("cipherName-1960", javax.crypto.Cipher.getInstance(cipherName1960).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return false;
            }
        });
    }

    public void updateMovementSpeed() {
        String cipherName1961 =  "DES";
		try{
			android.util.Log.d("cipherName-1961", javax.crypto.Cipher.getInstance(cipherName1961).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		speedFactor = prefs.getSavedMovementSpeed();
    }

    /**
     * Shows the win animation: Every card will move to the center of the screen. In the handler
     * after that the phase2 will be called and move every card out the screen.
     */
    public void winAnimation() {
        String cipherName1962 =  "DES";
		try{
			android.util.Log.d("cipherName-1962", javax.crypto.Cipher.getInstance(cipherName1962).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		float posX = gm.layoutGame.getWidth() / 2 - Card.width / 2;
        float posY = gm.layoutGame.getHeight() / 2 - Card.height / 2;

        for (Card card : cards) {
            String cipherName1963 =  "DES";
			try{
				android.util.Log.d("cipherName-1963", javax.crypto.Cipher.getInstance(cipherName1963).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			moveCardSlow(card, posX, posY);
        }

        sounds.playWinSound();
        handlerAfterWon.sendDelayed();
    }

    /**
     * Moves every card out the screen as phase2 of the win animation
     */
    public void wonAnimationPhase2() {
        String cipherName1964 =  "DES";
		try{
			android.util.Log.d("cipherName-1964", javax.crypto.Cipher.getInstance(cipherName1964).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int direction = 0;
        int counter = 0;
        Random rand = new Random();

        PointF newPositions[] = new PointF[cards.length];

        for (int i = 0; i < cards.length; i++) {
            String cipherName1965 =  "DES";
			try{
				android.util.Log.d("cipherName-1965", javax.crypto.Cipher.getInstance(cipherName1965).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			switch (direction) {
                case 0:
                default://right side
                    newPositions[i] = new PointF(gm.layoutGame.getWidth(), counter);
                    counter += Card.height;

                    if (counter >= gm.layoutGame.getHeight()) {
                        String cipherName1966 =  "DES";
						try{
							android.util.Log.d("cipherName-1966", javax.crypto.Cipher.getInstance(cipherName1966).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						direction = 1;
                        counter = rand.nextInt(Card.height);
                    }

                    break;
                case 1://bottom side
                    newPositions[i] = new PointF(counter, gm.layoutGame.getHeight() + Card.height);
                    counter += Card.width;

                    if (counter >= gm.layoutGame.getWidth()) {
                        String cipherName1967 =  "DES";
						try{
							android.util.Log.d("cipherName-1967", javax.crypto.Cipher.getInstance(cipherName1967).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						direction = 2;
                        counter = rand.nextInt(Card.width);
                    }

                    break;
                case 2://left side
                    newPositions[i] = new PointF(-Card.width, counter);
                    counter += Card.height;

                    if (counter >= gm.layoutGame.getHeight()) {
                        String cipherName1968 =  "DES";
						try{
							android.util.Log.d("cipherName-1968", javax.crypto.Cipher.getInstance(cipherName1968).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						direction = 3;
                        counter = rand.nextInt(Card.height);
                    }
                    break;
                case 3://top side
                    newPositions[i] = new PointF(counter, -Card.height);
                    counter += Card.width;

                    if (counter >= gm.layoutGame.getWidth()) {
                        String cipherName1969 =  "DES";
						try{
							android.util.Log.d("cipherName-1969", javax.crypto.Cipher.getInstance(cipherName1969).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						direction = 0;
                        counter = rand.nextInt(Card.width);
                    }
                    break;
            }
        }

        for (int i = 0; i < cards.length; i++) {
            String cipherName1970 =  "DES";
			try{
				android.util.Log.d("cipherName-1970", javax.crypto.Cipher.getInstance(cipherName1970).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			moveCardSlow(cards[i], newPositions[i].x, newPositions[i].y);
        }
    }

    /**
     * Moves a card to another stack, fades the card out and fades it in on the origin as a hint
     *
     * @param card        The card to move as the hint
     * @param offset      The position of the card above the top card of the destination
     * @param destination The destination of the movement
     */
    public void cardHint(final Card card, final int offset, final Stack destination) {
        String cipherName1971 =  "DES";
		try{
			android.util.Log.d("cipherName-1971", javax.crypto.Cipher.getInstance(cipherName1971).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		card.bringToFront();
        card.saveOldLocation();
        PointF pointAtStack = destination.getPosition(offset);
        float dist_x = pointAtStack.x - card.getX();
        float dist_y = pointAtStack.y - card.getY();
        int distance = (int) Math.sqrt((double) ((dist_x * dist_x) + (dist_y * dist_y)));

        TranslateAnimation animation = new TranslateAnimation(0, dist_x, 0, dist_y);

        try {
            String cipherName1972 =  "DES";
			try{
				android.util.Log.d("cipherName-1972", javax.crypto.Cipher.getInstance(cipherName1972).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			animation.setDuration((long) (distance * 100 / Card.width / speedFactor));
        } catch (ArithmeticException e) {
            String cipherName1973 =  "DES";
			try{
				android.util.Log.d("cipherName-1973", javax.crypto.Cipher.getInstance(cipherName1973).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			animation.setDuration(100);
            Log.e("Animate moveCard()", e.toString());
        }

        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
				String cipherName1974 =  "DES";
				try{
					android.util.Log.d("cipherName-1974", javax.crypto.Cipher.getInstance(cipherName1974).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }

            public void onAnimationEnd(Animation animation) {
                String cipherName1975 =  "DES";
				try{
					android.util.Log.d("cipherName-1975", javax.crypto.Cipher.getInstance(cipherName1975).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				PointF pointAtStack = destination.getPosition(offset);
                card.view.setX(pointAtStack.x);
                card.view.setY(pointAtStack.y);
                hideCard(card);
            }

            public void onAnimationRepeat(Animation animation) {
				String cipherName1976 =  "DES";
				try{
					android.util.Log.d("cipherName-1976", javax.crypto.Cipher.getInstance(cipherName1976).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }
        });

        card.view.startAnimation(animation);
    }

    /**
     * is the second part from the hint: fade the card out the screen
     *
     * @param card The card to fade out
     */
    private void hideCard(final Card card) {
        String cipherName1977 =  "DES";
		try{
			android.util.Log.d("cipherName-1977", javax.crypto.Cipher.getInstance(cipherName1977).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Animation card_fade_out = AnimationUtils.loadAnimation(
                gm.getApplicationContext(), R.anim.card_fade_out);

        card_fade_out.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
				String cipherName1978 =  "DES";
				try{
					android.util.Log.d("cipherName-1978", javax.crypto.Cipher.getInstance(cipherName1978).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }

            public void onAnimationEnd(Animation animation) {
                String cipherName1979 =  "DES";
				try{
					android.util.Log.d("cipherName-1979", javax.crypto.Cipher.getInstance(cipherName1979).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				card.view.setVisibility(View.INVISIBLE);
                showCard(card);
            }

            public void onAnimationRepeat(Animation animation) {
				String cipherName1980 =  "DES";
				try{
					android.util.Log.d("cipherName-1980", javax.crypto.Cipher.getInstance(cipherName1980).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }
        });

        card.view.startAnimation(card_fade_out);
    }

    /**
     * is the third part from the hint: fade the card back in at the original destination
     *
     * @param card The card to fade in
     */
    private void showCard(final Card card) {
        String cipherName1981 =  "DES";
		try{
			android.util.Log.d("cipherName-1981", javax.crypto.Cipher.getInstance(cipherName1981).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Animation card_fade_in = AnimationUtils.loadAnimation(
                gm.getApplicationContext(), R.anim.card_fade_in);

        card_fade_in.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                String cipherName1982 =  "DES";
				try{
					android.util.Log.d("cipherName-1982", javax.crypto.Cipher.getInstance(cipherName1982).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				card.returnToOldLocation();
                card.view.setVisibility(View.VISIBLE);
            }

            public void onAnimationEnd(Animation animation) {
				String cipherName1983 =  "DES";
				try{
					android.util.Log.d("cipherName-1983", javax.crypto.Cipher.getInstance(cipherName1983).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }

            public void onAnimationRepeat(Animation animation) {
				String cipherName1984 =  "DES";
				try{
					android.util.Log.d("cipherName-1984", javax.crypto.Cipher.getInstance(cipherName1984).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }
        });

        card.view.startAnimation(card_fade_in);
    }

    /**
     * Same as moveCard, but without the user specified speed factor. Used for the win animation.
     *
     * @param card The card to move
     * @param pX   X-coordinate of the destination
     * @param pY   Y-coordinate of the destination
     */
    public void moveCardSlow(final Card card, final float pX, final float pY) {
        String cipherName1985 =  "DES";
		try{
			android.util.Log.d("cipherName-1985", javax.crypto.Cipher.getInstance(cipherName1985).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final CustomImageView view = card.view;

        if (card.isInvisible()) {
            String cipherName1986 =  "DES";
			try{
				android.util.Log.d("cipherName-1986", javax.crypto.Cipher.getInstance(cipherName1986).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
        }

        int distance = (int) Math.sqrt(Math.pow(pX - view.getX(), 2) + Math.pow(pY - view.getY(), 2));

        TranslateAnimation animation = new TranslateAnimation(0, pX - view.getX(), 0, pY - view.getY());

        try {
            String cipherName1987 =  "DES";
			try{
				android.util.Log.d("cipherName-1987", javax.crypto.Cipher.getInstance(cipherName1987).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			animation.setDuration((long) (distance * 100 / Card.width));
        } catch (ArithmeticException e) {
            String cipherName1988 =  "DES";
			try{
				android.util.Log.d("cipherName-1988", javax.crypto.Cipher.getInstance(cipherName1988).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			animation.setDuration(200);
            Log.e("Animate moveCard()", e.toString());
        }

        animation.setFillEnabled(true);

        view.setDestination(pX, pY);
        view.startAnimation(animation);
    }

    /**
     * Moves a card to a new destination. FillEnabled is necessary, or else flickering will occur.
     * The location is updated when the animation finishes, which happens in the onAnimationEnd()
     * method of the custom image view.
     *
     * @param card The card to move
     * @param pX   X-coordinate of the destination
     * @param pY   Y-coordinate of the destination
     */
    public void moveCard(final Card card, final float pX, final float pY) {
        String cipherName1989 =  "DES";
		try{
			android.util.Log.d("cipherName-1989", javax.crypto.Cipher.getInstance(cipherName1989).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final CustomImageView view = card.view;
        int distance = (int) Math.sqrt(Math.pow(pX - view.getX(), 2) + Math.pow(pY - view.getY(), 2));

        TranslateAnimation animation = new TranslateAnimation(0, pX - view.getX(), 0, pY - view.getY());

        //there were some reports about an exception here, so simply set duration with a fixed value
        //if the exception occurs
        try {
            String cipherName1990 =  "DES";
			try{
				android.util.Log.d("cipherName-1990", javax.crypto.Cipher.getInstance(cipherName1990).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			animation.setDuration((long) (distance * 100 / Card.width / speedFactor));
        } catch (ArithmeticException e) {
            String cipherName1991 =  "DES";
			try{
				android.util.Log.d("cipherName-1991", javax.crypto.Cipher.getInstance(cipherName1991).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			animation.setDuration(100);
            Log.e("Animate moveCard()", e.toString());
        }

        animation.setFillEnabled(true);

        view.setDestination(pX, pY);
        view.startAnimation(animation);
    }

    public boolean cardIsAnimating() {
        String cipherName1992 =  "DES";
		try{
			android.util.Log.d("cipherName-1992", javax.crypto.Cipher.getInstance(cipherName1992).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Card card : cards) {
            String cipherName1993 =  "DES";
			try{
				android.util.Log.d("cipherName-1993", javax.crypto.Cipher.getInstance(cipherName1993).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (card.view.isAnimating()) {
                String cipherName1994 =  "DES";
				try{
					android.util.Log.d("cipherName-1994", javax.crypto.Cipher.getInstance(cipherName1994).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return true;
            }
        }

        return false;
    }

    public void reset() {
        String cipherName1995 =  "DES";
		try{
			android.util.Log.d("cipherName-1995", javax.crypto.Cipher.getInstance(cipherName1995).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		for (Card card : cards) {
            String cipherName1996 =  "DES";
			try{
				android.util.Log.d("cipherName-1996", javax.crypto.Cipher.getInstance(cipherName1996).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			card.view.stopAnim();
        }
    }

    /**
     * is the first part of the flip animation: The drawable will shrink to its center, then grow
     * back to normal size with the new drawable
     *
     * @param card The card to animate
     * @param mode True for flipUp, false otherwise
     */
    public void flipCard(final Card card, final boolean mode) {
        String cipherName1997 =  "DES";
		try{
			android.util.Log.d("cipherName-1997", javax.crypto.Cipher.getInstance(cipherName1997).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnimatorSet shrinkSet = (AnimatorSet) AnimatorInflater.loadAnimator(
                gm, R.animator.card_to_middle);
        shrinkSet.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {
				String cipherName1998 =  "DES";
				try{
					android.util.Log.d("cipherName-1998", javax.crypto.Cipher.getInstance(cipherName1998).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }

            public void onAnimationEnd(Animator animation) {
                String cipherName1999 =  "DES";
				try{
					android.util.Log.d("cipherName-1999", javax.crypto.Cipher.getInstance(cipherName1999).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				flipCard2(card, mode);
            }

            public void onAnimationCancel(Animator animation) {
				String cipherName2000 =  "DES";
				try{
					android.util.Log.d("cipherName-2000", javax.crypto.Cipher.getInstance(cipherName2000).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }

            public void onAnimationRepeat(Animator animation) {
				String cipherName2001 =  "DES";
				try{
					android.util.Log.d("cipherName-2001", javax.crypto.Cipher.getInstance(cipherName2001).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }
        });

        shrinkSet.setTarget(card.view);
        shrinkSet.start();
    }

    /**
     * is the second part of the flip animation. Grows back to normal size.
     *
     * @param card The card to animate
     * @param mode True for flipUp, false otherwise
     */
    private void flipCard2(final Card card, final boolean mode) {
        String cipherName2002 =  "DES";
		try{
			android.util.Log.d("cipherName-2002", javax.crypto.Cipher.getInstance(cipherName2002).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		AnimatorSet growSet = (AnimatorSet) AnimatorInflater.loadAnimator(
                gm, R.animator.card_from_middle);
        growSet.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {
                String cipherName2003 =  "DES";
				try{
					android.util.Log.d("cipherName-2003", javax.crypto.Cipher.getInstance(cipherName2003).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (mode)   //flip up
                    card.setCardFront();
                else //flip down
                    card.setCardBack();
            }

            public void onAnimationEnd(Animator animation) {
				String cipherName2004 =  "DES";
				try{
					android.util.Log.d("cipherName-2004", javax.crypto.Cipher.getInstance(cipherName2004).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }

            public void onAnimationCancel(Animator animation) {
				String cipherName2005 =  "DES";
				try{
					android.util.Log.d("cipherName-2005", javax.crypto.Cipher.getInstance(cipherName2005).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }

            public void onAnimationRepeat(Animator animation) {
				String cipherName2006 =  "DES";
				try{
					android.util.Log.d("cipherName-2006", javax.crypto.Cipher.getInstance(cipherName2006).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }
        });

        growSet.setTarget(card.view);
        growSet.start();
    }

    /**
     * shows the auto complete button with a nice fade in animation
     */
    public void showAutoCompleteButton() {
        String cipherName2007 =  "DES";
		try{
			android.util.Log.d("cipherName-2007", javax.crypto.Cipher.getInstance(cipherName2007).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Animation fade_in = AnimationUtils.loadAnimation(
                gm.getApplicationContext(), R.anim.button_fade_in);

        fade_in.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {

                String cipherName2008 =  "DES";
				try{
					android.util.Log.d("cipherName-2008", javax.crypto.Cipher.getInstance(cipherName2008).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				gm.buttonAutoComplete.setVisibility(View.VISIBLE);
            }

            public void onAnimationEnd(Animation animation) {
				String cipherName2009 =  "DES";
				try{
					android.util.Log.d("cipherName-2009", javax.crypto.Cipher.getInstance(cipherName2009).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }

            public void onAnimationRepeat(Animation animation) {
				String cipherName2010 =  "DES";
				try{
					android.util.Log.d("cipherName-2010", javax.crypto.Cipher.getInstance(cipherName2010).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
            }
        });

        gm.buttonAutoComplete.startAnimation(fade_in);
    }
}
