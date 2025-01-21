package v.o.i.d.lift;

import jodd.util.ThreadUtil;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static v.o.i.d.lift.LiftState.Running.STOPPED;

public class LiftState {
	public enum Running {
		ACTIVE, STOPPED
	}

	private int currentFloor;
	private int targetFloor;
	private Running running;

	public LiftState(int currentFloor) {
		this.currentFloor = currentFloor;
		this.targetFloor = currentFloor;
		this.running = STOPPED;
	}

	public int currentFloor() {
		return currentFloor;
	}

	public Running running() {
		return running;
	}

	public synchronized void startToTargetFloor(int desiredTargetFloor) {
		if (running == Running.ACTIVE) {
			if (desiredTargetFloor > currentFloor) {
				this.targetFloor = desiredTargetFloor;
			}
		} else {
			this.targetFloor = desiredTargetFloor;
			if (desiredTargetFloor == currentFloor) {
				return;
			}
		}

		runLift();
	}

	private void runLift() {
		running = Running.ACTIVE;
		CompletableFuture.runAsync(() -> {
			ThreadUtil.sleep(100);
			while (currentFloor != targetFloor) {
				if (currentFloor < targetFloor) {
					currentFloor++;
				} else {
					currentFloor--;
				}
				if (floorConsumer != null) {
					floorConsumer.accept(currentFloor);
				}
				ThreadUtil.sleep(100);
			}
			running = STOPPED;
		});
	}

	private Consumer<Integer> floorConsumer;

	public void registerFloorListener(Consumer<Integer> floorConsumer) {
		this.floorConsumer = floorConsumer;
	}

}