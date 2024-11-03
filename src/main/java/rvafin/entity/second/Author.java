package rvafin.entity.second;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author", schema = "test")
@Data
@EqualsAndHashCode(of = "name")
@ToString(exclude = "books")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraphs(
        @NamedEntityGraph(name = "removeBooks",
                attributeNodes = @NamedAttributeNode("books"))
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "author")
    @Builder.Default
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }
}
