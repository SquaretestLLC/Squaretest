package com.myapp;

import com.google.common.collect.*;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private ListMultimap mockListMultimap1;
    @Mock
    private HashBasedTable mockHashBasedTable1;
    @Mock
    private ImmutableRangeMap mockImmutableRangeMap1;
    @Mock
    private BigDecimal mockBigDecimal1;
    @Mock
    private Vector mockVector1;
    @Mock
    private Map mockMap1;
    @Mock
    private BigInteger mockBigInteger1;
    @Mock
    private GregorianCalendar mockGregorianCalendar1;
    @Mock
    private ConcurrentNavigableMap mockConcurrentNavigableMap1;
    @Mock
    private Set mockSet1;
    @Mock
    private CopyOnWriteArraySet mockCopyOnWriteArraySet1;
    @Mock
    private ImmutableListMultimap mockImmutableListMultimap1;
    @Mock
    private Dictionary mockDictionary1;
    @Mock
    private CopyOnWriteArrayList mockCopyOnWriteArrayList1;
    @Mock
    private SimpleDateFormat mockSimpleDateFormat1;
    @Mock
    private TreeSet mockTreeSet1;
    @Mock
    private ArrayList mockArrayList1;
    @Mock
    private LinkedHashMap mockLinkedHashMap1;
    @Mock
    private RandomAccess mockRandomAccess1;
    @Mock
    private ClassToInstanceMap mockClassToInstanceMap1;
    @Mock
    private ImmutableMultiset mockImmutableMultiset1;
    @Mock
    private ImmutableMultimap mockImmutableMultimap1;
    @Mock
    private Number mockNumber1;
    @Mock
    private LinkedHashSet mockLinkedHashSet1;
    @Mock
    private ImmutableSortedSet mockImmutableSortedSet1;
    @Mock
    private Properties mockProperties1;
    @Mock
    private TreeMap mockTreeMap1;
    @Mock
    private SortedSet mockSortedSet1;
    @Mock
    private Date mockDate1;
    @Mock
    private PriorityQueue mockPriorityQueue1;
    @Mock
    private ImmutableCollection mockImmutableCollection1;
    @Mock
    private DateFormat mockDateFormat1;
    @Mock
    private ImmutableSetMultimap mockImmutableSetMultimap1;
    @Mock
    private LinkedListMultimap mockLinkedListMultimap1;
    @Mock
    private BiMap mockBiMap1;
    @Mock
    private ImmutableSet mockImmutableSet1;
    @Mock
    private IdentityHashMap mockIdentityHashMap1;
    @Mock
    private ImmutableMap mockImmutableMap1;
    @Mock
    private Collection mockCollection1;
    @Mock
    private NavigableSet mockNavigableSet1;
    @Mock
    private NavigableMap mockNavigableMap1;
    @Mock
    private List mockList1;
    @Mock
    private Queue mockQueue1;
    @Mock
    private SortedMap mockSortedMap1;
    @Mock
    private HashMap mockHashMap1;
    @Mock
    private Deque mockDeque1;
    @Mock
    private Stack mockStack1;
    @Mock
    private ConcurrentMap mockConcurrentMap1;
    @Mock
    private HashSet mockHashSet1;
    @Mock
    private Charset mockCharset1;
    @Mock
    private LinkedList mockLinkedList1;
    @Mock
    private Hashtable mockHashtable1;
    @Mock
    private WeakHashMap mockWeakHashMap1;
    @Mock
    private ImmutableTable mockImmutableTable1;
    @Mock
    private BitSet mockBitSet1;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }
}
