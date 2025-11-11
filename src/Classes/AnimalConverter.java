package Classes;

public class AnimalConverter {

    public double parseAverageLifespanYears(String s) {
        if (s == null || s.trim().isEmpty()) return 0;

        String in = s.toLowerCase().replaceAll("[()]", "").trim();

        in = in.replaceAll("years?", "year").replaceAll("yrs?", "year");

        if (in.startsWith("up to ")) {
            String part = in.substring(6).trim();
            return valueWithUnitToYears(part);
        }

        String[] parts = splitRange(in);
        if (parts.length == 2) {
            double low = valueWithUnitToYears(parts[0]);
            double high = valueWithUnitToYears(parts[1]);
            if (low == 0) low = high;
            if (high == 0) high = low;
            return (low + high) / 2.0;
        }

        return valueWithUnitToYears(in);
    }

    private double valueWithUnitToYears(String token) {
        if (token == null) return 0;
        token = token.trim();
        String num = extractNumber(token);
        if (num.isEmpty()) return 0;
        double v = safeParseDouble(num);

        if (token.contains("month")) return v / 12.0;
        if (token.contains("week")) return v / 52.0;
        if (token.contains("day")) return v / 365.0;
        return v;
    }

    public double parseAverageHeightCm(String s) {
        if (s == null || s.trim().isEmpty()) return 0;
        String in = s.toLowerCase().replaceAll("[()]", "").trim();

        if (in.contains("cm") || in.contains("m") || in.contains("mm")) {
            String metricPart = firstPartBeforeParen(in);
            return parseMetricHeightToCm(metricPart);
        }

        return parseImperialHeightToCm(in);
    }

    private double parseMetricHeightToCm(String token) {
        token = token.replaceAll("cm", " cm ").replaceAll("m", " m ").replaceAll("mm", " mm ").trim();
        String[] parts = splitRange(token);
        if (parts.length == 2) {
            double a = safeParseDouble(extractNumber(parts[0]));
            double b = safeParseDouble(extractNumber(parts[1]));
            boolean aIsM = parts[0].contains(" m ");
            boolean bIsM = parts[1].contains(" m ");
            boolean aIsMm = parts[0].contains(" mm ");
            boolean bIsMm = parts[1].contains(" mm ");
            if (aIsM) a *= 100;
            if (bIsM) b *= 100;
            if (aIsMm) a /= 10.0;
            if (bIsMm) b /= 10.0;
            return (a + b) / 2.0;
        } else {
            double val = safeParseDouble(extractNumber(token));
            if (token.contains(" m ")) return val * 100;
            if (token.contains(" mm ")) return val / 10.0;
            return val;
        }
    }

    private double parseImperialHeightToCm(String token) {
        token = token.replaceAll("feet", "ft").replaceAll("foot", "ft").replaceAll("inches?", "in");
        String[] parts = splitRange(token);
        if (parts.length == 2) {
            double a = safeParseDouble(extractNumber(parts[0]));
            double b = safeParseDouble(extractNumber(parts[1]));
            boolean containsFt = parts[0].contains("ft") || parts[1].contains("ft");
            boolean containsIn = parts[0].contains("in") || parts[1].contains("in");
            if (containsFt && !containsIn) {
                return ((a + b) / 2.0) * 30.48;
            } else {
                return ((a + b) / 2.0) * 2.54;
            }
        } else {
            if (token.contains("ft") && token.contains("in")) {
                String[] tokens = token.split("\\s+");
                double feet = 0, inch = 0;
                for (String t : tokens) {
                    if (t.contains("ft")) feet = safeParseDouble(extractNumber(t));
                    if (t.contains("in")) inch = safeParseDouble(extractNumber(t));
                }
                return feet * 30.48 + inch * 2.54;
            } else if (token.contains("ft")) {
                double feet = safeParseDouble(extractNumber(token));
                return feet * 30.48;
            } else if (token.contains("in")) {
                double inch = safeParseDouble(extractNumber(token));
                return inch * 2.54;
            }
        }
        return 0;
    }

    public double parseAverageWeightKg(String s) {
        if (s == null || s.trim().isEmpty()) return 0;
        String in = s.toLowerCase().replaceAll("[()]", "").replaceAll(",", "").trim();

        if (in.contains("kg") || (in.contains("g") && !in.contains("kg"))) {
            return parseMetricWeightToKg(in);
        }

        return parseImperialWeightToKg(in);
    }

    private double parseMetricWeightToKg(String token) {
        token = token.replaceAll("kilograms?", "kg").replaceAll("grams?", "g").replaceAll("milligram", "mg");
        String[] parts = splitRange(token);
        if (parts.length == 2) {
            double a = safeParseDouble(extractNumber(parts[0]));
            double b = safeParseDouble(extractNumber(parts[1]));

            boolean aKg = parts[0].contains("kg");
            boolean bKg = parts[1].contains("kg");
            boolean aG = parts[0].contains("g") && !aKg;
            boolean bG = parts[1].contains("g") && !bKg;
            boolean aMg = parts[0].contains("mg");
            boolean bMg = parts[1].contains("mg");

            if ((aG && bKg) && b > 1000) {
                b = b / 1000.0;
                a = a / 1000.0;
                return (a + b) / 2.0;
            }

            if (aKg) { /* a in kg */ } else if (aG) a = a / 1000.0; else if (aMg) a = a / 1_000_000.0;
            if (bKg) { /* b in kg */ } else if (bG) b = b / 1000.0; else if (bMg) b = b / 1_000_000.0;

            return (a + b) / 2.0;
        } else {
            double v = safeParseDouble(extractNumber(token));
            if (token.contains("kg")) return v;
            if (token.contains("g")) return v / 1000.0;
            if (token.contains("mg")) return v / 1_000_000.0;
            return 0;
        }
    }

    private double parseImperialWeightToKg(String token) {
        token = token.replaceAll("pounds?", "lb").replaceAll("ounces?", "oz");
        String[] parts = splitRange(token);
        if (parts.length == 2) {
            double a = safeParseDouble(extractNumber(parts[0]));
            double b = safeParseDouble(extractNumber(parts[1]));
            if (parts[0].contains("lb") || parts[1].contains("lb")) {
                return ((a + b) / 2.0) * 0.453592;
            } else {
                return ((a + b) / 2.0) * 0.0283495;
            }
        } else {
            double v = safeParseDouble(extractNumber(token));
            if (token.contains("lb")) return v * 0.453592;
            if (token.contains("oz")) return v * 0.0283495;
            return 0;
        }
    }

    private String[] splitRange(String s) {
        if (s.contains(" to ")) return s.split("\\s+to\\s+", 2);
        if (s.contains(" - ")) return s.split("\\s+-\\s+", 2);
        if (s.contains("–")) return s.split("–", 2);
        if (s.contains("-")) return s.split("-", 2);
        return new String[] { s };
    }

    private String firstPartBeforeParen(String s) {
        int i = s.indexOf('(');
        return i >= 0 ? s.substring(0, i).trim() : s;
    }

    private String extractNumber(String s) {
        if (s == null) return "";
        StringBuilder b = new StringBuilder();
        boolean seenDigit = false;
        for (char c : s.toCharArray()) {
            if ((c >= '0' && c <= '9') || c == '.') {
                b.append(c);
                seenDigit = true;
            } else if (seenDigit) {
                break;
            }
        }
        return b.toString();
    }

    private double safeParseDouble(String n) {
        if (n == null || n.isEmpty()) return 0;
        try {
            return Double.parseDouble(n);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
