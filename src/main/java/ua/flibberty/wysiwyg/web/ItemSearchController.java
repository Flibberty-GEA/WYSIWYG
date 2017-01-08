package ua.flibberty.wysiwyg.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.flibberty.wysiwyg.application.EntityNotFoundException;
import ua.flibberty.wysiwyg.application.ItemEditService;
import ua.flibberty.wysiwyg.application.ItemSearchService;
import ua.flibberty.wysiwyg.domain.item.Item;


import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/items")
public class ItemSearchController {

  @Inject
  private ItemSearchService itemSearchService;

  @Inject
  private ItemEditService itemEditService;

  /* ВЫВОД ВСЕХ НОВОСТЕЙ */
  @RequestMapping(path = "/allItems", method = RequestMethod.GET)
  public String getAllItems(Map<String, Object> model) {
    final List<Item> items = itemSearchService.findAllItems();
    model.put("items", items);
    return "all_items";
  }

  //рекомендованные
  @RequestMapping(path = "/recommendations", method = RequestMethod.GET)
  public String getRecommendations(Map<String, Object> model) {
    final List<Item> items = itemSearchService.findAllItems();
    model.put("items", items);
    return "items";
  }

  /* ПРОСМОТР КОНКРЕТНОЙ НОВОСТИ */
  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public ModelAndView getItem(@PathVariable("id") Long id) throws EntityNotFoundException {
//    SecurityContextHolder.getContext().getAuthentication();
    final Item item = itemSearchService.getItem(id);
    return new ModelAndView("item_view", "item", item);
  }
//  //по тэгу
//  @RequestMapping(path = "/tag/{name}", method = RequestMethod.GET)
//  public String getItemByTag(@PathVariable("name") String tag, Map<String, Object> model) throws EntityNotFoundException {
//    final List<Item> items = itemSearchService.findItemsForTag(tag);
//    model.put("items", items);
//    return "search";
//  }
//  //по юзеру
//  @RequestMapping(path = "/byuser/{name}", method = RequestMethod.GET)
//  public String getItemByUser(@PathVariable("name") String username, Map<String, Object> model) throws EntityNotFoundException {
//    final List<Item> items = itemSearchService.findItemsForUsername(username);
//    model.put("items", items);
//    return "search";
//  }
  //в результате поиска
  @RequestMapping(path = "/search", method = RequestMethod.GET)
  public String getSearchItem(@RequestParam("title") String title, Map<String, Object> model) throws EntityNotFoundException {
    final List<Item> items = itemSearchService.findItemByTitle(title);
    model.put("items", items);
    return "search";
  }

  @RequestMapping(path = "items/{id}", method = RequestMethod.POST)
  public String updateItemAdmin(@ModelAttribute Item item) throws EntityNotFoundException {
    itemSearchService.updateItemInfo(item);
    return "redirect:/items/allItems";
  }

  /*СОЗДАНИЕ НОВОСТИ*/
  @RequestMapping(path = "/create", method = RequestMethod.POST)
  public String updateItem(@ModelAttribute Item item) throws EntityNotFoundException {
//    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    itemEditService.createItem(item.getTitle(), item.getContent());
    return "redirect:/items/allItems";
  }
  @RequestMapping(path = "/create", method = RequestMethod.GET)
  public String createItem(Map<String, Object> model) throws EntityNotFoundException {
    final Item item = new Item();
    model.put("item", item);
    return "create";
  }

  @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
  public ModelAndView edit(@PathVariable("id") Long id) throws EntityNotFoundException {
    final Item item = itemSearchService.getItem(id);
    return new ModelAndView("item_edit", "item", item);
  }
}
