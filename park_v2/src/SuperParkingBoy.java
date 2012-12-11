import java.util.Collections;
import java.util.Comparator;

public class SuperParkingBoy extends ParkingBoy {
	@Override
	public Ticket parking(Car c) throws NoPlaceException {
		Collections.sort(parkPlaces, new Comparator<ParkPlace>() {
			@Override
			public int compare(ParkPlace o1, ParkPlace o2) {
				return Double.compare(o1.vacancyRate(), o2.vacancyRate());
			}
		});
		ParkPlace pp = parkPlaces.get(0);
		if (pp.getAvailableNum() > 0) {
			return pp.parking(c);
		}
		throw new NoPlaceException("没有停车位了！");
	}
}
