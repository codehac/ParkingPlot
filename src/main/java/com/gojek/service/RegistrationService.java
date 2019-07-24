package com.gojek.service;

import com.gojek.modal.Registration;

import java.util.List;

public interface RegistrationService {
  public void register(final int slotSize);

  public int allocateSlots(Registration registration);

  public List<Integer> getAllSlotNumberByColor(final String color);

  public Integer getSlotNumberByRegistrationName(final String registrationNunber);

  public List<String> getRegistrationNumberByColor(String color);

  public List<Registration> getAllRegistration();

  public boolean removeAllocation(int slotNumber);
}
