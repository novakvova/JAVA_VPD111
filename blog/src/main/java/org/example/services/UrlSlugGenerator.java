package org.example.services;

import java.util.Map;
import java.util.HashMap;

public class UrlSlugGenerator {
    public static String generateUrlSlug(String input) {

        // Remove leading and trailing whitespaces
        String slug = input.trim().toLowerCase();
        slug = translate(slug);
        // Replace spaces with hyphens
        slug = slug.replaceAll("\\s+", "-");
        // Remove special characters
        slug = slug.replaceAll("[^a-zA-Z0-9-]", "");
        return slug;
    }
    private static String translate(String ukrainianText) {

        Map<Character, String> TRANSLITERATION_MAP = new HashMap<>();

        TRANSLITERATION_MAP.put('а', "a");
        TRANSLITERATION_MAP.put('б', "b");
        TRANSLITERATION_MAP.put('в', "v");
        TRANSLITERATION_MAP.put('г', "h");
        TRANSLITERATION_MAP.put('ґ', "g");
        TRANSLITERATION_MAP.put('д', "d");
        TRANSLITERATION_MAP.put('е', "e");
        TRANSLITERATION_MAP.put('є', "ie");
        TRANSLITERATION_MAP.put('ж', "zh");
        TRANSLITERATION_MAP.put('з', "z");
        TRANSLITERATION_MAP.put('и', "y");
        TRANSLITERATION_MAP.put('і', "i");
        TRANSLITERATION_MAP.put('ї', "i");
        TRANSLITERATION_MAP.put('й', "i");
        TRANSLITERATION_MAP.put('к', "k");
        TRANSLITERATION_MAP.put('л', "l");
        TRANSLITERATION_MAP.put('м', "m");
        TRANSLITERATION_MAP.put('н', "n");
        TRANSLITERATION_MAP.put('о', "o");
        TRANSLITERATION_MAP.put('п', "p");
        TRANSLITERATION_MAP.put('р', "r");
        TRANSLITERATION_MAP.put('с', "s");
        TRANSLITERATION_MAP.put('т', "t");
        TRANSLITERATION_MAP.put('у', "u");
        TRANSLITERATION_MAP.put('ф', "f");
        TRANSLITERATION_MAP.put('х', "kh");
        TRANSLITERATION_MAP.put('ц', "ts");
        TRANSLITERATION_MAP.put('ч', "ch");
        TRANSLITERATION_MAP.put('ш', "sh");
        TRANSLITERATION_MAP.put('щ', "shch");
        TRANSLITERATION_MAP.put('ю', "yu");
        TRANSLITERATION_MAP.put('я', "ia");

        StringBuilder result = new StringBuilder();

        for (char c : ukrainianText.toCharArray()) {
            // Check if the character is in the transliteration map
            if (TRANSLITERATION_MAP.containsKey(Character.toLowerCase(c))) {
                // Append the corresponding Latin characters
                result.append(TRANSLITERATION_MAP.get(Character.toLowerCase(c)));
            } else {
                // Keep non-transliterated characters unchanged
                result.append(c);
            }
        }

        return result.toString();
    }
}
