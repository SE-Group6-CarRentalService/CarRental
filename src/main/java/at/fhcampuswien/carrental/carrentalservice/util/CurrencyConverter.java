package at.fhcampuswien.carrental.carrentalservice.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CurrencyConverter {

    private final Map<String, BigDecimal> rates;

    public CurrencyConverter(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    public BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }

        BigDecimal rate = rates.get(toCurrency).divide(rates.get(fromCurrency), 5, RoundingMode.HALF_UP);
        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
}
