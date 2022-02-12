package com.golfie.rounding.domain;

import com.golfie.style.domain.Style;
import javax.persistence.*;

@Entity
public class Rounding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Style style;

}
