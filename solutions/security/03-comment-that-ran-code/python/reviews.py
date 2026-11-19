import html


def render_review(author: str, text: str) -> str:
    """One review, as HTML. Anything from a user is escaped where it enters the HTML."""
    return f'<div class="review"><b>{html.escape(author)}</b><p>{html.escape(text)}</p></div>'
