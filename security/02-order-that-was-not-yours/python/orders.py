class NotFound(Exception):
    pass


def get_order(store: dict, user: str, order_id: int) -> dict:
    """The order with this ID, for the signed-in user."""
    order = store.get(order_id)
    if order is None:
        raise NotFound(f"no order {order_id}")
    return order


def cancel_order(store: dict, user: str, order_id: int) -> None:
    order = store.get(order_id)
    if order is None:
        raise NotFound(f"no order {order_id}")
    order["status"] = "cancelled"
