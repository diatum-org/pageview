package org.diatum.pageview.service;

import java.security.*;
import java.security.spec.*;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.diatum.pageview.jpa.entity.Page;
import org.diatum.pageview.jpa.repository.PageRepository;

@Service
public class PageService {

  @Autowired
  private PageRepository pageRepository;

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Transactional
  public Integer setView(String id) {
    Page page = pageRepository.findOneByPage(id);
    if(page == null) {
      page = new Page();
      page.setPage(id);
      page.setViews(0);
    }

    page.setViews(page.getViews() + 1);
    page = pageRepository.save(page);
    return page.getViews();      
  }

}

