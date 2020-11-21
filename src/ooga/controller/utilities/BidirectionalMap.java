package ooga.controller.utilities;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ooga.model.GamePiece;
import ooga.view.Updatable;

/**
 * Our custom implementation of a Biderectional Map class that extends the collections interface...
 * We used AbstractCollection so we didn't have to override a bunch of methods that we didn't use
 *
 * @param <K>
 * @param <V>
 */
public class BidirectionalMap<K extends GamePiece, V extends Updatable> extends AbstractCollection {

  Map<K, V> backToFront;
  Map<V, K> frontToBack;

  /**
   * Initialize both maps
   */
  public BidirectionalMap() {
    backToFront = new HashMap<>();
    frontToBack = new HashMap<>();
  }

  private void putHelper(K backEndItem, V frontEndItem) {
    backToFront.put(backEndItem, frontEndItem);
    frontToBack.put(frontEndItem, backEndItem);
  }

  /**
   * Creates map from <Back,Front> declaration
   *
   * @param backEndItem
   * @param frontEndItem
   */
  public void put(K backEndItem, V frontEndItem) {
    putHelper(backEndItem, frontEndItem);
  }

  /**
   * Creates map from <Front,Back> declaration
   *
   * @param frontEndItem
   * @param backEndItem
   */
  public void put(V frontEndItem, K backEndItem) {
    putHelper(backEndItem, frontEndItem);
  }

  /**
   * Gets the proper item -> Gets from back end map
   *
   * @param backEndItem
   * @return
   */
  public V get(K backEndItem) {
    return backToFront.get(backEndItem);
  }

  /**
   * Gets the proper item -> gets from front end map
   *
   * @param frontEndItem
   * @return
   */
  public K get(V frontEndItem) {
    return frontToBack.get(frontEndItem);
  }

  @Override
  public int size() {
    return backToFront.keySet().size();
  }

  @Override
  public boolean isEmpty() {
    return backToFront.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return backToFront.containsKey(o) || frontToBack.containsKey(o);
  }

  /**
   * Only needs iterator for back
   *
   * @return
   */
  @Override
  public Iterator iterator() {
    return backToFront.keySet().iterator();
  }

  @Override
  public boolean remove(Object o) {
    if (backToFront.containsKey(o)) {
      frontToBack.remove(backToFront.get(o));
      backToFront.remove(o);
      return true;
    } else if (frontToBack.containsKey(o)) {
      backToFront.remove(frontToBack.get(o));
      frontToBack.remove(o);
      return true;
    }

    return false;
  }

  @Override
  public void clear() {
    backToFront.clear();
    frontToBack.clear();
  }

  @Override
  public boolean removeAll(Collection c) {
    int size = backToFront.size();
    for (Object o : c) {
      remove(o);
    }
    return size != backToFront.size();
  }

}
