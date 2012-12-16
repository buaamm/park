import java.util.ArrayList;
import java.util.List;

/**
 * ParkingManager不与ParkingBoy同时管理一个ParkPlace
 * 
 * @author Administrator
 * 
 */
public class ParkingManager implements Park {

	private List<ParkPlace> parkPlaces;

	private List<ParkingBoy> parkingBoys;

	public ParkingManager() {
		this.parkingBoys = new ArrayList<ParkingBoy>();
		this.parkPlaces = new ArrayList<ParkPlace>();
	}

	public void addParkPlace(ParkPlace parkPlace) {
		this.parkPlaces.add(parkPlace);
	}

	public void addParkBoy(ParkingBoy parkingBoy) {
		this.parkingBoys.add(parkingBoy);
	}

	/**
	 * 叫parkingBoy停车
	 * 
	 * @param car
	 *            要停的车
	 * @param parkingBoyIndex
	 *            指定第几个ParkingBoy
	 * @return
	 */
	public Ticket parking(Car car, int parkingBoyIndex) {
		return parkingBoys.get(parkingBoyIndex).parking(car);
	}

	@Override
	public Integer getAvailableNum() {
		int result = 0;
		for (ParkPlace pp : parkPlaces) {
			result += pp.getAvailableNum();
		}
		for (ParkingBoy pb : parkingBoys) {
			result += pb.getAvailableNum();
		}
		return result;
	}

	/**
	 * 自己停车
	 */
	@Override
	public Ticket parking(Car car) throws NoPlaceException {
		for (ParkPlace pp : parkPlaces) {
			if (pp.getAvailableNum() > 0) {
				return pp.parking(car);
			}
		}
		throw new NoPlaceException("没有停车位了！");
	}

	@Override
	public Car getParkedCar(Ticket ticket) throws NoCarException {
		for (ParkPlace pp : parkPlaces) {
			try {
				return pp.getParkedCar(ticket);
			} catch (NoCarException noCarException) {

			}
		}
		for (ParkingBoy pb : parkingBoys) {
			try {
				return pb.getParkedCar(ticket);
			} catch (NoCarException noCarException) {

			}
		}
		throw new NoCarException("没有此车 请拨打110！");
	}
}
