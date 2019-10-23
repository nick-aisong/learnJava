package cn.ch07.ch07_8;

public class Event implements Comparable<Event> {

	private String thread;
	private int priority;

	public Event(String thread, int priority) {
		this.thread = thread;
		this.priority = priority;
	}

	public String getThread() {
		return thread;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public int compareTo(Event o) {
		return 0;
	}
}
