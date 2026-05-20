package me.junior;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    static final byte MONTH_IN_YEAR = 12;
    static final byte PERCENT = 100;

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int principal = Main.getPrincipal(scanner);
        float monthRate = Main.getMonthRate(scanner);
        int period = Main.getPeriod(scanner);

        String mortgage = Main.getMortgage(principal, period, monthRate);

        System.out.println("Mortgage: " + mortgage);
    }

    private static String getMortgage(int principal, int period, float monthRate) {
        double ratedPeriod = Math.pow(1 + monthRate, period);
        double total = (principal * (monthRate * ratedPeriod) / (ratedPeriod - 1));

        return NumberFormat.getCurrencyInstance().format(total);
    }

    private static int getPrincipal(Scanner scanner) {
        final int MIN_PRINCIPAL = 1_000;
        final int MAX_PRINCIPAL = 1_000_000;

        int principal = 0;

        while (true) {
            System.out.print("Principal ($1k - $1M): ");
            principal = scanner.nextInt();
            if (principal >= MIN_PRINCIPAL && principal <= MAX_PRINCIPAL) {
                break;
            }
            System.out.println("Error: $1k - $1M");
        }

        return principal;
    }

    private static int getPeriod(Scanner scanner) {
        final byte MIN_PERIOD = 1;
        final byte MAX_PERIOD = 30;

        int period = 0;

        while (true) {
            System.out.print("Period (Years): ");
            period = scanner.nextByte() * Main.MONTH_IN_YEAR;

            if (period >= MIN_PERIOD && period <= MAX_PERIOD) {
                break;
            }
            System.out.println("Error: 1 - 30");
        }

        return period;
    }

    private static float getMonthRate(Scanner scanner) {
        final byte MIN_RATE = 1;
        final byte MAX_RATE = 30;

        float rate;
        float monthRate;

        while (true) {
            System.out.print("Annual interest rate: ");
            rate = scanner.nextFloat();

            if (rate >= MIN_RATE && rate <= MAX_RATE) {
                monthRate = rate / Main.MONTH_IN_YEAR / PERCENT;
                break;
            }
            System.out.println("Error: 1 - 30");
        }

        return monthRate;
    }
}