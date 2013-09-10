package korgeek.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import korgeek.util.ext.LimitSizeList;

import org.apache.commons.collections.list.FixedSizeList;

public class ListUtil {
	public final static <T> List<T> create(){
		return new ArrayList<T>() ;
	}
	
	public final static <T> List<T> create(int size){
		return new ArrayList<T>(size) ;
	}

	public final static <T> List<T> toSynchronized(List<T> list){
		return Collections.synchronizedList(list) ;
	}
	
	public final static FixedSizeList toFixedSizeList(List list){
		return (FixedSizeList)FixedSizeList.decorate(list);
	}
	
	public final static LimitSizeList<?> createLimitSize(int size){
		return (LimitSizeList<?>)LimitSizeList.create(size);
	}
	
	public final static <T> List<T> toList(T... objs){
		return Arrays.asList(objs) ;
	}
}
