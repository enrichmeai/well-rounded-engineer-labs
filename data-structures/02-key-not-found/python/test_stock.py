from stock import SkuKey, Stock


def test_stock_adds_up_per_sku_and_size():
    s = Stock()
    s.add("tee", 42, 5)
    s.add("tee", 42, 3)
    s.add("tee", 44, 1)
    assert s.in_stock("tee", 42) == 8
    assert s.in_stock("tee", 44) == 1
    assert s.in_stock("hat", 1) == 0
    assert len(s.counts) == 2, "one entry per (sku, size)"


def test_equal_keys_have_equal_hashes():
    assert SkuKey("tee", 42) == SkuKey("tee", 42)
    assert hash(SkuKey("tee", 42)) == hash(SkuKey("tee", 42))
