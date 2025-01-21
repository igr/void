package v.o.i.d.lift;

import jodd.util.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static v.o.i.d.lift.LiftState.Running.STOPPED;

class LiftTest {

	@Test
	void test_liftShowingTheFloor() {
		var lift = new Lift(0);
		assertEquals(0, lift.state().currentFloor());
	}

	@Test
	void test_goToTheFloor() {
		// Arrange
		var lift = new Lift(0);

		// Act
		lift.goToFloor(1);

		// Assert
		assertEquals(1, lift.state().currentFloor());
	}


	@Test
	void test_callElevator() {
		// Arrange
		var lift = new Lift(1);

		// Act
		lift.callElevator(0);
		ThreadUtil.sleep(110);

		// Assert
		assertEquals(0, lift.state().currentFloor());
	}

	@Test
	void test_stateForElevatorNotInMotion() {
		// Arrange
		var lift = new Lift(1);

		// Act

		// Assert
		assertEquals(1, lift.state().currentFloor());
	}

	@Test
	void test_stateForElevatorChangesWhileInMotion() {
		// Arrange
		var lift = new Lift(1);

		// Act
		lift.callElevator(3);
		ThreadUtil.sleep(110);

		// Assert
		assertEquals(2, lift.state().currentFloor());
	}

	@Test
	void test_whenDestinationisTheSameFloorDontWait() {
		// Arrange
		var lift = new Lift(1);

		// Act
		lift.callElevator(1);

		// Assert
		assertEquals(STOPPED, lift.state().running());
	}

	@Test
	void test_currentFloorReturnsAllFloorsInBetween() {
		// Arrange
		var list = new ArrayList<Integer>();
		var lift = new Lift(1);
		lift.state().registerFloorListener(list::add);

		// Act
		lift.callElevator(3);
		ThreadUtil.sleep(100 * 3 + 10);

		// Assert
		assertEquals(3, lift.state().currentFloor());
		assertEquals(2, list.size());

		assertEquals(2, list.get(0).intValue());
		assertEquals(3, list.get(1).intValue());
	}


	@Test
	void test_callLiftWhileRunning() {
		// Arrange
		var lift = new Lift(1);

		// Act
		lift.callElevator(10);
		ThreadUtil.sleep(100);
		lift.callElevator(3);
		ThreadUtil.sleep(310);

		// Assert
		assertEquals(3, lift.state().currentFloor());
		assertEquals(STOPPED, lift.state().running());
	}

}
