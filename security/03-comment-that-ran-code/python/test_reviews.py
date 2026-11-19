from reviews import render_review


def test_a_normal_review_is_unchanged():
    assert render_review("Ana", "Great boots") == '<div class="review"><b>Ana</b><p>Great boots</p></div>'


def test_a_script_is_shown_not_run():
    out = render_review("Ana", "<script>alert(1)</script>")
    assert "<script>" not in out
    assert "&lt;script&gt;alert(1)&lt;/script&gt;" in out


def test_the_name_is_escaped_too():
    out = render_review('"><img src=x onerror=alert(1)>', "ok")
    assert "<img" not in out
    assert "&quot;&gt;&lt;img src=x onerror=alert(1)&gt;" in out


def test_ampersands_and_quotes():
    out = render_review("Ana", "Fish & chips, 'crispy'")
    assert "<p>Fish &amp; chips, &#x27;crispy&#x27;</p>" in out
