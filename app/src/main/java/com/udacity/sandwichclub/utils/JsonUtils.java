package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Parses a given json string into Sandwich object
     */
    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject obj = new JSONObject(json);
            JSONObject nameObj = obj.getJSONObject("name");

            sandwich.setMainName(nameObj.getString("mainName"));
            sandwich.setAlsoKnownAs(getStringListByName("alsoKnownAs", nameObj));
            sandwich.setPlaceOfOrigin(obj.getString("placeOfOrigin"));
            sandwich.setDescription(obj.getString("description"));
            sandwich.setImage(obj.getString("image"));
            sandwich.setIngredients(getStringListByName("ingredients", obj));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    private static List<String> getStringListByName(String name, JSONObject obj) throws JSONException {
        JSONArray array = obj.getJSONArray(name);
        List<String> list = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            list.add(array.getString(i));
        }
        return list;
    }
}
