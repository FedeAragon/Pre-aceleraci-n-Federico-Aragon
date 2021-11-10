package com.alkemy.challenge.challenge.repository.specifications;

import com.alkemy.challenge.challenge.dto.CharacterFilterDTO;
import com.alkemy.challenge.challenge.entity.Character;
import com.alkemy.challenge.challenge.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<Character> getByFilters(CharacterFilterDTO filtersDTO){
        return (root, query, criteriaBuildder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuildder.like(
                                criteriaBuildder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if(filtersDTO.getAge()>0) {
                predicates.add(
                        criteriaBuildder.equal(root.get("age"), filtersDTO.getAge())
                );
            }

            if(!CollectionUtils.isEmpty(filtersDTO.getMovies())) {
                Join<Movie, Character> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }

            //duplicates
            query.distinct(true);

            //order resolver
            String orderByField = "name";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuildder.asc(root.get(orderByField)) :
                            criteriaBuildder.desc(root.get(orderByField))
            );

            return criteriaBuildder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
