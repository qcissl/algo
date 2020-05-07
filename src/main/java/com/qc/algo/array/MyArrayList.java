package com.qc.algo.array;

import java.util.*;
import java.util.function.Consumer;

/**
 * description
 *
 * @author qinc 2020/05/06 13:46
 */
public class MyArrayList<T> implements List<T> {

    private int size;
    private T[] data;

    public MyArrayList(int size) {
        data = (T[]) new Object[size];
        this.size = 0;
    }

    public MyArrayList() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size()==0){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        Object[] objs = new Object[size];
        for(int i = 0; i < size(); i++) {
            objs[i] = data[i];
        }
        return objs;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        for(int i = 0; i < size(); i++) {
            a[i] = (T1) data[i];
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        resize(size() + 1);
        data[size] = t;
        size++;
        return true;
    }

    private void resize(int capacity) {
        if (capacity > data.length){
            int oldSize = data.length;
            int newSize = oldSize + (oldSize >> 1);
            if (newSize < capacity){
                newSize = capacity;
            }
            data = Arrays.copyOf(data,newSize);
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = -1;
        for(int i = 0; i < size(); i++) {
            if (o.equals(data[i])){
                index = i;
            }
        }
        if (index >= 0){
            remove(index);
        }
        return index >= 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o:c){
            if (!contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        resize(size() + c.size());
        Object[] objects = c.toArray();
        System.arraycopy(objects,0,data,size,objects.length);
        size += objects.length;
        return objects.length > 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndexOfAdd(index);
        resize(size() + c.size());
        Object[] objects = c.toArray();
        int t = size() - index;
        if (t > 0){
            System.arraycopy(data,index,data,index+c.size(),t);
        }
        System.arraycopy(objects,0,data,index,c.size());
        size += c.size();
        return c.size() > 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c,false);
    }

    private boolean batchRemove(Collection<?> c, boolean complement) {
        Object[] objs = c.toArray();
        int w = 0,r = 0;
        boolean b = false;
        try {
            for(;r < size(); r++) {
                if (c.contains(get(r)) == complement){
                    set(w++,get(r));
                }
            }
        }finally {
            if (r != size()){
                System.arraycopy(data,r,data,w,size()-r);
                w += size() - r;
            }
            if (w != size()){
                for(int i = w; i < size(); i++) {
                    data[i] = null;
                }
                size = w;
            }
            b = true;
        }
        return b;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c,true);
    }

    @Override
    public void clear() {
        for(int i = 0; i < size(); i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T temp = get(index);
        data[index] = element;
        return temp;
    }

    @Override
    public void add(int index, T element) {
        checkIndexOfAdd(index);
        resize(size() + 1);
        int t = size() - index;
        if (t > 0){
            System.arraycopy(data,index,data,index+1,t);
        }
        data[index] = element;
        size ++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T temp = data[index];
        int t = size() - index -1;
        if (t >0){
            System.arraycopy(data,index+1,data,index,t);
        }
        data[--size] = null;
        return temp;
    }

    private void checkIndex(int index) {
        if (index<0 || index >= size()){
            throw new IllegalArgumentException("index outbound in array");
        }
    }
    private void checkIndexOfAdd(int index) {
        if (index<0 || index > size()){
            throw new IllegalArgumentException("index outbound in array");
        }
    }

    @Override
    public int indexOf(Object o) {
        if (o == null){
            for(int i = 0; i < size(); i++) {
                if (data[i] == null){
                    return i;
                }
            }
        }else {
            for(int i = 0; i < size(); i++) {
                if (o.equals(data[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null){
            for(int i = size()-1; i >= 0; i--) {
                if (data[i] == null){
                    return i;
                }
            }
        }else {
            for(int i = size()-1; i >= 0; i--) {
                if (o.equals(data[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    private class Itr implements Iterator<T>{

        private int cursor;
        private int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        public T next() {
            checkIndex(cursor);
            int i = cursor;
            cursor++;
            lastRet = i;
            return get(lastRet);
        }

        @Override
        public void remove() {
            if (lastRet < 0){
                throw new IllegalStateException();
            }
            MyArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }
}
