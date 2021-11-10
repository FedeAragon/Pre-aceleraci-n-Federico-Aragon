package com.alkemy.challenge.challenge.repository.specifications;

import com.alkemy.challenge.challenge.dto.MovieDTO;
import com.alkemy.challenge.challenge.dto.MovieFilterDTO;
import com.alkemy.challenge.challenge.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<Movie> getByFilters(MovieFilterDTO filtersDTO){
        return (root, query, criteriaBuildder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filtersDTO.getTitle())) {
                predicates.add(
                        criteriaBuildder.like(
                                criteriaBuildder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }

            if(filtersDTO.getGenreId()>0) {

                predicates.add(
                        criteriaBuildder.equal(root.get("genreId"), filtersDTO.getGenreId())
                );
            }

            //duplicates
            query.distinct(true);

            //order resolver
            String orderByField = "title";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuildder.asc(root.get(orderByField)) :
                            criteriaBuildder.desc(root.get(orderByField))
            );

            return criteriaBuildder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
