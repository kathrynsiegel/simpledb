package simpledb;

/** Unique identifier for BPlusTreeInternalPage, BPlusTreeLeafPage, BPlusTreeHeaderPage
 *  and BPlusTreeRootPtrPage objects. 
 */
public class BPlusTreePageId implements PageId {

	public final static int ROOT_PTR = 0;
	public final static int INTERNAL = 1;
	public final static int LEAF = 2;
	public final static int HEADER = 3;

	private final int tableId;
	private final int pgNo;
	private int pgcateg;

	/**
	 * Constructor. Create a page id structure for a specific page of a
	 * specific table.
	 *
	 * @param tableId The table that is being referenced
	 * @param pgNo The page number in that table.
	 * @param pgcateg which kind of page it is
	 */
	public BPlusTreePageId(int tableId, int pgNo, int pgcateg) {
		this.tableId = tableId;
		this.pgNo = pgNo;
		this.pgcateg = pgcateg;
	}

	/** @return the table associated with this PageId */
	public int getTableId() {
		return tableId;
	}

	/**
	 * @return the page number in the table getTableId() associated with
	 *   this PageId
	 */
	public int pageNumber() {
		return pgNo;
	}

	/**
	 * @return the category of this page
	 */
	public int pgcateg() {
		return pgcateg;
	}

	/**
	 * @return a hash code for this page, represented by the concatenation of
	 *   the table number, page number, and pgcateg (needed if a PageId is used as a
	 *   key in a hash table in the BufferPool, for example.)
	 * @see BufferPool
	 */
	public int hashCode() {
		int code = (tableId << 16) + (pgNo << 2) + pgcateg;
		return code;
	}

	/**
	 * Compares one PageId to another.
	 *
	 * @param o The object to compare against (must be a PageId)
	 * @return true if the objects are equal (e.g., page numbers, table
	 *   ids and pgcateg are the same)
	 */
	public boolean equals(Object o) {
		if (!(o instanceof BPlusTreePageId))
			return false;
		BPlusTreePageId p = (BPlusTreePageId)o;
		return tableId == p.tableId && pgNo == p.pgNo && pgcateg == p.pgcateg;
	}

	/**
	 *  Return a representation of this object as an array of
	 *  integers, for writing to disk.  Size of returned array must contain
	 *  number of integers that corresponds to number of args to one of the
	 *  constructors.
	 */
	public int[] serialize() {
		int data[] = new int[3];

		data[0] = tableId;
		data[1] = pgNo;
		data[2] = pgcateg;

		return data;
	}

}