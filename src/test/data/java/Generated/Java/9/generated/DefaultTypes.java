package generated;

public class DefaultTypes {
public DefaultTypes() throws Exception {}
private org.apache.commons.collections4.bag.SynchronizedBag SynchronizedBag0 = org.apache.commons.collections4.bag.SynchronizedBag.synchronizedBag(new org.apache.commons.collections4.bag.HashBag<>(java.util.List.of()));
private org.apache.commons.collections4.bag.SynchronizedBag<String> SynchronizedBag1 = org.apache.commons.collections4.bag.SynchronizedBag.synchronizedBag(new org.apache.commons.collections4.bag.HashBag<>(java.util.List.of("value")));
private java.util.Vector Vector0 = new java.util.Vector(java.util.List.of());
private java.util.Vector<String> Vector1 = new java.util.Vector(java.util.List.of("value"));
private org.apache.commons.collections4.SetValuedMap SetValuedMap0 = new org.apache.commons.collections4.multimap.HashSetValuedHashMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.SetValuedMap<String, Integer> SetValuedMap1 = new org.apache.commons.collections4.multimap.HashSetValuedHashMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.apache.commons.collections4.multiset.SynchronizedMultiSet SynchronizedMultiSet0 = org.apache.commons.collections4.multiset.SynchronizedMultiSet.synchronizedMultiSet(new org.apache.commons.collections4.multiset.HashMultiSet<>(java.util.List.of()));
private org.apache.commons.collections4.multiset.SynchronizedMultiSet<String> SynchronizedMultiSet1 = org.apache.commons.collections4.multiset.SynchronizedMultiSet.synchronizedMultiSet(new org.apache.commons.collections4.multiset.HashMultiSet<>(java.util.List.of("value")));
private org.apache.commons.collections4.multimap.AbstractListValuedMap AbstractListValuedMap0 = new org.apache.commons.collections4.multimap.ArrayListValuedHashMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.map.ListOrderedMap ListOrderedMap0 = org.apache.commons.collections4.map.ListOrderedMap.listOrderedMap(java.util.Map.ofEntries());
private org.apache.commons.collections4.map.ListOrderedMap<String, Integer> ListOrderedMap1 = org.apache.commons.collections4.map.ListOrderedMap.listOrderedMap(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private java.util.concurrent.CopyOnWriteArraySet CopyOnWriteArraySet0 = new java.util.concurrent.CopyOnWriteArraySet<>(java.util.List.of());
private java.util.concurrent.CopyOnWriteArraySet<String> CopyOnWriteArraySet1 = new java.util.concurrent.CopyOnWriteArraySet<>(java.util.List.of("value"));
private software.amazon.awssdk.services.dynamodb.model.BatchGetItemResponse BatchGetItemResponse0 = software.amazon.awssdk.services.dynamodb.model.BatchGetItemResponse.builder()
.responses(java.util.Map.of(
"TableName1", java.util.List.of(
java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKey1", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue1").build()),
java.util.Map.entry("KeyName2", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("Value2").build()))
),
"TableName2", java.util.List.of(
java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKey2", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue2").build()),
java.util.Map.entry("KeyName2", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("Value2").build()))
))).build();
private software.amazon.awssdk.services.dynamodb.model.GetItemRequest GetItemRequest0 = software.amazon.awssdk.services.dynamodb.model.GetItemRequest.builder()
.tableName("TableName")
.key(java.util.Map.of("PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue").build()))
.build();
private java.util.LinkedHashMap LinkedHashMap0 = new java.util.LinkedHashMap<>(java.util.Map.ofEntries());
private java.util.LinkedHashMap<String, Integer> LinkedHashMap1 = new java.util.LinkedHashMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private com.amazonaws.services.dynamodbv2.model.PutItemRequest PutItemRequest0 = new com.amazonaws.services.dynamodbv2.model.PutItemRequest("TableName", java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue")),
java.util.Map.entry("KeyName2", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("Value2"))
));
private java.util.TreeMap TreeMap0 = new java.util.TreeMap<>(java.util.Map.ofEntries());
private java.util.TreeMap<String, Integer> TreeMap1 = new java.util.TreeMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.apache.commons.collections4.list.SetUniqueList SetUniqueList0 = org.apache.commons.collections4.list.SetUniqueList.setUniqueList(java.util.List.of());
private org.apache.commons.collections4.list.SetUniqueList<String> SetUniqueList1 = org.apache.commons.collections4.list.SetUniqueList.setUniqueList(java.util.List.of("value"));
private java.util.SortedSet SortedSet0 = new java.util.TreeSet<>(java.util.List.of());
private java.util.SortedSet<String> SortedSet1 = new java.util.TreeSet<>(java.util.List.of("value"));
private software.amazon.awssdk.services.dynamodb.model.QueryResponse QueryResponse0 = software.amazon.awssdk.services.dynamodb.model.QueryResponse.builder().items(
java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue1").build()),
java.util.Map.entry("OtherKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("OtherKeyValue1").build())
),
java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue2").build()),
java.util.Map.entry("OtherKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("OtherKeyValue2").build())
)
).build();
private java.util.NavigableMap NavigableMap0 = new java.util.TreeMap<>(java.util.Map.ofEntries());
private java.util.NavigableMap<String, Integer> NavigableMap1 = new java.util.TreeMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private software.amazon.awssdk.enhanced.dynamodb.model.PageIterable<String> PageIterable0 = software.amazon.awssdk.enhanced.dynamodb.model.PageIterable.create(() -> java.util.List.of(software.amazon.awssdk.enhanced.dynamodb.model.Page.create(java.util.List.of("value"))).iterator());
private org.apache.commons.collections4.IterableMap IterableMap0 = org.apache.commons.collections4.MapUtils.iterableSortedMap(new java.util.TreeMap<>(java.util.Map.ofEntries()));
private org.apache.commons.collections4.IterableMap<String, Integer> IterableMap1 = org.apache.commons.collections4.MapUtils.iterableSortedMap(new java.util.TreeMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0))));
private com.google.common.collect.Multiset Multiset0 = com.google.common.collect.HashMultiset.create(java.util.List.of());
private com.google.common.collect.Multiset<String> Multiset1 = com.google.common.collect.HashMultiset.create(java.util.List.of("value"));
private org.apache.commons.collections4.bag.SynchronizedSortedBag SynchronizedSortedBag0 = org.apache.commons.collections4.bag.SynchronizedSortedBag.synchronizedSortedBag(new org.apache.commons.collections4.bag.TreeBag<>(java.util.List.of()));
private org.apache.commons.collections4.bag.SynchronizedSortedBag<String> SynchronizedSortedBag1 = org.apache.commons.collections4.bag.SynchronizedSortedBag.synchronizedSortedBag(new org.apache.commons.collections4.bag.TreeBag<>(java.util.List.of("value")));
private org.apache.commons.collections4.ListValuedMap ListValuedMap0 = new org.apache.commons.collections4.multimap.ArrayListValuedHashMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.ListValuedMap<String, Integer> ListValuedMap1 = new org.apache.commons.collections4.multimap.ArrayListValuedHashMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.apache.commons.collections4.SortedBag SortedBag0 = new org.apache.commons.collections4.bag.TreeBag<>(java.util.List.of());
private org.apache.commons.collections4.SortedBag<String> SortedBag1 = new org.apache.commons.collections4.bag.TreeBag<>(java.util.List.of("value"));
private com.amazonaws.services.dynamodbv2.model.DeleteItemRequest DeleteItemRequest0 = new com.amazonaws.services.dynamodbv2.model.DeleteItemRequest("TableName", java.util.Map.of("PrimaryKeyName", new com.amazonaws.services.dynamodbv2.model.AttributeValue("PrimaryKeyValue")));
private org.apache.commons.collections4.bag.AbstractBagDecorator AbstractBagDecorator0 = new org.apache.commons.collections4.bag.CollectionBag<>(new org.apache.commons.collections4.bag.HashBag<>(java.util.List.of()));
private org.apache.commons.collections4.bag.AbstractBagDecorator<String> AbstractBagDecorator1 = new org.apache.commons.collections4.bag.CollectionBag<>(new org.apache.commons.collections4.bag.HashBag<>(java.util.List.of("value")));
private com.amazonaws.services.dynamodbv2.model.BatchGetItemResult BatchGetItemResult0 = new com.amazonaws.services.dynamodbv2.model.BatchGetItemResult().withResponses(
java.util.Map.of("TableName1", java.util.List.of(
java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKey1", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue1")),
java.util.Map.entry("KeyName2", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("Value2"))
)),
"TableName2", java.util.List.of(
java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKey2", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue2")),
java.util.Map.entry("KeyName2", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("Value2"))
)
)
)
);
private com.google.common.collect.SortedMultiset SortedMultiset0 = com.google.common.collect.TreeMultiset.create(java.util.List.of());
private com.google.common.collect.SortedMultiset<String> SortedMultiset1 = com.google.common.collect.TreeMultiset.create(java.util.List.of("value"));
private com.google.common.collect.FluentIterable FluentIterable0 = com.google.common.collect.FluentIterable.from(java.util.List.of());
private com.google.common.collect.FluentIterable<String> FluentIterable1 = com.google.common.collect.FluentIterable.from(java.util.List.of("value"));
private software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest DeleteItemRequest1 = software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest.builder()
.tableName("TableName")
.key(java.util.Map.of(
"PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue").build()
)).build();
private com.amazonaws.services.dynamodbv2.model.GetItemResult GetItemResult0 = new com.amazonaws.services.dynamodbv2.model.GetItemResult().withItem(java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue")),
java.util.Map.entry("KeyName2", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("Value2"))
));
private org.apache.commons.collections4.trie.PatriciaTrie PatriciaTrie0 = new org.apache.commons.collections4.trie.PatriciaTrie<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.trie.PatriciaTrie<String> PatriciaTrie1 = new org.apache.commons.collections4.trie.PatriciaTrie<>(java.util.Map.ofEntries(java.util.Map.entry("prefix1", "value")));
private java.util.TreeSet TreeSet0 = new java.util.TreeSet<>(java.util.List.of());
private java.util.TreeSet<String> TreeSet1 = new java.util.TreeSet<>(java.util.List.of("value"));
private java.util.ArrayList ArrayList0 = new java.util.ArrayList<>(java.util.List.of());
private java.util.ArrayList<String> ArrayList1 = new java.util.ArrayList<>(java.util.List.of("value"));
private java.util.LinkedHashSet<String> LinkedHashSet0 = new java.util.LinkedHashSet<>(java.util.List.of("value"));
private software.amazon.awssdk.services.dynamodb.model.GetItemResponse GetItemResponse0 = software.amazon.awssdk.services.dynamodb.model.GetItemResponse.builder()
.item(java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue").build()),
java.util.Map.entry("OtherKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("OtherKeyValue").build())
)).build();
private com.google.common.collect.ArrayTable ArrayTable0 = com.google.common.collect.ArrayTable.create(java.util.List.of(), java.util.List.of());
private com.google.common.collect.HashMultiset HashMultiset0 = com.google.common.collect.HashMultiset.create(java.util.List.of());
private com.google.common.collect.HashMultiset<String> HashMultiset1 = com.google.common.collect.HashMultiset.create(java.util.List.of("value"));
private software.amazon.awssdk.services.dynamodb.model.TransactGetItemsRequest TransactGetItemsRequest0 = software.amazon.awssdk.services.dynamodb.model.TransactGetItemsRequest.builder()
.transactItems(software.amazon.awssdk.services.dynamodb.model.TransactGetItem.builder().get(
software.amazon.awssdk.services.dynamodb.model.Get.builder()
.tableName("TableName")
.key(java.util.Map.of(
"PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue").build()
)).build()).build()).build();
private java.util.List List0 = java.util.List.of();
private java.util.List<String> List1 = java.util.List.of("value");
private org.apache.commons.collections4.bag.TreeBag TreeBag0 = new org.apache.commons.collections4.bag.TreeBag<>(java.util.List.of());
private org.apache.commons.collections4.bag.TreeBag<String> TreeBag1 = new org.apache.commons.collections4.bag.TreeBag<>(java.util.List.of("value"));
private java.util.SortedMap SortedMap0 = new java.util.TreeMap<>(java.util.Map.ofEntries());
private java.util.SortedMap<String, Integer> SortedMap1 = new java.util.TreeMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private com.amazonaws.services.dynamodbv2.model.KeysAndAttributes KeysAndAttributes0 = new com.amazonaws.services.dynamodbv2.model.KeysAndAttributes().withKeys(java.util.Map.of("PrimaryKey", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue1")));
private java.util.Deque Deque0 = new java.util.LinkedList<>(java.util.List.of());
private java.util.Deque<String> Deque1 = new java.util.LinkedList<>(java.util.List.of("value"));
private com.amazonaws.services.dynamodbv2.model.WriteRequest WriteRequest0 = new com.amazonaws.services.dynamodbv2.model.WriteRequest().withPutRequest(new com.amazonaws.services.dynamodbv2.model.PutRequest().withItem(java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue")))
));
private java.lang.Iterable Iterable0 = java.util.List.of();
private java.lang.Iterable<String> Iterable1 = java.util.List.of("value");
private org.apache.commons.collections4.multimap.ArrayListValuedHashMap ArrayListValuedHashMap0 = new org.apache.commons.collections4.multimap.ArrayListValuedHashMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.BoundedMap BoundedMap0 = org.apache.commons.collections4.map.FixedSizeMap.fixedSizeMap(java.util.Map.ofEntries());
private org.apache.commons.collections4.BoundedMap<String, Integer> BoundedMap1 = org.apache.commons.collections4.map.FixedSizeMap.fixedSizeMap(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.apache.commons.collections4.map.LazyMap LazyMap0 = org.apache.commons.collections4.map.LazyMap.lazyMap(java.util.Map.ofEntries(), org.apache.commons.collections4.functors.ConstantFactory.constantFactory(null));
private org.apache.commons.collections4.map.LazyMap<String, Integer> LazyMap1 = org.apache.commons.collections4.map.LazyMap.lazyMap(java.util.Map.ofEntries(java.util.Map.entry("value", 0)), org.apache.commons.collections4.functors.ConstantFactory.constantFactory(0));
private software.amazon.awssdk.services.dynamodb.model.TransactGetItemsResponse TransactGetItemsResponse0 = software.amazon.awssdk.services.dynamodb.model.TransactGetItemsResponse.builder().responses(
software.amazon.awssdk.services.dynamodb.model.ItemResponse.builder()
.item(java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue").build())
)).build(),
software.amazon.awssdk.services.dynamodb.model.ItemResponse.builder()
.item(java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue2").build())
)).build()
).build();
private org.apache.commons.collections4.multiset.AbstractMapMultiSet AbstractMapMultiSet0 = new org.apache.commons.collections4.multiset.HashMultiSet<>(java.util.List.of());
private org.apache.commons.collections4.multiset.AbstractMapMultiSet<String> AbstractMapMultiSet1 = new org.apache.commons.collections4.multiset.HashMultiSet<>(java.util.List.of("value"));
private java.util.Map Map0 = java.util.Map.ofEntries();
private java.util.Map<String, Integer> Map1 = java.util.Map.ofEntries(java.util.Map.entry("value", 0));
private com.google.common.collect.LinkedHashMultiset LinkedHashMultiset0 = com.google.common.collect.LinkedHashMultiset.create(java.util.List.of());
private com.google.common.collect.LinkedHashMultiset<String> LinkedHashMultiset1 = com.google.common.collect.LinkedHashMultiset.create(java.util.List.of("value"));
private java.util.concurrent.ConcurrentNavigableMap ConcurrentNavigableMap0 = new java.util.concurrent.ConcurrentSkipListMap<>(java.util.Map.ofEntries());
private java.util.concurrent.ConcurrentNavigableMap<String, Integer> ConcurrentNavigableMap1 = new java.util.concurrent.ConcurrentSkipListMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.springframework.data.domain.Page Page0 = new org.springframework.data.domain.PageImpl<>(java.util.List.of());
private org.springframework.data.domain.Page<String> Page1 = new org.springframework.data.domain.PageImpl<>(java.util.List.of("value"));
private java.util.concurrent.CopyOnWriteArrayList CopyOnWriteArrayList0 = new java.util.concurrent.CopyOnWriteArrayList<>(java.util.List.of());
private java.util.concurrent.CopyOnWriteArrayList<String> CopyOnWriteArrayList1 = new java.util.concurrent.CopyOnWriteArrayList<>(java.util.List.of("value"));
private com.amazonaws.services.dynamodbv2.model.TransactGetItemsRequest TransactGetItemsRequest1 = new com.amazonaws.services.dynamodbv2.model.TransactGetItemsRequest().withTransactItems(
new com.amazonaws.services.dynamodbv2.model.TransactGetItem().withGet(new com.amazonaws.services.dynamodbv2.model.Get()
.withTableName("TableName")
.withKey(java.util.Map.of("PrimaryKeyName", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue")))
),
new com.amazonaws.services.dynamodbv2.model.TransactGetItem().withGet(new com.amazonaws.services.dynamodbv2.model.Get()
.withTableName("TableName2")
.withKey(java.util.Map.of("PrimaryKeyName2", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue2")))
)
);
private org.apache.commons.collections4.MultiSet MultiSet0 = new org.apache.commons.collections4.multiset.HashMultiSet<>(java.util.List.of());
private org.apache.commons.collections4.MultiSet<String> MultiSet1 = new org.apache.commons.collections4.multiset.HashMultiSet<>(java.util.List.of("value"));
private software.amazon.awssdk.services.dynamodb.model.BatchGetItemRequest BatchGetItemRequest0 = software.amazon.awssdk.services.dynamodb.model.BatchGetItemRequest.builder()
.requestItems(java.util.Map.of(
"TableName1", software.amazon.awssdk.services.dynamodb.model.KeysAndAttributes.builder().keys(java.util.Map.of(
"PrimaryKey", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue").build()
)).build(),
"TableName2", software.amazon.awssdk.services.dynamodb.model.KeysAndAttributes.builder().keys(java.util.Map.of(
"PrimaryKey2", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue2").build()
)).build()
)).build();
private org.apache.commons.collections4.multimap.HashSetValuedHashMap HashSetValuedHashMap0 = new org.apache.commons.collections4.multimap.HashSetValuedHashMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.multimap.HashSetValuedHashMap<String, Integer> HashSetValuedHashMap1 = new org.apache.commons.collections4.multimap.HashSetValuedHashMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private com.google.common.collect.TreeMultiset TreeMultiset0 = com.google.common.collect.TreeMultiset.create(java.util.List.of());
private com.google.common.collect.TreeMultiset<String> TreeMultiset1 = com.google.common.collect.TreeMultiset.create(java.util.List.of("value"));
private java.util.Collection Collection0 = java.util.List.of();
private java.util.Collection<String> Collection1 = java.util.List.of("value");
private software.amazon.awssdk.services.dynamodb.model.PutItemRequest PutItemRequest1 = software.amazon.awssdk.services.dynamodb.model.PutItemRequest.builder()
.tableName("TableName")
.item(java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue").build()),
java.util.Map.entry("OtherKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("OtherValue").build())))
.build();
private org.apache.commons.collections4.bag.AbstractMapBag AbstractMapBag0 = new org.apache.commons.collections4.bag.HashBag<>(java.util.List.of());
private org.apache.commons.collections4.bag.AbstractMapBag<String> AbstractMapBag1 = new org.apache.commons.collections4.bag.HashBag<>(java.util.List.of("value"));
private java.util.Queue Queue0 = new java.util.LinkedList<>(java.util.List.of());
private java.util.Queue<String> Queue1 = new java.util.LinkedList<>(java.util.List.of("value"));
private org.apache.commons.collections4.bag.HashBag HashBag0 = new org.apache.commons.collections4.bag.HashBag<>(java.util.List.of());
private org.apache.commons.collections4.bag.HashBag<String> HashBag1 = new org.apache.commons.collections4.bag.HashBag<>(java.util.List.of("value"));
private org.apache.commons.collections4.IterableGet IterableGet0 = new org.apache.commons.collections4.map.HashedMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.IterableGet<String, Integer> IterableGet1 = new org.apache.commons.collections4.map.HashedMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.apache.commons.collections4.set.ListOrderedSet ListOrderedSet0 = org.apache.commons.collections4.set.ListOrderedSet.listOrderedSet(java.util.List.of());
private org.apache.commons.collections4.set.ListOrderedSet<String> ListOrderedSet1 = org.apache.commons.collections4.set.ListOrderedSet.listOrderedSet(java.util.List.of("value"));
private com.amazonaws.services.dynamodbv2.model.ScanResult ScanResult0 = new com.amazonaws.services.dynamodbv2.model.ScanResult().withItems(
java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue1")),
java.util.Map.entry("KeyName2", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("Value2"))
),
java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue2")),
java.util.Map.entry("KeyName2", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("Value2"))
)
);
private java.util.concurrent.ConcurrentMap ConcurrentMap0 = new java.util.concurrent.ConcurrentHashMap<>(java.util.Map.ofEntries());
private java.util.concurrent.ConcurrentMap<String, Integer> ConcurrentMap1 = new java.util.concurrent.ConcurrentHashMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private java.util.HashSet HashSet0 = new java.util.HashSet<>(java.util.List.of());
private java.util.HashSet<String> HashSet1 = new java.util.HashSet<>(java.util.List.of("value"));
private java.util.LinkedList LinkedList0 = new java.util.LinkedList<>(java.util.List.of());
private java.util.LinkedList<String> LinkedList1 = new java.util.LinkedList<>(java.util.List.of("value"));
private org.apache.commons.collections4.BidiMap BidiMap0 = new org.apache.commons.collections4.bidimap.DualHashBidiMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.BidiMap<String, Integer> BidiMap1 = new org.apache.commons.collections4.bidimap.DualHashBidiMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private com.amazonaws.services.dynamodbv2.model.GetItemRequest GetItemRequest1 = new com.amazonaws.services.dynamodbv2.model.GetItemRequest("TableName",
java.util.Map.of("PrimaryKeyName", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue"))
);
private org.apache.commons.collections4.bidimap.TreeBidiMap TreeBidiMap0 = new org.apache.commons.collections4.bidimap.TreeBidiMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.bidimap.TreeBidiMap<String, Integer> TreeBidiMap1 = new org.apache.commons.collections4.bidimap.TreeBidiMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private com.amazonaws.services.dynamodbv2.model.TransactWriteItemsRequest TransactWriteItemsRequest0 = new com.amazonaws.services.dynamodbv2.model.TransactWriteItemsRequest().withTransactItems(
new com.amazonaws.services.dynamodbv2.model.TransactWriteItem().withPut(new com.amazonaws.services.dynamodbv2.model.Put()
.withTableName("TableName")
.withItem(java.util.Map.of("PrimaryKeyName", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue")))
));
private org.apache.commons.collections4.Bag Bag0 = new org.apache.commons.collections4.bag.HashBag<>(java.util.List.of());
private org.apache.commons.collections4.Bag<String> Bag1 = new org.apache.commons.collections4.bag.HashBag<>(java.util.List.of("value"));
private com.amazonaws.services.dynamodbv2.model.BatchWriteItemRequest BatchWriteItemRequest0 = new com.amazonaws.services.dynamodbv2.model.BatchWriteItemRequest(java.util.Map.of("TableName", java.util.List.of(
new com.amazonaws.services.dynamodbv2.model.WriteRequest().withPutRequest(new com.amazonaws.services.dynamodbv2.model.PutRequest().withItem(java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue")))
))
)));
private org.apache.commons.collections4.SortedBidiMap SortedBidiMap0 = new org.apache.commons.collections4.bidimap.DualTreeBidiMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.SortedBidiMap<String, Integer> SortedBidiMap1 = new org.apache.commons.collections4.bidimap.DualTreeBidiMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.apache.commons.collections4.OrderedBidiMap OrderedBidiMap0 = new org.apache.commons.collections4.bidimap.TreeBidiMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.OrderedBidiMap<String, Integer> OrderedBidiMap1 = new org.apache.commons.collections4.bidimap.TreeBidiMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private software.amazon.awssdk.services.dynamodb.model.TransactWriteItemsRequest TransactWriteItemsRequest1 = software.amazon.awssdk.services.dynamodb.model.TransactWriteItemsRequest.builder().transactItems(
software.amazon.awssdk.services.dynamodb.model.TransactWriteItem.builder()
.put(software.amazon.awssdk.services.dynamodb.model.Put.builder()
.tableName("TableName")
.item(java.util.Map.ofEntries(
java.util.Map.entry("PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("PrimaryKeyValue").build())
)).build())
.build())
.build();
private org.apache.commons.collections4.set.CompositeSet CompositeSet0 = new org.apache.commons.collections4.set.CompositeSet(java.util.Set.of());
private org.apache.commons.collections4.set.CompositeSet<String> CompositeSet1 = new org.apache.commons.collections4.set.CompositeSet(java.util.Set.of("value"));
private software.amazon.awssdk.services.dynamodb.model.ScanResponse ScanResponse0 = software.amazon.awssdk.services.dynamodb.model.ScanResponse.builder()
.items(
java.util.Map.ofEntries(
java.util.Map.entry("KeyName1", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("Value1").build()),
java.util.Map.entry("KeyName2", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("Value2").build())
),
java.util.Map.ofEntries(
java.util.Map.entry("KeyName1", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("Value12").build()),
java.util.Map.entry("KeyName2", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("Value22").build())
)
).build();
private org.apache.commons.collections4.multiset.HashMultiSet HashMultiSet0 = new org.apache.commons.collections4.multiset.HashMultiSet<>(java.util.List.of());
private org.apache.commons.collections4.multiset.HashMultiSet<String> HashMultiSet1 = new org.apache.commons.collections4.multiset.HashMultiSet<>(java.util.List.of("value"));
private java.util.Set Set0 = java.util.Set.of();
private java.util.Set<String> Set1 = java.util.Set.of("value");
private java.util.Dictionary Dictionary0 = new java.util.Hashtable<>(java.util.Map.ofEntries());
private java.util.Dictionary<String, Integer> Dictionary1 = new java.util.Hashtable<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.springframework.data.domain.SliceImpl SliceImpl0 = new org.springframework.data.domain.SliceImpl<>(java.util.List.of());
private org.springframework.data.domain.SliceImpl<String> SliceImpl1 = new org.springframework.data.domain.SliceImpl<>(java.util.List.of("value"));
private java.util.RandomAccess RandomAccess0 = new java.util.ArrayList<>(java.util.List.of());
private org.apache.commons.collections4.multimap.AbstractSetValuedMap AbstractSetValuedMap0 = new org.apache.commons.collections4.multimap.HashSetValuedHashMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.multimap.AbstractSetValuedMap<String, Integer> AbstractSetValuedMap1 = new org.apache.commons.collections4.multimap.HashSetValuedHashMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private software.amazon.awssdk.services.dynamodb.model.BatchWriteItemRequest BatchWriteItemRequest1 = software.amazon.awssdk.services.dynamodb.model.BatchWriteItemRequest.builder().requestItems(
java.util.Map.of(
"TableName1", java.util.List.of(
software.amazon.awssdk.services.dynamodb.model.WriteRequest.builder().putRequest(
software.amazon.awssdk.services.dynamodb.model.PutRequest.builder().item(java.util.Map.ofEntries(
java.util.Map.entry("Table1PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("Table1PrimaryKeyValue1").build())
)).build()).build(),
software.amazon.awssdk.services.dynamodb.model.WriteRequest.builder().putRequest(
software.amazon.awssdk.services.dynamodb.model.PutRequest.builder().item(java.util.Map.ofEntries(
java.util.Map.entry("Table1PrimaryKeyName", software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder().s("Table1PrimaryKeyValue2").build())
)).build()).build()
)
)).build();
private org.springframework.util.MultiValueMap MultiValueMap0 = new org.springframework.util.LinkedMultiValueMap<>(java.util.Map.ofEntries());
private org.springframework.util.MultiValueMap<String, Integer> MultiValueMap1 = new org.springframework.util.LinkedMultiValueMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", java.util.List.of(0))));
private org.apache.commons.collections4.BoundedCollection BoundedCollection0 = org.apache.commons.collections4.list.FixedSizeList.fixedSizeList(java.util.List.of());
private org.apache.commons.collections4.BoundedCollection<String> BoundedCollection1 = org.apache.commons.collections4.list.FixedSizeList.fixedSizeList(java.util.List.of("value"));
private com.google.common.collect.ConcurrentHashMultiset ConcurrentHashMultiset0 = com.google.common.collect.ConcurrentHashMultiset.create(java.util.List.of());
private com.google.common.collect.ConcurrentHashMultiset<String> ConcurrentHashMultiset1 = com.google.common.collect.ConcurrentHashMultiset.create(java.util.List.of("value"));
private org.apache.commons.collections4.Get Get0 = new org.apache.commons.collections4.map.HashedMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.Get<String, Integer> Get1 = new org.apache.commons.collections4.map.HashedMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private java.util.IdentityHashMap IdentityHashMap0 = new java.util.IdentityHashMap<>(java.util.Map.ofEntries());
private java.util.IdentityHashMap<String, Integer> IdentityHashMap1 = new java.util.IdentityHashMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.apache.commons.collections4.MultiValuedMap MultiValuedMap0 = new org.apache.commons.collections4.multimap.ArrayListValuedHashMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.MultiValuedMap<String, Integer> MultiValuedMap1 = new org.apache.commons.collections4.multimap.ArrayListValuedHashMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private java.util.NavigableSet NavigableSet0 = new java.util.TreeSet<>(java.util.List.of());
private java.util.NavigableSet<String> NavigableSet1 = new java.util.TreeSet<>(java.util.List.of("value"));
private org.apache.commons.collections4.bidimap.DualTreeBidiMap DualTreeBidiMap0 = new org.apache.commons.collections4.bidimap.DualTreeBidiMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.bidimap.DualTreeBidiMap<String, Integer> DualTreeBidiMap1 = new org.apache.commons.collections4.bidimap.DualTreeBidiMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.springframework.data.domain.Slice Slice0 = new org.springframework.data.domain.SliceImpl<>(java.util.List.of());
private org.springframework.data.domain.Slice<String> Slice1 = new org.springframework.data.domain.SliceImpl<>(java.util.List.of("value"));
private java.util.HashMap HashMap0 = new java.util.HashMap<>(java.util.Map.ofEntries());
private java.util.HashMap<String, Integer> HashMap1 = new java.util.HashMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private java.util.Hashtable Hashtable0 = new java.util.Hashtable<>(java.util.Map.ofEntries());
private java.util.Hashtable<String, Integer> Hashtable1 = new java.util.Hashtable<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private java.util.WeakHashMap WeakHashMap0 = new java.util.WeakHashMap<>(java.util.Map.ofEntries());
private java.util.WeakHashMap<String, Integer> WeakHashMap1 = new java.util.WeakHashMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private java.util.Iterator Iterator0 = java.util.List.of().iterator();
private java.util.Iterator<String> Iterator1 = java.util.List.of("value").iterator();
private com.amazonaws.services.dynamodbv2.model.BatchGetItemRequest BatchGetItemRequest1 = new com.amazonaws.services.dynamodbv2.model.BatchGetItemRequest().withRequestItems(java.util.Map.of(
"TableName1", new com.amazonaws.services.dynamodbv2.model.KeysAndAttributes().withKeys(
java.util.Map.of("PrimaryKey", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue1"))),
"TableName2", new com.amazonaws.services.dynamodbv2.model.KeysAndAttributes().withKeys(
java.util.Map.of("PrimaryKey", new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS("PrimaryKeyValue1"))
)
));
private java.util.concurrent.ConcurrentSkipListMap ConcurrentSkipListMap0 = new java.util.concurrent.ConcurrentSkipListMap<>(java.util.Map.ofEntries());
private java.util.concurrent.ConcurrentSkipListMap<String, Integer> ConcurrentSkipListMap1 = new java.util.concurrent.ConcurrentSkipListMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private org.apache.commons.collections4.Put Put0 = new org.apache.commons.collections4.map.HashedMap<>(java.util.Map.ofEntries());
private org.apache.commons.collections4.Put<String, Integer> Put1 = new org.apache.commons.collections4.map.HashedMap<>(java.util.Map.ofEntries(java.util.Map.entry("value", 0)));
private com.google.common.collect.Table Table0 = com.google.common.collect.ArrayTable.create(java.util.List.of(), java.util.List.of());
}