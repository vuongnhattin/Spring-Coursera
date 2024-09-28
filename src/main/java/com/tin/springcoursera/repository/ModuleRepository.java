package com.tin.springcoursera.repository;

import com.tin.springcoursera.entity.Module;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {
    @Query("""
            select m from Module m
            where m.courseId = ?1
            order by m.name asc
            """)
    List<Module> getModulesByCourseId(int courseId);

    Optional<Module> findModuleByNameAndCourseId(String name, int courseId);

    @Query("""
        select m from Module m
        where m.courseId in (
            select m.courseId from Module m
            where m.id = ?1
        )
        and m.id <> ?1
        """)
    List<Module> getModulesInSameCourse(int moduleId);
}
