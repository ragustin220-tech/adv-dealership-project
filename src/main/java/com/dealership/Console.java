package com.dealership;

import java.math.BigDecimal;
import java.util.Scanner;

    public class Console {

        private static final Scanner scanner = new Scanner(System.in);

        public static String promptForString(String prompt){
            System.out.print(prompt);
            return scanner.nextLine();
        }

        public static int promptForInt(String prompt){
            System.out.print(prompt);
            int result =  scanner.nextInt();
            scanner.nextLine();
            return result;
        }
        public static double promptForDouble(String prompt){
            System.out.print(prompt);
            double value = scanner.nextDouble();
            scanner.nextLine();
            return value;
        }
        public static BigDecimal promptForBigDecimal(String prompt){
            System.out.print(prompt);
            BigDecimal value = scanner.nextBigDecimal();
            scanner.nextLine();
            return value;
        }

    }

