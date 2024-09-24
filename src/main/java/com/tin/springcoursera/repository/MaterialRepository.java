package com.tin.springcoursera.repository;

import com.tin.springcoursera.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    @Query("""
            select m from Material m
            where m.moduleId = ?1
            """)
    List<Material> getMaterialsByModuleId(int moduleId);

    @Query("""
            select m from Material m
            where m.id in (
                select ma.id from Material ma, Module mo, Course c
                where ma.moduleId = mo.id
                and mo.courseId = c.id
                and c.id = ?1
                and mo.id = ?2
            )
            """)
    List<Material> getMaterialsByCourseIdAndModuleNo(int courseId, int moduleId);
}
