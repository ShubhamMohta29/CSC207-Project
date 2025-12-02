package Classes.retrieveInfo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public final class AnimalFactory {
    private final AnimalConverter converter = new AnimalConverter();

    /**
     * Converts a JSON array string representing animals into an {@link Animal} object.
     * Only the first element of the array is used.
     *
     * @param jsonArrayString the JSON array string containing animal data
     * @return an {@link Animal} object populated from the JSON
     * @throws IllegalArgumentException if the input string is null, empty, or contains an empty array
     */
    public Animal fromJsonArrayString(String jsonArrayString) {
        if (jsonArrayString == null || jsonArrayString.trim().isEmpty() || "[]".equals(jsonArrayString)) {
            throw new IllegalArgumentException("Empty JSON for Animal");
        }

        final String trimmed = jsonArrayString.trim();
        final JSONArray arr;

        // Wrap single object in array if needed
        if (trimmed.startsWith("{")) {
            arr = new JSONArray();
            arr.put(new JSONObject(trimmed));
        }
        else {
            arr = new JSONArray(trimmed);
        }

        if (arr.isEmpty()) {
            throw new IllegalArgumentException("Empty JSON array");
        }

        final JSONObject a = arr.getJSONObject(0);
        final JSONObject characteristics = a.optJSONObject("characteristics");
        final JSONObject taxonomy = a.optJSONObject("taxonomy");

        final Map<String, String> taxMap = parseTaxonomy(taxonomy);
        final String[] locations = parseLocations(a);
        final String[] prey = parsePrey(characteristics);

        final double lifespan = parseLifespan(characteristics);
        final double weight = parseWeight(characteristics);
        final double height = parseHeight(characteristics);

        return new Animal(
                a.optString("name"),
                taxMap,
                parseString(characteristics, "habitat"),
                locations,
                prey,
                parseString(characteristics, "most_distinctive_feature"),
                lifespan,
                parseString(characteristics, "diet"),
                parseString(characteristics, "lifestyle"),
                weight,
                height,
                parseString(characteristics, "group"),
                parseString(characteristics, "type")
        );
    }

    private Map<String, String> parseTaxonomy(final JSONObject taxonomy) {
        final Map<String, String> taxMap = new HashMap<>();
        if (taxonomy != null) {
            for (String key : taxonomy.keySet()) {
                taxMap.put(key, taxonomy.optString(key));
            }
        }
        return taxMap;
    }

    private String[] parseLocations(final JSONObject obj) {
        return toStringArray(obj.optJSONArray("locations"));
    }

    private String[] parsePrey(final JSONObject characteristics) {
        final String[] result;
        if (characteristics == null) {
            result = new String[0];
        }
        else {
            final String preyStr = characteristics.optString("prey", "");
            if (preyStr.isEmpty()) {
                result = new String[0];
            }
            else {
                result = preyStr.split(",\\s*");
            }
        }
        return result;
    }

    private double parseLifespan(final JSONObject characteristics) {
        final double result;
        if (characteristics == null) {
            result = 0;
        }
        else {
            result = converter.parseAverageLifespanYears(characteristics.optString("lifespan", ""));
        }
        return result;
    }

    private double parseWeight(final JSONObject characteristics) {
        final double result;
        if (characteristics == null) {
            result = 0;
        }
        else {
            result = converter.parseAverageWeightKg(characteristics.optString("weight", ""));
        }
        return result;
    }

    private double parseHeight(final JSONObject characteristics) {
        final double result;
        if (characteristics == null) {
            result = 0;
        }
        else {
            final String heightStr = characteristics.optString("height",
                    characteristics.optString("length", ""));
            result = converter.parseAverageHeightCm(heightStr);
        }
        return result;
    }

    private String parseString(final JSONObject characteristics, final String key) {
        final String result;
        if (characteristics == null) {
            result = "";
        }
        else {
            result = characteristics.optString(key, "");
        }
        return result;
    }

    private String[] toStringArray(JSONArray arr) {
        final String[] out;
        if (arr == null) {
            out = new String[0];
        }
        else {
            out = new String[arr.length()];
            for (int i = 0; i < arr.length(); i++) {
                out[i] = arr.optString(i);
            }
        }
        return out;
    }

}
