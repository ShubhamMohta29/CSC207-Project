package Classes;

/**
 * Utility class for converting animal measurement strings to numeric values.
 * Handles various units and formats for lifespan, height, and weight.
 */
public final class AnimalConverter {
    private static final int MONTHS_PER_YEAR = 12;
    private static final int WEEKS_PER_YEAR = 52;
    private static final int DAYS_PER_YEAR = 365;
    private static final int CM_PER_M = 100;
    private static final int MM_PER_CM = 10;
    private static final double FT_TO_CM = 30.48;
    private static final double IN_TO_CM = 2.54;
    private static final int GRAMS_PER_KG = 1000;
    private static final int MILLIGRAMS_PER_KG = 1_000_000;
    private static final double LB_TO_KG = 0.453592;
    private static final double OZ_TO_KG = 0.0283495;
    private static final int LARGE_G_THRESHOLD = 1000;
    private static final int UP_TO_PREFIX_LENGTH = 6;
    private static final int ARRAY_SIZE_TWO = 2;

    // Reused literal tokens as constants to reduce duplicate-literal warnings
    private static final String REGEX_PARENS = "[()]";
    private static final String CM = "cm";
    private static final String M = "m";
    private static final String MM = "mm";
    private static final String FT = "ft";
    private static final String IN = "in";
    private static final String KG = "kg";
    private static final String G = "g";
    private static final String MG = "mg";
    private static final String LB = "lb";
    private static final String OZ = "oz";
    private static final String TO_WITH_SPACES = " to ";
    private static final String DASH_SPACED = " - ";
    private static final String EN_DASH = "\u2013";
    private static final String HYPHEN = "-";
    private static final String SPACE = " ";

    AnimalConverter() {
        // utility class - prevent instantiation
    }

    /**
     * Parses a lifespan string and converts it to years.
     *
     * @param input the lifespan string
     * @return the average lifespan in years
     */
    public double parseAverageLifespanYears(final String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0.0;
        }

        String in = input.toLowerCase().replaceAll(REGEX_PARENS, "").trim();
        in = in.replaceAll("years?", "year").replaceAll("yrs?", "year");

        if (in.startsWith("up to ")) {
            final String part = in.substring(UP_TO_PREFIX_LENGTH).trim();
            return valueWithUnitToYears(part);
        }

        final String[] parts = splitRange(in);
        if (parts.length == ARRAY_SIZE_TWO) {
            double low = valueWithUnitToYears(parts[0]);
            double high = valueWithUnitToYears(parts[1]);
            if (low == 0) {
                low = high;
            }
            if (high == 0) {
                high = low;
            }
            return (low + high) / 2.0;
        }

