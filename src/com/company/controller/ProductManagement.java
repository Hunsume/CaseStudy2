package com.company.controller;

import com.company.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManagement implements IGeneralManagement<Product>,IReadWriteFile<Product> {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public void addNew(Product product) {
        products.add(product);

    }

    @Override
    public void clearAll() {
        products.clear();
    }

    @Override
    public void displayAll() {
        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("Không có sản phẩm nào!!!");
        }

    }

    @Override
    public void removeById(String id) {
        int index = findById(id);
        if (index != -1) {
            products.remove(index);
        } else {
            System.out.println("Không tồn tại");
        }

    }

    @Override
    public void updateById(String id, Product product) {
        int index = findById(id);
        products.set(index, product);
    }

    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int findByName(String name) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void displayWitchTypeProduct(String type) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (type.equals(product.getType())) {
                System.out.println(product);
            }
        }
    }

    public void displayWithBrandProduct(String brand) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (brand.equals(product.getBrand())) {
                System.out.println(product);
            }
        }
    }

    public void sortProductByPriceToUp() {
        Product product;
        int position;
        for (int i = 0; i < products.size(); i++) {
            product = products.get(i);
            position = i;
            while (position > 0 && (product.getPrice() < products.get(position - 1).getPrice())) {
                products.set(position, products.get(position - 1));
                position--;
            }
            products.set(position, product);
        }
    }

    public void sortProductByPriceToDown() {
        Product product;
        int position;
        for (int i = 0; i < products.size(); i++) {
            product = products.get(i);
            position = i;
            while (position > 0 && (product.getPrice() > products.get(position - 1).getPrice())) {
                products.set(position, products.get(position - 1));
                position--;
            }
            products.set(position, product);
        }
    }


    @Override
    public List<Product> readFile(String path) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            products = (List<Product>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (EOFException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void writeFile(String path, List<Product> products) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(products);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



