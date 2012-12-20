import java.util.ArrayList;
import java.util.List;

public class ParkingBoy implements Park {

	protected List<ParkPlace> parkPlaces;

	private int totalParkingSpace = 0;

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

	/**
	 * 返回报表
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(
				this.parkPlaces.size() * 23 + 24);

		int availableParkingSpace = 0;

		for (ParkPlace pp : parkPlaces) {
			stringBuilder.append(pp.toString());
			availableParkingSpace += pp.getAvailableNum();
		}

		stringBuilder.append("Total车位数：").append(this.totalParkingSpace())
				.append("\n");
		stringBuilder.append("Total空位数：").append(availableParkingSpace)
				.append("\n");

		return stringBuilder.toString();
	}

	public Integer totalParkingSpace() {
		if (this.totalParkingSpace == 0) {
			for (ParkPlace pp : parkPlaces) {
				totalParkingSpace += pp.getTotalParkingSpace();
			}
		}
		return this.totalParkingSpace;
	}
}
