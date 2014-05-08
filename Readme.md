HotCache
=================

HotCache is a simple implementation of LRU Cache algorithm.

## API

### HotCache cache = new HotCache<Type>(cacheSize);

Create an new cache with `cacheSize` capacity. The cache should be threadsafe.

### (void) cache.write(key, value);

Write a value to cache. Will remove the least-recently-used value if meets the capacity.

### (Type) cache.read(key, value);

Read a value from cache. Returns `null` if the value not found. Move the value up to the most-recently-used if found.

### (Type) cache.fetch(key, callable);

Read a value from cache. Execute the `callable` block if not found and set to the returned value.
