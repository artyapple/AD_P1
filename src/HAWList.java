
public interface HAWList<T> {

	/**
	 * inserts the specified element at the specified position in this list.
	 * @param pos
	 * @param elem
	 */
	public void insert(int pos, T elem);

	/**
	 * removes the element at the specified position in this list
	 * @param pos
	 */
	public void delete(int pos);

	/**
	 * removes the element with the specified key in this list
	 * @param elem
	 */
	public void delete();

	/**
	 * finds the element with the specified key in this list
	 * @return
	 */
	public int find();

	/**
	 * returns the element at the specified position in this list.
	 * @param pos
	 * @return
	 */
	public T retrieve(int pos);

	/**
	 * joins two lists.
	 * @param list
	 */
	public HAWList<T> concat(HAWList<T> list);
}
