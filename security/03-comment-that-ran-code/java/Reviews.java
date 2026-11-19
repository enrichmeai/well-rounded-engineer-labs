public class Reviews {
    /** One review, as HTML. */
    static String render(String author, String text) {
        return "<div class=\"review\"><b>" + author + "</b><p>" + text + "</p></div>";
    }
}
