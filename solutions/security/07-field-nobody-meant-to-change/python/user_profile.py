from dataclasses import dataclass

EDITABLE = {"display_name", "email"}      # what a customer may change about themselves, and nothing else


@dataclass
class User:
    id: int
    email: str
    display_name: str
    is_admin: bool = False
    credit_pence: int = 0


def update_profile(user: User, changes: dict) -> User:
    """Apply the profile form's changes. Any field outside the allow-list refuses the whole request."""
    refused = set(changes) - EDITABLE
    if refused:
        raise ValueError(f"fields not editable: {sorted(refused)}")
    for field, value in changes.items():
        setattr(user, field, value)
    return user
