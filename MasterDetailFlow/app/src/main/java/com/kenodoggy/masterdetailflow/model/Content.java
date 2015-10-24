package com.kenodoggy.masterdetailflow.model;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 */
public class Content {

    public static final JSONArray puppiesJSON;
    public static final List<String> PUPPY_TITLES = new ArrayList<>();

    static {
        puppiesJSON = parseJSON();
        // create list of puppy titles
        int length = puppiesJSON.length();
        for (int i = 0; i < length; i++) {
            PUPPY_TITLES.add(i, puppiesJSON.optJSONObject(i).optString("title"));
        }
    }

    private static JSONArray parseJSON() {
        JSONArray puppiesArray = null;
        String json = null;
        InputStream is = null;

        try {
            ClassLoader loader  = Content.class.getClassLoader();
            is = loader.getResourceAsStream("puppies.json");

            if (is != null) {
                int size = is.available();
                byte[] buffer = new byte[size];
                if (buffer != null) {
                    is.read(buffer);
                    json = new String(buffer, "UTF-8");

                    if (!TextUtils.isEmpty(json)) {
                        puppiesArray = new JSONArray(json);
                    }
                }
            }

        } catch (IOException e) {
            Log.e("Error", "Failed to read puppies.json");
        } catch (JSONException e) {
            Log.e("JSON Error", "Failed to create JSONArray from puppies.json data");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }

        return puppiesArray;
    }

}
