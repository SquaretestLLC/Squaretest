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
package com.squaretest.confirmsettings.v2;

import com.intellij.icons.AllIcons;
import com.intellij.ui.BooleanTableCellRenderer;
import com.intellij.ui.ColoredTableCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.table.JBTable;
import com.squaretest.template.api.Api;
import com.squaretest.utils.MathUtils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import static com.squaretest.confirmsettings.v2.SelectMocksTable.setupCheckboxColumn;

public class SelectMethodsTable extends JBTable {
    private static final int MaxDisplayNameLength = 370;
    private final List<? extends Api.Method> sourceClassMethodsThatCanBeTested;

    public SelectMethodsTable(
            final List<? extends Api.Method> allMethodsThatCanBeTested,
            final JCheckBox cbShowSuperMethods) {
        super();
        this.sourceClassMethodsThatCanBeTested = allMethodsThatCanBeTested.stream().filter(Api.Method::isInMainSourceClass).collect(Collectors.toList());
        boolean showSupers = allMethodsThatCanBeTested.stream().anyMatch(x -> !x.isInMainSourceClass() && x.getShouldTest());
        boolean canShowSupers = allMethodsThatCanBeTested.stream().anyMatch(x -> !x.isInMainSourceClass());
        cbShowSuperMethods.setSelected(showSupers);
        cbShowSuperMethods.setEnabled(canShowSupers);

        // Construct the TableModel.
        final List<? extends Api.Method> methodsToUse = showSupers ? allMethodsThatCanBeTested : sourceClassMethodsThatCanBeTested;
        final MyTableModel myTableModel = new MyTableModel(methodsToUse, "Methods");
        setModel(myTableModel);

        // Configure the Method Name column.
        columnModel.getColumn(MyTableModel.DISPLAY_NAME_COLUMN).setCellRenderer(new MyTableRenderer());

        // Configure Checkbox column.
        TableColumn checkBoxColumn = columnModel.getColumn(SelectMocksTable.MyTableModel.CHECKED_COLUMN);
        setupCheckboxColumn(this, SelectMocksTable.MyTableModel.CHECKED_COLUMN);
        checkBoxColumn.setCellRenderer(new MyBooleanRenderer());

        // Configure Checkbox column header with the "Select All" checkbox.
        final boolean isSelectAllChecked = methodsToUse.stream().allMatch(Api.Method::getShouldTest) && !methodsToUse.isEmpty();
        final BooleanHeaderRenderer cbSelectAll = new BooleanHeaderRenderer();
        cbSelectAll.setSelected(isSelectAllChecked);
        cbSelectAll.setForeground(tableHeader.getForeground());
        cbSelectAll.setBackground(tableHeader.getBackground());
        if(allMethodsThatCanBeTested.isEmpty()) {
            cbSelectAll.setEnabled(false);
        }
        checkBoxColumn.setHeaderRenderer(cbSelectAll);
        tableHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if(e.getButton() == 1) {
                    int columnAtPoint = columnAtPoint(e.getPoint());
                    if(columnAtPoint == MyTableModel.CHECKED_COLUMN && cbSelectAll.isEnabled()) {
                        final boolean currentState = cbSelectAll.isSelected();
                        final boolean newState = !currentState;
                        boolean wasChanged = false;
                        for(final Api.Method method : myTableModel.getMethodsToShow()) {
                            if(method.getShouldTest() != newState) {
                                wasChanged = true;
                                method.setShouldTest(newState);
                            }
                        }
                        if(wasChanged) {
                            ((AbstractTableModel) getModel()).fireTableDataChanged();
                        }
                        cbSelectAll.setSelected(newState);
                        tableHeader.repaint();
                        return;
                    }
                }
                super.mouseClicked(e);
            }
        });

        // Configure the showSupers checkbox.
        cbShowSuperMethods.addItemListener(e -> {
            final boolean showAllIsSelected = e.getStateChange() == ItemEvent.SELECTED;
            final List<? extends Api.Method> newMethodsToUse = showAllIsSelected ? allMethodsThatCanBeTested : sourceClassMethodsThatCanBeTested;
            boolean newSelectAllState = newMethodsToUse.stream().allMatch(Api.Method::getShouldTest) && !newMethodsToUse.isEmpty();
            final boolean currentSelectAllState = cbSelectAll.isSelected();
            cbSelectAll.setSelected(newSelectAllState);
            myTableModel.setMethodsToShow(newMethodsToUse);
            myTableModel.fireTableDataChanged();
            if(currentSelectAllState != newSelectAllState) {
                tableHeader.repaint();
            }
        });

        // Configure Table params.
        tableHeader.setResizingAllowed(false);
        tableHeader.setReorderingAllowed(false);
        setPreferredScrollableViewportSize(new Dimension(400, getRowHeight() * MathUtils.clamp(allMethodsThatCanBeTested.size(), 3, JBTable.PREFERRED_SCROLLABLE_VIEWPORT_HEIGHT_IN_ROWS)));
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
    }

    protected static class MyTableModel extends AbstractTableModel {
        static final int CHECKED_COLUMN = 0;
        static final int DISPLAY_NAME_COLUMN = 1;
        private List<? extends Api.Method> methodsToShow;

        private final String columnName;

        public MyTableModel(final List<? extends Api.Method> methodsToShow, final String columnName) {
            this.methodsToShow = methodsToShow;
            this.columnName = columnName;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public int getRowCount() {
            return this.methodsToShow.size();
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(columnIndex == CHECKED_COLUMN) {
                return Boolean.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            final Api.Method method = methodsToShow.get(rowIndex);
            return switch(columnIndex) {
                case CHECKED_COLUMN -> method.getShouldTest();
                case DISPLAY_NAME_COLUMN -> getDisplayName(method);
                default -> throw new RuntimeException("Incorrect column index");
            };
        }

        @Override
        public String getColumnName(int column) {
            return switch(column) {
                case CHECKED_COLUMN -> " ";
                case DISPLAY_NAME_COLUMN -> columnName;
                default -> throw new RuntimeException("Incorrect column index");
            };
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == CHECKED_COLUMN;
        }

        @Override
        public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
            if(columnIndex == CHECKED_COLUMN) {
                methodsToShow.get(rowIndex).setShouldTest((Boolean) aValue);
            }
        }

        public List<? extends Api.Method> getMethodsToShow() {
            return methodsToShow;
        }

        public void setMethodsToShow(final List<? extends Api.Method> methodsToShow) {
            this.methodsToShow = methodsToShow;
        }
    }

    private static class MyTableRenderer extends ColoredTableCellRenderer {

        @Override
        public void customizeCellRenderer(
                JTable table, final Object value, boolean isSelected, boolean hasFocus, final int row,
                final int column) {
            final int modelColumn = table.convertColumnIndexToModel(column);
            if(modelColumn == MyTableModel.DISPLAY_NAME_COLUMN) {
                setIcon(AllIcons.Nodes.Method);
            } else {
                setIcon(null);
            }
            setIconOpaque(false);
            setOpaque(false);
            setEnabled(true);
            if(value == null) {
                return;
            }
            append((String) value, new SimpleTextAttributes(SimpleTextAttributes.STYLE_PLAIN, null));
        }
    }

    private static class MyBooleanRenderer extends BooleanTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            final Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(component instanceof JCheckBox) {
                final int modelColumn = table.convertColumnIndexToModel(column);
                component.setEnabled(modelColumn == MyTableModel.CHECKED_COLUMN);
            }
            return component;
        }
    }

    private static String getDisplayName(final Api.Method method) {
        final StringBuilder builder = new StringBuilder(method.getName());
        builder.append("(");
        final Api.FluentList<Api.Variable> parameters = method.getParameters();
        for(int i = 0; i < parameters.size(); i++) {
            final Api.Variable param = parameters.get(i);
            builder.append(param.getType().getName());
            if(i != parameters.size() - 1) {
                builder.append(", ");
            }
            if(builder.length() > MaxDisplayNameLength) {
                break;
            }
        }
        builder.append(")");
        final String ret = builder.toString();
        return StringUtils.abbreviate(ret, "...", MaxDisplayNameLength);
    }
}
