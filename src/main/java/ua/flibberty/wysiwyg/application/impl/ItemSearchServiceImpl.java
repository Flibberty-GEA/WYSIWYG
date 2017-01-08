package ua.flibberty.wysiwyg.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.flibberty.wysiwyg.application.EntityNotFoundException;
import ua.flibberty.wysiwyg.application.ItemSearchService;
import ua.flibberty.wysiwyg.domain.item.Item;
import ua.flibberty.wysiwyg.domain.item.ItemRepo;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class ItemSearchServiceImpl implements ItemSearchService {

  @Inject
  private ItemRepo itemRepo;

  public Item getItem(Long id) throws EntityNotFoundException {
    return itemRepo.getById(id).orElseThrow(
        (() -> new EntityNotFoundException("Item with ID " + id + " not found")));
  }

  @Override
  public Item updateItemInfo(Item item) throws EntityNotFoundException {
    final Item origItem = getItem(item.getId());
    origItem.setTitle(item.getTitle());
    origItem.setContent(item.getContent());

    return origItem;
  }

  @Override
  public List<Item> findAllItems() {return itemRepo.getAllItems();}

  @Override
  public List<Item> findItemByTitle(String title) {return itemRepo.findItemByTitle(title);}

}
