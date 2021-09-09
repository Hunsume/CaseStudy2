package com.company.controller;

import java.util.List;

public interface IReadWriteFile<T> {

    List<T> readFile(String path);

    void writeFile(String path, List<T> ts);
}
