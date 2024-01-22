import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportingTool extends Thread {
    private static final double TOTAL_REVENUE = ProfitCalculations.getTotalEarnings();
    private static final double TOTAL_PROFIT = ProfitCalculations.getTotalProfit();
    private static final Map<String, Double> TOTAL_PRICE_PER_SHIRT_SIZE = ProfitCalculations.getProfitPerSizeMap();

    private final String reportName;

    public ReportingTool(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public void run() {
        switch (reportName) {
            case "Revenue":
                generateAndSaveReport("Total Revenue", TOTAL_REVENUE);
                break;
            case "Profit":
                generateAndSaveReport("Total Profit", TOTAL_PROFIT);
                break;
            case "SizeProfit":
                generateAndSaveSizeProfitReport(TOTAL_PRICE_PER_SHIRT_SIZE);
                break;
            default:
                System.out.println("Unsupported report type: " + reportName);
        }
    }

    private static void generateAndSaveReport(String title, double value) {
        String content = String.format("%s: €%d", title, Math.round(value));
        saveToFile(title, content);
    }

    private static void generateAndSaveSizeProfitReport(Map<String, Double> totalPricePerShirtSize) {
        Comparator<String> sizeComparator = (size1, size2) -> {
            int order1 = getSizeOrder(size1);
            int order2 = getSizeOrder(size2);
            return Integer.compare(order1, order2);
        };

        Map<String, Double> sortedMap = totalPricePerShirtSize.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(sizeComparator))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        StringBuilder content = new StringBuilder("Total Profit per Shirt Size:\n");
        sortedMap.forEach((key, value) -> content.append(key).append(": €").append(Math.round(value)).append("\n"));

        saveToFile("ProfitPerShirtSize", content.toString());
    }

    private static int getSizeOrder(String size) {
        switch (size) {
            case "XS": return 1;
            case "S": return 2;
            case "M": return 3;
            case "L": return 4;
            case "XL": return 5;
            case "2XL": return 6;
            case "3XL": return 7;
            default: return -1;
        }
    }

    private static void saveToFile(String reportName, String content) {
        try (FileWriter writer = new FileWriter("Assignment3/report/" +reportName + " Report.txt")) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
