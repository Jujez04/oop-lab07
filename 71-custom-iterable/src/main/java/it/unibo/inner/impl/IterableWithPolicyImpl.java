package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private List<T> array;

    public IterableWithPolicyImpl(T[] array) {
        this.array = List.of(array);
    }

    @Override
    public Iterator<T> iterator() {
        return new PolicyIterator<>();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

    private class PolicyIterator<T> implements Iterator<T>{

        private int current;

        public PolicyIterator() {
            this.current = 0;            
        }

        @Override
        public boolean hasNext() {
            return this.current < IterableWithPolicyImpl.this.array.size();
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return (T) IterableWithPolicyImpl.this.array.get(this.current++);
        }

    }

}