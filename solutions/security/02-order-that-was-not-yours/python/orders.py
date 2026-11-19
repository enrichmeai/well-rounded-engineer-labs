class NotFound(Exception):
    pass


def _order_for(store: dict, user: str, order_id: int) -> dict:
    """One place that fetches an order AND checks it belongs to the caller."""
    order = store.get(order_id)
    if order is None or order["owner"] != user:
        raise NotFound(f"no order {order_id}")   # the same answer: do not reveal that it exists
    return order


def get_order(store: dict, user: str, order_id: int) -> dict:
    return _order_for(store, user, order_id)


def cancel_order(store: dict, user: str, order_id: int) -> None:
    _order_for(store, user, order_id)["status"] = "cancelled"
