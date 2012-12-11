import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingBoyTest {

	private ParkingBoy parkingBoy;

	@Before
	public void setUp() {
		this.parkingBoy = new ParkingBoy();
	}

	@Test
	public void parkPlace_Parking_HavePlace() {

		this.parkingBoy.addParkPlace(new ParkPlace(20));
		Car C = new Car();
		Ticket proof = this.parkingBoy.parking(C);
		Assert.assertNotNull(proof);
	}

	@Test(expected = NoPlaceException.class)
	public void parkPlace_Parking_NoPlace() {

		this.parkingBoy.addParkPlace(new ParkPlace(1));
		this.parkingBoy.addParkPlace(new ParkPlace(1));

		this.parkingBoy.parking(new Car());
		this.parkingBoy.parking(new Car());

		this.parkingBoy.parking(new Car());
		Assert.assertFalse(true);
	}

	@Test
	public void parkPlace_GetCar() {

		this.parkingBoy.addParkPlace(new ParkPlace(20));
		Car car = new Car();
		Ticket proof = this.parkingBoy.parking(car);

		Assert.assertSame(car, this.parkingBoy.getParkedCar(proof));

	}

	@Test(expected = NoCarException.class)
	public void parkPlace_GetCar_NoThisCar() {

		this.parkingBoy.addParkPlace(new ParkPlace(20));
		Ticket proof = this.parkingBoy.parking(new Car());
		this.parkingBoy.getParkedCar(proof);

		this.parkingBoy.getParkedCar(proof);

	}

	@Test
	public void parkPlace_ShowAvailableNum() {
		this.parkingBoy.addParkPlace(new ParkPlace(1));
		this.parkingBoy.addParkPlace(new ParkPlace(1));

		this.parkingBoy.parking(new Car());
		Assert.assertEquals(Integer.valueOf(1),
				this.parkingBoy.getAvailableNum());
	}

}
