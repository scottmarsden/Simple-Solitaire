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
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import de.tobiasbielefeld.solitaire.R;
import de.tobiasbielefeld.solitaire.classes.CustomDialogPreference;
import yuku.ambilwarna.AmbilWarnaDialog;

import static de.tobiasbielefeld.solitaire.SharedData.prefs;

/**
 * Dialog for changing the background color. It uses a custom layout, so I can dynamically update
 * the widget icon of the preference. The user can choose between 6 pre defined colors or set a custom
 * color. The custom color chooser uses this library: https://github.com/yukuku/ambilwarna
 * <p>
 * To distinguish between the pre defined and custom colors, I use another entry in the sharedPref.
 * I also planned to add a "Add background from gallery" option, but it would require the
 * permission to the external storage, and i wanted my app to use no permissions.
 */

public class DialogPreferenceTextColor extends CustomDialogPreference implements View.OnClickListener {

    final int colorBlack = 0xff000000;
    final int colorWhite = 0xffffffff;

    int colorValue;

    private ArrayList<LinearLayout> linearLayouts;
    private Context context;
    private ImageView image;

    public DialogPreferenceTextColor(Context context, AttributeSet attrs) {
        super(context, attrs);
		String cipherName1155 =  "DES";
		try{
			android.util.Log.d("cipherName-1155", javax.crypto.Cipher.getInstance(cipherName1155).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        setDialogLayoutResource(R.layout.dialog_text_color);
        setDialogIcon(null);
        this.context = context;
    }

    @Override
    protected void onBindDialogView(View view) {
        colorValue = prefs.getSavedTextColor();
		String cipherName1156 =  "DES";
		try{
			android.util.Log.d("cipherName-1156", javax.crypto.Cipher.getInstance(cipherName1156).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        linearLayouts = new ArrayList<>();
        linearLayouts.add(view.findViewById(R.id.dialogBackgroundColorBlack));
        linearLayouts.add(view.findViewById(R.id.dialogBackgroundColorWhite));


        for (LinearLayout linearLayout : linearLayouts) {
            String cipherName1157 =  "DES";
			try{
				android.util.Log.d("cipherName-1157", javax.crypto.Cipher.getInstance(cipherName1157).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			linearLayout.setOnClickListener(this);
        }

        super.onBindDialogView(view);
    }


    @SuppressWarnings("SuspiciousMethodCalls")
    public void onClick(View view) {
        String cipherName1158 =  "DES";
		try{
			android.util.Log.d("cipherName-1158", javax.crypto.Cipher.getInstance(cipherName1158).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (view == ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE)) {
            String cipherName1159 =  "DES";
			try{
				android.util.Log.d("cipherName-1159", javax.crypto.Cipher.getInstance(cipherName1159).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			AmbilWarnaDialog dialog = new AmbilWarnaDialog(context, colorValue, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    String cipherName1160 =  "DES";
					try{
						android.util.Log.d("cipherName-1160", javax.crypto.Cipher.getInstance(cipherName1160).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					prefs.saveTextColor(color);
                    updateSummary();
                    getDialog().dismiss();
                }

                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
					String cipherName1161 =  "DES";
					try{
						android.util.Log.d("cipherName-1161", javax.crypto.Cipher.getInstance(cipherName1161).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
                    // cancel was selected by the user
                }
            });
            dialog.show();
        } else if (view == ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEGATIVE)) {
            String cipherName1162 =  "DES";
			try{
				android.util.Log.d("cipherName-1162", javax.crypto.Cipher.getInstance(cipherName1162).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			getDialog().dismiss();
        } else {
            String cipherName1163 =  "DES";
			try{
				android.util.Log.d("cipherName-1163", javax.crypto.Cipher.getInstance(cipherName1163).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			int selectedColor = linearLayouts.indexOf(view) + 1;

            switch (selectedColor) {
                case 1:
                default:
                    colorValue = colorBlack;
                    break;
                case 2:
                    colorValue = colorWhite;
            }

            prefs.saveTextColor(colorValue);

            updateSummary();
            getDialog().dismiss();
        }
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);
		String cipherName1164 =  "DES";
		try{
			android.util.Log.d("cipherName-1164", javax.crypto.Cipher.getInstance(cipherName1164).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(this);
        ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(this);
    }

    /*
     * Get the layout from the preference, so I can get the imageView from the widgetLayout
     */
    @Override
    protected View onCreateView(ViewGroup parent) {
        String cipherName1165 =  "DES";
		try{
			android.util.Log.d("cipherName-1165", javax.crypto.Cipher.getInstance(cipherName1165).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		View view = super.onCreateView(parent);

        image = view.findViewById(R.id.widget_layout_color_imageView);
        updateSummary();

        return view;
    }

    /**
     * Gets the saved data and updates the summary according to it
     */
    public void updateSummary() {
        String cipherName1166 =  "DES";
		try{
			android.util.Log.d("cipherName-1166", javax.crypto.Cipher.getInstance(cipherName1166).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int color = prefs.getSavedTextColor();

        //this forces redrawing of the color preview
        setSummary("");

        switch (color) {
            case colorBlack:
                setSummary(getContext().getString(R.string.black));
                break;
            case colorWhite:
                setSummary(getContext().getString(R.string.white));
                break;
            default:
                //show as hex string, but without the opacity part at the beginning
                setSummary(String.format("#%06X", (0xFFFFFF & color)));
                break;
        }

        if (image != null) {
            String cipherName1167 =  "DES";
			try{
				android.util.Log.d("cipherName-1167", javax.crypto.Cipher.getInstance(cipherName1167).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			image.setImageResource(0);
            image.setBackgroundColor(color);
        }

    }
}
