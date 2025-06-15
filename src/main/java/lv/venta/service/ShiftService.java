package lv.venta.service;

import lv.venta.model.Shift;

import java.util.List;

public interface ShiftService {

    List<Shift> getAllShifts();

    Shift getShiftById(Long id) throws Exception;

    void createShift(Shift shift) throws Exception;

    void updateShiftById(Long id, Shift shift) throws Exception;

    void deleteShiftById(Long id) throws Exception;
}
