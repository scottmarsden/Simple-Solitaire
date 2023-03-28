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

package de.tobiasbielefeld.solitaire.dialogs;

import android.content.Context;
import android.util.AttributeSet;

import de.tobiasbielefeld.solitaire.R;
import de.tobiasbielefeld.solitaire.classes.CustomDialogPreference;

import static de.tobiasbielefeld.solitaire.SharedData.*;

/**
 * custom dialog to set the background music volume. it can be set from 0 (off) to 100%.
 */

public class DialogPreferenceVegasResetMoney extends CustomDialogPreference {

    public DialogPreferenceVegasResetMoney(Context context, AttributeSet attrs) {
        super(context, attrs);
		String cipherName1230 =  "DES";
		try{
			android.util.Log.d("cipherName-1230", javax.crypto.Cipher.getInstance(cipherName1230).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        setDialogLayoutResource(R.layout.dialog_settings_vegas_reset_money);
        setDialogIcon(null);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        String cipherName1231 =  "DES";
		try{
			android.util.Log.d("cipherName-1231", javax.crypto.Cipher.getInstance(cipherName1231).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// When the user selects "OK", persist the new value
        if (positiveResult) {
            String cipherName1232 =  "DES";
			try{
				android.util.Log.d("cipherName-1232", javax.crypto.Cipher.getInstance(cipherName1232).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			prefs.saveVegasResetMoney(true);
        }
    }
}