        return valueWithUnitToYears(in);
    }

    private double valueWithUnitToYears(final String tokenParam) {
        if (tokenParam == null) {
            return 0;
        }

        final String tokenTrimmed = tokenParam.trim();
        final String num = extractNumber(tokenTrimmed);
        if (num.isEmpty()) {
            return 0;
        }

        final double value = safeParseDouble(num);

        if (tokenTrimmed.contains("month")) {
            return value / MONTHS_PER_YEAR;
        }

        if (tokenTrimmed.contains("week")) {
            return value / WEEKS_PER_YEAR;
        }

        if (tokenTrimmed.contains("day")) {
            return value / DAYS_PER_YEAR;
        }

        return value;
    }

    /**
     * Parses a height string and converts it to centimeters.
     *
     * @param input the height string
     * @return the average height in centimeters
     */
    public double parseAverageHeightCm(final String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }

        final String processedInput = input.toLowerCase().replaceAll(REGEX_PARENS, "").trim();

        if (processedInput.contains(CM) || processedInput.contains(M) || processedInput.contains(MM)) {
            final String metricPart = firstPartBeforeParen(processedInput);
            return parseMetricHeightToCm(metricPart);
        }

        return parseImperialHeightToCm(processedInput);
    }

    private double parseMetricHeightToCm(final String tokenParam) {
        String tokenLocal = tokenParam;

        if (!tokenLocal.contains(CM)) {
            if (tokenLocal.contains(MM)) {
                tokenLocal = tokenLocal.replaceAll(MM, SPACE + MM + SPACE).trim();
            }
            else if (tokenLocal.contains(M)) {
                tokenLocal = tokenLocal.replaceAll(M, SPACE + M + SPACE).trim();
            }
        }
        else {
            tokenLocal = tokenLocal.replaceAll(CM, SPACE + CM + SPACE).trim();
        }

        final String[] parts = splitRange(tokenLocal);

        if (parts.length == ARRAY_SIZE_TWO) {
            return calculateMetricHeightAverage(parts);
        }
        else {
            return convertSingleMetricHeight(tokenLocal);
        }
    }

    private double calculateMetricHeightAverage(final String[] parts) {
        double firstValue = safeParseDouble(extractNumber(parts[0]));
        double secondValue = safeParseDouble(extractNumber(parts[1]));

        final boolean part0HasCm = parts[0].contains(SPACE + CM + SPACE);
        final boolean part1HasCm = parts[1].contains(SPACE + CM + SPACE);

        if (!part0HasCm && !part1HasCm) {
            if (parts[0].contains(SPACE + MM + SPACE)) {
                firstValue /= MM_PER_CM;
            }
            else if (parts[0].contains(SPACE + M + SPACE)) {
                firstValue *= CM_PER_M;
            }

            if (parts[1].contains(SPACE + MM + SPACE)) {
                secondValue /= MM_PER_CM;
            }
            else if (parts[1].contains(SPACE + M + SPACE)) {
                secondValue *= CM_PER_M;
            }
        }

        return (firstValue + secondValue) / 2.0;
    }

    private double convertSingleMetricHeight(final String tokenLocal) {
        final double val = safeParseDouble(extractNumber(tokenLocal));
        if (tokenLocal.contains(SPACE + MM + SPACE)) {
            return val / MM_PER_CM;
        }

        if (tokenLocal.contains(SPACE + M + SPACE) && !tokenLocal.contains(SPACE + CM + SPACE)) {
            return val * CM_PER_M;
        }

        return val;
    }

    private double parseImperialHeightToCm(final String tokenParam) {
        String tokenLocal = tokenParam;
        tokenLocal = tokenLocal.replaceAll("feet", FT).replaceAll("foot", FT)
                .replaceAll("inches?", IN);

        final String[] parts = splitRange(tokenLocal);

        if (parts.length == ARRAY_SIZE_TWO) {
            return calculateImperialHeightAverage(parts);
        }
        else {
            return convertSingleImperialHeight(tokenLocal);
        }
    }

    private double calculateImperialHeightAverage(final String[] parts) {
        final double firstValue = safeParseDouble(extractNumber(parts[0]));
        final double secondValue = safeParseDouble(extractNumber(parts[1]));
        final boolean containsFt = parts[0].contains(FT) || parts[1].contains(FT);
        final boolean containsIn = parts[0].contains(IN) || parts[1].contains(IN);

        if (containsFt && !containsIn) {
            return (firstValue + secondValue) / 2.0 * FT_TO_CM;
        }
        else {
            return (firstValue + secondValue) / 2.0 * IN_TO_CM;
        }
    }

    private double convertSingleImperialHeight(final String tokenLocal) {
        if (tokenLocal.contains(FT) && tokenLocal.contains(IN)) {
            return parseFeetAndInches(tokenLocal);
        }
        else if (tokenLocal.contains(FT)) {
            final double feet = safeParseDouble(extractNumber(tokenLocal));
            return feet * FT_TO_CM;
        }
        else if (tokenLocal.contains(IN)) {
            final double inch = safeParseDouble(extractNumber(tokenLocal));
            return inch * IN_TO_CM;
        }

        return 0;
    }

    private double parseFeetAndInches(final String tokenLocal) {
        final String[] tokens = tokenLocal.split("\\s+");
        double feet = 0;
        double inch = 0;
        for (final String token : tokens) {
            if (token.contains(FT)) {
                feet = safeParseDouble(extractNumber(token));
            }

            if (token.contains(IN)) {
                inch = safeParseDouble(extractNumber(token));
            }
        }

        return feet * FT_TO_CM + inch * IN_TO_CM;
    }

    /**
     * Parses a weight string and converts it to kilograms.
     *
     * @param input the weight string
     * @return the average weight in kilograms
     */
    public double parseAverageWeightKg(final String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }

        final String processedInput = input.toLowerCase().replaceAll(REGEX_PARENS, "")
                .replaceAll(",", "").trim();

        if (processedInput.contains(KG) || (processedInput.contains(G) && !processedInput.contains(KG))) {
            return parseMetricWeightToKg(processedInput);
        }

        return parseImperialWeightToKg(processedInput);
    }

    private double parseMetricWeightToKg(final String tokenParam) {
        String tokenLocal = tokenParam;
        tokenLocal = tokenLocal.replaceAll("kilograms?", KG).replaceAll("grams?", G)
                .replaceAll("milligram", MG);

        final String[] parts = splitRange(tokenLocal);

        if (parts.length == ARRAY_SIZE_TWO) {
            return calculateMetricWeightAverage(parts);
        }
        else {
            return convertSingleMetricWeight(tokenLocal);
        }
    }

    private double calculateMetricWeightAverage(final String[] parts) {
        double firstValue = safeParseDouble(extractNumber(parts[0]));
        double secondValue = safeParseDouble(extractNumber(parts[1]));

        final boolean aKg = parts[0].contains(KG);
        final boolean bKg = parts[1].contains(KG);
        final boolean aG = parts[0].contains(G) && !aKg;
        final boolean bG = parts[1].contains(G) && !bKg;
        final boolean aMg = parts[0].contains(MG);
        final boolean bMg = parts[1].contains(MG);

        if (aG && bKg && secondValue > LARGE_G_THRESHOLD) {
            secondValue = secondValue / GRAMS_PER_KG;
            firstValue = firstValue / GRAMS_PER_KG;
            return (firstValue + secondValue) / 2.0;
        }

        if (!aKg) {
            if (aG) {
                firstValue = firstValue / GRAMS_PER_KG;
            }
            else if (aMg) {
                firstValue = firstValue / MILLIGRAMS_PER_KG;
            }
        }

        if (!bKg) {
            if (bG) {
                secondValue = secondValue / GRAMS_PER_KG;
            }
            else if (bMg) {
                secondValue = secondValue / MILLIGRAMS_PER_KG;
            }
        }

        return (firstValue + secondValue) / 2.0;
    }

    private double convertSingleMetricWeight(final String tokenLocal) {
        final double value = safeParseDouble(extractNumber(tokenLocal));
        if (tokenLocal.contains(KG)) {
            return value;
        }

        if (tokenLocal.contains(G)) {
            return value / GRAMS_PER_KG;
        }

        if (tokenLocal.contains(MG)) {
            return value / MILLIGRAMS_PER_KG;
        }

        return 0;
    }

    private double parseImperialWeightToKg(final String tokenParam) {
        String tokenLocal = tokenParam;
        tokenLocal = tokenLocal.replaceAll("pounds?", LB).replaceAll("ounces?", OZ);

        final String[] parts = splitRange(tokenLocal);

        if (parts.length == ARRAY_SIZE_TWO) {
            final double firstValue = safeParseDouble(extractNumber(parts[0]));
            final double secondValue = safeParseDouble(extractNumber(parts[1]));

            if (parts[0].contains(LB) || parts[1].contains(LB)) {
                return (firstValue + secondValue) / 2.0 * LB_TO_KG;
            }
            else {
                return (firstValue + secondValue) / 2.0 * OZ_TO_KG;
            }
        }
        else {
            return convertSingleImperialWeight(tokenLocal);
        }
    }

    private double convertSingleImperialWeight(final String tokenLocal) {
        final double value = safeParseDouble(extractNumber(tokenLocal));
        if (tokenLocal.contains(LB)) {
            return value * LB_TO_KG;
        }

        if (tokenLocal.contains(OZ)) {
            return value * OZ_TO_KG;
        }

        return 0;
    }

    private String[] splitRange(final String str) {
        if (str.contains(TO_WITH_SPACES)) {
            return str.split("\\s+to\\s+", 2);
        }

        if (str.contains(DASH_SPACED)) {
            return str.split("\\s+-\\s+", 2);
        }

        if (str.contains(EN_DASH)) {
            return str.split(EN_DASH, 2);
        }

        if (str.contains(HYPHEN)) {
            return str.split(HYPHEN, 2);
        }

        return new String[]{str};
    }

    private String firstPartBeforeParen(final String str) {
        final int index = str.indexOf('(');
        if (index >= 0) {
            return str.substring(0, index).trim();
        }

        return str;
    }

    private String extractNumber(final String str) {
        if (str == null) {
            return "";
        }

        final StringBuilder builder = new StringBuilder();
        boolean seenDigit = false;
        for (final char character : str.toCharArray()) {
            if (character >= '0' && character <= '9' || character == '.') {
                builder.append(character);
                seenDigit = true;
            }
            else if (seenDigit) {
                break;
            }
        }

        return builder.toString();
    }

    private double safeParseDouble(final String numStr) {
        if (numStr == null || numStr.isEmpty()) {
            return 0;
        }

        try {
            return Double.parseDouble(numStr);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
}