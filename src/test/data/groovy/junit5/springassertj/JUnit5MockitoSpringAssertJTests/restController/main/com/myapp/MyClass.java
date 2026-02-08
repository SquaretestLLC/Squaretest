/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
public class MyClass {

    private final FooService fooService;
    private final BarService barService;
    private final MetricsServiceAdapter metricsService;

    @Autowired
    public MyClass(FooService fooService, BarService barService, MetricsServiceAdapter metricsService) {
        this.fooService = fooService;
        this.barService = barService;
        this.metricsService = metricsService;
    }

    @GetMapping("/foo/{id}")
    public FooData getFooById(@PathVariable int id) throws InvalidFooDataIdException {
        final FooData fooData = fooService.getFooDataById(id);
        this.metricsService.recordMetric("getFooById success");
        return fooData;
    }

    @GetMapping("/safefoo/{id}")
    public FooData safeGetFooById(@PathVariable int id) {
        try {
            final FooData fooData = fooService.getFooDataById(id);
            this.metricsService.recordMetric("getFooById success");
            return fooData;
        } catch (InvalidFooDataIdException e) {
            this.metricsService.recordMetric("getFooById error", e);
            return null;
        }
    }

    @GetMapping("/foos/")
    public List<FooData> getAllFoos() throws InvalidFooDataIdException {
        final List<FooData> fooData = fooService.getAllFoos();
        this.metricsService.recordMetric("getAllFoos success");
        return fooData;
    }

    @GetMapping("/bar/{id}")
    public BarBean getBarById(@PathVariable int id) throws InvalidBarDataIdException {
        final BarBean barBean = barService.getBarDataById(id);
        this.metricsService.recordMetric("getBarById success");
        return barBean;
    }

    @GetMapping("/safebar/{id}")
    public BarBean safeGetBarById(@PathVariable int id) {
        try {
            final BarBean bar = barService.getBarDataById(id);
            this.metricsService.recordMetric("getBarById success");
            return bar;
        } catch (InvalidBarDataIdException e) {
            this.metricsService.recordMetric("getBarById error", e);
            return null;
        }
    }

    @GetMapping("/bars/")
    public List<BarBean> getAllBars() throws IOException {
        final List<BarBean> bars = barService.getAllBars();
        this.metricsService.recordMetric("getAllBars success");
        return bars;
    }

    @GetMapping("/bar")
    public BarBean getBarWithGetParam(@RequestParam( value="id") final String id) throws InvalidBarDataIdException {
        final BarBean barBean = barService.getBarDataById(Long.parseLong(id));
        this.metricsService.recordMetric("getBarWithGetParam success");
        return barBean;
    }

    @GetMapping("/favoriteBar")
    public BarBean getFavoriteBarFromCookie(@CookieValue(name="favoriteBarId") final String id) throws InvalidBarDataIdException {
        final BarBean barBean = barService.getBarDataById(Long.parseLong(id));
        this.metricsService.recordMetric("getFavoriteBarFromCookie success");
        return barBean;
    }

    @GetMapping("/favoriteBarWithResponseEntity")
    public ResponseEntity<BarBean> getFavoriteBarFromCookieWithResponseEntity(@CookieValue(name="favoriteBarId") final String id) throws InvalidBarDataIdException {
        final BarBean barBean = barService.getBarDataById(Long.parseLong(id));
        this.metricsService.recordMetric("getFavoriteBarFromCookie success");
        return new ResponseEntity<>(barBean, HttpStatus.CREATED);
    }

    @GetMapping("/barsearch")
    public List<BarBean> getBarWithMultipleGetParams(@RequestParam( value="name") final String name, @RequestParam( value="startDate") final String startDate) throws IOException {
        final List<BarBean> barBeans = barService.getAllBars().stream().filter(x -> x.getName().equals(name) && x.getStartDate().equals(startDate)).collect(Collectors.toList());
        this.metricsService.recordMetric("getBarWithMultipleGetParams success");
        return barBeans;
    }

    @PostMapping("/postBar")
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewBar(@RequestBody final BarBean barBean) {
        barService.saveBar(barBean);
    }

    @PutMapping("/putBar")
    @ResponseStatus(HttpStatus.CREATED)
    public void putNewBar(@RequestBody final BarBean barBean) {
        barService.saveBar(barBean);
    }

    @PutMapping("/putBarWithHttpEntity")
    @ResponseStatus(HttpStatus.CREATED)
    public void putNewBarWithHttpEntity(final HttpEntity<BarBean> entity) {
        barService.saveBar(entity.getBody());
    }

    @PatchMapping("/patchBar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void patchBar(@RequestBody final BarBean barBean) {
        barService.saveBar(barBean);
    }

    @DeleteMapping("/bar/{id}")
    public void deleteBarWithId(@PathVariable int id) throws InvalidBarDataIdException, NetworkException {
        barService.deleteBarWithId(id);
        this.metricsService.recordMetric("deleteBarWithId success");
    }

    static void tryHttpRequestStuff(final RequestEntity<BarBean> request) {

    }

    static void tryHttpStuff(final HttpEntity<BarBean> request) {

    }

    /**
     * This is a non-controller method that should be ignored.
     * Its purpose is to provide a default value for 'bar' params.
     */
    @ModelAttribute("bar")
    public String getBar() {
        return "bar";
    }

    public static String getControllerId() {
        return "MyClassController";
    }

    static String urlEncode(final String paramValue) {
        return paramValue;
    }
}
