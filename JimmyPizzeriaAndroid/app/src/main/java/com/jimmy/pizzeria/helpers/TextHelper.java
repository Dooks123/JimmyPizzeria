package com.jimmy.pizzeria.helpers;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.widget.EditText;

import androidx.annotation.StringRes;
import androidx.core.text.HtmlCompat;

import com.jimmy.pizzeria.MainApplication;

import java.util.Arrays;
import java.util.List;

public class TextHelper {
    public static String checkNull(String value, String defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value.trim();
    }

    public static String checkNullEmpty(String... values) {
        if (values == null) return "";

        for (String value : values) {
            if (!isNullOrEmpty(value)) {
                return value;
            }
        }
        return "";
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static Spanned fromHtml(@StringRes int resourceID) {
        CharSequence value = MainApplication.getContext().getText(resourceID);
        return new SpannedString(value);
    }

    public static Spanned fromHtml(String value) {
        return HtmlCompat.fromHtml(value, HtmlCompat.FROM_HTML_MODE_LEGACY);
    }

    public static String getEditTextString(EditText editText) {
        if (editText == null || editText.getText() == null || editText.getText().length() == 0)
            return "";

        return editText.getText().toString();
    }

    public static SpannableString getEditTextSpannable(EditText editText) {
        if (editText == null || editText.getText() == null || editText.getText().length() == 0)
            return new SpannableString("");

        return (SpannableString) editText.getText();
    }

    public static String stripTooManyNewLines(String value) {
        return isNullOrEmpty(value) ? "" : value.trim().replaceAll("[\r\n][\n]{2,}", "\n\n");
    }

    public static String padRight(String input, int length, String fill) {
        String pad = input.trim() + String.format("%" + length + "s", "").replace(" ", fill);
        return pad.substring(0, length);
    }

    public static String padLeft(String input, int length, String fill) {
        String pad = String.format("%" + length + "s", "").replace(" ", fill) + input.trim();
        return pad.substring(pad.length() - length, pad.length());
    }

    public static String join(String delimiter, int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static String join(String delimiter, String[] array) {
        return join(delimiter, Arrays.asList(array));
    }

    public static String join(String delimiter, List<String> array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            String item = array.get(i);
            if (!isNullOrEmpty(item)) {
                if (i > 0) {
                    sb.append(delimiter);
                }
                sb.append(item);
            }
        }
        return sb.toString();
    }

    public static String join(String delimiter, String[] array, int startIndex, int count) {
        return join(delimiter, Arrays.asList(array), startIndex, count);
    }

    public static String join(String delimiter, List<String> array, int startIndex, int count) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < array.size(); i++) {
            if (i >= startIndex && counter <= count) {
                String item = array.get(i);
                if (!isNullOrEmpty(item)) {
                    if (i > 0) {
                        sb.append(delimiter);
                    }
                    sb.append(item);
                }
                counter++;
            }
        }
        return sb.toString();
    }

    public static String concat(String delimiter, String... values) {
        if (values == null) return "";

        StringBuilder concat = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            if (!isNullOrEmpty(value)) {
                concat.append(i > 0 ? delimiter : "");
                concat.append(value);
            }
        }
        return concat.toString();
    }

    public static String takeLast(String value, int count) {
        if (isNullOrEmpty(value)) return "";
        if (count < 1) return value;

        if (value.length() > count) {
            return value.substring(value.length() - count);
        } else {
            return value;
        }
    }

    public static int tryParse(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            ex.printStackTrace();
            return defaultValue;
        }
    }

    public static long tryParse(String value, long defaultValue) {
        try {
            return Long.parseLong(value);
        } catch (Exception ex) {
            ex.printStackTrace();
            return defaultValue;
        }
    }

    public static int[] toIntArray(Integer... i) {
        if (i == null || i.length == 0) return new int[0];

        int[] arr = new int[i.length];
        for (int o = 0; o < i.length; o++) arr[o] = i[o];
        return arr;
    }

    public static Integer[] toIntegerArray(int... i) {
        if (i == null || i.length == 0) return new Integer[0];

        Integer[] arr = new Integer[i.length];
        for (int o = 0; o < i.length; o++) arr[o] = i[o];
        return arr;
    }
}
