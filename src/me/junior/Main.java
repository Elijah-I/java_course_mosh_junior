package me.junior;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        final byte MONTH_IN_YEAR = 12;
        final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Principal: ");
        int principal = scanner.nextInt();

        System.out.print("Annual interest rate: ");
        float rate = scanner.nextFloat() / MONTH_IN_YEAR / PERCENT;

        System.out.print("Period (Years): ");
        int period = scanner.nextByte() * MONTH_IN_YEAR;

        double ratedPeriod = Math.pow(1 + rate, period);
        double total = (principal * (rate * ratedPeriod) / (ratedPeriod - 1));

        System.out.println("Mortgage: " + NumberFormat.getCurrencyInstance().format(total));
    }
}