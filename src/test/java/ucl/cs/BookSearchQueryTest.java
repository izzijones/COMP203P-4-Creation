package ucl.cs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static ucl.cs.BookSearchQueryBuilder.*;

import java.util.List;
import org.junit.Test;
import ucl.cs.catalogues.BritishLibraryCatalogue;

public class BookSearchQueryTest {

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    List<Book> books =  query().withLastName("dickens").build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    List<Book> books = query().withFirstName("Jane").build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    List<Book> books = query().withTitle("Two Cities").build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books = query().withPublishedBefore(1700).build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books = query().withPublishedAfter(1950).build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books = query().withLastName("dickens").withPublishedBefore(1840).build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
