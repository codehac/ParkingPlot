import com.gojek.PlotSlotManager;
import com.gojek.modal.Registration;
import com.gojek.service.RegistrationServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlotSlotManagerTest {
  private RegistrationServiceImpl registrationService;

  @Before
  public void intialize() {
    registrationService = new RegistrationServiceImpl();
  }

  @Before
  public void beforeEachTest() {
    System.out.println("This is executed before each Test");
  }

  @After
  public void AfterEachTest() {
    System.out.println("This is executed After each Tests");
  }

  @Test
  public void createSlotwithError() {
    PlotSlotManager.processData(new String[] {"create_parking_lot", "b"});
  }

  @Test
  public void createSlot() {
    registrationService.register(6);
  }

  @Test(expected = FileNotFoundException.class)
  public void checkFile() throws FileNotFoundException {
    final String fileName = "fileinput1.txt";
    PlotSlotManager.readDirectory(fileName);
  }

  @Test
  public void removeCarFromSlot() {
    PlotSlotManager.processData(new String[] {"leave", "4"});
  }

  @Test
  public void removeFromSlot() {
    PlotSlotManager.processData(new String[] {"leave", "a"});
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

  @Test
  public void statusOfSlot() {
    registrationService.register(5);
    registrationService.allocateSlots(new Registration("KA-01-P-333 ", "white"));
    registrationService.allocateSlots(new Registration("KA-01-P-332 ", "black"));
    registrationService.allocateSlots(new Registration("KA-01-P-331 ", "orange"));
    registrationService.allocateSlots(new Registration("KA-01-P-330 ", "red"));
    List<Registration> list = registrationService.getAllRegistration();
    assertNotEquals(list.size(), 0);
  }

  @Test
  public void getAllRegistrationByColorPresent() {
    registrationService.register(5);
    registrationService.allocateSlots(new Registration("KA-01-P-333 ", "white"));
    registrationService.allocateSlots(new Registration("KA-01-P-332 ", "black"));
    registrationService.allocateSlots(new Registration("KA-01-P-331 ", "orange"));
    registrationService.allocateSlots(new Registration("KA-01-P-330 ", "red"));
    List<String> list = registrationService.getRegistrationNumberByColor("white");
    assertEquals(list.size(), 1);
  }

  @Test
  public void getAllRegistrationByColorNotPresent() {
    registrationService.register(5);
    registrationService.allocateSlots(new Registration("KA-01-P-333 ", "white"));
    registrationService.allocateSlots(new Registration("KA-01-P-332 ", "black"));
    registrationService.allocateSlots(new Registration("KA-01-P-331 ", "orange"));
    registrationService.allocateSlots(new Registration("KA-01-P-330 ", "red"));
    List<String> list = registrationService.getRegistrationNumberByColor("violet");
    assertEquals(list.size(), 0);
  }

  @Test
  public void slotNumberByRegistrationNumberPresent() {
    registrationService.register(5);
    registrationService.allocateSlots(new Registration("KA-01-P-333", "white"));
    registrationService.allocateSlots(new Registration("KA-01-P-332", "black"));
    registrationService.allocateSlots(new Registration("KA-01-P-331", "orange"));
    registrationService.allocateSlots(new Registration("KA-01-P-330", "red"));
    int slotNumber = registrationService.getSlotNumberByRegistrationName("KA-01-P-331");
    assertEquals(slotNumber, 3);
  }

  @Test
  public void slotNumberByRegistrationNumbersNotPresent() {
    registrationService.register(5);
    registrationService.allocateSlots(new Registration("KA-01-P-333 ", "white"));
    registrationService.allocateSlots(new Registration("KA-01-P-332 ", "black"));
    registrationService.allocateSlots(new Registration("KA-01-P-331 ", "orange"));
    registrationService.allocateSlots(new Registration("KA-01-P-330 ", "red"));
    int slotNumber = registrationService.getSlotNumberByRegistrationName("KA-01-P-335");
    assertEquals(slotNumber, -1);
  }

  @After
  public void freeMemory() {
    registrationService = null;
  }
}
