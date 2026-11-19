SAFE_HEADERS = {"user-agent", "content-type", "x-request-id"}
SAFE_FIELDS = {"order_id", "currency", "items"}


def _redact(values: dict, safe: set, case_insensitive: bool = False) -> dict:
    def allowed(name):
        return (name.lower() if case_insensitive else name) in safe
    return {name: (value if allowed(name) else "[redacted]") for name, value in values.items()}


def log_request(headers: dict, body: dict, write) -> None:
    """Write one line describing the request. Only allow-listed values appear; the rest are named but redacted."""
    write(f"request headers={_redact(headers, SAFE_HEADERS, True)} body={_redact(body, SAFE_FIELDS)}")
