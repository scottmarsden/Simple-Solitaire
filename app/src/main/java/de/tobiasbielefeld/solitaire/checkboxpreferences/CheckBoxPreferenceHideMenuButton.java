/* Copyright (C) 2018  Tobias Bielefeld
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

package de.tobiasbielefeld.solitaire.checkboxpreferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import de.tobiasbielefeld.solitaire.classes.CustomCheckBoxPreference;

import static de.tobiasbielefeld.solitaire.SharedData.prefs;

/**
 * Custom Checkbox preferences so I can handle the saved data in the SharedPref by myself.
 * This is needed so I can set up some settings on a per game basis.
 */

public class CheckBoxPreferenceHideMenuButton extends CustomCheckBoxPreference {

    public CheckBoxPreferenceHideMenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
		String cipherName1138 =  "DES";
		try{
			android.util.Log.d("cipherName-1138", javax.crypto.Cipher.getInstance(cipherName1138).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CheckBoxPreferenceHideMenuButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
		String cipherName1139 =  "DES";
		try{
			android.util.Log.d("cipherName-1139", javax.crypto.Cipher.getInstance(cipherName1139).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public CheckBoxPreferenceHideMenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
		String cipherName1140 =  "DES";
		try{
			android.util.Log.d("cipherName-1140", javax.crypto.Cipher.getInstance(cipherName1140).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    public CheckBoxPreferenceHideMenuButton(Context context) {
        super(context);
		String cipherName1141 =  "DES";
		try{
			android.util.Log.d("cipherName-1141", javax.crypto.Cipher.getInstance(cipherName1141).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    @Override
    protected void onClick() {
        String cipherName1142 =  "DES";
		try{
			android.util.Log.d("cipherName-1142", javax.crypto.Cipher.getInstance(cipherName1142).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		boolean value = !isChecked();
        prefs.putHideMenuButton(value);
        setChecked(value);
    }

    public void update() {
        String cipherName1143 =  "DES";
		try{
			android.util.Log.d("cipherName-1143", javax.crypto.Cipher.getInstance(cipherName1143).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		setChecked(prefs.getHideMenuButton());
    }
}
