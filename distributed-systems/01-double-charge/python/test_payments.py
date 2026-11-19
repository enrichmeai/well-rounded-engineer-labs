from payments import PaymentService


def test_a_retry_with_the_same_key_charges_once():
    service = PaymentService()
    first = service.charge("key-order-7", "order-7", 100)
    # the reply to the first call was lost; the app retries with the same key
    second = service.charge("key-order-7", "order-7", 100)
    assert service.total_charged() == 100, "the customer was charged twice"
    assert first == second, "a retry should get the original receipt back"


def test_a_new_key_is_a_new_charge():
    service = PaymentService()
    service.charge("key-order-7", "order-7", 100)
    service.charge("key-order-8", "order-8", 40)
    assert service.total_charged() == 140


def test_many_retries():
    service = PaymentService()
    receipts = {service.charge("key-order-9", "order-9", 25) for _ in range(5)}
    assert service.total_charged() == 25 and len(receipts) == 1
