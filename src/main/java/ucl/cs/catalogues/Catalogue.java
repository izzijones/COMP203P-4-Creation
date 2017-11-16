package ucl.cs.catalogues;

import java.util.List;
import ucl.cs.Book;

public interface  Catalogue {

  List<Book> searchFor(String query);
}
