package ag.josepfu;

public class Boy {

	public Boy(int no) {
		this.no = no;
	}

	private int no;

	private Boy next;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}

}
