package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private List<T> array;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(T[] array, Predicate<T> predicate){
        this(array);
        this.predicate = predicate;
    }

    public IterableWithPolicyImpl(T[] array) {
        this.array = List.of(array);
        this.predicate = new Predicate<>() {

            @Override
            public boolean test(T elem) {
                return true;             
            }
            
        };
    }

    @Override
    public Iterator<T> iterator() {
        return new PolicyIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }

    private class PolicyIterator implements Iterator<T>{

        private int current;

        public PolicyIterator() {
            this.current = 0;            
        }

        @Override
        public boolean hasNext() {
            while(this.current < IterableWithPolicyImpl.this.array.size()) {
                if(predicate.test(array.get(current))){
                    return true;
                }
                this.current++;
            }
            return false;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return (T) IterableWithPolicyImpl.this.array.get(this.current++);
            
        }

    }

}