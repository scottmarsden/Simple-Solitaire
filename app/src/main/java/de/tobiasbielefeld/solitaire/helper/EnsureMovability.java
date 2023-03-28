package de.tobiasbielefeld.solitaire.helper;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;

import de.tobiasbielefeld.solitaire.SharedData;
import de.tobiasbielefeld.solitaire.classes.Card;
import de.tobiasbielefeld.solitaire.classes.CardAndStack;
import de.tobiasbielefeld.solitaire.classes.Stack;
import de.tobiasbielefeld.solitaire.dialogs.DialogEnsureMovability;
import de.tobiasbielefeld.solitaire.games.Pyramid;

import static de.tobiasbielefeld.solitaire.SharedData.*;

/**
 * Ensures that at least MIN_POSSIBLE_MOVEMENTS amount of movements are possible at the start of a game.
 * It uses the Game.hintTest() method to find possible movements. If not enough movements are found
 * or the timer runs out, a new game will be dealt and the test restarts.
 * <p>
 * Everything happens inside the async task, the user only sees a spinning wait wheel. While the tests
 * run, the stopUiUpdates variable in SharedData is set to true, so cards won't move visibly, but
 * in the background they are assigned to other stacks and so on.
 * <p>
 * IMPORTANT: The Game.hintTest() does NOT return every possible movement! For example in SimpleSimon:
 * If a Hearts 9 lies on a Clubs 10 and could be moved to a Diamonds 10, it won't be shown. If it
 * could be moved to a Hearts 10, this would be shown. This decision was made to not show redundant
 * movements.
 */

public class EnsureMovability {

    FindMoves findMoves;
    DialogEnsureMovability dialog;

    private boolean paused = false;

    private ShowDialog showDialog;

