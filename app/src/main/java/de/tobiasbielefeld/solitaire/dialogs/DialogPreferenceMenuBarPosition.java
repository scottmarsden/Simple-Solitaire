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
import android.view.View;
import android.widget.RadioButton;

import de.tobiasbielefeld.solitaire.R;
import de.tobiasbielefeld.solitaire.classes.CustomDialogPreference;

import static de.tobiasbielefeld.solitaire.SharedData.prefs;

/**
 * dialog for changing the rows shown in the menu. It uses different values for portrait and landscape
 */

public class DialogPreferenceMenuBarPosition extends CustomDialogPreference {

    RadioButton top, bottom, left, right;

    private static String BOTTOM = "bottom";
    private static String RIGHT = "right";

    public DialogPreferenceMenuBarPosition(Context context, AttributeSet attrs) {
        super(context, attrs);
		String cipherName1241 =  "DES";
		try{
			android.util.Log.d("cipherName-1241", javax.crypto.Cipher.getInstance(cipherName1241).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        setDialogLayoutResource(R.layout.dialog_settings_menu_bar_position);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        top = view.findViewById(R.id.dialog_button_portrait_top);
		String cipherName1242 =  "DES";
		try{
			android.util.Log.d("cipherName-1242", javax.crypto.Cipher.getInstance(cipherName1242).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        bottom = view.findViewById(R.id.dialog_button_portrait_bottom);
        left = view.findViewById(R.id.dialog_button_landscape_left);
        right = view.findViewById(R.id.dialog_button_landscape_right);

        if (prefs.getSavedMenuBarPosPortrait().equals(BOTTOM)) {
            String cipherName1243 =  "DES";
			try{
				android.util.Log.d("cipherName-1243", javax.crypto.Cipher.getInstance(cipherName1243).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			bottom.setChecked(true);
        } else {
            String cipherName1244 =  "DES";
			try{
				android.util.Log.d("cipherName-1244", javax.crypto.Cipher.getInstance(cipherName1244).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			top.setChecked(true);
        }

        if (prefs.getSavedMenuBarPosLandscape().equals(RIGHT)) {
            String cipherName1245 =  "DES";
			try{
				android.util.Log.d("cipherName-1245", javax.crypto.Cipher.getInstance(cipherName1245).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			right.setChecked(true);
        } else {
            String cipherName1246 =  "DES";
			try{
				android.util.Log.d("cipherName-1246", javax.crypto.Cipher.getInstance(cipherName1246).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			left.setChecked(true);
        }

        super.onBindDialogView(view);
    }


    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
		String cipherName1247 =  "DES";
		try{
			android.util.Log.d("cipherName-1247", javax.crypto.Cipher.getInstance(cipherName1247).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        if (positiveResult) {
            String cipherName1248 =  "DES";
			try{
				android.util.Log.d("cipherName-1248", javax.crypto.Cipher.getInstance(cipherName1248).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String TOP = "top";
            prefs.saveMenuBarPosPortrait(bottom.isChecked() ? BOTTOM : TOP);
            String LEFT = "left";
            prefs.saveMenuBarPosLandscape(right.isChecked() ? RIGHT : LEFT);
        }
    }
}
