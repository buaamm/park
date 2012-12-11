public interface Park {

	public Integer getAvailableNum();

	public Ticket parking(Car car) throws NoPlaceException;

	public Car getParkedCar(Ticket ticket) throws NoCarException;

}
