import java.util.HashMap;
import java.util.Map;

public class ParkPlace implements Park {
	private Map<Ticket, Car> parkedCarList = new HashMap<Ticket, Car>();
	private Integer maxParkingNum;

	private int id;

	public Integer getTotalParkingSpace() {
		return this.maxParkingNum;
	}

	public Integer getAvailableNum() {
		return maxParkingNum - parkedCarList.size();
	}

	public double vacancyRate() {
		return getAvailableNum() / this.maxParkingNum;
	}

	public ParkPlace(int id, int maxParkingNum) {
		this.maxParkingNum = maxParkingNum;
		this.id = id;
	}

	public Ticket parking(Car c) throws NoPlaceException {
		if (getAvailableNum() == 0) {
			throw new NoPlaceException("没有停车位了！");
		}
		Ticket ticket = new Ticket();
		parkedCarList.put(ticket, c);
		return ticket;
	}

	public Car getParkedCar(Ticket pp) throws NoCarException {
		if (parkedCarList.containsKey(pp)) {
			return parkedCarList.remove(pp);
		}
		throw new NoCarException("没有此车 请拨打110！");
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(23);
		stringBuilder.append("停车场编号：").append(this.id).append("\n");
		stringBuilder.append("    车位数：").append(this.maxParkingNum).append("\n");
		stringBuilder.append("    空位数：").append(this.getAvailableNum())
				.append("\n");
		return stringBuilder.toString();
	}
}
