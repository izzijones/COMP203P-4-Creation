package ucl.cs;



public class BookSearchQueryBuilder {
    private String name1 = null;
    private String name2 = null;
    private String title = null;
    private Integer date1 = null;
    private Integer date2 = null;

    private BookSearchQueryBuilder(){}

    public static BookSearchQueryBuilder query(){
        return new BookSearchQueryBuilder();
    }

    public BookSearchQueryBuilder withFirstName(String name1){
        this.name1 = name1;
        return this;
    }

    public BookSearchQueryBuilder withLastName(String name2){
        this.name2 = name2;
        return this;
    }

    public BookSearchQueryBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    public BookSearchQueryBuilder withPublishedAfter(Integer date1){
        this.date1 = date1;
        return this;
    }

    public BookSearchQueryBuilder withPublishedBefore(Integer date2){
        this.date2 = date2;
        return this;
    }

    public BookSearchQuery build(){
        return new BookSearchQuery(name1,name2,title,date1,date2);
    }
}
