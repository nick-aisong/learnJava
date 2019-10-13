package cn.ch07.ch07_7;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {
	private static final long serialVersionUID = 1L;

	private AtomicInteger state;

	public MyAbstractQueuedSynchronizer() {
		this.state = new AtomicInteger(0);
	}

	@Override
	protected boolean tryAcquire(int arg) {
		return state.compareAndSet(0, 1);
	}

	@Override
	protected boolean tryRelease(int arg) {
		return state.compareAndSet(1, 0);
	}

}
