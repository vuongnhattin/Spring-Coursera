package com.tin.springcoursera.repository;

import com.tin.springcoursera.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {
    List<Module> getModulesByCourseId(int courseId);
    @Query("""
            select m from Module m
            where m.moduleNo = ?2
            and m.courseId = ?1
            """)
    Optional<Module> getModuleByCourseIdAndModuleNo(int courseId, int moduleNo);
}
