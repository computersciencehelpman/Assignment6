package package1;

import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.Stream;

public class Assignment6 {

	public static void main(String[] args) {
		
		String fileName1= "model3.csv";
		String fileName2= "modelS.csv";
		String fileName3= "modelX.csv";
		

	    int totalSalesModel316 = calculateTotalSalesForYear(fileName1, 16);
	    int totalSalesModel317 = calculateTotalSalesForYear(fileName1, 17);
	    int totalSalesModel318 = calculateTotalSalesForYear(fileName1, 18);
	    int totalSalesModel319 = calculateTotalSalesForYear(fileName1, 19);
	    
	    int totalSalesModelS16 = calculateTotalSalesForYear(fileName2, 16);
	    int totalSalesModelS17 = calculateTotalSalesForYear(fileName2, 17);
	    int totalSalesModelS18 = calculateTotalSalesForYear(fileName2, 18);
	    int totalSalesModelS19 = calculateTotalSalesForYear(fileName2, 19);
	    
	    int totalSalesModelX16 = calculateTotalSalesForYear(fileName3, 16);
	    int totalSalesModelX17 = calculateTotalSalesForYear(fileName3, 17);
	    int totalSalesModelX18 = calculateTotalSalesForYear(fileName3, 18);
	    int totalSalesModelX19 = calculateTotalSalesForYear(fileName3, 19);

	    
	    System.out.println("Model 3 Yearly Sales Report");
	    System.out.println("---------------------------");
	    System.out.println("2016 -> " + totalSalesModel316);
	    System.out.println("2017 -> "+totalSalesModel317);
	    System.out.println("2018 -> "+totalSalesModel318);
	    System.out.println("2019 -> "+totalSalesModel319);
	    System.out.println();
	    ReadFromCSV1(fileName1);
	    System.out.println();
	    System.out.println("Model S Yearly Sales Report");
	    System.out.println("---------------------------");
	    System.out.println("2016 -> " + totalSalesModelS16);
	    System.out.println("2017 -> " + totalSalesModelS17);
	    System.out.println("2018 -> " + totalSalesModelS18);
	    System.out.println("2019 -> " + totalSalesModelS19);
	    System.out.println();
	    ReadFromCSV2(fileName2);
	    System.out.println();
	    System.out.println("model X Yearly Sales Report");
	    System.out.println("---------------------------");
	    System.out.println("2016 -> " + totalSalesModelX16);
	    System.out.println("2017 -> " + totalSalesModelX17);
	    System.out.println("2018 -> " + totalSalesModelX18);
	    System.out.println("2019 -> " + totalSalesModelX19);
	    System.out.println();
		ReadFromCSV3(fileName3);

	}
	
	
	public static void ReadFromCSV1(String fileName1) {
		
		try(Scanner scanner = new Scanner(new File(fileName1))) {
			
			List <String[]> data = new ArrayList<>();
			
			if(scanner.hasNextLine()) {
				scanner.nextLine();
			}
			
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[]  DatesandSales = line.split(",");
				//String Dates = DatesandSales[0];
				
				 //sum2016 = ranges(DatesandSales);
				 
				data.add(DatesandSales);
			}
			

			//Process the data using streams to generate the report
			//For example finding the highest sale
			OptionalInt highestSale = data.stream()
					                      .mapToInt(arr ->Integer.parseInt(arr[1]))
					                      .max();
	
			if(highestSale.isPresent()) {
				int highestSaleValue = highestSale.getAsInt();
				
				
				Optional<String> dateWithHighestSale = data.stream()
						                                   .filter(arr -> Integer.parseInt(arr[1]) == highestSaleValue)
						                                   .map(arr -> arr[0])
						                                   .findFirst();
				
				//dateWithHighestSale.ifPresent(date -> System.out.println("Highest sale: "+date + " "+highestSaleValue));
				
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM-yy");
				DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
				
				dateWithHighestSale.ifPresent(date -> {
					YearMonth yearMonth = YearMonth.parse(date, inputFormatter);
					LocalDate firstDayOfMonth = yearMonth.atDay(1);
					System.out.println("The best month for Model 3 was: "+firstDayOfMonth.format(outputFormatter));
				});
				
				
			}else {
				System.out.println("No sales data found");
			}
			
			OptionalInt lowestSale = data.stream()
                    .mapToInt(arr -> Integer.parseInt(arr[1]))
                    .min();
			
			if(lowestSale.isPresent()) {
				int lowestSaleValue = lowestSale.getAsInt();
				
				
				Optional<String> dateWithLowestSale = data.stream()
						                                   .filter(arr -> Integer.parseInt(arr[1]) == lowestSaleValue)
						                                   .map(arr -> arr[0])
						                                   .findFirst();
				
				//dateWithHighestSale.ifPresent(date -> System.out.println("Highest sale: "+date + " "+highestSaleValue));
				
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM-yy");
				DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
				
				dateWithLowestSale.ifPresent(date -> {
					YearMonth yearMonth = YearMonth.parse(date, inputFormatter);
					LocalDate firstDayOfMonth = yearMonth.atDay(1);
					System.out.println("The worst month for Model 3 was: "+firstDayOfMonth.format(outputFormatter));
				});
				
				
			}else {
				System.out.println("No sales data found");
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void ReadFromCSV2(String fileName2) {
		try(Scanner scanner = new Scanner(new File(fileName2))){
		
			List <String[]> data = new ArrayList<>();
			
			if(scanner.hasNextLine()) {
				scanner.nextLine();
			}
			
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[]  DatesandSales = line.split(",");
				
				
				//sum2016 += ranges(DatesandSales);
				
				
				data.add(DatesandSales);
			}
			

			 
			//Process the data using streams to generate the report
			//For example finding the highest sale
			OptionalInt highestSale = data.stream()
					                      .mapToInt(arr ->Integer.parseInt(arr[1]))
					                      .max();
	
			if(highestSale.isPresent()) {
				int highestSaleValue = highestSale.getAsInt();
				
				
				Optional<String> dateWithHighestSale = data.stream()
						                                   .filter(arr -> Integer.parseInt(arr[1]) == highestSaleValue)
						                                   .map(arr -> arr[0])
						                                   .findFirst();
				
				//dateWithHighestSale.ifPresent(date -> System.out.println("Highest sale: "+date + " "+highestSaleValue));
				
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM-yy");
				DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
				
				dateWithHighestSale.ifPresent(date -> {
					YearMonth yearMonth = YearMonth.parse(date, inputFormatter);
					LocalDate firstDayOfMonth = yearMonth.atDay(1);
					System.out.println("The best month for Model S was: "+firstDayOfMonth.format(outputFormatter));
				});
				
			}else {
				System.out.println("No sales data found");
			}
			
			OptionalInt lowestSale = data.stream()
                    .mapToInt(arr -> Integer.parseInt(arr[1]))
                    .min();
			
			if(lowestSale.isPresent()) {
				int lowestSaleValue = lowestSale.getAsInt();
				
				
				Optional<String> dateWithLowestSale = data.stream()
						                                   .filter(arr -> Integer.parseInt(arr[1]) == lowestSaleValue)
						                                   .map(arr -> arr[0])
						                                   .findFirst();
				
				//dateWithHighestSale.ifPresent(date -> System.out.println("Highest sale: "+date + " "+highestSaleValue));
				
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM-yy");
				DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
				
				dateWithLowestSale.ifPresent(date -> {
					YearMonth yearMonth = YearMonth.parse(date, inputFormatter);
					LocalDate firstDayOfMonth = yearMonth.atDay(1);
					System.out.println("The worst month for Model S was: "+firstDayOfMonth.format(outputFormatter));
				});
				
				
			}else {
				System.out.println("No sales data found");
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void ReadFromCSV3(String fileName3) {
	try(Scanner scanner = new Scanner(new File(fileName3))){
			
			List <String[]> data = new ArrayList<>();
			
			if(scanner.hasNextLine()) {
				scanner.nextLine();
			}
			
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[]  DatesandSales = line.split(",");
				//String Dates = DatesandSales[0];
				
				//sum2016 += ranges(DatesandSales);
			
				data.add(DatesandSales);
			}
			
			 
			//Process the data using streams to generate the report
			//For example finding the highest sale
			OptionalInt highestSale = data.stream()
					                      .mapToInt(arr ->Integer.parseInt(arr[1]))
					                      .max();
	
			if(highestSale.isPresent()) {
				int highestSaleValue = highestSale.getAsInt();
				
				
				Optional<String> dateWithHighestSale = data.stream()
						                                   .filter(arr -> Integer.parseInt(arr[1]) == highestSaleValue)
						                                   .map(arr -> arr[0])
						                                   .findFirst();
				
				//dateWithHighestSale.ifPresent(date -> System.out.println("Highest sale: "+date + " "+highestSaleValue));
				
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM-yy");
				DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
				
				dateWithHighestSale.ifPresent(date -> {
					YearMonth yearMonth = YearMonth.parse(date, inputFormatter);
					LocalDate firsrDayOfMonth = yearMonth.atDay(1);
					System.out.println("The best month for Model X was: "+firsrDayOfMonth.format(outputFormatter));
				});
				
				
				
			}else {
				System.out.println("No sales data found");
			}
			
			OptionalInt lowestSale = data.stream()
                    .mapToInt(arr -> Integer.parseInt(arr[1]))
                    .min();
			
			if(lowestSale.isPresent()) {
				int lowestSaleValue = lowestSale.getAsInt();
				
				
				Optional<String> dateWithLowestSale = data.stream()
						                                   .filter(arr -> Integer.parseInt(arr[1]) == lowestSaleValue)
						                                   .map(arr -> arr[0])
						                                   .findFirst();
				
				//dateWithHighestSale.ifPresent(date -> System.out.println("Highest sale: "+date + " "+highestSaleValue));
				
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM-yy");
				DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
				
				dateWithLowestSale.ifPresent(date -> {
					YearMonth yearMonth = YearMonth.parse(date, inputFormatter);
					LocalDate firstDayOfMonth = yearMonth.atDay(1);
					System.out.println("The worst month for Model X was: "+firstDayOfMonth.format(outputFormatter));
				});
				
				
			}else {
				System.out.println("No sales data found");
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}


	public static int calculateTotalSalesForYear(String fileName, int year) {
	    int totalSales =0;
	 
	    Map<String, Integer> monthMap = new HashMap<>();
	    monthMap.put("Jan", 1);
	    monthMap.put("Feb", 2);
	    monthMap.put("Mar", 3);
	    monthMap.put("Apr", 4);
	    monthMap.put("May", 5);
	    monthMap.put("Jun", 6);
	    monthMap.put("Jul", 7);
	    monthMap.put("Aug", 8);
	    monthMap.put("Sep", 9);
	    monthMap.put("Oct", 10);
	    monthMap.put("Nov", 11);
	    monthMap.put("Dec", 12);
	    
	    try (Scanner scanner = new Scanner(new File(fileName))) {
	       
	    
	    	
	    	// Skip the header line
	        if (scanner.hasNextLine()) {
	            scanner.nextLine();
	        }

	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] data = line.split(",");
	            String[] dateParts = data[0].split("-");
	            int sales = Integer.parseInt(data[1]);
	            // Ensure dateParts has at least two elements
	            if (dateParts.length == 2) { 
	                try {
	                   // int salesYear = Integer.parseInt(dateParts[0]);
	                	 
	                	//Extract month and year components
	                	String monthName = dateParts[0];
	                	int salesYear = Integer.parseInt(dateParts[1]);
	                	 
	                	//Convert month name to numeric value
	                	int monthValue = monthMap.get(monthName);
	                	 
	                	if (salesYear == year) {
	                		 
	                        totalSales += sales;
	                       
	                    }
	                } catch (NumberFormatException e) {
	                    System.err.println("Invalid sales value: " + data[1]);
	                    // Handle or log the exception as needed
	                }
	            } else {
	                System.err.println("Invalid date format: " + data[0]);
	                // Handle or log the invalid date format as needed
	                
	            }
	         
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	     return totalSales; 
	}


}
