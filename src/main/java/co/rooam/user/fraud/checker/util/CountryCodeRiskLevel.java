package co.rooam.user.fraud.checker.util;

import java.util.Set;

public class CountryCodeRiskLevel {

    // High-risk countries list
    private static final Set<String> HIGH_RISK_LIST = Set.of(
            "AF", // Afghanistan
            "BF", // Burkina Faso
            "BY", // Belarus
            "CF", // Central African Republic
            "CN", // China
            "CU", // Cuba
            "HN", // Honduras
            "HT", // Haiti
            "IR", // Iran
            "IQ", // Iraq
            "KG", // Kyrgyzstan
            "KP", // North Korea
            "KW", // Kuwait
            "KZ", // Kazakhstan
            "LY", // Libya
            "ML", // Mali
            "MM", // Myanmar
            "NE", // Niger
            "NI", // Nicaragua
            "PS", // State of Palestine
            "UA", // Ukraine
            "RU", // Russia
            "SD", // Sudan
            "SL", // Sierra Leone
            "SO", // Somalia
            "SS", // South Sudan
            "SY", // Syria
            "TD", // Chad
            "TM", // Turkmenistan
            "TV", // Tuvalu
            "VE", // Venezuela
            "YE"  // Yemen
    );

    // Mid-risk countries list
    private static final Set<String> MID_RISK_LIST = Set.of(
            "AR", // Argentina
            "BI", // Burundi
            "BR", // Brazil
            "CM", // Cameroon
            "CO", // Colombia
            "DZ", // Algeria
            "EC", // Ecuador
            "EG", // Egypt
            "ET", // Ethiopia
            "GN", // Guinea
            "GT", // Guatemala
            "GY", // Guyana
            "GW", // Guinea-Bissau
            "HK", // Hong Kong
            "IN", // India
            "ID", // Indonesia
            "JM", // Jamaica
            "JO", // Jordan
            "KY", // Cayman Islands
            "LA", // Laos
            "LB", // Lebanon
            "MA", // Morocco
            "MR", // Mauritania
            "MX", // Mexico
            "MO", // Macao
            "NG", // Nigeria
            "PK", // Pakistan
            "SA", // Saudi Arabia
            "SH", // Saint Helena
            "SV", // El Salvador
            "TH", // Thailand
            "TN", // Tunisia
            "TR", // Turkey
            "TT", // Trinidad and Tobago
            "TW", // Taiwan
            "UG", // Uganda
            "ZA"  // South Africa
    );

    public static boolean isHighRiskCountryCode(String countryCode) {
        return HIGH_RISK_LIST.contains(countryCode);
    }

    // Medium risk
    public static boolean isMidRiskCountryCode(String countryCode) {
        return MID_RISK_LIST.contains(countryCode);
    }
}
