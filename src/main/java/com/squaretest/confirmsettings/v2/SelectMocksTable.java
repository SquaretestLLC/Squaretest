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
import com.squaretest.template.impl.TypeImpl;
import com.squaretest.utils.MathUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SelectMocksTable extends JBTable {

    public SelectMocksTable(final List<? extends Api.Variable> dependencies, final String columnName) {
        super(new MyTableModel(dependencies, columnName));
        tableHeader.setResizingAllowed(false);
        tableHeader.setReorderingAllowed(false);
        // Determine if we should have the "Select All" checkbox checked by default.
        final List<Api.Variable> mockableDeps = dependencies.stream().filter(SelectMocksTable::canMock).collect(Collectors.toList());
        final boolean selectAllCheckedInitially;
        if(mockableDeps.isEmpty()) {
            selectAllCheckedInitially = false;
        } else {
            selectAllCheckedInitially = mockableDeps.stream().allMatch(Api.Variable::getShouldBeMocked);
        }
        TableColumnModel columnModel = getColumnModel();
        columnModel.getColumn(MyTableModel.DISPLAY_NAME_COLUMN).setCellRenderer(new MyTableRenderer(dependencies, this));
        TableColumn checkBoxColumn = columnModel.getColumn(MyTableModel.CHECKED_COLUMN);
        setupCheckboxColumn(this, MyTableModel.CHECKED_COLUMN);
        checkBoxColumn.setCellRenderer(new MyBooleanRenderer(dependencies, this));
        final BooleanHeaderRenderer cbSelectAll = new BooleanHeaderRenderer();
        cbSelectAll.setSelected(selectAllCheckedInitially);
        cbSelectAll.setForeground(tableHeader.getForeground());
        cbSelectAll.setBackground(tableHeader.getBackground());
        if(mockableDeps.isEmpty()) {
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
                        for(final Api.Variable variable : dependencies) {
                            if(canMock(variable) && variable.getShouldBeMocked() != newState) {
                                wasChanged = true;
                                variable.setShouldBeMocked(newState);
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
        setPreferredScrollableViewportSize(new Dimension(400, getRowHeight() * MathUtils.clamp(dependencies.size(), 3, JBTable.PREFERRED_SCROLLABLE_VIEWPORT_HEIGHT_IN_ROWS)));
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
    }

    protected static class MyTableModel extends AbstractTableModel {
        static final int CHECKED_COLUMN = 0;
        static final int DISPLAY_NAME_COLUMN = 1;
        private final List<? extends Api.Variable> dependencies;

        private final String columnName;

        public MyTableModel(final List<? extends Api.Variable> dependencies, final String columnName) {
            this.dependencies = dependencies;
            this.columnName = columnName;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public int getRowCount() {
            return dependencies.size();
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
            final Api.Variable memberInfo = dependencies.get(rowIndex);
            return switch(columnIndex) {
                case CHECKED_COLUMN -> memberInfo.getShouldBeMocked();
                case DISPLAY_NAME_COLUMN -> getDisplayName(memberInfo);
                default -> throw new RuntimeException("Incorrect column index");
            };
        }

        @Override
        public String getColumnName(int column) {
            return switch(column) {
                case CHECKED_COLUMN -> "";
                case DISPLAY_NAME_COLUMN -> columnName;
                default -> throw new RuntimeException("Incorrect column index");
            };
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex == CHECKED_COLUMN) {
                if(rowIndex < 0) {
                    // The header column is a checkbox that is selectable. It is the "Select all" checkbox.
                    return true;
                }
                return canMock(dependencies.get(rowIndex));
            }
            return false;
        }

        @Override
        public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
            if(columnIndex == CHECKED_COLUMN) {
                dependencies.get(rowIndex).setShouldBeMocked((Boolean) aValue);
            }
        }
    }

    private static class MyTableRenderer extends ColoredTableCellRenderer {

        private final List<? extends Api.Variable> dependencies;
        private final JTable myTable;

        private MyTableRenderer(final List<? extends Api.Variable> dependencies, final JTable myTable) {
            this.dependencies = dependencies;
            this.myTable = myTable;
        }

        @Override
        public void customizeCellRenderer(
                @NotNull final JTable table, final Object value, boolean isSelected, boolean hasFocus, final int row,
                final int column) {

            final int modelColumn = myTable.convertColumnIndexToModel(column);
            final Api.Variable variable = dependencies.get(row);
            if(modelColumn == MyTableModel.DISPLAY_NAME_COLUMN) {
                Icon iconToUse;
                if(variable instanceof Api.ClassMember) {
                    iconToUse = AllIcons.Nodes.Field;
                } else {
                    iconToUse = AllIcons.Nodes.Parameter;
                }
                setIcon(iconToUse);
            } else {
                setIcon(null);
            }
            setIconOpaque(false);
            setOpaque(false);
            final boolean cellEditable = canMock(variable);
            setEnabled(cellEditable);

            if(value == null) {
                return;
            }
            append((String) value, new SimpleTextAttributes(SimpleTextAttributes.STYLE_PLAIN, null));
        }
    }

    private static class MyBooleanRenderer extends BooleanTableCellRenderer {

        private final List<? extends Api.Variable> dependencies;
        private final JTable myTable;

        private MyBooleanRenderer(final List<? extends Api.Variable> dependencies, final JTable table) {
            super();
            this.dependencies = dependencies;
            this.myTable = table;
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(component instanceof JCheckBox) {
                int modelColumn = myTable.convertColumnIndexToModel(column);
                Api.Variable variable = dependencies.get(row);
                component.setEnabled(modelColumn == MyTableModel.CHECKED_COLUMN && canMock(variable));
            }
            return component;
        }
    }

    private static String getDisplayName(final Api.Variable variable) {
        final String declaredName = variable.getDeclaredName();
        if(variable instanceof Api.ClassMember) {
            return declaredName;
        }
        final Api.Method containingMethod = variable.getContainingMethod();
        if(containingMethod == null) {
            return declaredName;
        }
        if(!containingMethod.isSetter()) {
            return declaredName;
        }
        final Api.ClassMember targetField = containingMethod.getTargetField();
        if(targetField == null) {
            return declaredName;
        }
        return targetField.getDeclaredName();
    }

    public static boolean canMock(final Api.Variable variable) {
        final TypeImpl type = (TypeImpl) variable.getType();
        if(type.isMockable()) {
            // If the type is mockable under the old Mockito rules, return true.
            return true;
        }
        if(type.isArray()) {
            // Do not allow mocking of arrays.
            return false;
        }
        if(type.isRecognized() && !type.isMockable()) {
            // If we recognize the type, and it's not mockable return false.
            // This covers primitives, boxed types, java.lang.Class, etc.
            // This also covers types that really should not be mocked like java.time.Instant, java.time.LocalDate, etc.
            return false;
        }

        // The class is considered to be unmockable, because it is static or final. The user may want to mock it
        // using the new, inline mock maker. We should allow them to do that.
        return true;
    }

    public static void setupCheckboxColumn(final JTable table, final int columnIndex) {
        TableColumnModel cModel = table.getColumnModel();
        setupCheckboxColumn(cModel.getColumn(columnIndex), cModel.getColumnMargin());
    }

    private static void setupCheckboxColumn(final TableColumn column, final int additionalWidth) {
        // FIXME: This is a hack. This is needed to top the table header form expanding when we select it.
        // Possible better solution: https://stackoverflow.com/questions/17473041/i-dont-want-jtable-to-truncate-text-with-ellipses.
        int checkboxWidth = new JCheckBox().getPreferredSize().width + additionalWidth + 10;
        column.setResizable(false);
        column.setPreferredWidth(checkboxWidth);
        column.setMaxWidth(checkboxWidth);
        column.setMinWidth(checkboxWidth);
    }
}
