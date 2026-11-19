from prices import cheapest_three, with_tax


def test_results():
    assert with_tax([10.0, 20.0], 0.2) == [12.0, 24.0]
    assert cheapest_three([5.0, 1.0, 4.0, 2.0, 3.0]) == [1.0, 2.0, 3.0]


def test_the_callers_list_is_left_alone():
    basket = [10.0, 20.0, 5.0, 1.0]
    with_tax(basket, 0.2)
    cheapest_three(basket)
    assert basket == [10.0, 20.0, 5.0, 1.0], f"the caller's list was changed to {basket}"


def test_same_input_same_answer():
    basket = [10.0, 20.0]
    assert with_tax(basket, 0.2) == with_tax(basket, 0.2)
