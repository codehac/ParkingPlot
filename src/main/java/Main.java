import service.RegistrationService;
import service.RegistrationServiceImpl;

public class Main {
static RegistrationService registrationService = new RegistrationServiceImpl();
public static void main(String[] args) {
	if (0 == args[0].length() || null == args[0]) {

	} else {

	}
}
}
