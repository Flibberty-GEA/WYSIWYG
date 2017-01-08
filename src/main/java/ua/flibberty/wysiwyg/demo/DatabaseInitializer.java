package ua.flibberty.wysiwyg.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.flibberty.wysiwyg.domain.item.Item;
import ua.flibberty.wysiwyg.domain.item.ItemRepo;

import javax.inject.Inject;

@Slf4j
@Component
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

  //private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);


  @Inject
  private ItemRepo itemRepo;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info(" --- app ctx started!");
    initDb();
  }

  private void initDb() {
    initItems();
  }

  private void initItems() {
    final Item item1 = new Item();
    item1.setTitle("Title of item1 - кириллица");
    item1.setContent("Some content of item1");
    itemRepo.add(item1);

    final Item item2 = new Item();
    item2.setTitle("Title of item2");
    item2.setContent("Some content of item2");
    itemRepo.add(item2);

    final Item item3 = new Item();
    item3.setTitle("Title of item3");
    item3.setContent("Some content of item3");
    itemRepo.add(item3);
  }
}
