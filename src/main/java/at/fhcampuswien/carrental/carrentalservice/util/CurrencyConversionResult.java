package at.fhcampuswien.carrental.carrentalservice.util;

public class CurrencyConversionResult {

    private double amount;
    private String baseCurrency;
    private String targetCurrency;
    private double exchangeRate;
    private double result;

    public CurrencyConversionResult() {}

    public CurrencyConversionResult(double amount, String baseCurrency, String targetCurrency, double exchangeRate, double result) {
        this.amount = amount;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.result = result;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
