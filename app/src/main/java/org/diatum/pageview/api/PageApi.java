package org.diatum.pageview.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@Api(value = "page", description = "page view count")
public interface PageApi {

    @ApiOperation(value = "", nickname = "setView", notes = "add view count", response = Integer.class, tags={ "page", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Integer.class),
        @ApiResponse(code = 500, message = "internal server error") })
    @RequestMapping(value = "/view",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Integer> setView(@NotNull @ApiParam(value = "page identifier", required = true) @Valid @RequestParam(value = "id", required = true) String id
);

}


