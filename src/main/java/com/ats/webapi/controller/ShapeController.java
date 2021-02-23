package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.Shape;
import com.ats.webapi.model.Info;
import com.ats.webapi.repo.ShapeRepo;

@RestController
public class ShapeController {
	@Autowired 
	ShapeRepo shapeRepo;
	
	@RequestMapping(value = { "/saveItemChef" }, method = RequestMethod.POST)
	public @ResponseBody Shape Object(@RequestBody Shape shape) {
		Info info=new Info();
		Shape sh	=shapeRepo.save(shape);
	    
		
		if(sh!=null)
		{
			info.setError(false);
			info.setMessage("Data Inseert Successfully");
		}
		else
		{
			info.setError(true);
			info.setMessage("Data Inseert Failed");
		}
		
		
		return sh;
	}
	
	@RequestMapping(value = { "/getAllChef" }, method = RequestMethod.GET)
	public @ResponseBody List<Shape> getAllChef() {

		Info info = new Info();
		
		List<Shape> shapeList=new ArrayList<Shape>();
	
	
		shapeList=shapeRepo.findAll();
			return (List<Shape>) shapeList;
		
	}
	
	@RequestMapping(value = { "/getAllChefById" }, method = RequestMethod.GET)
	public @ResponseBody Shape getAllChefById(@RequestParam Integer shapeId) {

		
		Shape sh=shapeRepo.findByShapeId(shapeId);
		return sh;
		 
	}
}
