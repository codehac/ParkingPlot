import constants.Constants;
import service.RegistrationService;
import service.RegistrationServiceImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

  static RegistrationService registrationService = new RegistrationServiceImpl();

  public static void main(String[] args) {
    if (0 == args[0].length() || null == args[0]) {

    } else {

    }
  }

  /**
   * @param directoryName
   * @return
   */
  public static void readDirectory(String directoryName) throws FileNotFoundException {
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
  public static void processData(String[] data) {
    String commandType = data[0];
    switch (commandType) {
      case Constants.CREATE:
        registrationService.register(Integer.parseInt(data[1]));
        break;
      case Constants.PARK:
        break;
      case Constants.STATUS:
        break;
      case Constants.LEAVE:
        break;
      case Constants.SLOT_NUMBER_BY_COLOR:
        break;
      case Constants.SLOT_NUMBER_BY_REGISTRATION_NUMBER:
        break;
      case Constants.REGISTARTION_NUMBER_BY_COLOR:
        break;
      default:
    }
  }
}
