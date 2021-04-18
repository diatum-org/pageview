package org.diatum.pageview.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import org.diatum.pageview.service.PageService;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Controller
public class PageApiController implements PageApi {

    private static final Logger log = LoggerFactory.getLogger(PageApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    private PageService pageService;

    @org.springframework.beans.factory.annotation.Autowired
    public PageApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Integer> setView(@NotNull @ApiParam(value = "id", required = true) @Valid @RequestParam(value = "id", required = true) String id
) {
    try {
        Integer views = pageService.setView(id);
        return new ResponseEntity<Integer>(views, HttpStatus.OK);
      }
      catch(Exception e) {
        log.error(e.toString());
        return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

}

