package ua.flibberty.wysiwyg.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ua.flibberty.wysiwyg.domain.Repo;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public abstract class GenericRepo<T> implements Repo<T> {

  private final Class<T> clazz;

  @Inject
  protected SessionFactory sessionFactory;

  protected GenericRepo(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public Optional<T> getById(Long id) {
    return Optional.ofNullable(sessionFactory.getCurrentSession().get(clazz, id));
  }

  @Override
  public List<T> getAll() {
    return Util.castList(
        sessionFactory.getCurrentSession()
            .createCriteria(clazz)
            .list()
    );
  }

  @Override
  public Optional<T> findOneByField(String field, String value) {
    return Optional.ofNullable(Util.cast(
        sessionFactory.getCurrentSession()
            .createCriteria(clazz)
            .add(Restrictions.eq(field, value))
            .uniqueResult()
    ));
  }

  @Override
  public List<T> findWhereFieldLike(String field, String value) {
    return Util.castList(
        sessionFactory.getCurrentSession()
            .createCriteria(clazz)
            .add(Restrictions.ilike(field, "%" + value + "%"))
            .list()
    );
  }

  @Override
  public void add(T t) {
    sessionFactory.getCurrentSession().save(t);
  }
}
