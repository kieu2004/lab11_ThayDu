package ex11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BNode<E extends Comparable<E>> {
	private E data;
	private BNode<E> left;
	private BNode<E> right;

	public BNode(E data, BNode<E> left, BNode<E> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public BNode(E data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public void add(E e) {
		int comp = e.compareTo(data);
		if (comp < 0) {
			if (left == null) {
				left = new BNode<E>(e);
			}

			left.add(e);
		} else if (comp > 0) {
			if (right == null) {
				right = new BNode<E>(e);
			}

			right.add(e);
		}
	}

	public int depth(E node) {
		int comp = node.compareTo(data);
		int count = 0;
		if (comp == 0) {
			return count;
		} else if (comp < 0) {
			if (left != null) {
				count++;
				return count + left.depth(node);
			}

		} else if (comp > 0) {
			if (right != null) {
				count++;
				return count + right.depth(node);
			}

		}
		return -1;

	}

	public int height() {
		int leftHeight = (left == null) ? -1 : left.height();
		int rightHeight = (right == null) ? -1 : right.height();
		return 1 + Math.max(leftHeight, rightHeight);
	}

	// Cach 1
//	public int size() {
//		int leftSize = (left == null) ? 0 : left.size();
//		int rightSize = (right == null) ? 0 : right.size();
//		return 1 + leftSize + rightSize;
//
//	}

	// Cach 2
	public int size() {
		return (int) (Math.pow(2, this.height() + 1) - 1);
	}

	public boolean contains(E e) {
		int comp = e.compareTo(data);
		if (comp == 0)
			return true;
		else if (comp < 0) {
			return (left == null) ? false : left.contains(e);
		} else {
			return (right == null) ? false : right.contains(e);
		}
	}

	public E findMin() {
		if (left == null) {
			return data;
		} else {
			return left.findMin();
		}

	}

	public E findMax() {
		if (right == null) {
			return data;
		}
		return right.findMax();
	}

	// three different cases
	public boolean remove(E e) {
		int comp = e.compareTo(data);
		if (comp == 0) {
			if (left == null && right == null) {
				data=null;
				return true;
			} else if (left == null) {
				data = right.data;
				right.remove(right.data);
				return true;
			} else if (right == null) {
				data = left.data;
				left.remove(left.data);
				return true;
			} else {
				E value = right.findMin();
				data = value;
				return right.remove(value);
			}
		} else if (comp > 0 && right != null) {
			if (right.data == null) {
				return false;
			}
			return right.remove(e);
		} else if (comp < 0 && left != null) {
			if (left.data == null) {
				return false;
			}
			return left.remove(e);
		}

		return false;
	}

	public List<E> descendants(E node) {
		int comp = node.compareTo(data);
		List<E> list = new ArrayList<E>();

		if (comp == 0) {
			if (left != null) {
				list.add(left.data);
				descendants(left.data);
				list.addAll(left.descendants(left.data));
			}

			if (right != null) {
				list.add(right.data);
				descendants(right.data);
				list.addAll(right.descendants(right.data));
			}
		} else if (comp > 0) {
			if (right != null) {
				right.descendants(right.data);
				list.addAll(right.descendants(right.data));
			}
		} else {
			if (left != null) {
				left.descendants(left.data);
				list.addAll(left.descendants(left.data));
			}
		}
		return list;
	}

	public List<E> ancestors(E node) {
		List<E> list = new ArrayList<E>();
		if (data == null) {
			return Collections.emptyList();
		}

		if (data != null) {
			int comp = node.compareTo(data);
			if (comp == 0) {
				return list;
			} else if (comp > 0) {
				if (right != null) {
					list.add(data);
					list.addAll(right.ancestors(node));
				}
			} else {
				if (left != null) {
					list.add(data);
					list.addAll(left.ancestors(node));
				}
			}
		}
		return list;
	}

	public void inorder() {
		if (left != null) {
			left.inorder();
		}

		System.out.print(data + " ");

		if (right != null) {
			right.inorder();
		}
	}

	public void preorder() {
		System.out.print(data + " ");

		if (left != null) {
			left.preorder();
		}

		if (right != null) {
			right.preorder();
		}

	}

	public void postorder() {
		if (left != null) {
			left.postorder();
		}

		if (right != null) {
			right.postorder();
		}
		
		System.out.print(data + " ");
		
	}

}
