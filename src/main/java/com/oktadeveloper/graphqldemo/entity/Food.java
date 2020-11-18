package com.oktadeveloper.graphqldemo.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue
    @GraphQLQuery(name = "id")
    private Long id;

    @GraphQLQuery(name = "name")
    private String name;

}