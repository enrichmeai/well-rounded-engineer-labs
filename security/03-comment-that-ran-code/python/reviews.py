def render_review(author: str, text: str) -> str:
    """One review, as HTML."""
    return f'<div class="review"><b>{author}</b><p>{text}</p></div>'
