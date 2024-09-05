package com.qsp.view;

import com.qsp.controller.BankController;
import com.qsp.model.Bank;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BankView {
    static boolean alter = false;
    static BankController controller = new BankController();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            while (!alter){
                System.out.println();
                System.out.println("1. Add bank");
                System.out.println("2. fetch bank");
                System.out.println("3. delete bank");
                System.out.println("4. Update bank");
                System.out.println("5. View all banks");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int c = sc.nextInt();
                switch (c){
                    case 1:
                        addBank();
                        break;

                    case 2:
                        fetchBank();
                        break;

                    case 3:
                        deleteBank();
                        break;

                    case 4:
                        updateBank();
                        break;

                    case 5:
                        getAllBank();
                        break;

                    case 6:
                        alter = !alter;
                        break;
                    default:
                        System.out.println("Enter correct choice.");
                }
            }
        }catch (SQLException | RuntimeException e){
            System.out.println();
            System.out.println("Something wrong happened..!!!");
            System.out.println(e.toString());
        }
    }
    private static void addBank() throws SQLException {
        System.out.print("Enter bank id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter bank name: ");
        String name = sc.nextLine();
        System.out.print("Enter bank loc: ");
        String loc = sc.nextLine();

        Bank b = new Bank();
        b.setBid(id);
        b.setName(name);
        b.setLoc(loc);
        controller.saveBank(b);
    }
    private static void getAllBank() throws SQLException {
        List<Bank> list = controller.fetchAll();
        if(list.isEmpty()){
            System.out.println("Empty database.");
            return;
        }
        for(Bank e : list)
            System.out.println(e);
    }

    private static void fetchBank() throws SQLException {
        System.out.print("Enter bank id: ");
        int id = sc.nextInt();
        if(controller.fetchBank(id).getName() == null){
            System.out.println("No Data found.");
        }else{
            System.out.println(controller.fetchBank(id));
        }
    }

    private static void deleteBank() throws SQLException {
        System.out.print("Enter bank id: ");
        int id = sc.nextInt();
        controller.deleteBank(id);
    }

    private static void updateBank() throws SQLException {
        System.out.print("Enter bank id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter location: ");
        String loc = sc.nextLine();
        controller.updateBank(id, loc);
    }
}
