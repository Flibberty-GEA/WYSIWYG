package ua.flibberty.wysiwyg.domain.item;

import ua.flibberty.wysiwyg.domain.Repo;

import java.util.List;

public interface ItemRepo extends Repo<Item> {
    List<Item> getAllItems();
    List<Item> findItemByTitle(String title);
}
