package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItemListByCategoryId(Long categoryId);

    Item getItemById(Long itemId);
}