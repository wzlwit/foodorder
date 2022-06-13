package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.model.Item;
import jac.fsd02.foodorder.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> getItemListByCategoryId(Long categoryId){
        List<Item> itemList = itemRepository.findByCategoryId(categoryId);
        return itemList;
    }

    @Override
    public Item getItemById(Long itemId) {
        Optional<Item> optItem = itemRepository.findById(itemId);
        Item item;
        if (optItem.isPresent()) {
            item = optItem.get();
        } else {
            item = new Item();
        }
        return item;
    }

}
