package com.smartvoting.service.impl;

import com.smartvoting.dto.GuestDTO;
import com.smartvoting.entity.Guest;
import com.smartvoting.entity.Room;
import com.smartvoting.repository.GuestRepository;
import com.smartvoting.repository.RoomRepository;
import com.smartvoting.service.IGuestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public class GuestServiceImpl implements IGuestService {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public Single<Object> addGuest(GuestDTO guestDTO) {
        return Single.create(
                singleSubscriber -> {
                    Guest addedGuest = guestRepository.save(toGuest(guestDTO));
                    singleSubscriber.onSuccess(addedGuest);
                }
        );
    }

    @Override
    public Guest toGuest(GuestDTO guestDTO) {
        Guest addedGuest = new Guest();
        BeanUtils.copyProperties(guestDTO,addedGuest);
        Room room = roomRepository.findById(guestDTO.getRoomId()).get();
        addedGuest.setRoom(room);
        return addedGuest;
    }
}
