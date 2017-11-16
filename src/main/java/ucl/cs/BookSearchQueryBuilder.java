package ucl.cs;

public class BookSearchQueryBuilder {

  private String firstname = null;
  private String surname = null;
  private String title = null;
  private Integer startDate = null;
  private Integer endDate = null;

  private BookSearchQueryBuilder() {
  }

  public static BookSearchQueryBuilder queryForBooks() {
    return new BookSearchQueryBuilder();
  }

  public BookSearchQueryBuilder withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public BookSearchQueryBuilder withSurname(String surname) {
    this.surname = surname;
    return this;
  }

  public BookSearchQueryBuilder withTitleContaining(String title) {
    this.title = title;
    return this;
  }

  public BookSearchQueryBuilder publishedAfter(Integer startDate) {
    this.startDate = startDate;
    return this;
  }

  public BookSearchQueryBuilder publishedBefore(Integer endDate) {
    this.endDate = endDate;
    return this;
  }

  public BookSearchQuery build() {
    return new BookSearchQuery(firstname, surname, title, startDate, endDate);
  }
}