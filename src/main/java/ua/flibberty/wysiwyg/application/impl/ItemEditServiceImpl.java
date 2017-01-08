package ua.flibberty.wysiwyg.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.flibberty.wysiwyg.application.ItemEditService;
import ua.flibberty.wysiwyg.domain.item.Item;
import ua.flibberty.wysiwyg.domain.item.ItemRepo;

import javax.inject.Inject;


@Service
@Transactional
public class ItemEditServiceImpl implements ItemEditService {

  @Inject
  private ItemRepo itemRepo;

  public Item createItem(String title, String content) {

    final Item item = new Item();
    item.setTitle(title);
    item.setContent(content);

    itemRepo.add(item);
    return item;
  }

  @Override
  public void quickAddItem(String title) {
  }
}
