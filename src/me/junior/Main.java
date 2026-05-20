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
        int period = Main.getMonthPeriod(scanner);

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
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        while (true) {
            System.out.print("Principal (" + currency.format(MIN_PRINCIPAL) + " - " + currency.format(MAX_PRINCIPAL) + "): ");
            principal = scanner.nextInt();
            if (principal >= MIN_PRINCIPAL && principal <= MAX_PRINCIPAL) {
                break;
            }
            System.out.println("Error: " + currency.format(MIN_PRINCIPAL) + " - " + currency.format(MAX_PRINCIPAL));
        }

        return principal;
    }

    private static int getMonthPeriod(Scanner scanner) {
        final byte MIN_PERIOD = 1;
        final byte MAX_PERIOD = 30;

        int period = 0;
        int monthPeriod = 0;

        while (true) {
            System.out.print("Period (Years): ");
            period = scanner.nextByte();

            if (period >= MIN_PERIOD && period <= MAX_PERIOD) {
                monthPeriod = period * Main.MONTH_IN_YEAR;
                break;
            }
            System.out.println("Error: " + MIN_PERIOD + " - " + MAX_PERIOD);
        }

        return monthPeriod;
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
            System.out.println("Error: " + MIN_RATE + " - " + MAX_RATE);
        }

        return monthRate;
    }
}