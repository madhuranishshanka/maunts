package com.devspace.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.devspace.reservation.model.RoomType;
/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Controller
@RequestMapping("/roomType")
public class RoomTypeController {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("roomType") RoomType roomType,BindingResult result) {


        return modelAndView;
    }

    @RequestMapping(value = "/delete/{roomTypeName}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String roomTypeName) {
        ModelAndView modelAndView = new ModelAndView("roomType");
        return modelAndView;
    }


    @RequestMapping(value = "/read/{roomTypeName}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable String roomTypeName) {
        ModelAndView modelAndView = new ModelAndView("roomType");
        return modelAndView;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("roomType");
        return modelAndView;
    }

}
