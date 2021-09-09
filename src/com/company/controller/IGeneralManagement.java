package com.company.controller;

public interface IGeneralManagement<T> {
    void addNew(T t);

    void clearAll();

    void displayAll();

    void removeById(String id);

    void updateById(String id, T t);

    int findById(String id);

}
