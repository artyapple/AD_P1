
public interface HAWList<T> {

	/**
	 * inserts the specified element at the specified position in this list.
	 * @param pos
	 * @param elem
	 * @ensure find(elem).equals(pos)
	 * @throw IndexOutOfBoundsException 
	 *         if the pos is out of range (index < 0 || index > size())
	 */
	public void insert(int pos, T elem);

	/**
	 * removes the element at the specified position in this list
	 * @param pos
	 * @ensure element at position pos is deleted
     * @throw IndexOutOfBoundsException 
     *         if the pos is out of range (index < 0 || index > size())
	 */
	public void delete(int pos);

	/**
	 * removes the element with the specified key in this list
	 * @param key
	 */
	public void delete(); //TODO param

	/**
	 * finds the element with the specified key in this list
	 * @param key
	 * @return the position of element in this list
	 * @ensure retrieve(find(elem)).equals(elem)
	 */
	public int find(); //TODO param

	/**
	 * returns the element at the specified position in this list.
	 * @param pos
	 * @return The element on Position pos
	 * @require pos >= 0 && pos < length
	 * @ensure find(retrieve(pos)).equals(pos)
	 */
	public T retrieve(int pos);

	/**
	 * joins two lists.
	 * @param otherList
	 * @return new list
	 * @require otherList != null
	 * @require otherList instance of IList<T>
	 */
	public HAWList<T> concat(HAWList<T> otherList);
}
