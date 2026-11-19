def log_request(headers: dict, body: dict, write) -> None:
    """Write one line describing the request, to help debug checkout problems."""
    write(f"request headers={headers} body={body}")
