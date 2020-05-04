//This is my implementation of a SplayTreeMap, which implements the TreeMap ADT, and is based off a BalanceableBinaryTree.
//It overrides the TreeMap balancing functions and uses splay to decide when to rotate correctly.

package projectCode20280;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplayTreeMap<K,V> extends TreeMap<K,V> {

    protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();


	  /** Constructs an empty map using the natural ordering of keys. */
	  public SplayTreeMap() { super(); }

	  /**
	   * Constructs an empty map using the given comparator to order keys.
	   * @param comp comparator defining the order of keys in the map
	   */
	  public SplayTreeMap(Comparator<K> comp) { super(comp); }

	  /** Utility used to rebalance after a map operation. */
	  private void splay(Position<Entry<K,V>> child) {
	  	while(!isRoot(child)) {

			Position<Entry<K,V>> parent = parent(child);
			Position<Entry<K,V>> grandparent = parent(parent);

			if(grandparent==null) {
	  			rotate(child);
			} else if((parent==left(grandparent)) == (child==left(parent))) {
	  			rotate(parent);
	  			rotate(child);
			} else {
	  			rotate(child);
	  			rotate(child);
			}
		}
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after a node access. */
	  @Override
	  protected void rebalanceAccess(Position<Entry<K,V>> p) {
	  	if(isExternal(p)) {
	  		p = parent(p);
		}
	  	if(p!=null) {
	  		splay(p);
		}
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
	  @Override
	  protected void rebalanceInsert(Position<Entry<K,V>> p) {
	  	splay(p);
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
	  @Override
	  protected void rebalanceDelete(Position<Entry<K,V>> p) {
	  	if(!isRoot(p)) {
	  		splay(parent(p));
		}
	  }

	public static void main(String[] args) {
		SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
		Integer[] arr = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};

		for(Integer i : arr) {
			map.put(i, Integer.toString(i));
		}
		map.get(15);
		map.get(24);
		map.get(-1);
	}
}