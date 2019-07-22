package modal;

public class Registration {
private String registrationNumber;
private String color;
private int slotNumber;

public Registration() {
}

public Registration(String registrationNumber, String color, int slotNumber) {
	this.registrationNumber = registrationNumber;
	this.color = color;
	this.slotNumber = slotNumber;
}

public String getRegistrationNumber() {
	return registrationNumber;
}

public void setRegistrationNumber(final String registrationNumber) {
	this.registrationNumber = registrationNumber;
}

public String getColor() {
	return color;
}

public void setColor(final String color) {
	this.color = color;
}

public int getSlotNumber() {
	return slotNumber;
}

public void setSlotNumber(final int slotNumber) {
	this.slotNumber = slotNumber;
}
}
