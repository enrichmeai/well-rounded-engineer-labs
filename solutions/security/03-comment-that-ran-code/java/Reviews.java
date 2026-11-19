public class Reviews {
    /** Escape text for an HTML element or a quoted attribute. & goes first. */
    static String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#x27;");
    }

    /** One review, as HTML. Anything from a user is escaped where it enters the HTML. */
    static String render(String author, String text) {
        return "<div class=\"review\"><b>" + escape(author) + "</b><p>" + escape(text) + "</p></div>";
    }
}
