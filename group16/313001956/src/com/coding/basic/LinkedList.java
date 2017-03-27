package com.coding.basic;

public class LinkedList implements List {

	private Node head;
	private int size;

	public LinkedList() {

	}

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Խ�磡");
		}
		if (head == null) {
			head = new Node();
			head.data = o;
		} else {

			Node n = getNodebyIndex(index);

			Node newnode = new Node();
			newnode.data = o;
			newnode.next = n.next;
			n.next = newnode;
		}
		size++;
	}

	public Object get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Խ�磡");
		}
		if (head == null)
			return null;
		Node n = getNodebyIndex(index);
		return n.data;
	}

	public Object remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Խ�磡");
		}
		Node n = getNodebyIndex(index - 1);
		Object o = n.next.data;
		n.next = n.next.next;
		size--;
		return o;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		if (head == null) {
			head = new Node();
			head.data = o;
		} else {
			Node newnode = new Node();
			newnode.data = o;
			newnode.next = head;
			head = newnode;
		}
		size++;
	}

	public void addLast(Object o) {
		if (head == null) {
			head = new Node();
			head.data = o;
		} else {
			Node n = getNodebyIndex(size);
			Node newnode = new Node();
			newnode.data = o;
			n.next = newnode;
		}
		size++;
	}

	public Object removeFirst() {
		if (head == null)
			return null;

		head = head.next;
		size--;
		return head.data;
	}

	public Object removeLast() {
		if (head == null)
			return null;
		Node n = getNodebyIndex(size - 1);
		Object o = n.next.data;
		n.next = null;
		size--;
		return o;
	}

	public Iterator iterator() {
		return null;
	}

	private Node getNodebyIndex(int index) {
		int i = 0;
		Node n = head;
		while (i < index) {
			n = n.next;
			i++;
		}
		return n;
	}

	private static class Node {
		Object data;
		Node next;

		Object Getdata() {
			return data;
		}

		void Setdata(Object o) {
			data = o;
		}

		Node Getnext() {
			return next;
		}

		void Setnext(Node n) {
			next = n;
		}
	}
	/**
	 * �Ѹ��������� ��������Ϊ 3->7->10 , ���ú��Ϊ 10->7->3
	 */
	public void reverse(LinkedList list) {

		Node node = list.head.next;
		list.head.next = null;
		while (node != null) {
			Node temp = node.next;
			node.next = head.next;
			head.next = node;
			node = temp;
		}

	}

	/**
	 * ɾ��һ���������ǰ�벿�� ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8 ���list = 2->5->7->8->10
	 * ,ɾ���Ժ��ֵΪ7,8,10
	 * 
	 */
	public void removeFirstHalf(LinkedList list) {
		int lsize = list.size();
		int semisize = lsize / 2;
		Node node = head.next;

		for (int i = 0; i < semisize; i++) {
			node.data = null;
			Node temp = node.next;
			node.next = null;
			node = temp;
		}
		head.next = node;
	}

	/**
	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
	 * 
	 * @param i
	 * @param length
	 * @throws Exception
	 */
	public void remove(LinkedList list, int i, int length) {
		int lsize = list.size();
		if (i < 0 || i > lsize - 1 || (i + length > lsize - 1))
			throw new IndexOutOfBoundsException("Խ�磡");
		Node nodebefore = list.head, node = list.head;
		for (int j = 0; j <= i + length; j++)
			if (j <= i) {
				node = node.next;
				if (j == i - 1)
					nodebefore = node;
			} else {
				Node temp = node.next;
				node.data = null;
				node.next = null;
				node = temp;
			}
		nodebefore.next = node;
	}

	/**
	 * �ٶ���ǰ�����listB���������������е����� �ӵ�ǰ������ȡ����ЩlistB��ָ����Ԫ�� ���統ǰ���� =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * ���صĽ��Ӧ����[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list, LinkedList listB) {
		ArrayList al = new ArrayList();
		int sizeB = listB.size();
		int index;
		Object o;
		for (int i = 0; i < sizeB; i++) {
			index = Integer.parseInt(listB.get(i).toString());
			o = list.get(index);
			al.add(o);
		}
		int[] array = new int[al.size()];
		for (int i = 0; i < al.size(); i++)
			array[i] = Integer.parseInt(al.get(i).toString());
		return array;
	}

	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ�� �ӵ�ǰ��������ɾ����listB�г��ֵ�Ԫ��
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list, LinkedList listB) {
		int sizeB = listB.size();
		int index;
		int end = 0;
		Node node = list.head;
		for (int i = 0; i < sizeB; i++) {
			index = Integer.parseInt(listB.get(i).toString());
			for (int j = end; j < index; j++) {
				if (j != index - 1)
					node = node.next;
				else {
					Node temp = node.next.next;
					node.next.data = null;
					node.next.next = null;
					node.next = temp;
					node = temp;
					end = j;
				}
			}
		}
	}

	/**
	 * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ�� ɾ����������ֵ��ͬ�Ķ���Ԫ�� ��ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��
	 */
	public void removeDuplicateValues() {
		Object o = head.data;
		Node prenode = null;
		for (Node node = head.next; node != null; node = node.next) {
			if (o.equals(node.data)) {
				Node temp = node.next;
				node.data = null;
				node.next = null;
				prenode.next = temp;
				node = prenode;
			} else {
				o = node.data;
				prenode = node;
			}
		}
	}

	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ�� ��дһ��Ч���㷨�� ɾ����������ֵ����min��С��max��Ԫ�أ������д���������Ԫ�أ�
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		Node prenode = null;
		for (Node node = head.next; node != null; node = node.next) {
			int value = Integer.parseInt(node.data.toString());
			if (value > min) {
				if (value >= max) {
					break;
				} else {
					Node temp = node.next;
					node.data = null;
					node.next = null;
					prenode.next = temp;
					node = prenode;
				}

			} else {
				prenode = node;
			}
		}
	}

	/**
	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
	 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList listA, LinkedList listB) {
		LinkedList listC = new LinkedList();
		Node nodeBaf = listB.head;
		for (Node nodeA = listA.head; nodeA != null; nodeA = nodeA.next) {
			for (Node nodeB = nodeBaf; nodeB != null; nodeB = nodeB.next) {
				if (nodeA.data.equals(nodeB.data)) {
					listC.add(nodeA.data);
					nodeBaf = nodeB.next;
					break;
				}
			}
		}

		return null;
	}

}

