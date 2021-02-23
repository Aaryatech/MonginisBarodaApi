package com.ats.webapi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.Shape;



public interface ShapeRepo extends JpaRepository<Shape, Integer> {
Shape save(Shape shape);



Shape findByShapeId(int shapeId);
}
