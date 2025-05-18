package org.example.demo7;

import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<String> items;

    public ToDoList() {
        items = new ArrayList<>();
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void addItem(String item) {
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("Item cannot be empty");
        }
        items.add(item.trim());
    }

    public void removeItem(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        items.remove(index);
    }

    public void editItem(int index, String newItem) {
        if (newItem == null || newItem.trim().isEmpty()) {
            throw new IllegalArgumentException("New item cannot be empty");
        }
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        items.set(index, newItem.trim());
    }
}
