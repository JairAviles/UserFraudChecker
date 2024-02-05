package co.rooam.user.fraud.checker.util;

import java.util.Set;

public class CountryCodeRiskList {

    // High-risk countries list
    private static final Set<String> HIGH_RISK_LIST = Set.of(
            "AF", // Afghanistan
            "BF", // Burkina Faso
            "BY", // Belarus
            "CF", // Central African Republic
            "CM", // Cameroon
            "CN", // China
            "CU", // Cuba
            "HN", // Honduras
            "HT", // Haiti
            "IR", // Iran
            "IQ", // Iraq
            "KP", // North Korea
            "KW", // Kuwait
            "LY", // Libya
            "ML", // Mali
            "NE", // Niger
            "NI", // Nicaragua
            "PS", // State of Palestine
            "UA", // Ukraine
            "RU", // Russia
            "SD", // Sudan
            "SO", // Somalia
            "SS", // South Sudan
            "SY", // Syria
            "TD", // Chad
            "VE", // Venezuela
            "YE" // Yemen
    );

    // Mid-risk countries list
    private static final Set<String> MID_RISK_LIST = Set.of(
            "AR", // Argentina
            "BR", // Brazil
            "CO", // Colombia
            "DZ", // Algeria
            "EC", // Ecuador
            "EG", // Egypt
            "GT", // Guatemala
            "HK", // Hong Kong
            "IN", // India
            "ID", // Indonesia
            "JO", // Jordan
            "KY", // Cayman Islands
            "MA", // Morocco
            "MX", // Mexico
            "MO", // Macao
            "NG", // Nigeria
            "SV", // El Salvador
            "TR", // Turkey
            "ZA" // South Africa
    );

    public static boolean isHighRiskCountryCode(String countryCode) {
        return HIGH_RISK_LIST.contains(countryCode);
    }

    // Medium risk
    public static boolean isMidRiskCountryCode(String countryCode) {
        return MID_RISK_LIST.contains(countryCode);
    }
}
