"""Drill 05, solved: an update is applied only if it is newer than what the profile already has."""

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
    if update.version <= profile.version:     # old news, or a duplicate
        return
    profile.email = update.email
    profile.version = update.version
