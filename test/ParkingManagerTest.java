import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingManagerTest {

	private ParkingManager parkingManager;

	private int totalParkingSpace;

	@Before
	public void setUp() {
		this.parkingManager = new ParkingManager();

		this.parkingManager.addParkPlace(new ParkPlace(1, 20));
		this.parkingManager.addParkPlace(new ParkPlace(2, 20));

		ParkingBoy parkingBoy1 = new ParkingBoy();
		parkingBoy1.addParkPlace(new ParkPlace(3, 10));
		parkingBoy1.addParkPlace(new ParkPlace(4, 10));
		this.parkingManager.addParkBoy(parkingBoy1);

		ParkingBoy parkingBoy2 = new ParkingBoy();
		parkingBoy2.addParkPlace(new ParkPlace(5, 20));
		parkingBoy2.addParkPlace(new ParkPlace(6, 10));
		this.parkingManager.addParkBoy(parkingBoy2);

		this.totalParkingSpace = 20 + 20 + 10 + 10 + 20 + 10;
	}

	@Test
	public void parkPlace_Parking_HavePlace() {

		Car C = new Car();
		Ticket proof = this.parkingManager.parking(C);
		Assert.assertNotNull(proof);
	}

	@Test(expected = NoPlaceException.class)
	public void parkPlace_Parking_NoPlace() {
		boolean flag = true;
		while (flag) {
			this.parkingManager.parking(new Car());
		}
		Assert.assertFalse(true);
	}

	@Test
	public void parkPlace_GetCar() {

		Car car = new Car();
		Ticket proof = this.parkingManager.parking(car);

		Assert.assertSame(car, this.parkingManager.getParkedCar(proof));

	}

	@Test(expected = NoCarException.class)
	public void parkPlace_GetCar_NoThisCar() {

		Ticket proof = this.parkingManager.parking(new Car());
		this.parkingManager.getParkedCar(proof);

		this.parkingManager.getParkedCar(proof);

		Assert.assertFalse(true);
	}

	@Test
	public void parkPlace_ShowAvailableNum() {

		this.parkingManager.parking(new Car());
		Assert.assertEquals(Integer.valueOf(this.totalParkingSpace - 1),
				this.parkingManager.getAvailableNum());
	}
	
	@Test
	public void toString_test(){
	this.parkingManager.parking(new Car());
	String  expected=
	"停车场编号：1\n"+
	"    车位数：20\n"+
	"    空位数：19\n"+
	"停车场编号：2\n"+
	"    车位数：20\n"+
	"    空位数：20\n"+
	"停车仔编号：0\n"+
	"    停车场编号：3\n"+
	"        车位数：10\n"+
	"        空位数：10\n"+
	"    停车场编号：4\n"+
	"        车位数：10\n"+
	"        空位数：10\n"+
	"    Total车位数：20\n"+
	"    Total空位数：20\n"+
	"停车仔编号：1\n"+
	"    停车场编号：5\n"+
	"        车位数：20\n"+
	"        空位数：20\n"+
	"    停车场编号：6\n"+
	"        车位数：10\n"+
	"        空位数：10\n"+
	"    Total车位数：30\n"+
	"    Total空位数：30\n"+
	"Total车位数：90\n"+
	"Total空位数：89\n";
	Assert.assertEquals("to String wrong", expected,this.parkingManager.toString());
	}

}
