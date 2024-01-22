import java.util.*;

public class ProfitCalculations {
    private static double totalEarnings = 0.0;
    private static double totalProfit = 0.0;
    private static final Map<String, TransactionMethod> paymentStrategyMap = new HashMap<>();
    private static final Map<String, Double> profitPerSizeMap = new HashMap<>();

    static {
        paymentStrategyMap.put("wallet", new WalletTransaction());
        paymentStrategyMap.put("card", new CardTransaction());
        paymentStrategyMap.put("visa", new VisaTransaction());
        paymentStrategyMap.put("mastercard", new MastercardTransaction());
        paymentStrategyMap.put("other", new OtherTransaction());
    }

    public static double getTotalEarnings() {
        return totalEarnings;
    }

    public static double getTotalProfit() {
        return totalProfit;
    }

    public static Map<String, Double> getProfitPerSizeMap() {
        return profitPerSizeMap;
    }

    public static void setTotalEarnings(double totalEarnings) {

        ProfitCalculations.totalEarnings = totalEarnings;
    }

    public static void setTotalProfit(double totalProfit) {

        ProfitCalculations.totalProfit = totalProfit;
    }
}
