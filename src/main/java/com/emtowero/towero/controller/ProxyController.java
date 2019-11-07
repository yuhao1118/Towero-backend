package com.emtowero.towero.controller;

import com.emtowero.towero.dto.ResultModel;
import com.emtowero.towero.service.ProxyService;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/proxy", method = RequestMethod.GET)
public class ProxyController {
    @Resource
    private ProxyService proxyService;

    @RequestMapping(value = "/**")
    public ResultModel proxy(HttpServletRequest request) {
        String api = getExtractPath(request);
        System.out.println(api);
        return proxyService.getTbaResult(api);
    }

    private String getExtractPath(final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }

}
