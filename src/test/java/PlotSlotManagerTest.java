import com.gojek.PlotSlotManager;
import com.gojek.modal.Registration;
import com.gojek.service.RegistrationService;
import com.gojek.service.RegistrationServiceImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.testng.Assert.assertEquals;

public class PlotSlotManagerTest {
private RegistrationService registrationService;

@BeforeMethod
public void intialize() {
	registrationService = new RegistrationServiceImpl();
}


@Test(expectedExceptions = NumberFormatException.class)
public void createSlotwithError() {
	PlotSlotManager.processData(new String[]{"create", "b"});
}

@Test
public void createSlot() {
	registrationService.register(6);
}

@Test(expectedExceptions = FileNotFoundException.class)
public void checkFile() throws FileNotFoundException {
	final String fileName = "fileinput1.txt";
	PlotSlotManager.readDirectory(fileName);
}

@Test
public void removeCarFromSlot() {
	PlotSlotManager.processData(new String[]{"leave", "4"});
}

@Test(expectedExceptions = NumberFormatException.class)
public void removeFromSlot() {
	PlotSlotManager.processData(new String[]{"leave", "a"});
}

@Test(expectedExceptions = IndexOutOfBoundsException.class)
public void removeCarWtihIndexOutOfBoundsException() {
	registrationService.register(5);
	registrationService.allocateSlots(new Registration("KA-01-P-333 ", "white"));
	registrationService.removeAllocation(6);
}

@Test
public void removeCarFromSlotNumber() {
	registrationService.register(5);
	registrationService.allocateSlots(new Registration("KA-01-P-333 ", "white"));
	boolean status = registrationService.removeAllocation(2);
	assertEquals(status, false);

}

@Test
public void removeCarFromSlotNumber1() {
	registrationService.register(5);
	registrationService.allocateSlots(new Registration("KA-01-P-333 ", "white"));
	boolean status = registrationService.removeAllocation(1);
	assertEquals(status, true);
}


@AfterMethod
public void freeMemory() {
	registrationService = null;
}
}
