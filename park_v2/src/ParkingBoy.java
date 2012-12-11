import java.util.ArrayList;
import java.util.List;

public class ParkingBoy implements Park {

	protected List<ParkPlace> parkPlaces;

	public ParkingBoy() {
		this.parkPlaces = new ArrayList<ParkPlace>();
	}

	public void addParkPlace(ParkPlace parkPlace) {
		this.parkPlaces.add(parkPlace);
	}

	@Override
	public Integer getAvailableNum() {
		int result = 0;
		for (ParkPlace pp : parkPlaces) {
			result += pp.getAvailableNum();
		}
		return result;
	}

	@Override
	public Ticket parking(Car c) throws NoPlaceException {
		for (ParkPlace pp : parkPlaces) {
			if (pp.getAvailableNum() > 0) {
				return pp.parking(c);
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
		throw new NoCarException("没有此车 请拨打110！");
	}

}
