package com.ats.webapi.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.Shape;



public interface ShapeRepo extends JpaRepository<Shape, Integer> {
Shape save(Shape shape);


@Query(value="SELECT * FROM m_shape WHERE shape_id=:shapeId AND del_status=0 ",nativeQuery=true)
Shape findByShapeId(@Param("shapeId") int shapeId);

@Query(value="SELECT * FROM m_shape WHERE  del_status=0",nativeQuery=true)
List<Shape> getAllShapes();


@Modifying
@Transactional
@Query(value="UPDATE m_shape SET del_status=1 WHERE shape_id=:shapeId",nativeQuery=true)
int deleteShape(@Param("shapeId") int shapeId);

}
