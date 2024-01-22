import java.io.File;
import java.util.Scanner;
import java.util.Map;

public class ECommProfitCalculator {
    public static void main(String[] args) {
        loadOrdersAndCompute("Input/customer_orders.csv");
        calculateFinancialMetrics();
        generateReports();
    }

    private static void generateReports() {
        ReportingTool revenueReporter = new ReportingTool("Revenue");
        ReportingTool profitReporter = new ReportingTool("Profit");
        ReportingTool sizeProfitReporter = new ReportingTool("SizeProfit");

        revenueReporter.run();
        profitReporter.run();
        sizeProfitReporter.run();

        try {
            revenueReporter.join();
            profitReporter.join();
            sizeProfitReporter.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadOrdersAndCompute(String filePath) {
        File inputFile = new File(filePath);

        try (Scanner scanner = new Scanner(inputFile)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] lineParts = currentLine.split(",");

                try {
                    String customerName = lineParts[0].trim();
                    String tshirtSize = lineParts[1].trim();
                    boolean hasDesign = Boolean.parseBoolean(lineParts[2].trim());
                    boolean hasHoodie = Boolean.parseBoolean(lineParts[3].trim());
                    String paymentType = lineParts[4].trim();

                    Order.orderList.add(new Order(customerName, tshirtSize, hasDesign, hasHoodie, paymentType));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void calculateFinancialMetrics() {
        for (Order order : Order.orderList) {
            double profitAmount = 40.0;
            double totalEarnings = ProfitCalculations.getTotalEarnings();
            double totalProfit = ProfitCalculations.getTotalProfit();
            Map<String, Double> profitPerSizeMap = ProfitCalculations.getProfitPerSizeMap();

            totalEarnings += profitAmount;

            profitAmount -= 14.0;
            if (order.hasHoodie()) profitAmount -= 3.0;
            if (order.hasDesign()) profitAmount -= 2.0;

            TransactionMethod paymentStrategy = PaymentFactory.createPaymentStrategy(order.getPaymentType());
            TransactionContext paymentContext = new TransactionContext(paymentStrategy);

            double transactionFee = paymentContext.getTransactionFee(40);
            totalProfit += (profitAmount - transactionFee);

            String tshirtSize = order.getShirtSize();
            profitPerSizeMap.put(tshirtSize, profitPerSizeMap.getOrDefault(tshirtSize, 0.0) + (profitAmount - transactionFee));

            ProfitCalculations.setTotalEarnings(totalEarnings);
            ProfitCalculations.setTotalProfit(totalProfit);
        }
    }
}
