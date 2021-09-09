package com.company.controller;

import com.company.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagement implements IGeneralManagement<User>,IReadWriteFile<User> {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public void addNew(User user) {
        users.add(user);
    }

    @Override
    public void clearAll() {
        users.clear();

    }

    @Override
    public void displayAll() {
        for (User user : users) {
            if (!user.getRole().equals("admin"))
                System.out.println(user);
        }
    }

    @Override
    public void removeById(String id) {
        int index = findById(id);
        if (index != -1) {
            users.remove(index);
        } else {
            System.out.println("Không có user này");
        }
    }

    @Override
    public void updateById(String id, User user) {
        int index = findById(id);
        if (index != -1) {
            users.set(index, user);
        } else {
            System.out.println("Không có user này");
        }
    }

    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int findByName(String name) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public User isLogin(User user) {
        for (User user1 : users) {
            if (user.getUsername().equals(user1.getUsername())
                    && user.getPassword().equals(user1.getPassword())) {
                return user1;
            }
        }
        return null;
    }

    @Override
    public List<User> readFile(String path) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            users = (List<User>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (EOFException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void writeFile(String path, List<User> users) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(users);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
