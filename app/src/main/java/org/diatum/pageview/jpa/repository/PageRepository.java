package org.diatum.pageview.jpa.repository;

import org.springframework.data.jpa.repository.*;

import org.diatum.pageview.jpa.entity.Page;

public interface PageRepository
      extends JpaRepository<Page, Integer>, JpaSpecificationExecutor<Page> {
  Page findOneByPage(String page);
}


