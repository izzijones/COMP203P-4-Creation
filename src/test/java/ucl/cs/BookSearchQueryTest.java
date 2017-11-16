package ucl.cs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static ucl.cs.BookSearchQueryBuilder.queryForBooks;

import java.util.Arrays;
import java.util.List;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import ucl.cs.catalogues.BritishLibraryCatalogue;
import ucl.cs.catalogues.Catalogue;

public class BookSearchQueryTest {

  private static final List<Book> BOOKS = Arrays.asList(new Book("A Christmas Carol", "Charles Dickens", 1835));
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  Catalogue catalogue = context.mock(Catalogue.class);

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    context.checking(new Expectations() {{
      exactly(1).of(catalogue).searchFor("LASTNAME='dickens' "); will(returnValue(BOOKS));
    }});

    List<Book> books = queryForBooks().withSurname("dickens").build().execute(
        catalogue);

    assertThat(books, is(BOOKS));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    context.checking(new Expectations() {{
      exactly(1).of(catalogue).searchFor("FIRSTNAME='Jane' ");
    }});

    queryForBooks().withFirstname("Jane").build().execute(
        catalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    List<Book> books = queryForBooks().withTitleContaining("Two Cities")
        .build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books = queryForBooks().publishedBefore(1700).build().execute(
        BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books = queryForBooks().publishedAfter(1950).build().execute(
        BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books = queryForBooks().withSurname("dickens").publishedBefore(1840)
        .build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
