package com.lippio.shortest_path.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CountryVortex {

    String cca3;
    String region;
    String subregion;
    ArrayList<NodeRelation> relations;
    String area;

}
