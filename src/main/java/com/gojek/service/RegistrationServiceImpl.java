package com.gojek.service;

import com.gojek.modal.Registration;

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
  @Override
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
  @Override
  public int allocateSlots(Registration registration) {
    if (EmptySlots()) {
      return -1;
    }
    int slotNumber = slots.get(0);
    registration.setSlotNumber(slotNumber);
    registrationsList.add(slotNumber - 1, registration);
    slots.remove(new Integer(slotNumber));
    return slotNumber;
  }

  /**
   * @param color
   * @return List<Integer>slotNumber
   */
  @Override
  public List<Integer> getAllSlotNumberByColor(final String color) {
    final List<Integer> registrations = new ArrayList<>();
    registrationsList.forEach(
        (x) -> {
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
  @Override
  public Integer getSlotNumberByRegistrationName(final String registrationNunber) {
    Registration registration = null;
    if (EmptyRegistration()) {
      System.out.println("empty with registration");
      return -1;
    }
    registration =
        registrationsList.stream()
            .filter(x -> registrationNunber.equals(x.getRegistrationNumber()))
            .findAny()
            .orElse(null);
    if (registration != null) {
      return registration.getSlotNumber();
    }
    return -1;
  }

  /**
   * @param color
   * @return List<String> registrationNumber
   */
  @Override
  public List<String> getRegistrationNumberByColor(final String color) {
    final List<String> registrations = new ArrayList<>();
    registrationsList.forEach(
        (x) -> {
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
  @Override
  public List<Registration> getAllRegistration() {
    return registrationsList;
  }

  @Override
  public boolean removeAllocation(int slotNumber) {

    if (null == slots
        || slots.contains(slotNumber)
        || slotNumber <= 0
        || slotNumber > totalSlotSize) {
      return false;
    }
    registrationsList.remove(slotNumber - 1);
    slots.add(slotNumber);
    return true;
  }

  private boolean EmptyRegistration() {
    if (null == registrationsList || registrationsList.isEmpty()) {
      return true;
    }
    return false;
  }

  private boolean EmptySlots() {
    if (null == slots || slots.isEmpty()) {
      return true;
    }
    return false;
  }
}
