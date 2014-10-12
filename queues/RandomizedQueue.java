import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size;
	private Item[] rqueue;

	public RandomizedQueue() {
		size = 0;
		rqueue = (Item[]) new Object[2];
	} // construct an empty randomized queue

	public boolean isEmpty() {
		return size == 0;
	} // is the queue empty?

	public int size() {
		return size;
	} // return the number of items on the queue

	private void resize(int capcity) {
		Item[] temp = (Item[]) new Object[capcity];
		for (int i = 0; i < size; i++) {
			temp[i] = rqueue[i];
		}
		rqueue = temp;
	}

	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		if (size == rqueue.length)
			resize(2 * rqueue.length);
		rqueue[size++] = item;
	} // add the item

	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int index = StdRandom.uniform(size());
		Item item = rqueue[index];
		rqueue[index] = rqueue[--size];
		rqueue[size] = null;
		if (size > 0 && size == rqueue.length / 4)
			resize(rqueue.length / 2);
		return item;
	} // delete and return a random item

	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int index = StdRandom.uniform(size());
		return rqueue[index];
	} // return (but do not delete) a random item

	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator<Item>();
	} // return an independent iterator over items in random order

	private class RandomizedQueueIterator<Item> implements Iterator<Item> {
		public boolean hasNext() {
			return size() > 0;
		}

		public Item next() {
			if (isEmpty()) {
				throw new java.util.NoSuchElementException();
			}
			return (Item) dequeue();
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {

	} // unit testing
}
