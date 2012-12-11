import java.util.Collections;
import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
	@Override
	public Ticket parking(Car c) throws NoPlaceException {
		Collections.sort(parkPlaces, new Comparator<Park>() {
			@Override
			public int compare(Park o1, Park o2) {
				return o1.getAvailableNum().compareTo(o2.getAvailableNum());
			}
		});
		ParkPlace pp = parkPlaces.get(0);
		if (pp.getAvailableNum() > 0) {
			return pp.parking(c);
		}
		throw new NoPlaceException("没有停车位了！");
	}
}
