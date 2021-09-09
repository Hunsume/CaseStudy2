package com.company.view;

import com.company.controller.ProductManagement;

import java.util.Scanner;

public class ProductMenuWithUser {
    public static ProductManagement productManagement = new ProductManagement();
    public static Scanner scanner = new Scanner(System.in);

    public void run() {
        int choice;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    displayProduct();
                    break;
                }
            }
        } while (choice != 0);
    }

    public void displayProduct() {
        int choice;
        do {
            menuShowProduct();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    displayProductWithType();
                    break;
                }
                case 2: {
                    productManagement.sortProductByPriceToUp();
                    break;
                }
                case 3: {
                    productManagement.sortProductByPriceToDown();
                    break;
                }
                case 4: {
                    break;
                }
            }
        }while (choice != 0);
    }



    public void displayProductWithType() {
        int choice;
        do {
            menuShowTypeProduct();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    productManagement.displayWitchTypeProduct("SmartPhone");
                }
                case 2: {
                    productManagement.displayWitchTypeProduct("LapTop");
                }
                case 3: {
                    productManagement.displayWitchTypeProduct("Tablet");
                }
                case 4: {
                    productManagement.displayWitchTypeProduct("SmartWatch");
                }
                case 5: {
                    productManagement.displayWitchTypeProduct("EarBuds");
                }
            }
        } while (choice != 0);
    }



    public void menuShowTypeProduct() {
        System.out.println("XEM DANH SÁCH SẢN PHẨM THEO LOẠI SẢN PHẨM");
        System.out.println("1. SmartPhone");
        System.out.println("2. LapTop");
        System.out.println("3. TabLet");
        System.out.println("4. SmartWatch");
        System.out.println("5. EarBuds");
        System.out.println("0. Quay lại!!!");
    }

    public void menuShowProduct() {
        System.out.println("XEM DANH SÁCH SẢN PHẨM");
        System.out.println("1. Xem danh sách sản phẩm theo loại sản phẩm: ");
        System.out.println("2. Xem danh sách sản phẩm theo giá từ thấp đến cao: ");
        System.out.println("3. Xem danh sách sản phẩm theo giá từ cao xuống thấp: ");
        System.out.println("0. Thoát ");
    }


    public void menu() {
        System.out.println("--- TRÌNH QUẢN LÝ MUA HÀNG ---");
        System.out.println("1. Xem danh sách sản phẩm: ");
        System.out.println("2. Tìm kiếm sản phẩm");
        System.out.println("3. Thêm sản phẩm vào giỏ: ");
        System.out.println("4. Thanh toán:");
        System.out.println("0. Thoát");
    }


}
