import constants.Constants;
import modal.Registration;
import service.RegistrationService;
import service.RegistrationServiceImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

static RegistrationService registrationService = new RegistrationServiceImpl();
static final Scanner cin = new Scanner(System.in);

public static void main(String[] args) throws FileNotFoundException {
	if (0 == args[0].length() || null == args[0]) {
		readDirectory(args[0]);
	} else {
		startCMD();
	}
}

/**
 * @param
 * @return
 */

private static void startCMD() {
	while (true) {
		processData(cin.nextLine().split("/ "));
	}
}

/**
 * @param directoryName
 * @return
 */
private static void readDirectory(String directoryName) throws FileNotFoundException {
	FileInputStream inputStream = new FileInputStream(directoryName);
	Scanner cin = new Scanner(inputStream);
	while (cin.hasNextLine()) {
		String[] data = cin.nextLine().split("/ ");
		processData(data);
	}
}

/**
 * @param data
 * @return
 */
private static void processData(String[] data) {
	String commandType = data[0];

	switch (commandType) {
		case Constants.CREATE:
			registrationService.register(Integer.parseInt(data[1]));
			break;
		case Constants.PARK:
			if (checkEmpty(data[1]) || checkEmpty(data[2])) {
				System.out.println("Please Enter Valid Input");
				break;
			}
			final int status = registrationService.allocateSlots(getRegistration(data[1], data[2]));
			if (status == -1) {
				System.out.println("Sorry, parking lot is full");
			} else {
				System.out.println("Allocated slot number: " + String.valueOf(status));
			}
			break;
		case Constants.STATUS:
			List<Registration> registrationList = registrationService.getAllRegistration();
			printStatus(registrationList);
			break;
		case Constants.LEAVE:
			if (checkEmpty(data[1])) {
				System.out.println("Please Enter Valid Input");
				break;
			}
			boolean removeStatus = registrationService.removeAllocation(Integer.parseInt(data[1]));
			if (!removeStatus) {
				System.out.println("Invalid Operation");
			} else {
				System.out.println("Slot number" + data[1] + "is free ");
			}
			break;
		case Constants.SLOT_NUMBER_BY_COLOR:
			if (checkEmpty(data[1])) {
				System.out.println("Invalid Input");
				break;
			}
			List<Integer> slotNumbers = registrationService.getAllSlotNumberByColor(data[1]);
			if (slotNumbers.isEmpty() || null == slotNumbers) {
				System.out.println("Not found");
				break;
			}
			System.out.println(slotNumbers.toString().substring(1, slotNumbers.toString().length() - 1));
			break;
		case Constants.SLOT_NUMBER_BY_REGISTRATION_NUMBER:
			if (checkEmpty(data[1])) {
				System.out.println("Invalid Input");
				break;
			}
			final int slotNumberByRegistrationName = registrationService.getSlotNumberByRegistrationName(data[1]);
			if (slotNumberByRegistrationName == -1) {
				System.out.println("Not found");
			} else {
				System.out.println(slotNumberByRegistrationName);
			}
			break;
		case Constants.REGISTARTION_NUMBER_BY_COLOR:
			if (checkEmpty(data[1])) {
				System.out.println("Invalid Input");
				break;
			}
			List<String> registationNumberList = registrationService.getRegistrationNumberByColor(data[1]);
			if (registationNumberList.isEmpty() || null == registationNumberList) {
				System.out.println("Not Found");
			} else {
				System.out.println(registationNumberList.toString().substring(1, registationNumberList.toString().length() - 1));
			}
			break;
		case Constants.EXIT:
			System.exit(0);
			break;
		default:
			System.out.println("Please Enter Valid Input");
	}
}

private static Registration getRegistration(final String registration, final String color) {
	return new Registration(registration, color);
}

private static boolean checkEmpty(final String entity) {
	if (entity.isEmpty() || null == entity) {
		return true;
	}
	return false;
}

private static void printStatus(final List<Registration> registration) {
	if (registration.isEmpty() || null == registration) {
		System.out.println("No Registartion is found");
		return;
	}
	System.out.println("Slot No.  " + " Registration No  " + " Colour");
	registration.forEach((x) -> {
		System.out.println(x.getSlotNumber() + " " + x.getRegistrationNumber() + " " + x.getColor());
	});
}

}
