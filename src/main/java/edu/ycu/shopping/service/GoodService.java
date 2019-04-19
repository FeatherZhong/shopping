package edu.ycu.shopping.service;

import edu.ycu.shopping.entity.Good;

import java.util.List;

public interface GoodService {
    List<Good> search(String keyword);
}
