package service;

import modal.Registration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {
  private int totalSlotSize = 0;
  private List<Registration> registrations = null;

  public void register(final int slotSize) {
    this.totalSlotSize = slotSize;
    this.registrations = new ArrayList<>(totalSlotSize);
  }

  public List<Registration> getAllRegistrationByColor(final String color) {
    return null;
  }

  public Registration getRegistrationByName(final String registrationName) {
    return null;
  }
}
