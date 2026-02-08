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
package com.squaretest.confirmsettings.v1;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.BooleanTableCellRenderer;
import com.intellij.ui.ColoredTableCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.table.JBTable;
import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ConfirmMocksDialog extends DialogWrapper {

    private JPanel rootPanel;
    private JTable dependenciesTable;
    private JScrollPane dependenciesScrollPane;

    private final List<? extends Api.Variable> dependencies;

    private List<VariableWrapper> wrappedDependencies;
    private VariableUpdater variableUpdater;

    protected ConfirmMocksDialog(
            @Nullable final Project project, final List<? extends Api.Variable> dependencies,
            final VariableUpdater variableUpdater) {
        super(project);
        this.dependencies = dependencies;
        this.variableUpdater = variableUpdater;
        // createUiComponents() is called here by the IntelliJ-injected bytecode.
        init();
        setTitle("Select Dependencies to Mock");
    }

    private List<VariableWrapper> wrapDependencies(final List<? extends Api.Variable> dependencies) {
        final ArrayList<VariableWrapper> ret = new ArrayList<>(dependencies.size());
        for(final Api.Variable dep : dependencies) {
            ret.add(new VariableWrapper(dep));
        }
        return ret;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return rootPanel;
    }

    @Override
    protected void doOKAction() {
        variableUpdater.updateVariables(this.wrappedDependencies);
        super.doOKAction();
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        // Override this to remove the Cancel button.
        return new Action[]{getOKAction()};
    }

    private void createUIComponents() {
        this.wrappedDependencies = wrapDependencies(dependencies);
        final MyTableModel defaultTableModel = new MyTableModel(wrappedDependencies);
        dependenciesTable = new JBTable(defaultTableModel);
        TableColumnModel columnModel = dependenciesTable.getColumnModel();
        columnModel.getColumn(MyTableModel.DISPLAY_NAME_COLUMN).setCellRenderer(new MyTableRenderer(wrappedDependencies, dependenciesTable));
        TableColumn checkBoxColumn = columnModel.getColumn(MyTableModel.CHECKED_COLUMN);
        setupCheckboxColumn(checkBoxColumn);
        checkBoxColumn.setCellRenderer(new MyBooleanRenderer(wrappedDependencies, dependenciesTable));
        dependenciesTable.setPreferredScrollableViewportSize(new Dimension(400, dependenciesTable.getRowHeight() * JBTable.PREFERRED_SCROLLABLE_VIEWPORT_HEIGHT_IN_ROWS));
        dependenciesTable.setShowGrid(false);
        dependenciesTable.setIntercellSpacing(new Dimension(0, 0));
    }

    protected static class MyTableModel extends AbstractTableModel {
        static final int CHECKED_COLUMN = 0;
        static final int DISPLAY_NAME_COLUMN = 1;
        static final String DISPLAY_NAME_COLUMN_HEADER = "Dependency";
        private final List<VariableWrapper> dependencies;

        public MyTableModel(final List<VariableWrapper> dependencies) {
            this.dependencies = dependencies;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public int getRowCount() {
            return this.dependencies.size();
        }

        @Override
        public Class getColumnClass(int columnIndex) {
            if(columnIndex == CHECKED_COLUMN) {
                return Boolean.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            final VariableWrapper memberInfo = dependencies.get(rowIndex);
            return switch(columnIndex) {
                case CHECKED_COLUMN -> memberInfo.shouldMock();
                case DISPLAY_NAME_COLUMN -> memberInfo.getDisplayName();
                default -> throw new RuntimeException("Incorrect column index");
            };
        }

        @Override
        public String getColumnName(int column) {
            return switch(column) {
                case CHECKED_COLUMN -> " ";
                case DISPLAY_NAME_COLUMN -> DISPLAY_NAME_COLUMN_HEADER;
                default -> throw new RuntimeException("Incorrect column index");
            };
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex == CHECKED_COLUMN) {
                return dependencies.get(rowIndex).canMock();
            }
            return false;
        }


        @Override
        public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
            if(columnIndex == CHECKED_COLUMN) {
                dependencies.get(rowIndex).setShouldMock((Boolean) aValue);
            }

        }

    }

    private static class MyTableRenderer extends ColoredTableCellRenderer {

        private final List<VariableWrapper> dependencies;
        private final JTable myTable;

        private MyTableRenderer(final List<VariableWrapper> dependencies, final JTable myTable) {
            this.dependencies = dependencies;
            this.myTable = myTable;
        }

        @Override
        public void customizeCellRenderer(
                @NotNull final JTable table, final Object value, boolean isSelected, boolean hasFocus, final int row,
                final int column) {

            final int modelColumn = myTable.convertColumnIndexToModel(column);
            final VariableWrapper memberInfo = dependencies.get(row);
            if(modelColumn == MyTableModel.DISPLAY_NAME_COLUMN) {
                Icon iconToUse;
                if(memberInfo.isField()) {
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
            final boolean cellEditable = memberInfo.canMock();
            setEnabled(cellEditable);

            if(value == null) {
                return;
            }
            append((String) value, new SimpleTextAttributes(SimpleTextAttributes.STYLE_PLAIN, null));
        }
    }

    private static class MyBooleanRenderer extends BooleanTableCellRenderer {

        private final List<VariableWrapper> dependencies;
        private final JTable myTable;

        private MyBooleanRenderer(final List<VariableWrapper> dependencies, final JTable table) {
            this.dependencies = dependencies;
            this.myTable = table;
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(component instanceof JCheckBox) {
                int modelColumn = myTable.convertColumnIndexToModel(column);
                VariableWrapper memberInfo = dependencies.get(row);
                component.setEnabled(modelColumn == MyTableModel.CHECKED_COLUMN && memberInfo.canMock());
            }
            return component;
        }
    }

    private void setupCheckboxColumn(final TableColumn column) {
        int checkboxWidth = new JCheckBox().getPreferredSize().width;
        column.setResizable(false);
        column.setPreferredWidth(checkboxWidth);
        column.setMaxWidth(checkboxWidth);
        column.setMinWidth(checkboxWidth);
    }

}
