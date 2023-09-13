package com.aqua.fall23g1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * test_table
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestTable implements Serializable {
    private Integer id;

    private String testColumn;

    private static final long serialVersionUID = 1L;
}