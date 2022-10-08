package com.lippio.shortest_path.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Country {

    String cca3;
    String region;
    String subregion;
    ArrayList<String> borders;
    String area;
//    latlng  - ?

}
