package service;

import modal.Registration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {
private int totalSlotSize = 0;
private List<Registration> registrationsList = null;
private List<Integer> slots = null;

/**
 * @param slotSize
 * @return
 */
public void register(final int slotSize) {
	this.totalSlotSize = slotSize;
	this.registrationsList = new ArrayList<>(totalSlotSize);
	this.slots = new ArrayList<>(totalSlotSize);
	for (int i = 0; i < slotSize; i++) {
		slots.add(i + 1);
	}
}

/**
 * @param registration
 * @return slotNumber
 */
public int allocateSlots(Registration registration) {
	if (EmptySlots()) {
		return -1;
	}
	int slotNumber = slots.get(0);
	registration.setSlotNumber(slotNumber);
	registrationsList.add(registration);
	slots.remove(new Integer(slotNumber));
	return slotNumber;
}

/**
 * @param color
 * @return List<Integer>slotNumber
 */
public List<Integer> getAllSlotNumberByColor(final String color) {
	final List<Integer> registrations = new ArrayList<>();
	registrationsList.forEach((x) -> {
		if (color.equals(x.getColor())) {
			registrations.add(x.getSlotNumber());
		}
	});
	return registrations;
}

/**
 * @param registrationNunber
 * @return slotNumber
 */
public Integer getSlotNumberByRegistrationName(final String registrationNunber) {
	Registration registration = null;
	if (EmptyRegistration()) {
		return -1;
	}
	registration = registrationsList.stream().filter(x -> registrationNunber.equals(x.getRegistrationNumber())).findAny().orElse(null);
	if (registration != null) {
		registration.getRegistrationNumber();
	}
	return -1;
}

/**
 * @param color
 * @return List<String> registrationNumber
 */
public List<String> getRegistrationNumberByColor(String color) {
	final List<String> registrations = new ArrayList<>();
	registrationsList.forEach((x) -> {
		if (color.equals(x.getColor())) {
			registrations.add(x.getRegistrationNumber());
		}
	});
	return registrations;
}


/**
 * @param
 * @return registrationList
 */
public List<Registration> getAllRegistration() {
	return registrationsList;
}

public boolean removeAllocation(int slotNumber) {
	if (slots.contains(slotNumber) || slotNumber <= 0 || slotNumber > totalSlotSize) {
		return false;
	}
	registrationsList.remove(slotNumber - 1);
	slots.add(slotNumber);
	return true;
}

private boolean EmptyRegistration() {
	if (registrationsList.isEmpty() || null == registrationsList) {
		return true;
	}
	return false;
}

private boolean EmptySlots() {
	if (slots.isEmpty() || null == slots) {
		return true;
	}
	return false;
}
}
