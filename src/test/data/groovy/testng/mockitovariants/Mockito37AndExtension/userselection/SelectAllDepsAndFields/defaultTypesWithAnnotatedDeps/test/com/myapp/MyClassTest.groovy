package com.myapp

import com.google.common.collect.*
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners

import java.nio.charset.Charset
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.*
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.ConcurrentNavigableMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CopyOnWriteArraySet

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private ListMultimap mockListMultimap1
    @Mock
    private HashBasedTable mockHashBasedTable1
    @Mock
    private ImmutableRangeMap mockImmutableRangeMap1
    @Mock
    private BigDecimal mockBigDecimal1
    @Mock
    private Vector mockVector1
    @Mock
    private Map mockMap1
    @Mock
    private BigInteger mockBigInteger1
    @Mock
    private GregorianCalendar mockGregorianCalendar1
    @Mock
    private ConcurrentNavigableMap mockConcurrentNavigableMap1
    @Mock
    private Set mockSet1
    @Mock
    private CopyOnWriteArraySet mockCopyOnWriteArraySet1
    @Mock
    private ImmutableListMultimap mockImmutableListMultimap1
    @Mock
    private Dictionary mockDictionary1
    @Mock
    private CopyOnWriteArrayList mockCopyOnWriteArrayList1
    @Mock
    private SimpleDateFormat mockSimpleDateFormat1
    @Mock
    private TreeSet mockTreeSet1
    @Mock
    private ArrayList mockArrayList1
    @Mock
    private LinkedHashMap mockLinkedHashMap1
    @Mock
    private RandomAccess mockRandomAccess1
    @Mock
    private ClassToInstanceMap mockClassToInstanceMap1
    @Mock
    private ImmutableMultiset mockImmutableMultiset1
    @Mock
    private ImmutableMultimap mockImmutableMultimap1
    @Mock
    private Number mockNumber1
    @Mock
    private LinkedHashSet mockLinkedHashSet1
    @Mock
    private ImmutableSortedSet mockImmutableSortedSet1
    @Mock
    private Properties mockProperties1
    @Mock
    private TreeMap mockTreeMap1
    @Mock
    private SortedSet mockSortedSet1
    @Mock
    private Date mockDate1
    @Mock
    private PriorityQueue mockPriorityQueue1
    @Mock
    private ImmutableCollection mockImmutableCollection1
    @Mock
    private DateFormat mockDateFormat1
    @Mock
    private ImmutableSetMultimap mockImmutableSetMultimap1
    @Mock
    private LinkedListMultimap mockLinkedListMultimap1
    @Mock
    private BiMap mockBiMap1
    @Mock
    private ImmutableSet mockImmutableSet1
    @Mock
    private IdentityHashMap mockIdentityHashMap1
    @Mock
    private ImmutableMap mockImmutableMap1
    @Mock
    private Collection mockCollection1
    @Mock
    private NavigableSet mockNavigableSet1
    @Mock
    private NavigableMap mockNavigableMap1
    @Mock
    private List mockList1
    @Mock
    private Queue mockQueue1
    @Mock
    private SortedMap mockSortedMap1
    @Mock
    private HashMap mockHashMap1
    @Mock
    private Deque mockDeque1
    @Mock
    private Stack mockStack1
    @Mock
    private ConcurrentMap mockConcurrentMap1
    @Mock
    private HashSet mockHashSet1
    @Mock
    private Charset mockCharset1
    @Mock
    private LinkedList mockLinkedList1
    @Mock
    private Hashtable mockHashtable1
    @Mock
    private WeakHashMap mockWeakHashMap1
    @Mock
    private ImmutableTable mockImmutableTable1
    @Mock
    private BitSet mockBitSet1

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.ArrayListMultimap1 = ArrayListMultimap.create()
        myClassUnderTest.Float1 = 0.0f
        myClassUnderTest.ListMultimap1 = mockListMultimap1
        myClassUnderTest.ZonedDateTime1 = ZonedDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0), ZoneId.of("UTC"))
        myClassUnderTest.HashBasedTable1 = mockHashBasedTable1
        myClassUnderTest.Integer1 = 0
        myClassUnderTest.ImmutableRangeMap1 = mockImmutableRangeMap1
        myClassUnderTest.BigDecimal1 = mockBigDecimal1
        myClassUnderTest.Vector1 = mockVector1
        myClassUnderTest.Character1 = 'a'
        myClassUnderTest.Long1 = 0L
        myClassUnderTest.Map1 = mockMap1
        myClassUnderTest.LinkedHashMultiset1 = LinkedHashMultiset.create([])
        myClassUnderTest.BigInteger1 = mockBigInteger1
        myClassUnderTest.ImmutableRangeSet1 = ImmutableRangeSet.of()
        myClassUnderTest.ZoneOffset1 = ZoneOffset.UTC
        myClassUnderTest.LinkedHashMultimap1 = LinkedHashMultimap.create()
        myClassUnderTest.Void1 = null
        myClassUnderTest.GregorianCalendar1 = mockGregorianCalendar1
        myClassUnderTest.byte1 = (byte) 0b0
        myClassUnderTest.double1 = 0.0d
        myClassUnderTest.ConcurrentNavigableMap1 = mockConcurrentNavigableMap1
        myClassUnderTest.Set1 = mockSet1
        myClassUnderTest.CopyOnWriteArraySet1 = mockCopyOnWriteArraySet1
        myClassUnderTest.ImmutableListMultimap1 = mockImmutableListMultimap1
        myClassUnderTest.StringBuilder1 = new StringBuilder()
        myClassUnderTest.Dictionary1 = mockDictionary1
        myClassUnderTest.CopyOnWriteArrayList1 = mockCopyOnWriteArrayList1
        myClassUnderTest.Double1 = 0.0d
        myClassUnderTest.LocalDateTime1 = LocalDateTime.of(2020, 1, 1, 0, 0, 0)
        myClassUnderTest.SimpleDateFormat1 = mockSimpleDateFormat1
        myClassUnderTest.Currency1 = Currency.getInstance("USD")
        myClassUnderTest.TreeSet1 = mockTreeSet1
        myClassUnderTest.long1 = 0L
        myClassUnderTest.ArrayList1 = mockArrayList1
        myClassUnderTest.LinkedHashMap1 = mockLinkedHashMap1
        myClassUnderTest.RandomAccess1 = mockRandomAccess1
        myClassUnderTest.ClassToInstanceMap1 = mockClassToInstanceMap1
        myClassUnderTest.ImmutableMultiset1 = mockImmutableMultiset1
        myClassUnderTest.ImmutableMultimap1 = mockImmutableMultimap1
        myClassUnderTest.Number1 = mockNumber1
        myClassUnderTest.ImmutableSortedMap1 = ImmutableSortedMap.of()
        myClassUnderTest.LinkedHashSet1 = mockLinkedHashSet1
        myClassUnderTest.ImmutableSortedSet1 = mockImmutableSortedSet1
        myClassUnderTest.Properties1 = mockProperties1
        myClassUnderTest.ConcurrentHashMultiset1 = ConcurrentHashMultiset.create([])
        myClassUnderTest.TreeMap1 = mockTreeMap1
        myClassUnderTest.DayOfWeek1 = DayOfWeek.FRIDAY
        myClassUnderTest.char1 = 'a'
        myClassUnderTest.YearMonth1 = YearMonth.of(2020, Month.JANUARY)
        myClassUnderTest.SortedSet1 = mockSortedSet1
        myClassUnderTest.Date1 = mockDate1
        myClassUnderTest.PriorityQueue1 = mockPriorityQueue1
        myClassUnderTest.ImmutableCollection1 = mockImmutableCollection1
        myClassUnderTest.float1 = 0.0f
        myClassUnderTest.ArrayTable1 = ArrayTable.create([], [])
        myClassUnderTest.HashMultimap1 = HashMultimap.create()
        myClassUnderTest.HashMultiset1 = HashMultiset.create([])
        myClassUnderTest.Short1 = (short) 0
        myClassUnderTest.Locale1 = Locale.US
        myClassUnderTest.LocalTime1 = LocalTime.of(0, 0, 0)
        myClassUnderTest.Byte1 = (byte) 0b0
        myClassUnderTest.DateFormat1 = mockDateFormat1
        myClassUnderTest.ImmutableSetMultimap1 = mockImmutableSetMultimap1
        myClassUnderTest.LinkedListMultimap1 = mockLinkedListMultimap1
        myClassUnderTest.BiMap1 = mockBiMap1
        myClassUnderTest.ImmutableSet1 = mockImmutableSet1
        myClassUnderTest.IdentityHashMap1 = mockIdentityHashMap1
        myClassUnderTest.ImmutableMap1 = mockImmutableMap1
        myClassUnderTest.Collection1 = mockCollection1
        myClassUnderTest.NavigableSet1 = mockNavigableSet1
        myClassUnderTest.NavigableMap1 = mockNavigableMap1
        myClassUnderTest.List1 = mockList1
        myClassUnderTest.Year1 = Year.of(2020)
        myClassUnderTest.LocalDate1 = LocalDate.of(2020, 1, 1)
        myClassUnderTest.Queue1 = mockQueue1
        myClassUnderTest.ImmutableClassToInstanceMap1 = new ImmutableClassToInstanceMap.Builder().build()
        myClassUnderTest.SortedMap1 = mockSortedMap1
        myClassUnderTest.EnumHashBiMap1 = null
        myClassUnderTest.EnumBiMap1 = null
        myClassUnderTest.HashMap1 = mockHashMap1
        myClassUnderTest.Deque1 = mockDeque1
        myClassUnderTest.Stack1 = mockStack1
        myClassUnderTest.ConcurrentMap1 = mockConcurrentMap1
        myClassUnderTest.HashSet1 = mockHashSet1
        myClassUnderTest.Charset1 = mockCharset1
        myClassUnderTest.EvictingQueue1 = EvictingQueue.create(10)
        myClassUnderTest.Boolean1 = false
        myClassUnderTest.LinkedList1 = mockLinkedList1
        myClassUnderTest.Hashtable1 = mockHashtable1
        myClassUnderTest.WeakHashMap1 = mockWeakHashMap1
        myClassUnderTest.Period1 = Period.between(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2))
        myClassUnderTest.Month1 = Month.JANUARY
        myClassUnderTest.MonthDay1 = MonthDay.of(Month.JANUARY, 1)
        myClassUnderTest.int1 = 0
        myClassUnderTest.HashBiMap1 = HashBiMap.create(ImmutableMap.of())
        myClassUnderTest.boolean1 = false
        myClassUnderTest.ImmutableTable1 = mockImmutableTable1
        myClassUnderTest.short1 = (short) 0
        myClassUnderTest.BitSet1 = mockBitSet1
        myClassUnderTest.EnumMultiset1 = null
    }
}
