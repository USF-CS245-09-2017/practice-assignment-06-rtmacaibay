/**
 * Creates a binary heap which utilizes an array
 * @author Robert
 *
 */

public class BinaryHeap {
	//private global variables
	private int[] a;
	private int size;
	
	/**
	 * constructor
	 */
	public BinaryHeap() {
		//initialize
		a = new int[10];
		size = 0;
	}
	
	/**
	 * adds a value (could be something that signifies priority) into the heap
	 * @param priority - value to be added
	 */
	public void add(int priority) {
		//check if we should grow the heap
		if (a.length == size)
			grow_heap();
		//add the value at the next available space
		a[size++] = priority;
		//keep track of the index of where the new value is at
		int index = size - 1;
		
		//check if the parent of the new value is greater and that the index isnt out of bounds
		while (index > 0 && a[index] < a[parent(index)]) {
			//if the parent is greater bc priority things
			swap(index, parent(index));
			//keep track of where the new value is at
			index = parent(index);
		}
	}
	
	/**
	 * removes the first element in the array because it has the biggest priority (like a dequeue)
	 * @return int - the value that was in the front of the array that was removed
	 */
	public int remove() {
		//save a copy of the value to be removed
		int temp = a[0];
		//make the first spot equal the last available element 
		a[0] = a[--size];
		//bubble down the elements
		bubble_down(0);
		//return the removed value
		return temp;
	}
	
	/**
	 * corrects the position of all the element such that the first element is bigger than all and that
	 * the parents are bigger than their children
	 * @param i - index from which we are "bubbling down"
	 */
	private void bubble_down(int i) {
		//check if the index of the left child is less than the size
		if (left_child(i) < size) {
			//find the index of the left child
			int child = left_child(i);
			//check if the index of the right child is less than the size and that the right child is less than the left child
			if(right_child(i) < size && a[right_child(i)] < a[child])
				//if so, we want the index of the child to be the right child index
				child = right_child(i);
			//check if the parent is greater than the child
			if(a[i] > a[child]) {
				//swap bc priority
				swap(i, child);
				//bubble down the child to its right spot
				bubble_down(child);
			}
		}
	}
	
	/**
	 * parent of said index, which is calculated
	 * @param i - index
	 * @return int - parent of index
	 */
	private int parent(int i) {
		return (i - 1) / 2;
	}
	
	/**
	 * left child of said index, which is calculated
	 * @param i - index
	 * @return int - left child of index
	 */
	private int left_child(int i) {
		return i * 2 + 1;
	}
	
	/**
	 * right child of said index, which is calculated
	 * @param i - index
	 * @return int - right child of said index
	 */
	private int right_child(int i) {
		return i * 2 + 2;
	}
	
	/**
	 * normal swap method of two positions
	 * @param i - first index
	 * @param j - second index
	 */
	private void swap(int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	/**
	 * grows the heap AKA the array
	 */
	private void grow_heap() {
		int[] temp = new int[a.length * 2];
		System.arraycopy(a, 0, temp, 0, a.length);
		a = temp;
	}
}
