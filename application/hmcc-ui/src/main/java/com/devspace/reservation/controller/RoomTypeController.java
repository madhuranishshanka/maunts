package com.devspace.reservation.controller;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.reservation.domain.RoomType;
import com.devspace.reservation.model.RoomTypeView;
import com.devspace.reservation.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Controller
@RequestMapping("/roomType")
public class RoomTypeController {

    @Resource(name = "roomService")
    private RoomService roomService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("roomType") RoomTypeView roomTypeView) {
        TenantContext.setTenant(TenantContext.ROOT);

        RoomType roomType = new RoomType();
        roomType.setDescription(roomTypeView.getDescription());
        roomType.setName(roomTypeView.getRoomTypeName());
        roomService.createRoomType(roomType);

        List<RoomType> roomTypes = roomService.getAllRoomTypes();

        List<RoomTypeView> roomTypeViews  = new ArrayList<RoomTypeView>();
        for (RoomType tmpRoomType : roomTypes){
            RoomTypeView tmpRoomTypeView = new RoomTypeView();

            tmpRoomTypeView.setRoomTypeName(tmpRoomType.getName());
            tmpRoomTypeView.setDescription(tmpRoomType.getDescription());
            roomTypeViews.add(tmpRoomTypeView);
        }

        ModelAndView modelAndView = new ModelAndView("roomTypeList");
        modelAndView.addObject("roomTypeList",roomTypes);
        return modelAndView;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView getRoomTypeForm() {
        ModelAndView modelAndView = new ModelAndView("addRoomType");
        modelAndView.addObject("roomType", new RoomTypeView());
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{roomTypeName}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String roomTypeName) {
        ModelAndView modelAndView = new ModelAndView("roomTypeList");
        return modelAndView;
    }


    @RequestMapping(value = "/read/{roomTypeName}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable String roomTypeName) {
        ModelAndView modelAndView = new ModelAndView("roomTypeList");
        return modelAndView;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("roomTypeList");
        return modelAndView;
    }

}
