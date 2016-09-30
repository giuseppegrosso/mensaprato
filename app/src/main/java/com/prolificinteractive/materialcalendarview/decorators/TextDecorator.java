package com.prolificinteractive.materialcalendarview.decorators;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

/**
 * Creato da Giuseppe Grosso
 */
public class TextDecorator implements DayViewDecorator{


    CharSequence symbol = "●";

    public TextDecorator(CharSequence symbol) {
        this.symbol = symbol;
    }


    public TextDecorator(){}

    public void decorate(DayView view, Context context) {

        CharSequence text = view.getText();
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#CE93D8"));
        SpannableString spannableString = new SpannableString(symbol + " " + text);
        spannableString.setSpan(colorSpan, 0, 1, 0); // change dot color
        view.setText(spannableString);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day)
    {
        return false;
    }

    @Override
    public void decorate(DayViewFacade view)
    {

    }
}