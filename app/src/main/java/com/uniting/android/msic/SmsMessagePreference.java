package com.uniting.android.msic;

import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Chris.Oelerich on 11/25/2017.
 */

public class SmsMessagePreference extends EditTextPreference {
    private static final String REMAINING_FORMAT = "%1$d/%2$d";
    private static final String TAG = "SmsMessagePreference";

    /**
     * for now we're only instantiating this programmatically, but to do it proper through XML would require this method
     * https://developer.android.com/training/custom-views/create-view.html
     *
     * @param context Context
     * @param attrs   AttributeSet
     */
    public SmsMessagePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    int defaultTextColor;

    static SmsMessagePreference newInstance(Context context, String title, String defaultValue, String key) {

        SmsMessagePreference ed = new SmsMessagePreference(context, null);

        ed.setDefaultValue(defaultValue);
        ed.setKey(key);
        ed.setTitle(title);

        return ed;
    }

    private int dpTpPx(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, getContext().getResources().getDisplayMetrics());
    }

    @Override
    public void showDialog(Bundle state) {
        super.showDialog(state);

        int maxChars = Prefs.isLoc(getContext()) ? 120 : 160;

        TextView textCounter = new TextView(getContext());
        int curLen= Prefs.getMsg(getContext()).length();

        textCounter.setText(String.format(Locale.getDefault(), REMAINING_FORMAT, curLen, maxChars));
        defaultTextColor = textCounter.getCurrentTextColor();
        if (curLen == 0) {
            textCounter.setTextColor(getContext().getResources().getColor(R.color.ms_errorColor));
        }


        final LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.BOTTOM;
        params.setMargins(params.leftMargin + dpTpPx(8), params.topMargin, params.rightMargin, params.bottomMargin + dpTpPx(6));
        getDialog().addContentView(textCounter, params);


        EditText editText = getEditText();
//        editText.setInputType(EditorInfo.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        editText.setMaxLines(6);
        editText.setSelectAllOnFocus(true);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxChars)});

//        ViewGroup.LayoutParams layoutParams = editText.getLayoutParams();
//        layoutParams.height += dpTpPx(100);
//        editText.setLayoutParams(layoutParams);

        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textCounter.setText(String.format(
                        Locale.getDefault(),
                        REMAINING_FORMAT,
                        s.length(),
                        maxChars
                ));

                if (s.length() == 0) {
                    textCounter.setTextColor(getContext().getResources().getColor(R.color.ms_errorColor));
                } else {
                    textCounter.setTextColor(defaultTextColor);
                }
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }
}