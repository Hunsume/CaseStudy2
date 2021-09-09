package com.company.view;

import com.company.model.Product;
import static com.company.view.ProductMenuWithUser.productManagement;
import static com.company.view.ProductMenuWithUser.scanner;

public class ProductMenuWithAdmin {

    public void run() {
        readFileProduct();
        int choice;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    addNewProductList();
                    writeFileProduct();
                    break;
                }
                case 2: {
                    removeProductList();
                    writeFileProduct();
                    break;
                }
                case 3: {
                    displayProductList();
                    break;
                }
                case 4: {
                    editProductList();
                    writeFileProduct();
                    break;
                }
                case 5: {
                    break;
                }
            }
        }while (choice !=0 );
    }

    private void addNewProductList() {
        int choice;
        do {
            menuAddNew();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    addNewSmartPhone();
                    break;
                }
                case 2: {
                    addNewLapTop();
                    break;
                }
                case 3: {
                    addNewTabLet();
                    break;
                }
                case 4: {
                    addNewSmartWatch();
                }
                case 5: {
                    addNewEarBuds();
                }
            }



        }while (choice != 0);
    }


    private void addNewSmartPhone() {
        Product product = creatProductList();
        product.setType("SmartPhone");
        productManagement.addNew(product);
    }

    private void addNewLapTop() {
        Product product = creatProductList();
        product.setType("LapTop");
        productManagement.addNew(product);
    }

    private void addNewTabLet() {
        Product product = creatProductList();
        product.setType("TabLet");
        productManagement.addNew(product);
    }

    private void addNewSmartWatch() {
        Product product = creatProductList();
        product.setType("SmartWatch");
        productManagement.addNew(product);
    }

    private void addNewEarBuds() {
        Product product = creatProductList();
        product.setType("EarBuds");
        productManagement.addNew(product);
    }

    private void removeProductList() {
        int choice;
        do {
            menuRemove();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    removeProductById();
                    break;
                }
                case 2: {
                    removeProduct();
                    break;
                }

            }
        }while (choice != 0);
    }

    private void removeProductById() {
        System.out.println("Nhập mã sản phẩm cần xóa: ");
        String id = scanner.nextLine();
        productManagement.removeById(id);
    }

    private void removeProduct() {
       productManagement.clearAll();
        System.out.println("Đã xóa hết!!!");
    }

    private void displayProductList() {
        productManagement.displayAll();
    }

    private void editProductList() {
        System.out.println("NHẬP MÃ SẢN PHẨM CẦN CHỈNH SỬA!!");
        String id = scanner.nextLine();
        int index = productManagement.findById(id);
        if (index != -1){
            Product product = creatProductList();
            scanner.nextLine();
            System.out.println("Nhập vào loại mặt hàng");
            String type = scanner.nextLine();
            product.setType(type);
            productManagement.updateById(id, product);
        }else System.out.println("Không tìm thấy loại mặt hàng này!!!");
    }

    public void readFileProduct() {
        productManagement.readFile("product");
    }

    public void writeFileProduct() {
        productManagement.writeFile("product", productManagement.getProducts());
    }

    private Product creatProductList() {
        System.out.println("Mã sản phẩm: ");
        String id = scanner.nextLine();
        System.out.println("Tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.println("Thương hiệu sản phẩm: ");
        String brand = scanner.nextLine();
        System.out.println("Gía sản phẩm: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        return new Product(id,name,brand,price);
    }


    private void menuRemove() {
        System.out.println("--- CHỌN CÁCH XÓA SẢN PHẨM ---");
        System.out.println("1. Xóa sản phẩm theo mã sản phẩm: ");
        System.out.println("2. Xóa hết tất cả sản phẩm có trong kho: ");
        System.out.println("0. Quay lại!!!");
    }


    private void menuAddNew() {
        System.out.println("--- THÊM SẢN PHẨM THEO LOẠI SẢN PHẨM ---");
        System.out.println("1. SmartPhone");
        System.out.println("2. LapTop");
        System.out.println("3. TabLet");
        System.out.println("4. SmartWatch");
        System.out.println("5. EarBuds");
        System.out.println("0. Quay lại!!!");
    }

    private void menu() {
        System.out.println("--- QUẢN LÝ SẢN PHẨM ---");
        System.out.println("1. Thêm sản phẩm mới vào kho: ");
        System.out.println("2. Xóa sản phẩm trong kho: ");
        System.out.println("3. Xem các sản phẩm có trong kho: ");
        System.out.println("4. Sửa lại thông tin sản phẩm trong kho: ");
        System.out.println("5. Tìm kiếm sản phẩm trong kho: ");
        System.out.println("0. Quay lại!!!");
    }
}
