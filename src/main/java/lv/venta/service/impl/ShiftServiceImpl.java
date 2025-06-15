package lv.venta.service.impl;

import lv.venta.model.Shift;
import lv.venta.repository.ShiftRepository;
import lv.venta.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftRepository shiftRepo;

    @Override
    public List<Shift> getAllShifts() {
        return shiftRepo.findAll();
    }

    @Override
    public Shift getShiftById(Long id) throws Exception {
        return shiftRepo.findById(id)
                .orElseThrow(() -> new Exception("Shift not found with id: " + id));
    }

    @Override
    public void createShift(Shift shift) {
        shiftRepo.save(shift);
    }

    @Override
    public void updateShiftById(Long id, Shift updatedShift) throws Exception {
        Shift shift = getShiftById(id);
        shift.setName(updatedShift.getName());
        shift.setStartTime(updatedShift.getStartTime());
        shift.setEndTime(updatedShift.getEndTime());
        shiftRepo.save(shift);
    }

    @Override
    public void deleteShiftById(Long id) {
        shiftRepo.deleteById(id);
    }
}
