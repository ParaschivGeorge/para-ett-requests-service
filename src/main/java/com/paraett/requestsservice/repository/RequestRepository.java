package com.paraett.requestsservice.repository;

import com.paraett.requestsservice.model.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>, JpaSpecificationExecutor<Request> {
    @Transactional
    @Modifying
    void deleteAllByCompanyId(Long companyId);
    @Transactional
    @Modifying
    void deleteAllByUserId(Long userId);
    @Transactional
    @Modifying
    void deleteAllByManagerId(Long managerId);
}
