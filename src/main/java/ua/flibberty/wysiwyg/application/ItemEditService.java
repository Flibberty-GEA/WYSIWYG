package ua.flibberty.wysiwyg.application;


import ua.flibberty.wysiwyg.domain.item.Item;

import java.util.List;

public interface ItemEditService {

  Item createItem(String title, String content);

  void quickAddItem(String title);

}
