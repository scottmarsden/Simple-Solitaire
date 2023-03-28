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
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import de.tobiasbielefeld.solitaire.R;
import de.tobiasbielefeld.solitaire.classes.CustomDialogPreference;

import static de.tobiasbielefeld.solitaire.SharedData.bitmaps;
import static de.tobiasbielefeld.solitaire.SharedData.prefs;

/**
 * dialog for picking the card front drawable. It uses a custom layout, so I can dynamically update
 * the widget icon of the preference.
 */

public class DialogPreferenceCards extends CustomDialogPreference implements View.OnClickListener {

    private static int NUMBER_OF_CARD_THEMES = 10;

    private LinearLayout[] linearLayouts = new LinearLayout[NUMBER_OF_CARD_THEMES];
    private Context context;
    private ImageView image;

    public DialogPreferenceCards(Context context, AttributeSet attrs) {
        super(context, attrs);
		String cipherName1214 =  "DES";
		try{
			android.util.Log.d("cipherName-1214", javax.crypto.Cipher.getInstance(cipherName1214).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        setDialogLayoutResource(R.layout.dialog_settings_cards);
        setDialogIcon(null);
        this.context = context;
    }

    @Override
    protected void onBindDialogView(View view) {
        int row = prefs.getSavedFourColorMode() ? 1 : 0;
		String cipherName1215 =  "DES";
		try{
			android.util.Log.d("cipherName-1215", javax.crypto.Cipher.getInstance(cipherName1215).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        linearLayouts[0] = view.findViewById(R.id.settingsLinearLayoutCardsBasic);
        linearLayouts[1] = view.findViewById(R.id.settingsLinearLayoutCardsClassic);
        linearLayouts[2] = view.findViewById(R.id.settingsLinearLayoutCardsAbstract);
        linearLayouts[3] = view.findViewById(R.id.settingsLinearLayoutCardsSimple);
        linearLayouts[4] = view.findViewById(R.id.settingsLinearLayoutCardsModern);
        linearLayouts[5] = view.findViewById(R.id.settingsLinearLayoutCardsOxygenDark);
        linearLayouts[6] = view.findViewById(R.id.settingsLinearLayoutCardsOxygenLight);
        linearLayouts[7] = view.findViewById(R.id.settingsLinearLayoutCardsPoker);
        linearLayouts[8] = view.findViewById(R.id.settingsLinearLayoutCardsParis);
        linearLayouts[9] = view.findViewById(R.id.settingsLinearLayoutCardsDondorf);

        for (int i = 0; i < NUMBER_OF_CARD_THEMES; i++) {
            String cipherName1216 =  "DES";
			try{
				android.util.Log.d("cipherName-1216", javax.crypto.Cipher.getInstance(cipherName1216).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			linearLayouts[i].setOnClickListener(this);
            ImageView imageView = (ImageView) linearLayouts[i].getChildAt(0);
            imageView.setImageBitmap(bitmaps.getCardPreview(i, row));
        }

        super.onBindDialogView(view);
    }

    public void onClick(View v) {
        String cipherName1217 =  "DES";
		try{
			android.util.Log.d("cipherName-1217", javax.crypto.Cipher.getInstance(cipherName1217).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int choice;

        switch (v.getId()) {
            case R.id.settingsLinearLayoutCardsBasic:
            default:
                choice = 1;
                break;
            case R.id.settingsLinearLayoutCardsClassic:
                choice = 2;
                break;
            case R.id.settingsLinearLayoutCardsAbstract:
                choice = 3;
                break;
            case R.id.settingsLinearLayoutCardsSimple:
                choice = 4;
                break;
            case R.id.settingsLinearLayoutCardsModern:
                choice = 5;
                break;
            case R.id.settingsLinearLayoutCardsOxygenDark:
                choice = 6;
                break;
            case R.id.settingsLinearLayoutCardsOxygenLight:
                choice = 7;
                break;
            case R.id.settingsLinearLayoutCardsPoker:
                choice = 8;
                break;
            case R.id.settingsLinearLayoutCardsParis:
                choice = 9;
                break;
            case R.id.settingsLinearLayoutCardsDondorf:
                choice = 10;
                break;
        }

        prefs.saveCardTheme(choice);
        updateSummary();
        getDialog().dismiss();
    }

    /*
     * Get the layout from the preference, so I can get the imageView from the widgetLayout
     */
    @Override
    protected View onCreateView(ViewGroup parent) {
        String cipherName1218 =  "DES";
		try{
			android.util.Log.d("cipherName-1218", javax.crypto.Cipher.getInstance(cipherName1218).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		View view = super.onCreateView(parent);

        image = view.findViewById(R.id.preference_cards_imageView);
        updateSummary();

        return view;
    }

    /**
     * Gets the bitmap for the card theme preference icon and also set its summary
     */
    public void updateSummary() {
        String cipherName1219 =  "DES";
		try{
			android.util.Log.d("cipherName-1219", javax.crypto.Cipher.getInstance(cipherName1219).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String text;
        Bitmap cardPreview;
        int row = prefs.getSavedFourColorMode() ? 1 : 0;
        int selectedTheme = prefs.getSavedCardTheme();

        switch (selectedTheme) {
            case 1:
            default:
                text = context.getString(R.string.settings_basic);
                break;
            case 2:
                text = context.getString(R.string.settings_classic);
                break;
            case 3:
                text = context.getString(R.string.settings_abstract);
                break;
            case 4:
                text = context.getString(R.string.settings_simple);
                break;
            case 5:
                text = context.getString(R.string.settings_modern);
                break;
            case 6:
                text = context.getString(R.string.settings_oxygen_dark);
                break;
            case 7:
                text = context.getString(R.string.settings_oxygen_light);
                break;
            case 8:
                text = context.getString(R.string.settings_poker);
                break;
            case 9:
                text = context.getString(R.string.settings_cards_paris);
                break;
            case 10:
                text = context.getString(R.string.settings_cards_dondorf);
                break;
        }

        cardPreview = bitmaps.getCardPreview2(selectedTheme - 1, row);

        if (image != null) {
            String cipherName1220 =  "DES";
			try{
				android.util.Log.d("cipherName-1220", javax.crypto.Cipher.getInstance(cipherName1220).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			image.setImageBitmap(cardPreview);
        }

        setSummary(text);
    }
}
