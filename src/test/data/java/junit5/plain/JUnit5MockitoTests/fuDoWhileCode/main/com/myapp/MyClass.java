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

import java.util.List;

public class MyClass {
    private final FooService fooService;
    private final MetricService metricService;

    public MyClass(final FooService fooService, final MetricService metricService) {
        this.fooService = fooService;
        this.metricService = metricService;
    }

    public void deleteFoo1(final String id) {
        do {
            fooService.deleteFoos1(id);
        } while (!fooService.getFoos1(id).isEmpty());
    }

    public void deleteFoo2(final String id) {
        do {
            fooService.deleteFoos1(id);
        } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
    }
    public void deleteFoo3(final String id) {
        do {
            final String deletedId = fooService.deleteFoos2(id);
            assert !deletedId.isEmpty();
        } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
    }
    public void deleteFoo4(final String id) {
        do {
            if(fooService.canDelete1(id)) {
                fooService.deleteFoos1(id);
                continue;
            }
        } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
    }
    public void deleteFoo5(final String id) {
        do {
            if(fooService.canDelete1(id)) {
                fooService.deleteFoos1(id);
                break;
            }
        } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
    }
    public void deleteFirstFoo1(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                }
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
        }
    }
    public void deleteFirstFoo2(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
    }
    public void deleteFirstFoo3(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                    continue;
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
    }
    public void deleteFirstFoo4(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        outerLoop:
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                    continue outerLoop;
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
    }
    public void deleteFirstFoo5(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        outerLoop:
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                    continue outerLoop;
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo6(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                    break;
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
    }
    public void deleteFirstFoo7(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        outerLoop:
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                    break outerLoop;
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
    }
    public void deleteFirstFoo8(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        outerLoop:
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                    break outerLoop;
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo9(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        fakeLabel:
        outerLoop:
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                    break outerLoop;
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo10(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        fakeLabel:
        outerLoop:
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                    break fakeLabel;
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo11(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    fooService.deleteFoos1(id);
                    return;
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo12(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    try {
                        fooService.deleteFoos3(id);
                    } catch (FooServiceException e) {
                        metricService.recordFooServiceException(id, e);
                    }
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo13(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    try {
                        fooService.deleteFoos3(id);
                    } catch (FooServiceException e) {
                        metricService.recordFooServiceException(id, e);
                        continue;
                    }
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo14(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        outerLoop:
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    try {
                        fooService.deleteFoos3(id);
                    } catch (FooServiceException e) {
                        metricService.recordFooServiceException(id, e);
                        continue outerLoop;
                    }
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo15(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    try {
                        fooService.deleteFoos3(id);
                    } catch (FooServiceException e) {
                        metricService.recordFooServiceException(id, e);
                        break;
                    }
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo16(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        outerLoop:
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    try {
                        fooService.deleteFoos3(id);
                    } catch (FooServiceException e) {
                        metricService.recordFooServiceException(id, e);
                        break outerLoop;
                    }
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo17(final FooType fooType) {
        final List<FooData> foos = fooService.getFoos2(fooType);
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    try {
                        fooService.deleteFoos3(id);
                    } catch (FooServiceException e) {
                        metricService.recordFooServiceException(id, e);
                        return;
                    }
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
    public void deleteFirstFoo18(final FooType fooType) throws FooServiceException {
        final List<FooData> foos = fooService.getFoos2(fooType);
        for(final FooData foo : foos) {
            final String id = foo.getId();
            do {
                if(fooService.canDelete1(id)) {
                    try {
                        fooService.deleteFoos3(id);
                    } catch (FooServiceException e) {
                        metricService.recordFooServiceException(id, e);
                        throw e;
                    }
                }
                metricService.recordBottomOfDoWhile(id);
            } while (fooService.getFooData1(id).orElseThrow().getId().equals(id));
            metricService.recordEndOfForeachLoop(id);
        }
        metricService.recordAfterForeachLoop(fooType);
    }
}
