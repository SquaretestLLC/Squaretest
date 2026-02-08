package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private ActionService mockActionService;
    @Mock
    private OrderService mockOrderService;
    @Mock
    private OrderService mockAltOrderService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockActionService, mockOrderService, mockAltOrderService, mockMetricService);
    }

    @Test
    void testTransferOrder1() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder1("orderId");

        // Verify the results
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder1_ActionServiceGetActionId1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder1("orderId");

        // Verify the results
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder1_ActionServiceGetActionGroup1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder1("orderId");

        // Verify the results
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder1_ActionServiceGetKeyForOrder1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.empty());

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder1("orderId");

        // Verify the results
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder1_OrderServiceReturnsNull() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        myClassUnderTest.transferOrder1("orderId");

        // Verify the results
    }

    @Test
    void testTransferOrder2() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder2("orderId");

        // Verify the results
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder2_ActionServiceGetActionId1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.transferOrder2("orderId"));
    }

    @Test
    void testTransferOrder2_ActionServiceGetActionGroup1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.transferOrder2("orderId"));
    }

    @Test
    void testTransferOrder2_ActionServiceGetKeyForOrder1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.transferOrder2("orderId"));
    }

    @Test
    void testTransferOrder2_OrderServiceReturnsNull() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        myClassUnderTest.transferOrder2("orderId");

        // Verify the results
    }

    @Test
    void testTransferOrder3() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder3("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder3_ActionServiceGetActionId1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder3("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder3_ActionServiceGetActionGroup1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder3("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder3_ActionServiceGetKeyForOrder1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.empty());

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder3("orderId");

        // Verify the results
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder3_OrderServiceReturnsNull() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        myClassUnderTest.transferOrder3("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
    }

    @Test
    void testTransferOrder4() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        myClassUnderTest.transferOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordAltBaseIdForOrder1("altBaseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
        verify(mockMetricService).recordAltBaseIdForOrder2("altBaseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder4_ActionServiceGetActionId1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        myClassUnderTest.transferOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordAltBaseIdForOrder1("altBaseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
        verify(mockMetricService).recordAltBaseIdForOrder2("altBaseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder4_ActionServiceGetActionGroup1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        myClassUnderTest.transferOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordAltBaseIdForOrder1("altBaseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
        verify(mockMetricService).recordAltBaseIdForOrder2("altBaseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder4_ActionServiceGetBaseIdForAction1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        myClassUnderTest.transferOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordAltBaseIdForOrder1("altBaseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
        verify(mockMetricService).recordAltBaseIdForOrder2("altBaseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder4_ActionServiceGetKeyForOrder1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        myClassUnderTest.transferOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
        verify(mockMetricService).recordAltBaseIdForOrder2("altBaseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder4_ActionServiceGetBaseIdForAction2ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        myClassUnderTest.transferOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
        verify(mockMetricService).recordAltBaseIdForOrder2("altBaseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder4_ActionServiceGetKeyForOrder2ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.empty());

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        myClassUnderTest.transferOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder4_OrderServiceReturnsNull() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        myClassUnderTest.transferOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
    }

    @Test
    void testTransferOrder4_AltOrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        myClassUnderTest.transferOrder4("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
        verify(mockAltOrderService).putOrder(any(Order.class));
        verify(mockMetricService).recordNullOrderFromAltService("orderId");
    }

    @Test
    void testTransferOrder5() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        myClassUnderTest.transferOrder5("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("baseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("baseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
        verify(mockMetricService).recordOrderStoredSuccessfully("orderId");
    }

    @Test
    void testTransferOrder5_ActionServiceGetActionId1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        myClassUnderTest.transferOrder5("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("baseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("baseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
        verify(mockMetricService).recordOrderStoredSuccessfully("orderId");
    }

    @Test
    void testTransferOrder5_ActionServiceGetActionGroup1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Configure OrderService.getOrderWithId1(...).
        final Order order1 = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order1);

        // Run the test
        myClassUnderTest.transferOrder5("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("baseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("baseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
        verify(mockMetricService).recordOrderStoredSuccessfully("orderId");
    }

    @Test
    void testTransferOrder5_ActionServiceGetBaseIdForAction1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.transferOrder5("orderId"));
    }

    @Test
    void testTransferOrder5_ActionServiceGetKeyForOrder1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.transferOrder5("orderId"));
    }

    @Test
    void testTransferOrder5_ActionServiceGetBaseIdForAction2ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.transferOrder5("orderId"));
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("baseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
    }

    @Test
    void testTransferOrder5_ActionServiceGetKeyForOrder2ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.transferOrder5("orderId"));
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("baseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
    }

    @Test
    void testTransferOrder5_OrderServiceReturnsNull() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotFoundException.class, () -> myClassUnderTest.transferOrder5("orderId"));
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("baseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("baseId");
    }

    @Test
    void testTransferOrder5_AltOrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        assertThrows(OrderNotStoredException.class, () -> myClassUnderTest.transferOrder5("orderId"));
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("baseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("baseId");
        verify(mockAltOrderService).putOrder(any(Order.class));
        verify(mockMetricService).recordNullOrderFromAltService("orderId");
    }

    @Test
    void testTransferOrder6_ThrowsOrderKeyException1() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Run the test
        assertThrows(OrderKeyException1.class, () -> myClassUnderTest.transferOrder6("orderId"));
    }

    @Test
    void testTransferOrder6_ActionServiceGetActionId1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Run the test
        assertThrows(OrderKeyException1.class, () -> myClassUnderTest.transferOrder6("orderId"));
    }

    @Test
    void testTransferOrder6_ActionServiceGetActionGroup1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Run the test
        assertThrows(OrderKeyException1.class, () -> myClassUnderTest.transferOrder6("orderId"));
    }

    @Test
    void testTransferOrder6_ActionServiceGetBaseIdForAction1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.of("value"));

        // Run the test
        assertThrows(OrderKeyException1.class, () -> myClassUnderTest.transferOrder6("orderId"));
    }

    @Test
    void testTransferOrder6_ActionServiceGetKeyForOrder1ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Run the test
        assertThrows(OrderKeyException2.class, () -> myClassUnderTest.transferOrder6("orderId"));
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordAltBaseIdForOrder1("altBaseId");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
    }

    @Test
    void testTransferOrder6_ActionServiceGetBaseIdForAction2ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.of("value"));

        // Run the test
        assertThrows(OrderKeyException2.class, () -> myClassUnderTest.transferOrder6("orderId"));
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
    }

    @Test
    void testTransferOrder6_ActionServiceGetKeyForOrder2ReturnsAbsent() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.empty());

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        assertThrows(OrderAlreadyExistsException.class, () -> myClassUnderTest.transferOrder6("orderId"));
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
        verify(mockMetricService).recordAltBaseIdForOrder2("altBaseId");
    }

    @Test
    void testTransferOrder6_OrderServiceReturnsNull() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.empty());
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Configure OrderService.getOrderWithId1(...).
        final Order order = new Order("orderId", "description", Arrays.asList("value"));
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(order);

        // Run the test
        assertThrows(OrderAlreadyExistsException.class, () -> myClassUnderTest.transferOrder6("orderId"));
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
        verify(mockAltOrderService).putOrder(any(Order.class));
    }

    @Test
    void testTransferOrder6_AltOrderServiceGetOrderWithId1ReturnsNull() {
        // Setup
        when(mockActionService.getActionId1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getActionGroup1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getBaseIdForAction1("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder1("orderId")).thenReturn(Optional.empty());
        when(mockActionService.getThirdBaseIdForAction1("orderId")).thenReturn("result");
        when(mockActionService.getBaseIdForAction2("orderId")).thenReturn(Optional.of("value"));
        when(mockActionService.getAltBaseIdForAction2("orderId")).thenReturn("result");
        when(mockActionService.getKeyForOrder2("orderId")).thenReturn(Optional.empty());
        when(mockOrderService.getOrderWithId1("orderId")).thenReturn(null);
        when(mockAltOrderService.getOrderWithId1("orderId")).thenReturn(null);

        // Run the test
        myClassUnderTest.transferOrder6("orderId");

        // Verify the results
        verify(mockMetricService).recordActionServiceKeyForOrderFound1("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder1("s");
        verify(mockMetricService).recordThirdBaseIdForOrder("thirdBaseId");
        verify(mockMetricService).recordActionServiceKeyForOrderFound2("orderId");
        verify(mockMetricService).recordActionBaseKeyForOrder2("s");
        verify(mockAltOrderService).putOrder(any(Order.class));
        verify(mockMetricService).recordNullOrderFromAltService("orderId");
    }
}
