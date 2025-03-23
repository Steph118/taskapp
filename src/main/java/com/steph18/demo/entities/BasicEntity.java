
package com.steph18.demo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BasicEntity implements Serializable {

    protected static final long serialVersionUID = 1L;

    @Column(name = "version", nullable = false)
    private int version = 1;
}
