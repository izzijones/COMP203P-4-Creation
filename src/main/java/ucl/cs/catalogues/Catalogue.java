package ucl.cs.catalogues;

import ucl.cs.Book;

import java.util.List;

public interface Catalogue {

    List<Book> searchFor(String query);
}
