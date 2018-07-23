package com.groupunix.drivewireui;

import java.text.Collator;
import java.util.Locale;
import java.util.regex.Pattern;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/** FIXME: This class was decompiled from a class file, as it no longer exists in the
 * source but appears to be needed.
 */
public class SortTreeListener implements SelectionListener {
    public SortTreeListener() {
    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

    public void widgetSelected(SelectionEvent e) {
        this.sortTree(e);
    }

    private void sortTree(SelectionEvent e) {
        TreeColumn column = (TreeColumn)e.widget;
        Tree tree = column.getParent();
        TreeItem[] treeItems = tree.getItems();
        TreeColumn sortColumn = tree.getSortColumn();
        TreeColumn[] columns = tree.getColumns();
        tree.setSortColumn(column);
        int numOfColumns = columns.length;
        int columnIndex = this.findColumnIndex(columns, column, numOfColumns);
        Collator collator = Collator.getInstance(Locale.getDefault());
        Boolean sort = false;
        Pattern pattern = Pattern.compile("([\\+]*|[\\-]*)\\d+");
        int i;
        String value1;
        int j;
        String value2;
        double d1;
        TreeItem[] subItems;
        double d2;
        TreeItem[] arr$;
        int len$;
        int i$;
        TreeItem si;
        TreeItem[] subSubItems;
        TreeItem subItem;
        TreeItem ssi;
        TreeItem subSubItem;
        String[] values;
        TreeItem item;
        if (column.equals(sortColumn) && tree.getSortDirection() == 128) {
            tree.setSortDirection(1024);

            for(i = 1; i < treeItems.length; ++i) {
                value1 = treeItems[i].getText(columnIndex).trim();

                for(j = 0; j < i; ++j) {
                    value2 = treeItems[j].getText(columnIndex).trim();
                    if (pattern.matcher(value1).matches() && pattern.matcher(value2).matches()) {
                        d1 = this.getDouble(value1);
                        d2 = this.getDouble(value2);
                        if (d1 > d2) {
                            sort = true;
                        }
                    } else if (collator.compare(value1, value2) > 0) {
                        sort = true;
                    }

                    if (sort) {
                        values = this.getColumnValues(treeItems[i], numOfColumns);
                        subItems = treeItems[i].getItems();
                        item = new TreeItem(tree, 0, j);
                        item.setText(values);
                        arr$ = subItems;
                        len$ = subItems.length;

                        for(i$ = 0; i$ < len$; ++i$) {
                            si = arr$[i$];
                            subSubItems = si.getItems();
                            subItem = new TreeItem(item, 0);
                            subItem.setText(this.getColumnValues(si, numOfColumns));
                            arr$ = subSubItems;
                            len$ = subSubItems.length;

                            for(i$ = 0; i$ < len$; ++i$) {
                                ssi = arr$[i$];
                                subSubItem = new TreeItem(subItem, 0);
                                subSubItem.setText(this.getColumnValues(ssi, numOfColumns));
                            }
                        }

                        treeItems[i].dispose();
                        treeItems = tree.getItems();
                        sort = false;
                        break;
                    }
                }
            }
        } else {
            tree.setSortDirection(128);

            for(i = 1; i < treeItems.length; ++i) {
                value1 = treeItems[i].getText(columnIndex).trim();

                for(j = 0; j < i; ++j) {
                    value2 = treeItems[j].getText(columnIndex).trim();
                    if (pattern.matcher(value1).matches() && pattern.matcher(value2).matches()) {
                        d1 = this.getDouble(value1);
                        d2 = this.getDouble(value2);
                        if (d1 < d2) {
                            sort = true;
                        }
                    } else if (collator.compare(value1, value2) < 0) {
                        sort = true;
                    }

                    if (sort) {
                        values = this.getColumnValues(treeItems[i], numOfColumns);
                        subItems = treeItems[i].getItems();
                        item = new TreeItem(tree, 0, j);
                        item.setText(values);
                        arr$ = subItems;
                        len$ = subItems.length;

                        for(i$ = 0; i$ < len$; ++i$) {
                            si = arr$[i$];
                            subSubItems = si.getItems();
                            subItem = new TreeItem(item, 0);
                            subItem.setText(this.getColumnValues(si, numOfColumns));
                            arr$ = subSubItems;
                            len$ = subSubItems.length;

                            for(i$ = 0; i$ < len$; ++i$) {
                                ssi = arr$[i$];
                                subSubItem = new TreeItem(subItem, 0);
                                subSubItem.setText(this.getColumnValues(ssi, numOfColumns));
                            }
                        }

                        treeItems[i].dispose();
                        treeItems = tree.getItems();
                        sort = false;
                        break;
                    }
                }
            }
        }

    }

    private int findColumnIndex(TreeColumn[] columns, TreeColumn column, int numOfColumns) {
        int index = 0;

        for(int i = 0; i < numOfColumns; ++i) {
            if (column.equals(columns[i])) {
                index = i;
                break;
            }
        }

        return index;
    }

    private double getDouble(String str) {
        double d;
        if (str.startsWith("+")) {
            d = Double.parseDouble(str.split("\\+")[1]);
        } else {
            d = Double.parseDouble(str);
        }

        return d;
    }

    private String[] getColumnValues(TreeItem treeItem, int numOfColumns) {
        String[] values = new String[numOfColumns];

        for(int i = 0; i < numOfColumns; ++i) {
            values[i] = treeItem.getText(i);
        }

        return values;
    }
}