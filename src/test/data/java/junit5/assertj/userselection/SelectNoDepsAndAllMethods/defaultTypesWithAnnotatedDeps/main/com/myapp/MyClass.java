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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.BiMap;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.EnumBiMap;
import com.google.common.collect.EnumHashBiMap;
import com.google.common.collect.EnumMultiset;
import com.google.common.collect.EvictingQueue;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableClassToInstanceMap;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Deque;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.inject.Inject;

public class MyClass {

    @Inject
    ArrayListMultimap ArrayListMultimap1 = ArrayListMultimap.create();
    @Inject
    Float Float1 = 0.0f;
    @Inject
    ListMultimap ListMultimap1 = LinkedListMultimap.create();
    @Inject
    ZonedDateTime ZonedDateTime1 = ZonedDateTime.of(LocalDateTime.of(2017,1,1,0,0), ZoneId.of("Z"));
    @Inject
    HashBasedTable HashBasedTable1 = HashBasedTable.create();
    @Inject
    Integer Integer1 = 0;
    @Inject
    ImmutableRangeMap ImmutableRangeMap1 = ImmutableRangeMap.of();
    @Inject
    BigDecimal BigDecimal1 = new BigDecimal("0.00");
    @Inject
    Vector Vector1 = new Vector(Arrays.asList());
    @Inject
    Character Character1 = 'a';
    @Inject
    Long Long1 = 0L;
    @Inject
    Map Map1 = new HashMap<>();
    @Inject
    LinkedHashMultiset LinkedHashMultiset1 = LinkedHashMultiset.create();
    @Inject
    BigInteger BigInteger1 = new BigInteger("100");
    @Inject
    ImmutableRangeSet ImmutableRangeSet1 = ImmutableRangeSet.of();
    @Inject
    ZoneOffset ZoneOffset1 = ZoneOffset.UTC;
    @Inject
    LinkedHashMultimap LinkedHashMultimap1 = LinkedHashMultimap.create();
    @Inject
    Void Void1 = null;
    @Inject
    GregorianCalendar GregorianCalendar1 = new GregorianCalendar(2017, 1, 1, 0, 0, 0);
    @Inject
    byte byte1 = (byte) 0b0;
    @Inject
    double double1 = 0.0;
    @Inject
    ConcurrentNavigableMap ConcurrentNavigableMap1 = new ConcurrentSkipListMap<>();
    @Inject
    Set Set1 = new HashSet<>();
    @Inject
    CopyOnWriteArraySet CopyOnWriteArraySet1 = new CopyOnWriteArraySet<>(Arrays.asList());
    @Inject
    ImmutableListMultimap ImmutableListMultimap1 = ImmutableListMultimap.of();
    @Inject
    StringBuilder StringBuilder1 = new StringBuilder();
    @Inject
    Dictionary Dictionary1 = new Hashtable<>();
    @Inject
    CopyOnWriteArrayList CopyOnWriteArrayList1 = new CopyOnWriteArrayList<>(Arrays.asList());
    @Inject
    Double Double1 = 0.0;
    @Inject
    LocalDateTime LocalDateTime1 = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
    @Inject
    SimpleDateFormat SimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US);
    @Inject
    Currency Currency1 = Currency.getInstance("USD");
    @Inject
    TreeSet TreeSet1 = new TreeSet<>(Arrays.asList());
    @Inject
    long long1 = 0L;
    @Inject
    ArrayList ArrayList1 = new ArrayList<>(Arrays.asList());
    @Inject
    LinkedHashMap LinkedHashMap1 = new LinkedHashMap<>();
    @Inject
    RandomAccess RandomAccess1 = new ArrayList<>(Arrays.asList());
    @Inject
    ClassToInstanceMap ClassToInstanceMap1 = new ImmutableClassToInstanceMap.Builder().build();
    @Inject
    ImmutableMultiset ImmutableMultiset1 = ImmutableMultiset.of();
    @Inject
    ImmutableMultimap ImmutableMultimap1 = ImmutableListMultimap.of();
    @Inject
    Number Number1 = new BigDecimal("0.00");
    @Inject
    ImmutableSortedMap ImmutableSortedMap1 = ImmutableSortedMap.of();
    @Inject
    LinkedHashSet LinkedHashSet1 = new LinkedHashSet<>();
    @Inject
    ImmutableSortedSet ImmutableSortedSet1 = ImmutableSortedSet.of();
    @Inject
    Properties Properties1 = new Properties();
    @Inject
    ConcurrentHashMultiset ConcurrentHashMultiset1 = ConcurrentHashMultiset.create(Arrays.asList());
    @Inject
    TreeMap TreeMap1 = new TreeMap<>();
    @Inject
    DayOfWeek DayOfWeek1 = DayOfWeek.FRIDAY;
    @Inject
    char char1 = 'a';
    @Inject
    YearMonth YearMonth1 = YearMonth.of(2017, Month.JANUARY);
    @Inject
    SortedSet SortedSet1 = new TreeSet<>(Arrays.asList());
    @Inject
    Date Date1 = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime();
    @Inject
    PriorityQueue PriorityQueue1 = new PriorityQueue<>();
    @Inject
    ImmutableCollection ImmutableCollection1 = ImmutableList.of();
    @Inject
    float float1 = 0.0f;
    @Inject
    ArrayTable ArrayTable1 = ArrayTable.create(Arrays.asList(), Arrays.asList());
    @Inject
    HashMultimap HashMultimap1 = HashMultimap.create();
    @Inject
    HashMultiset HashMultiset1 = HashMultiset.create(Arrays.asList());
    @Inject
    Short Short1 = 0;
    @Inject
    Locale Locale1 = Locale.US;
    @Inject
    LocalTime LocalTime1 = LocalTime.of(12, 0, 0);
    @Inject
    Byte Byte1 = (byte) 0b0;
    @Inject
    DateFormat DateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US);
    @Inject
    ImmutableSetMultimap ImmutableSetMultimap1 = ImmutableSetMultimap.of();
    @Inject
    LinkedListMultimap LinkedListMultimap1 = LinkedListMultimap.create();
    @Inject
    BiMap BiMap1 = HashBiMap.create(ImmutableMap.of());
    @Inject
    ImmutableSet ImmutableSet1 = ImmutableSet.of();
    @Inject
    IdentityHashMap IdentityHashMap1 = new IdentityHashMap<>();
    @Inject
    ImmutableMap ImmutableMap1 = ImmutableMap.of();
    @Inject
    Collection Collection1 = Arrays.asList();
    @Inject
    NavigableSet NavigableSet1 = new TreeSet<>(Arrays.asList());
    @Inject
    NavigableMap NavigableMap1 = new TreeMap<>();
    @Inject
    List List1 = Arrays.asList();
    @Inject
    Year Year1 = Year.of(2017);
    @Inject
    LocalDate LocalDate1 = LocalDate.of(2017, 1, 1);
    @Inject
    Queue Queue1 = new LinkedList<>(Arrays.asList());
    @Inject
    ImmutableClassToInstanceMap ImmutableClassToInstanceMap1 = new ImmutableClassToInstanceMap.Builder().build();
    @Inject
    SortedMap SortedMap1 = new TreeMap<>();
    @Inject
    EnumHashBiMap EnumHashBiMap1 = null;
    @Inject
    EnumBiMap EnumBiMap1 = null;
    @Inject
    HashMap HashMap1 = new HashMap<>();
    @Inject
    Deque Deque1 = new LinkedList<>(Arrays.asList());
    @Inject
    Stack Stack1 = new Stack<>();
    @Inject
    ConcurrentMap ConcurrentMap1 = new ConcurrentHashMap<>();
    @Inject
    HashSet HashSet1 = new HashSet<>(Arrays.asList());
    @Inject
    Charset Charset1 = StandardCharsets.UTF_8;
    @Inject
    EvictingQueue EvictingQueue1 = EvictingQueue.create(10);
    @Inject
    Boolean Boolean1 = false;
    @Inject
    LinkedList LinkedList1 = new LinkedList<>(Arrays.asList());
    @Inject
    Hashtable Hashtable1 = new Hashtable<>();
    @Inject
    WeakHashMap WeakHashMap1 = new WeakHashMap<>();
    @Inject
    Period Period1 = Period.between(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 2));
    @Inject
    Month Month1 = Month.JANUARY;
    @Inject
    MonthDay MonthDay1 = MonthDay.of(Month.JANUARY, 1);
    @Inject
    int int1 = 0;
    @Inject
    HashBiMap HashBiMap1 = HashBiMap.create(ImmutableMap.of());
    @Inject
    boolean boolean1 = false;
    @Inject
    ImmutableTable ImmutableTable1 = ImmutableTable.builder().build();
    @Inject
    short short1 = 0;
    @Inject
    BitSet BitSet1 = new BitSet();
    @Inject
    EnumMultiset EnumMultiset1 = null;
}
