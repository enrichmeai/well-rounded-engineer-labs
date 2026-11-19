public class ReviewsTest {
    public static void main(String[] args) {
        Check.equal("a normal review is unchanged", "<div class=\"review\"><b>Ana</b><p>Great boots</p></div>",
                Reviews.render("Ana", "Great boots"));
        String script = Reviews.render("Ana", "<script>alert(1)</script>");
        Check.isTrue("a script is not left as markup", !script.contains("<script>"));
        Check.isTrue("a script is shown as text", script.contains("&lt;script&gt;alert(1)&lt;/script&gt;"));
        String name = Reviews.render("\"><img src=x onerror=alert(1)>", "ok");
        Check.isTrue("the name is escaped too", !name.contains("<img")
                && name.contains("&quot;&gt;&lt;img src=x onerror=alert(1)&gt;"));
        Check.isTrue("ampersands and quotes", Reviews.render("Ana", "Fish & chips, 'crispy'")
                .contains("<p>Fish &amp; chips, &#x27;crispy&#x27;</p>"));
        Check.done();
    }
}
