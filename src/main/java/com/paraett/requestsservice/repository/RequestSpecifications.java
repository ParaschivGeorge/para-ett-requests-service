package com.paraett.requestsservice.repository;

import com.paraett.requestsservice.model.entities.Request;
import com.paraett.requestsservice.model.enums.RequestStatus;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestSpecifications {
    public static Specification<Request> findAllFiltered(Long companyId, Long managerId, Long userId, RequestStatus status, Date minDate, Date maxDate) {
        return new Specification<Request>() {
            @Override
            public Predicate toPredicate(Root<Request> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final List<Predicate> predicateList = new ArrayList<>();

                if (companyId != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("companyId"), companyId));
                }

                if (managerId != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("managerId"), managerId));
                }

                if (userId != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("userId"), userId));
                }

                if (status != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("status"), status));
                }

                if (minDate != null) {
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), minDate));
                }

                if (maxDate != null) {
                    predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"), maxDate));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}