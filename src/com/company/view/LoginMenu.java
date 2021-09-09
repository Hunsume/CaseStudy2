package com.company.view;

import com.company.controller.UserManagement;
import com.company.model.User;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu {
    Scanner scanner = new Scanner(System.in);
    public static UserManagement userManagement = new UserManagement();
    ProductMenuWithAdmin productMenuWithAdmin = new ProductMenuWithAdmin();
    ProductMenuWithUser productMenuWithUser = new ProductMenuWithUser();


    static {
        userManagement.addNew(new User("Admin123", "Admin123","admin"));
    }

    public void run() {
        readFile();
        int choice;
        do {
            menu();
            System.out.println("Nhập vào lựa chọn của bạn");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    loginAccount();
                    break;
                }
                case 2: {
                    registerAccount();
                    break;
                }
            }
        } while (choice != 0);
    }

    public void registerAccount() {
        System.out.println("Đăng ký tài khoản mới!!!");
            User user = creatNewUser();
            userManagement.addNew(user);
            writeFile();
            System.out.println("Đăng ký tài khoản thành công!");
    }


    public void loginAccount() {
        System.out.println("ĐĂNG NHẬP");
        System.out.println("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.println("Nhập password: ");
        String password = scanner.nextLine();
        User userLogin = new User(username, password);
        if (userManagement.isLogin(userLogin) != null) {
            User user = userManagement.isLogin(userLogin);
            if (user.getRole().equals("admin")) {
                showMenuAdmin();
            } else {
                showMenuUser();
            }
        } else {
            System.err.println("Sai tài khoản hoặc mật khẩu");
            System.out.println("Vui lòng nhập lại!");
        }
    }

    public User creatNewUser() {
        String username = inputUserName();
        String password = inputPassword();
        String fullName = inputFullName();
        String email = inputEmail();
        String phoneNumber = inputPhoneNumber();
        String dateOfBirth = inputDateOfBirth();
        String address = inputAddress();
        String gender = inputGender();
        String role = "user";
        return new User(username, password,fullName,email,phoneNumber, dateOfBirth,address,gender,role);
    }

    private String inputUserName() {
        int index;
        String userName;
        String USER_NAME = "^[A-z_](\\w|\\.|_){6,18}$";
        Pattern pattern = Pattern.compile(USER_NAME);
        Matcher matcher;
        do {
            System.out.println("Nhập vào tên đăng nhập: ");
            userName = scanner.nextLine();
            matcher = pattern.matcher(userName);
          index = userManagement.findByName(userName);
            if (!matcher.matches()) {
                System.out.println("Tài khoản của bạn không hợp lệ!!!");
                System.out.println("Tên đăng nhập phải có độ dài tối thiểu từ 6 - 18 ký tự và không có khoảng trắng! ");
            } else if (index != -1) {
                System.out.println("Tài khoản đã tồn tại");
            }
        } while (!matcher.matches() || index != -1);
        return userName;
    }

    private String inputPassword() {
        String password;
        String rePassword;
        String PASS_WORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        Pattern pattern = Pattern.compile(PASS_WORD);
        Matcher matcher;
        do {
            System.out.println("Nhập mật khẩu: ");
            password = scanner.nextLine();
            matcher = pattern.matcher(password);
            if (!matcher.matches()) {
                System.out.println("Mật khẩu không hợp lệ!!!");
                System.out.println("Mật khẩu phải có 8 ký tự bao gồm một chữ hoa, một chứ thường và số! ");
            }
        } while (!matcher.matches());
        do {
            System.out.println("Nhập lại mật khẩu: ");
            rePassword = scanner.nextLine();
            if (!rePassword.equals(password)) {
                System.out.println("Mật khẩu không khớp! ");
                System.out.println("Xin mời nhập lại ");
            }
        } while (!rePassword.equals(password));
        return password;
    }

    private String inputFullName() {
        System.out.println("Nhập tên đầy đủ: ");
        return scanner.nextLine();
    }

    private String inputEmail() {
        String email;
        String EMAIL = "^[a-zA-Z]+[A-Za-z0-9]*@[a-zA-Z]+(\\.com)$";
        Pattern pattern = Pattern.compile(EMAIL);
        Matcher matcher;
        do {
            System.out.println("Nhập email: ");
            email = scanner.nextLine();
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                System.out.println("Email không hợp lệ! ");
                System.out.println("Vui lòng nhập lại! ");
            }
        } while (!matcher.matches());
        return email;
    }

    private String inputPhoneNumber() {
        String phoneNumber;
        String PHONE_NUMBER = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
        Pattern pattern = Pattern.compile(PHONE_NUMBER);
        Matcher matcher;
        do {
            System.out.println("Nhập số điện thoại: ");
            phoneNumber = scanner.nextLine();
            matcher = pattern.matcher(phoneNumber);
            if (!matcher.matches()) {
                System.out.println("Số điện thoại bạn nhập không hợp lệ!");
                System.out.println("Vui lòng nhập lại!");
            }
        }while (!matcher.matches());
        return phoneNumber;
    }

    private String inputDateOfBirth() {
        String dateOfBirth;
        String DATE_OF_BIRTH = "^(?:0[1-9]|[12]\\d|3[01])([\\/.-])(?:0[1-9]|1[012])\\1(?:19|20)\\d\\d$";
        Pattern pattern = Pattern.compile(DATE_OF_BIRTH);
        Matcher matcher;
        do {
            System.out.println("Nhập ngày tháng năm sinh: ");
            dateOfBirth = scanner.nextLine();
            matcher = pattern.matcher(dateOfBirth);
            if (!matcher.matches()) {
                System.out.println("Bạn đã nhập sai!");
                System.out.println("Vui lòng nhập lại!");
            }
        } while (!matcher.matches());
        return dateOfBirth;
    }

    private String inputAddress() {
        System.out.println("Nhập địa chỉ hiện tại: ");
        return scanner.nextLine();
    }

    private String inputGender() {
        String gender = "";
        int choice;
        do {
            menuGender();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    gender = "Nam";
                    break;
                }
                case 2: {
                    gender = "Nữ";
                    break;
                }
                case 3: {
                    gender = "Khác";
                    break;
                }
            }
        } while (gender.equals(""));
        return gender;
    }

    public void writeFile() {
        userManagement.writeFile("user", userManagement.getUsers());
    }

    public void readFile() {
        userManagement.readFile("user");
    }

    private void showMenuAdmin() {
        int choice;
        do {
            menuAdmin();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    productMenuWithAdmin.run();
                    break;
                }
                case 2: {
                    System.out.println("Danh sách người dùng");

                    userManagement.displayAll();
                    break;
                }
            }
        }while (choice!=0);
    }

    private void showMenuUser() {
        int choice;
        do {
            System.out.println("Nhập lựa chọn của bạn");
            menuUser();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    productMenuWithUser.run();
                }
            }
        } while (choice!=0);
    }

    private void menuAdmin() {
        System.out.println("Trình quản lý Admin");
        System.out.println("1. Quản lý sản phẩm bằng quyền admin ");
        System.out.println("2. Xem danh sách người dùng");
        System.out.println("0. Thoát!");
    }

    private void menuUser() {
        System.out.println("Trình quản lý người dùng");
        System.out.println("1. Xem thông tin tài khoản: ");
        System.out.println("2. Sửa đổi thông tin của bạn: ");
        System.out.println("3. Trình quản lý mua hàng: ");
        System.out.println("0. Thoát!");
    }

    private void menuGender() {
        System.out.println("Chọn giới tính");
        System.out.println("1. Nam");
        System.out.println("2. Nữ ");
        System.out.println("3. Khác");
    }



    private void menu() {
        System.out.println("--- TRÌNH ĐĂNG NHẬP ---");
        System.out.println("1. ĐĂNG NHẬP ");
        System.out.println("2. ĐĂNG KÝ ");
        System.out.println("0. THOÁT");
    }
}
