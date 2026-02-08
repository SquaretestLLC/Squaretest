package com.myapp

import groovy.transform.CompileStatic
import org.joda.time.*
import org.joda.time.chrono.*
import org.joda.time.convert.ConverterManager
import org.joda.time.field.*
import org.joda.time.format.*
import org.joda.time.tz.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomethingWithJodaTimeArgs() {
        // Setup
        def buddhistChronology1 = BuddhistChronology.getInstanceUTC()
        def ethiopicChronology1 = EthiopicChronology.getInstanceUTC()
        def lenientChronology1 = LenientChronology.getInstance(null)
        def zonedChronology1 = ZonedChronology.getInstance(null, DateTimeZone.forID("id"))
        def durationFieldType1 = DurationFieldType.millis()
        def zeroIsMaxDateTimeField1 = new ZeroIsMaxDateTimeField(null, DateTimeFieldType.millisOfSecond())
        def jodaTimePermission1 = new JodaTimePermission("name")
        def defaultNameProvider1 = new DefaultNameProvider()
        def islamicChronology1 = IslamicChronology.getInstanceUTC()
        def scaledDurationField1 = new ScaledDurationField(null, DurationFieldType.millis(), 0)
        def periodType1 = PeriodType.forFields([DurationFieldType.millis()] as DurationFieldType[])
        def zoneInfoLogger1 = new ZoneInfoLogger()
        def gregorianChronology1 = GregorianChronology.getInstanceUTC()
        def delegatedDateTimeField1 = new DelegatedDateTimeField(null)
        def offsetDateTimeField1 = new OffsetDateTimeField(null, 0)
        def skipUndoDateTimeField1 = new SkipUndoDateTimeField(null, null)
        def dateTimeFormatter1 = new DateTimeFormatter(null, null)
        def dateTimeZoneBuilder1 = new DateTimeZoneBuilder()
        def copticChronology1 = CopticChronology.getInstanceUTC()
        def isoChronology1 = ISOChronology.getInstanceUTC()
        def strictChronology1 = StrictChronology.getInstance(null)
        def converterManager1 = ConverterManager.getInstance()
        def unsupportedDateTimeField1 = UnsupportedDateTimeField.getInstance(DateTimeFieldType.millisOfSecond(), null)
        def zoneInfoProvider1 = new ZoneInfoProvider(new File("filename.txt"))
        def dateTimeComparator1 = DateTimeComparator.getInstance()
        def decoratedDurationField1 = new DecoratedDurationField(null, DurationFieldType.millis())
        def preciseDateTimeField1 = new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), null, null)
        def periodFormatter1 = new PeriodFormatter(null, null)
        def zoneInfoCompiler1 = new ZoneInfoCompiler()
        def dividedDateTimeField1 = new DividedDateTimeField(new RemainderDateTimeField(null),
                DateTimeFieldType.millisOfSecond())
        def periodFormatterBuilder1 = new PeriodFormatterBuilder()
        def gjChronology1 = GJChronology.getInstanceUTC()
        def limitChronology1 = LimitChronology.getInstance(null, null, null)
        def dateTimeZone1 = DateTimeZone.forID("id")
        def unsupportedDurationField1 = UnsupportedDurationField.getInstance(DurationFieldType.millis())
        def dateTimeFormatterBuilder1 = new DateTimeFormatterBuilder()
        def julianChronology1 = JulianChronology.getInstanceUTC()
        def dateTimeFieldType1 = DateTimeFieldType.millisOfSecond()
        def remainderDateTimeField1 = new RemainderDateTimeField(
                new DividedDateTimeField(null, DateTimeFieldType.millisOfSecond()))
        def skipDateTimeField1 = new SkipDateTimeField(null, null)
        def cachedDateTimeZone1 = CachedDateTimeZone.forZone(DateTimeZone.forID("id"))
        def utcProvider1 = new UTCProvider()

        // Run the test
        myClassUnderTest.doSomethingWithJodaTimeArgs(null, buddhistChronology1, ethiopicChronology1, lenientChronology1,
                zonedChronology1, durationFieldType1, null, zeroIsMaxDateTimeField1, null, jodaTimePermission1,
                new MutableInterval(0L, 0L), null, null, defaultNameProvider1, Weeks.weeks(1), null, null,
                islamicChronology1, null, null, new Duration(0L, 0L), null, null, scaledDurationField1, null, null,
                new Interval(0L, 0L), new LocalDate(2020, 1, 1), periodType1, null, null, zoneInfoLogger1, null, null,
                gregorianChronology1, null, null, new DateTime(2020, 1, 1, 1, 0, 0, 0), delegatedDateTimeField1,
                offsetDateTimeField1, skipUndoDateTimeField1, dateTimeFormatter1,
                new DateTimeParserBucket(0L, ISOChronology.getInstanceUTC(), Locale.US, 2020, 2020), new Instant(0L),
                new LocalTime(0, 0, 0, 0), new MutableDateTime(2020, 1, 1, 1, 0, 0, 0), null, Seconds.seconds(0),
                dateTimeZoneBuilder1, null, null, null, copticChronology1, isoChronology1, strictChronology1,
                converterManager1, null, null, null, null, null, unsupportedDateTimeField1, null, Hours.hours(0), null,
                zoneInfoProvider1, null, null, null, dateTimeComparator1, null, decoratedDurationField1, null,
                preciseDateTimeField1, null, periodFormatter1,
                new IllegalFieldValueException("fieldName", new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00")), new LocalDateTime(2020, 1, 1, 1, 0, 0, 0), Months.months(1),
                new Period(2020, 1, 1, 1, 0, 0, 0, 0), null, new FixedDateTimeZone("id", "nameKey", 0, 0),
                zoneInfoCompiler1, Years.years(2020), null, null, dividedDateTimeField1, null, null,
                periodFormatterBuilder1, new MutablePeriod(2020, 1, 1, 1, 0, 0, 0, 0), null, new TimeOfDay(1, 0, 0, 0),
                null, null, null, gjChronology1, limitChronology1, null, null, null, new DateMidnight(2020, 1, 1),
                dateTimeZone1, null, new PreciseDurationField(DurationFieldType.millis(), 0L),
                unsupportedDurationField1, dateTimeFormatterBuilder1, null,
                new Partial(new DateTimeFieldType[]{DateTimeFieldType.millisOfSecond()}, new int[]{0}), null, null,
                new YearMonth(2020, 1), null, null, julianChronology1, dateTimeFieldType1, Days.days(1), null, null,
                remainderDateTimeField1, skipDateTimeField1, null, null, new IllegalInstantException(0L, "zoneId"),
                Minutes.minutes(0), new MonthDay(1, 1), null, null, cachedDateTimeZone1, utcProvider1,
                new YearMonthDay(2020, 1, 1))

        // Verify the results
    }
}
