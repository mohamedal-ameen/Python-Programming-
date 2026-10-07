class ListNode:
    def __init__(self, key):
        self.key = key
        self.next = None

class MyHashSet:

    def __init__(self):
        # Choose a prime number for the number of buckets to minimize collisions
        self.num_buckets = 769
        self.buckets = [ListNode(0) for _ in range(self.num_buckets)]

    def _hash(self, key: int) -> int:
        return key % self.num_buckets

    def add(self, key: int) -> None:
        bucket_index = self._hash(key)
        curr = self.buckets[bucket_index]
        
        # Traverse the bucket to check if key already exists
        while curr.next:
            if curr.next.key == key:
                return
            curr = curr.next
        
        # Key not found, append new node at the end
        curr.next = ListNode(key)

    def remove(self, key: int) -> None:
        bucket_index = self._hash(key)
        curr = self.buckets[bucket_index]
        
        # Traverse to find and remove the node
        while curr.next:
            if curr.next.key == key:
                curr.next = curr.next.next
                return
            curr = curr.next

    def contains(self, key: int) -> bool:
        bucket_index = self._hash(key)
        curr = self.buckets[bucket_index]
        
        # Traverse to check existence
        while curr.next:
            if curr.next.key == key:
                return True
            curr = curr.next
            
        return False