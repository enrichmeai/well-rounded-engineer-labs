import threading

import flight


def test_one_booking():
    f = flight.Flight(1)
    assert f.book("Ana") is True
    assert f.book("Ben") is False
    assert f.seats_left == 0


def test_forty_people_ten_seats():
    f = flight.Flight(10)
    start = threading.Barrier(40)
    results = []

    def press_book(i):
        start.wait()                      # everyone presses at the same moment
        results.append(f.book(f"passenger-{i}"))

    threads = [threading.Thread(target=press_book, args=(i,)) for i in range(40)]
    for t in threads:
        t.start()
    for t in threads:
        t.join()
    assert sum(results) == 10, f"sold {sum(results)} seats on a flight with 10"
    assert f.seats_left == 0
    assert len(f.passengers) == 10
