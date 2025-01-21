package v.o.i.d.lift;

public class Lift {
	private LiftState state;    // todo final

	public Lift(int floor) {
		this.state = new LiftState(floor);
	}

	public void goToFloor(int destinationFloor) {
		this.state = new LiftState(destinationFloor);
	}

	public void callElevator(int callingFloor) {
		this.state.startToTargetFloor(callingFloor);
	}

	public LiftState state() {
		return state;
	}
}
