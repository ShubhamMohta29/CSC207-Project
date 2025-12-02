package classes.retrieveInfo;

public final class animalConverter {

    private static final String PARENS_REGEX = "[()]";

    /**
     * Parses a lifespan string and returns the average lifespan in years.
     *
     * @param age the lifespan string (e.g., "2-4 years", "up to 5 yrs")
     * @return the average lifespan in years, or 0 if unable to parse
     */
    public double parseAverageLifespanYears(final String age) {
        double result = 0.0;

        if (age != null && !age.trim().isEmpty()) {
            final String normalized = normalizeAgeString(age);

            if (normalized.startsWith("up to ")) {
                result = parseUpToValue(normalized);
            }
            else {
                result = parseRangeOrSingle(normalized);
            }
        }

        return result;
    }

    private String normalizeAgeString(final String age) {
        String in = age.toLowerCase().replaceAll(PARENS_REGEX, "").trim();
        in = in.replaceAll("years?", "year").replaceAll("yrs?", "year");
        return in;
    }

    private double parseUpToValue(final String input) {
        final String valuePart = input.substring(animalConstants.UP_TO_PREFIX_LENGTH).trim();
        return valueWithUnitToYears(valuePart);
    }

    private double parseRangeOrSingle(final String input) {
        final double result;

        final String[] parts = splitRange(input);
        if (parts.length == 2) {
            result = averageTwoValues(parts[0], parts[1]);
        }
        else {
            result = valueWithUnitToYears(input);
        }

        return result;
    }

    private double averageTwoValues(final String part1, final String part2) {
        double low = valueWithUnitToYears(part1);
        double high = valueWithUnitToYears(part2);

        if (low == 0) {
            low = high;
        }
        if (high == 0) {
            high = low;
        }
        return (low + high) / 2.0;
    }

    /**
     * Parses a weight string and returns the average weight in kilograms.
     * Supports ranges (e.g., "10-20 kg"), "up to" values, and various units.
     *
     * @param weight the weight string
     * @return the average weight in kilograms, or 0 if input is null/empty
     */
    public double parseAverageWeightKg(String weight) {
        double result = 0;

        if (weight != null && !weight.trim().isEmpty()) {
            final String in = weight.toLowerCase().replaceAll(PARENS_REGEX, "").trim();
            final String[] parts = splitRange(in);

            if (parts.length == 2) {
                final double low = valueWithUnitToKg(parts[0]);
                final double high = valueWithUnitToKg(parts[1]);
                result = (low + high) / 2.0;
            }
            else {
                result = valueWithUnitToKg(in);
            }
        }

        return result;
    }

    /**
     * Parses a height string and returns the average height in centimeters.
     * Supports ranges (e.g., "150-170 cm") and single values.
     *
     * @param height the height string
     * @return the average height in centimeters, or 0 if input is null/empty
     */
    public double parseAverageHeightCm(String height) {
        double result = 0;

        if (height != null && !height.trim().isEmpty()) {
            final String in = height.toLowerCase().replaceAll(PARENS_REGEX, "").trim();
            final String[] parts = splitRange(in);

            if (parts.length == 2) {
                result = (toCm(parts[0]) + toCm(parts[1])) / 2.0;
            }
            else {
                result = toCm(in);
            }
        }

        return result;
    }

    // helpers
    private String[] splitRange(String string) {
        String[] result = {string};

        if (string.contains("-")) {
            result = string.split("-");
        }
        else if (string.contains("to")) {
            result = string.split("to");
        }

        return result;
    }

    private double valueWithUnitToYears(String token) {
        double result = extractNumber(token.trim());

        if (token.contains("month")) {
            result /= animalConstants.MONTHS_IN_YEAR;
        }
        else if (token.contains("week")) {
            result /= animalConstants.WEEKS_IN_YEAR;
        }
        else if (token.contains("day")) {
            result /= animalConstants.DAYS_IN_YEAR;
        }

        return result;
    }

    private double valueWithUnitToKg(String token) {
        double result = extractNumber(token.trim());

        if (token.contains("lb") || token.contains("lbs") || token.contains("pound")) {
            result *= animalConstants.LB_TO_KG;
        }
        else if (token.contains("g") && !token.contains("kg")) {
            result /= animalConstants.GRAMS_IN_KG;
        }

        return result;
    }

    private double toCm(String token) {
        double result = extractNumber(token.trim());

        try {
            if (token.contains("mm")) {
                result /= animalConstants.MM_TO_CM;
            }
            else if (token.contains("m")) {
                result *= animalConstants.M_TO_CM;
            }
            else if (token.contains("ft") || token.contains("'")) {
                final String[] parts = token.replace("\"", "").replace("'", " ").split("\\s+");
                final double feet = Double.parseDouble(parts[0]);
                double inches = 0;
                if (parts.length > 1) {
                    inches = Double.parseDouble(parts[1]);
                }
                result = feet * animalConstants.FEET_TO_CM + inches * animalConstants.INCH_TO_CM;
            }
            else if (token.contains("in") || token.contains("\"")) {
                result *= animalConstants.INCH_TO_CM;
            }
        }
        catch (NumberFormatException error) {
            //
        }

        return result;
    }

    private double extractNumber(String string) {
        double result = 0;
        final StringBuilder b = new StringBuilder();
        boolean seen = false;

        for (char c : string.toCharArray()) {
            if (c >= '0' && c <= '9' || c == '.') {
                b.append(c);
                seen = true;
            }
            else if (seen) {
                break;
            }
        }

        if (!b.isEmpty()) {
            try {
                result = Double.parseDouble(b.toString());
            }
            catch (NumberFormatException ex) {
                result = 0;
            }
        }

        return result;
    }

}
