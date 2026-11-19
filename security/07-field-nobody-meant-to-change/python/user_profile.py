from dataclasses import dataclass


@dataclass
class User:
    id: int
    email: str
    display_name: str
    is_admin: bool = False
    credit_pence: int = 0


def update_profile(user: User, changes: dict) -> User:
    """Apply the profile form's changes to the user."""
    for field, value in changes.items():
        setattr(user, field, value)
    return user
