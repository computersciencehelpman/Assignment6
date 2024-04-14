package package1;

import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Assignment6 {

    public static void main(String[] args) {
        // Define file names for each car model
        String[] fileNames = {"model3.csv", "modelS.csv", "modelX.csv"};

        // Process data for each car model
        for (String fileName : fileNames) {
            processCarModel(fileName);
            System.out.println();
        }
    }

    public static void processCarModel(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            List<String[]> data = new ArrayList<>();

            if(scanner.hasNextLine()) {
                scanner.nextLine();
            }
            
            // Read data from CSV file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] datesAndSales = line.split(",");
                data.add(datesAndSales);
            }

            // Group data by year
            Map<Integer, List<String[]>> salesByYear = groupSalesByYear(data);

            // Print yearly sales report and best/worst months for the current car model
            printBestAndWorstMonths(getCarModelName(fileName), salesByYear);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, List<String[]>> groupSalesByYear(List<String[]> data) {
        // Group sales data by year
        return data.stream()
                .collect(Collectors.groupingBy(arr -> getYearFromDateString(arr[0])));
    }

    public static int getYearFromDateString(String dateString) {
        String[] dateParts = dateString.split("-");
        if (dateParts.length >= 2) {
            return Integer.parseInt(dateParts[1]);
        }
        return -1; // Invalid date format
    }

    public static int calculateTotalSalesForYear(List<String[]> data) {
        return data.stream()
                .mapToInt(arr -> Integer.parseInt(arr[1]))
                .sum();
    }

    public static void printBestAndWorstMonths(String modelName, Map<Integer, List<String[]>> salesByYear) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

        // Print yearly sales report for the current car model
        System.out.println(modelName + " Yearly Sales Report");
        System.out.println("---------------------------");
        for (Map.Entry<Integer, List<String[]>> entry : salesByYear.entrySet()) {
            int year = entry.getKey();
            int totalSales = calculateTotalSalesForYear(entry.getValue());
            System.out.println("20" +year + " -> " + totalSales);
        }

        // Find best and worst months for the current car model
        List<String[]> allSalesDataForModel = salesByYear.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Optional<String> bestMonth = getBestMonth(allSalesDataForModel);
        Optional<String> worstMonth = getWorstMonth(allSalesDataForModel);

        // Print the best and worst months
        bestMonth.ifPresent(month -> System.out.println("The best month for " + modelName + " was: " + month));
        worstMonth.ifPresent(month -> System.out.println("The worst month for " + modelName + " was: " + month));

        System.out.println(); // Add a newline between car models
    }


    public static Optional<String> getBestMonth(List<String[]> salesData) {
        return salesData.stream()
                .max(Comparator.comparingInt(arr -> {
                    try {
                        return Integer.parseInt(arr[1]);
                    } catch (NumberFormatException e) {
                        return 0; // Skip non-numeric values
                    }
                }))
                .map(arr -> arr[0]);
    }

    public static Optional<String> getWorstMonth(List<String[]> salesData) {
        return salesData.stream()
                .min(Comparator.comparingInt(arr -> {
                    try {
                        return Integer.parseInt(arr[1]);
                    } catch (NumberFormatException e) {
                        return Integer.MAX_VALUE; // Set maximum value for non-numeric values
                    }
                }))
                .map(arr -> arr[0]);
    }

    public static String getMonthWithSale(List<String[]> salesData, int saleValue) {
        return salesData.stream()
                .filter(arr -> Integer.parseInt(arr[1]) == saleValue)
                .map(arr -> arr[0])
                .findFirst()
                .orElse("");
    }

    public static String getCarModelName(String fileName) {
        return fileName.replace(".csv", "").toUpperCase();
    }
}
	