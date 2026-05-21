package me.junior;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        int principal = (int) getNumber("Principal (1.000 - 1.000.000): ", 1_000, 1_000_000);
        byte years = (byte) getNumber("Period (Years): ", 1, 30);
        float rate = (float) getNumber("Annual interest rate: ", 1, 30);

        double mortgage = calculateMortgage(principal, years, rate);

        printSchedule(mortgage, principal);
    }

    public static void printSchedule(double mortgage, double principal) {
        NumberFormat number = NumberFormat.getCurrencyInstance();

        System.out.println("MORTGAGE\n----------\nMonthly payments: " + number.format(mortgage) + "\n\nPAYMENT SCHEDULE\n----------");

        while (principal > 0) {
            principal -= mortgage;
            System.out.println(number.format(Math.max(principal, 0)));
        }
    }

    private static double getNumber(String message, double min, double max) {
        double number;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(message);
            number = scanner.nextDouble();
            if (number >= min && number <= max) {
                break;
            }
            System.out.println("Error: enter value between " + min + " and " + max);
        }

        return number;
    }

    private static double calculateMortgage(int principal, byte years, float rate) {
        final byte MONTH_IN_YEAR = 12;
        final byte PERCENT = 100;

        int period = years * MONTH_IN_YEAR;
        float monthRate = rate / MONTH_IN_YEAR / PERCENT;

        double ratedPeriod = Math.pow(1 + monthRate, period);
        double total = (principal * (monthRate * ratedPeriod) / (ratedPeriod - 1));

        return total;
    }
}