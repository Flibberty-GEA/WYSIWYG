package ua.flibberty.wysiwyg.domain.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"title", "author"})
//@ToString(exclude = {"summary", "tags"})

@Entity
public class Item {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(length = 10000)
  private String content;
}
