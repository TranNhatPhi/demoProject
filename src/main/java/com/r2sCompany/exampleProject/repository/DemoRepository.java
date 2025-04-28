package com.r2sCompany.exampleProject.repository;

import com.r2sCompany.exampleProject.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity, Long> {
    // Bạn có thể thêm custom query nếu cần sau này
}
