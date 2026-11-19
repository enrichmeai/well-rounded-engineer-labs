"""Drill 05: the update that went back in time. Make test_profile.py pass."""

from dataclasses import dataclass


@dataclass
class Profile:
    email: str
    version: int


@dataclass(frozen=True)
class EmailChanged:
    email: str
    version: int


def apply(profile: Profile, update: EmailChanged) -> None:
    """Apply one update as it arrives from the event stream."""
    profile.email = update.email
    profile.version = update.version
