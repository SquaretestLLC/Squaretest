package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.google.common.collect.*
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.runner.RunWith

import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.time.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentSkipListMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CopyOnWriteArraySet

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.ArrayListMultimap1 = ArrayListMultimap.create()
        myClassUnderTest.Float1 = 0.0f
        myClassUnderTest.ListMultimap1 = LinkedListMultimap.create()
        myClassUnderTest.ZonedDateTime1 = ZonedDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0), ZoneId.of("UTC"))
        myClassUnderTest.HashBasedTable1 = HashBasedTable.create()
        myClassUnderTest.Integer1 = 0
        myClassUnderTest.ImmutableRangeMap1 = ImmutableRangeMap.of()
        myClassUnderTest.BigDecimal1 = new BigDecimal("0.00")
        myClassUnderTest.Vector1 = new Vector([])
        myClassUnderTest.Character1 = 'a'
        myClassUnderTest.Long1 = 0L
        myClassUnderTest.Map1 = [:]
        myClassUnderTest.LinkedHashMultiset1 = LinkedHashMultiset.create([])
        myClassUnderTest.BigInteger1 = new BigInteger("100")
        myClassUnderTest.ImmutableRangeSet1 = ImmutableRangeSet.of()
        myClassUnderTest.ZoneOffset1 = ZoneOffset.UTC
        myClassUnderTest.LinkedHashMultimap1 = LinkedHashMultimap.create()
        myClassUnderTest.Void1 = null
        myClassUnderTest.GregorianCalendar1 = new GregorianCalendar(2020, Calendar.JANUARY, 1, 0, 0, 0)
        myClassUnderTest.byte1 = (byte) 0b0
        myClassUnderTest.double1 = 0.0d
        myClassUnderTest.ConcurrentNavigableMap1 = new ConcurrentSkipListMap<>([:])
        myClassUnderTest.Set1 = new HashSet<>([])
        myClassUnderTest.CopyOnWriteArraySet1 = new CopyOnWriteArraySet<>([])
        myClassUnderTest.ImmutableListMultimap1 = ImmutableListMultimap.of()
        myClassUnderTest.StringBuilder1 = new StringBuilder()
        myClassUnderTest.Dictionary1 = new Hashtable<>([:])
        myClassUnderTest.CopyOnWriteArrayList1 = new CopyOnWriteArrayList<>([])
        myClassUnderTest.Double1 = 0.0d
        myClassUnderTest.LocalDateTime1 = LocalDateTime.of(2020, 1, 1, 0, 0, 0)
        myClassUnderTest.SimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US)
        myClassUnderTest.Currency1 = Currency.getInstance("USD")
        myClassUnderTest.TreeSet1 = new TreeSet<>([])
        myClassUnderTest.long1 = 0L
        myClassUnderTest.ArrayList1 = new ArrayList<>([])
        myClassUnderTest.LinkedHashMap1 = new LinkedHashMap<>([:])
        myClassUnderTest.RandomAccess1 = new ArrayList<>([])
        myClassUnderTest.ClassToInstanceMap1 = new ImmutableClassToInstanceMap.Builder().build()
        myClassUnderTest.ImmutableMultiset1 = ImmutableMultiset.of()
        myClassUnderTest.ImmutableMultimap1 = ImmutableListMultimap.of()
        myClassUnderTest.Number1 = new BigDecimal("0.00")
        myClassUnderTest.ImmutableSortedMap1 = ImmutableSortedMap.of()
        myClassUnderTest.LinkedHashSet1 = new LinkedHashSet<>([])
        myClassUnderTest.ImmutableSortedSet1 = ImmutableSortedSet.of()
        myClassUnderTest.Properties1 = new Properties()
        myClassUnderTest.ConcurrentHashMultiset1 = ConcurrentHashMultiset.create([])
        myClassUnderTest.TreeMap1 = new TreeMap<>([:])
        myClassUnderTest.DayOfWeek1 = DayOfWeek.FRIDAY
        myClassUnderTest.char1 = 'a'
        myClassUnderTest.YearMonth1 = YearMonth.of(2020, Month.JANUARY)
        myClassUnderTest.SortedSet1 = new TreeSet<>([])
        myClassUnderTest.Date1 = new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime()
        myClassUnderTest.PriorityQueue1 = new PriorityQueue<>()
        myClassUnderTest.ImmutableCollection1 = ImmutableList.of()
        myClassUnderTest.float1 = 0.0f
        myClassUnderTest.ArrayTable1 = ArrayTable.create([], [])
        myClassUnderTest.HashMultimap1 = HashMultimap.create()
        myClassUnderTest.HashMultiset1 = HashMultiset.create([])
        myClassUnderTest.Short1 = (short) 0
        myClassUnderTest.Locale1 = Locale.US
        myClassUnderTest.LocalTime1 = LocalTime.of(0, 0, 0)
        myClassUnderTest.Byte1 = (byte) 0b0
        myClassUnderTest.DateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US)
        myClassUnderTest.ImmutableSetMultimap1 = ImmutableSetMultimap.of()
        myClassUnderTest.LinkedListMultimap1 = LinkedListMultimap.create()
        myClassUnderTest.BiMap1 = HashBiMap.create(ImmutableMap.of())
        myClassUnderTest.ImmutableSet1 = ImmutableSet.of()
        myClassUnderTest.IdentityHashMap1 = new IdentityHashMap<>([:])
        myClassUnderTest.ImmutableMap1 = ImmutableMap.of()
        myClassUnderTest.Collection1 = []
        myClassUnderTest.NavigableSet1 = new TreeSet<>([])
        myClassUnderTest.NavigableMap1 = new TreeMap<>([:])
        myClassUnderTest.List1 = []
        myClassUnderTest.Year1 = Year.of(2020)
        myClassUnderTest.LocalDate1 = LocalDate.of(2020, 1, 1)
        myClassUnderTest.Queue1 = new LinkedList<>([])
        myClassUnderTest.ImmutableClassToInstanceMap1 = new ImmutableClassToInstanceMap.Builder().build()
        myClassUnderTest.SortedMap1 = new TreeMap<>([:])
        myClassUnderTest.EnumHashBiMap1 = null
        myClassUnderTest.EnumBiMap1 = null
        myClassUnderTest.HashMap1 = [:]
        myClassUnderTest.Deque1 = new LinkedList<>([])
        myClassUnderTest.Stack1 = new Stack<>()
        myClassUnderTest.ConcurrentMap1 = new ConcurrentHashMap<>([:])
        myClassUnderTest.HashSet1 = new HashSet<>([])
        myClassUnderTest.Charset1 = StandardCharsets.UTF_8
        myClassUnderTest.EvictingQueue1 = EvictingQueue.create(10)
        myClassUnderTest.Boolean1 = false
        myClassUnderTest.LinkedList1 = new LinkedList<>([])
        myClassUnderTest.Hashtable1 = new Hashtable<>([:])
        myClassUnderTest.WeakHashMap1 = new WeakHashMap<>([:])
        myClassUnderTest.Period1 = Period.between(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2))
        myClassUnderTest.Month1 = Month.JANUARY
        myClassUnderTest.MonthDay1 = MonthDay.of(Month.JANUARY, 1)
        myClassUnderTest.int1 = 0
        myClassUnderTest.HashBiMap1 = HashBiMap.create(ImmutableMap.of())
        myClassUnderTest.boolean1 = false
        myClassUnderTest.ImmutableTable1 = ImmutableTable.builder().build()
        myClassUnderTest.short1 = (short) 0
        myClassUnderTest.BitSet1 = new BitSet()
        myClassUnderTest.EnumMultiset1 = null
    }
}
