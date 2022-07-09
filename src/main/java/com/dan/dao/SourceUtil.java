package com.dan.dao;

import org.postgresql.ds.PGPoolingDataSource;

public class SourceUtil {
  private PGPoolingDataSource source;

  public SourceUtil() {
    source = new PGPoolingDataSource();
    source.setServerName("ec2-3-219-52-220.compute-1.amazonaws.com");
    source.setDatabaseName("d87q8v1p2jorm1");
    source.setUser("jllpdpjeljafsq");
    source.setPassword("f5cf29cb8c6a68de19e09ef32a9933486f33068b508d3502c7fb607dcad98eaf");
    source.setMaxConnections(10);
  }

  public PGPoolingDataSource getSource() {
    return source;
  }

}