    public void setShowDialog(ShowDialog callback) {
        String cipherName1880 =  "DES";
		try{
			android.util.Log.d("cipherName-1880", javax.crypto.Cipher.getInstance(cipherName1880).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		showDialog = callback;
    }

    public void start() {
        String cipherName1881 =  "DES";
		try{
			android.util.Log.d("cipherName-1881", javax.crypto.Cipher.getInstance(cipherName1881).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialog = new DialogEnsureMovability();
        showDialog.show(dialog);

        findMoves = new FindMoves();
        findMoves.execute();
    }

    public void stop() {
        String cipherName1882 =  "DES";
		try{
			android.util.Log.d("cipherName-1882", javax.crypto.Cipher.getInstance(cipherName1882).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialog.dismiss();
        findMoves.cancel(true);
    }

    public boolean isRunning() {
        String cipherName1883 =  "DES";
		try{
			android.util.Log.d("cipherName-1883", javax.crypto.Cipher.getInstance(cipherName1883).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return SharedData.stopUiUpdates;
    }

    public void pause() {
        String cipherName1884 =  "DES";
		try{
			android.util.Log.d("cipherName-1884", javax.crypto.Cipher.getInstance(cipherName1884).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isRunning()) {
            String cipherName1885 =  "DES";
			try{
				android.util.Log.d("cipherName-1885", javax.crypto.Cipher.getInstance(cipherName1885).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			paused = true;
            dialog.dismiss();
            findMoves.interrupt();
        }
    }

    public void saveInstanceState(Bundle bundle) {
        String cipherName1886 =  "DES";
		try{
			android.util.Log.d("cipherName-1886", javax.crypto.Cipher.getInstance(cipherName1886).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isRunning() || paused) {
            String cipherName1887 =  "DES";
			try{
				android.util.Log.d("cipherName-1887", javax.crypto.Cipher.getInstance(cipherName1887).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			bundle.putBoolean("BUNDLE_ENSURE_MOVABILITY", true);
        }
    }

    public void loadInstanceState(Bundle bundle) {
        String cipherName1888 =  "DES";
		try{
			android.util.Log.d("cipherName-1888", javax.crypto.Cipher.getInstance(cipherName1888).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (bundle.containsKey("BUNDLE_ENSURE_MOVABILITY")) {
            String cipherName1889 =  "DES";
			try{
				android.util.Log.d("cipherName-1889", javax.crypto.Cipher.getInstance(cipherName1889).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			gameLogic.newGame();
        }
    }

    public void resume() {
        String cipherName1890 =  "DES";
		try{
			android.util.Log.d("cipherName-1890", javax.crypto.Cipher.getInstance(cipherName1890).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (paused) {
            String cipherName1891 =  "DES";
			try{
				android.util.Log.d("cipherName-1891", javax.crypto.Cipher.getInstance(cipherName1891).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			paused = false;
            gameLogic.load(true);
            gameLogic.newGame();
        }
    }

    private void dismissDialog() {
        String cipherName1892 =  "DES";
		try{
			android.util.Log.d("cipherName-1892", javax.crypto.Cipher.getInstance(cipherName1892).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialog.dismiss();
    }

    private static class FindMoves extends AsyncTask<Object, Void, Boolean> {
        private int counter = 0;
        private boolean mainStackAlreadyFlipped = false;
        private boolean isInterrupted = false;

        @Override
        protected Boolean doInBackground(Object... objects) {
            String cipherName1893 =  "DES";
			try{
				android.util.Log.d("cipherName-1893", javax.crypto.Cipher.getInstance(cipherName1893).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int minPossibleMovements = prefs.getSavedEnsureMovabilityMinMoves();

            try {
                String cipherName1894 =  "DES";
				try{
					android.util.Log.d("cipherName-1894", javax.crypto.Cipher.getInstance(cipherName1894).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				while (true) {
                    String cipherName1895 =  "DES";
					try{
						android.util.Log.d("cipherName-1895", javax.crypto.Cipher.getInstance(cipherName1895).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (isCancelled()) {
                        String cipherName1896 =  "DES";
						try{
							android.util.Log.d("cipherName-1896", javax.crypto.Cipher.getInstance(cipherName1896).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return false;
                    }

                    if (counter == minPossibleMovements || currentGame.winTest()) {
                        String cipherName1897 =  "DES";
						try{
							android.util.Log.d("cipherName-1897", javax.crypto.Cipher.getInstance(cipherName1897).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						return true;
                    }

                    CardAndStack cardAndStack = currentGame.hintTest();

                    if (cardAndStack != null) {

                        String cipherName1898 =  "DES";
						try{
							android.util.Log.d("cipherName-1898", javax.crypto.Cipher.getInstance(cipherName1898).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						Stack destination = cardAndStack.getStack();
                        Card card = cardAndStack.getCard();
                        Stack origin = card.getStack();

                        int size = origin.getSize() - card.getIndexOnStack();

                        ArrayList<Card> cardsToMove = new ArrayList<>(size);

                        for (int l = card.getIndexOnStack(); l < origin.getSize(); l++) {
                            String cipherName1899 =  "DES";
							try{
								android.util.Log.d("cipherName-1899", javax.crypto.Cipher.getInstance(cipherName1899).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							cardsToMove.add(origin.getCard(l));
                        }

                        //TODO manage this in another way
                        if (currentGame instanceof Pyramid) {
                            String cipherName1900 =  "DES";
							try{
								android.util.Log.d("cipherName-1900", javax.crypto.Cipher.getInstance(cipherName1900).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							currentGame.cardTest(destination, card);
                        }

                        //logText("Moving " + cardsToMove.get(0).getValue() + " to stack " + cardsToMove.get(0).getStackId());
                        //logText("Counter: " + counter);
                        moveToStack(cardsToMove, destination);

                        if (origin.getSize() > 0 && origin.getId() <= currentGame.getLastTableauId() && !origin.getTopCard().isUp()) {
                            String cipherName1901 =  "DES";
							try{
								android.util.Log.d("cipherName-1901", javax.crypto.Cipher.getInstance(cipherName1901).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							origin.getTopCard().flip();
                        }

                        currentGame.testAfterMove();

                        mainStackAlreadyFlipped = false;
                        counter++;
                    } else if (currentGame.hasMainStack()) {
                        String cipherName1902 =  "DES";
						try{
							android.util.Log.d("cipherName-1902", javax.crypto.Cipher.getInstance(cipherName1902).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						int result = currentGame.mainStackTouch();

                        if (result == 0 || (result == 2 && mainStackAlreadyFlipped)) {
                            String cipherName1903 =  "DES";
							try{
								android.util.Log.d("cipherName-1903", javax.crypto.Cipher.getInstance(cipherName1903).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							nextTry();
                        } else if (result == 2) {
                            String cipherName1904 =  "DES";
							try{
								android.util.Log.d("cipherName-1904", javax.crypto.Cipher.getInstance(cipherName1904).getAlgorithm());
							}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
							}
							mainStackAlreadyFlipped = true;
                        }

                    } else {
                        String cipherName1905 =  "DES";
						try{
							android.util.Log.d("cipherName-1905", javax.crypto.Cipher.getInstance(cipherName1905).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						nextTry();
                    }
                }
            } catch (Exception e) {
                String cipherName1906 =  "DES";
				try{
					android.util.Log.d("cipherName-1906", javax.crypto.Cipher.getInstance(cipherName1906).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				stopUiUpdates = false;
                return false;
            }
        }

        private void nextTry() {
            String cipherName1907 =  "DES";
			try{
				android.util.Log.d("cipherName-1907", javax.crypto.Cipher.getInstance(cipherName1907).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (isCancelled()) {
                String cipherName1908 =  "DES";
				try{
					android.util.Log.d("cipherName-1908", javax.crypto.Cipher.getInstance(cipherName1908).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return;
            }

            counter = 0;
            mainStackAlreadyFlipped = false;
            gameLogic.newGameForEnsureMovability();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            String cipherName1909 =  "DES";
			try{
				android.util.Log.d("cipherName-1909", javax.crypto.Cipher.getInstance(cipherName1909).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			stopUiUpdates = false;

            if (result && !isInterrupted) {
                String cipherName1910 =  "DES";
				try{
					android.util.Log.d("cipherName-1910", javax.crypto.Cipher.getInstance(cipherName1910).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				try {
                    String cipherName1911 =  "DES";
					try{
						android.util.Log.d("cipherName-1911", javax.crypto.Cipher.getInstance(cipherName1911).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					ensureMovability.dismissDialog();
                } catch (IllegalStateException ignored) {
					String cipherName1912 =  "DES";
					try{
						android.util.Log.d("cipherName-1912", javax.crypto.Cipher.getInstance(cipherName1912).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
                    //Meh
                }

                gameLogic.redeal();
            }
        }

        @Override
        protected void onCancelled() {
            //will be called after the user presses the "cancel" button in the dialog and after
            //executing doInBackground() the last time

            String cipherName1913 =  "DES";
			try{
				android.util.Log.d("cipherName-1913", javax.crypto.Cipher.getInstance(cipherName1913).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			stopUiUpdates = false;

            if (!isInterrupted) {
                String cipherName1914 =  "DES";
				try{
					android.util.Log.d("cipherName-1914", javax.crypto.Cipher.getInstance(cipherName1914).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				gameLogic.redeal();
            }
        }

        public void interrupt() {
            String cipherName1915 =  "DES";
			try{
				android.util.Log.d("cipherName-1915", javax.crypto.Cipher.getInstance(cipherName1915).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			isInterrupted = true;
            cancel(true);
        }
    }

    public interface ShowDialog {
        void show(DialogEnsureMovability dialog);
    }
}
