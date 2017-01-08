package ua.flibberty.wysiwyg.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;
import ua.flibberty.wysiwyg.domain.item.Item;
import ua.flibberty.wysiwyg.domain.item.ItemRepo;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepoImpl extends GenericRepo<Item> implements ItemRepo {

  public ItemRepoImpl() {
    super(Item.class);
  }

  @Inject
  private SessionFactory session;


  @Override
  public List<Item> getAllItems(){/*return getAll();*/

    return Util.castList(
            session.getCurrentSession()
                    .createQuery("FROM Item")
                    .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                    .list());
  }

  @Override
  public List<Item> findItemByTitle (String title) {
    // todo: check this for n+1 problem
            return Util.castList(
            session.getCurrentSession()
                    .createQuery("FROM Item i where i.title LIKE :title")
                    .setParameter("title", "%" + title + "%")
                    .list());
  }

}
