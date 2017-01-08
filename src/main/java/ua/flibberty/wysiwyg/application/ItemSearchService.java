package ua.flibberty.wysiwyg.application;

import ua.flibberty.wysiwyg.domain.item.Item;

import java.util.List;

public interface ItemSearchService {

  List<Item> findAllItems();

  List<Item> findItemByTitle(String title); //+

  Item getItem(Long id) throws EntityNotFoundException; //+

  Item updateItemInfo(Item item) throws EntityNotFoundException; //+
}
