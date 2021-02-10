package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {
    private final int TICKET_PRICE = 10;
    private final int TICKET_PRICE_LAST_ROW = 8;
    private final int SMALL_CINEMA_MAX_SEATS = 60;

    private Scanner scanner = new Scanner(System.in);
    private char[][] cinemaPlan;
    private int currentIncome = 0;
    private int ticketsSold = 0;

    private void createCinema() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println("");

        cinemaPlan = new char[rows][seats];
        for (int i = 0; i < cinemaPlan.length; i++) {
            for (int j = 0; j < cinemaPlan[i].length; j++) {
                cinemaPlan[i][j] = 'S';
            }
        }
    }

    private void menu() {
        int choose = -1;
        while (choose != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    printCinemaPlan();
                    System.out.println("");
                    break;
                case 2:
                    soldTicket();
                    System.out.println("");
                    break;
                case 3:
                    printStatistic();
                    System.out.println("");
                    break;
                default:
                    break;
            }
        }
    }

    private void printStatistic() {
        DecimalFormat df = new DecimalFormat("0.00");
        double seatsCount = cinemaPlan[0].length * cinemaPlan.length;
        double percentage = (100 / seatsCount * (double) this.ticketsSold);
        System.out.println("Number of purchased tickets: " + this.ticketsSold +
                "\nPercentage: " + df.format(percentage) + "%" +
                "\nCurrent income: $" + this.currentIncome +
                "\nTotal income: $" + totalIncome());
    }

    private void printCinemaPlan() {
        String printLine = " ";
        System.out.println("\nCinema:");

        for (int a = 0; a < cinemaPlan[0].length; a++) {
            printLine = printLine.concat(" " + (a + 1));
        }

        System.out.println(printLine);
        for (int b = 0; b < cinemaPlan.length; b++) {
            printLine = (b + 1) + "";
            for (int c = 0; c < cinemaPlan[b].length; c++) {
                printLine = printLine.concat(" " + cinemaPlan[b][c]);
            }
            System.out.println(printLine);
        }
    }

    private void soldTicket() {
        int row = 0;
        int seat = 0;
        while (true) {
            System.out.println("Enter a row number:");
            row = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();

            if ((row - 1 < 0 ||
                    row - 1 >= cinemaPlan.length) ||
                    (seat - 1 < 0 ||
                            seat - 1 >= cinemaPlan[0].length)) {
                System.out.println("Wrong input!");
                System.out.println("");
            } else if (cinemaPlan[row - 1][seat - 1] == 'S') {
                cinemaPlan[row - 1][seat - 1] = 'B';
                System.out.println("Ticket price: $" + ticketPrice(row));
                this.currentIncome += ticketPrice(row);
                this.ticketsSold++;
                break;
            } else {
                System.out.println("That ticket has already been purchased!");
                System.out.println("");
            }
        }
    }

    private int ticketPrice(int row) {
        if (cinemaPlan[0].length * cinemaPlan.length <= this.SMALL_CINEMA_MAX_SEATS) {
            return this.TICKET_PRICE;
        } else if (row <= (cinemaPlan.length / 2)) {
            return this.TICKET_PRICE;
        } else {
            return this.TICKET_PRICE_LAST_ROW;
        }
    }

    private int totalIncome() {
        if (cinemaPlan[0].length * cinemaPlan.length <= this.SMALL_CINEMA_MAX_SEATS) {
            return cinemaPlan[0].length * cinemaPlan.length * this.TICKET_PRICE;
        } else {
            return ((cinemaPlan.length / 2) * cinemaPlan[0].length * this.TICKET_PRICE) +
                    ((cinemaPlan.length - (cinemaPlan.length / 2)) * cinemaPlan[0].length * this.TICKET_PRICE_LAST_ROW);
        }
    }

    public static void main(String[] args) {
        //Stage 1: create method printCinemaPlan
        //Stage 2: create method soldTicket and income
        //Stage 3: create method createCinema, modify method printCinemaPlan
        //Stage 4: create method menu and CONSTANCE, modify cinemaPlan, ticketPrice, income
        //Stage 5: create method printStatistic, modify soldTicket
        Cinema cinema = new Cinema();
        cinema.createCinema();
        cinema.menu();
    }
}