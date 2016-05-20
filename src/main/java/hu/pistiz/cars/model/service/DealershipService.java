package hu.pistiz.cars.model.service;

import hu.pistiz.cars.model.Dealership;

public class DealershipService {

	/*public Dealership createDealership(String name) {
		Dealership dealership = new Dealership();
		dealership.setName(name);
		dealership.setOwner(null);
		dealership.setAddress(null);
		dealership.setOwner(new Person());
		dealership.setIncome(0);
		return dealership;
	}*/

	public DealershipService() {
	}

	public void incrementIncome(Dealership dealership, long increment) {
		dealership.incrementIncome(increment);
	}

	public void incrementProfit(Dealership dealership, long increment) {
		dealership.incrementProfit(increment);
	}

	public void incrementStartingMoney(Dealership dealership, long increment) {
		dealership.incrementStartingMoney(increment);
	}

	public void incrementRemainder(Dealership dealership, long increment) {
		dealership.incrementRemainder(increment);
	}

	public void decreaseRemainder(Dealership dealership, long decrease) {
		dealership.decreaseRemainder(decrease);
	}

	public void saleCar(Dealership dealership) {
		dealership.saleCar();
	}

}
